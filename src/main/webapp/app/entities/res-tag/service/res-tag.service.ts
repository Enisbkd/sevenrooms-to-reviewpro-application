import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IResTag, NewResTag } from '../res-tag.model';

export type PartialUpdateResTag = Partial<IResTag> & Pick<IResTag, 'id'>;

export type EntityResponseType = HttpResponse<IResTag>;
export type EntityArrayResponseType = HttpResponse<IResTag[]>;

@Injectable({ providedIn: 'root' })
export class ResTagService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/res-tags');

  create(resTag: NewResTag): Observable<EntityResponseType> {
    return this.http.post<IResTag>(this.resourceUrl, resTag, { observe: 'response' });
  }

  update(resTag: IResTag): Observable<EntityResponseType> {
    return this.http.put<IResTag>(`${this.resourceUrl}/${this.getResTagIdentifier(resTag)}`, resTag, { observe: 'response' });
  }

  partialUpdate(resTag: PartialUpdateResTag): Observable<EntityResponseType> {
    return this.http.patch<IResTag>(`${this.resourceUrl}/${this.getResTagIdentifier(resTag)}`, resTag, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IResTag>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IResTag[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getResTagIdentifier(resTag: Pick<IResTag, 'id'>): string {
    return resTag.id;
  }

  compareResTag(o1: Pick<IResTag, 'id'> | null, o2: Pick<IResTag, 'id'> | null): boolean {
    return o1 && o2 ? this.getResTagIdentifier(o1) === this.getResTagIdentifier(o2) : o1 === o2;
  }

  addResTagToCollectionIfMissing<Type extends Pick<IResTag, 'id'>>(
    resTagCollection: Type[],
    ...resTagsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const resTags: Type[] = resTagsToCheck.filter(isPresent);
    if (resTags.length > 0) {
      const resTagCollectionIdentifiers = resTagCollection.map(resTagItem => this.getResTagIdentifier(resTagItem));
      const resTagsToAdd = resTags.filter(resTagItem => {
        const resTagIdentifier = this.getResTagIdentifier(resTagItem);
        if (resTagCollectionIdentifiers.includes(resTagIdentifier)) {
          return false;
        }
        resTagCollectionIdentifiers.push(resTagIdentifier);
        return true;
      });
      return [...resTagsToAdd, ...resTagCollection];
    }
    return resTagCollection;
  }
}
