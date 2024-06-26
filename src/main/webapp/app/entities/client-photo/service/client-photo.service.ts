import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IClientPhoto, NewClientPhoto } from '../client-photo.model';

export type PartialUpdateClientPhoto = Partial<IClientPhoto> & Pick<IClientPhoto, 'id'>;

export type EntityResponseType = HttpResponse<IClientPhoto>;
export type EntityArrayResponseType = HttpResponse<IClientPhoto[]>;

@Injectable({ providedIn: 'root' })
export class ClientPhotoService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/client-photos');

  create(clientPhoto: NewClientPhoto): Observable<EntityResponseType> {
    return this.http.post<IClientPhoto>(this.resourceUrl, clientPhoto, { observe: 'response' });
  }

  update(clientPhoto: IClientPhoto): Observable<EntityResponseType> {
    return this.http.put<IClientPhoto>(`${this.resourceUrl}/${this.getClientPhotoIdentifier(clientPhoto)}`, clientPhoto, {
      observe: 'response',
    });
  }

  partialUpdate(clientPhoto: PartialUpdateClientPhoto): Observable<EntityResponseType> {
    return this.http.patch<IClientPhoto>(`${this.resourceUrl}/${this.getClientPhotoIdentifier(clientPhoto)}`, clientPhoto, {
      observe: 'response',
    });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IClientPhoto>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IClientPhoto[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getClientPhotoIdentifier(clientPhoto: Pick<IClientPhoto, 'id'>): string {
    return clientPhoto.id;
  }

  compareClientPhoto(o1: Pick<IClientPhoto, 'id'> | null, o2: Pick<IClientPhoto, 'id'> | null): boolean {
    return o1 && o2 ? this.getClientPhotoIdentifier(o1) === this.getClientPhotoIdentifier(o2) : o1 === o2;
  }

  addClientPhotoToCollectionIfMissing<Type extends Pick<IClientPhoto, 'id'>>(
    clientPhotoCollection: Type[],
    ...clientPhotosToCheck: (Type | null | undefined)[]
  ): Type[] {
    const clientPhotos: Type[] = clientPhotosToCheck.filter(isPresent);
    if (clientPhotos.length > 0) {
      const clientPhotoCollectionIdentifiers = clientPhotoCollection.map(clientPhotoItem => this.getClientPhotoIdentifier(clientPhotoItem));
      const clientPhotosToAdd = clientPhotos.filter(clientPhotoItem => {
        const clientPhotoIdentifier = this.getClientPhotoIdentifier(clientPhotoItem);
        if (clientPhotoCollectionIdentifiers.includes(clientPhotoIdentifier)) {
          return false;
        }
        clientPhotoCollectionIdentifiers.push(clientPhotoIdentifier);
        return true;
      });
      return [...clientPhotosToAdd, ...clientPhotoCollection];
    }
    return clientPhotoCollection;
  }
}
