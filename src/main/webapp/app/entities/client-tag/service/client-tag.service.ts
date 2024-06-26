import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IClientTag, NewClientTag } from '../client-tag.model';

export type PartialUpdateClientTag = Partial<IClientTag> & Pick<IClientTag, 'id'>;

export type EntityResponseType = HttpResponse<IClientTag>;
export type EntityArrayResponseType = HttpResponse<IClientTag[]>;

@Injectable({ providedIn: 'root' })
export class ClientTagService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/client-tags');

  create(clientTag: NewClientTag): Observable<EntityResponseType> {
    return this.http.post<IClientTag>(this.resourceUrl, clientTag, { observe: 'response' });
  }

  update(clientTag: IClientTag): Observable<EntityResponseType> {
    return this.http.put<IClientTag>(`${this.resourceUrl}/${this.getClientTagIdentifier(clientTag)}`, clientTag, { observe: 'response' });
  }

  partialUpdate(clientTag: PartialUpdateClientTag): Observable<EntityResponseType> {
    return this.http.patch<IClientTag>(`${this.resourceUrl}/${this.getClientTagIdentifier(clientTag)}`, clientTag, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IClientTag>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IClientTag[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getClientTagIdentifier(clientTag: Pick<IClientTag, 'id'>): string {
    return clientTag.id;
  }

  compareClientTag(o1: Pick<IClientTag, 'id'> | null, o2: Pick<IClientTag, 'id'> | null): boolean {
    return o1 && o2 ? this.getClientTagIdentifier(o1) === this.getClientTagIdentifier(o2) : o1 === o2;
  }

  addClientTagToCollectionIfMissing<Type extends Pick<IClientTag, 'id'>>(
    clientTagCollection: Type[],
    ...clientTagsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const clientTags: Type[] = clientTagsToCheck.filter(isPresent);
    if (clientTags.length > 0) {
      const clientTagCollectionIdentifiers = clientTagCollection.map(clientTagItem => this.getClientTagIdentifier(clientTagItem));
      const clientTagsToAdd = clientTags.filter(clientTagItem => {
        const clientTagIdentifier = this.getClientTagIdentifier(clientTagItem);
        if (clientTagCollectionIdentifiers.includes(clientTagIdentifier)) {
          return false;
        }
        clientTagCollectionIdentifiers.push(clientTagIdentifier);
        return true;
      });
      return [...clientTagsToAdd, ...clientTagCollection];
    }
    return clientTagCollection;
  }
}
