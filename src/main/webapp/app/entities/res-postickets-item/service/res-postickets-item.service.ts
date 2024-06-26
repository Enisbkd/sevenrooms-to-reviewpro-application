import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IResPosticketsItem, NewResPosticketsItem } from '../res-postickets-item.model';

export type PartialUpdateResPosticketsItem = Partial<IResPosticketsItem> & Pick<IResPosticketsItem, 'id'>;

export type EntityResponseType = HttpResponse<IResPosticketsItem>;
export type EntityArrayResponseType = HttpResponse<IResPosticketsItem[]>;

@Injectable({ providedIn: 'root' })
export class ResPosticketsItemService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/res-postickets-items');

  create(resPosticketsItem: NewResPosticketsItem): Observable<EntityResponseType> {
    return this.http.post<IResPosticketsItem>(this.resourceUrl, resPosticketsItem, { observe: 'response' });
  }

  update(resPosticketsItem: IResPosticketsItem): Observable<EntityResponseType> {
    return this.http.put<IResPosticketsItem>(
      `${this.resourceUrl}/${this.getResPosticketsItemIdentifier(resPosticketsItem)}`,
      resPosticketsItem,
      { observe: 'response' },
    );
  }

  partialUpdate(resPosticketsItem: PartialUpdateResPosticketsItem): Observable<EntityResponseType> {
    return this.http.patch<IResPosticketsItem>(
      `${this.resourceUrl}/${this.getResPosticketsItemIdentifier(resPosticketsItem)}`,
      resPosticketsItem,
      { observe: 'response' },
    );
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IResPosticketsItem>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IResPosticketsItem[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getResPosticketsItemIdentifier(resPosticketsItem: Pick<IResPosticketsItem, 'id'>): string {
    return resPosticketsItem.id;
  }

  compareResPosticketsItem(o1: Pick<IResPosticketsItem, 'id'> | null, o2: Pick<IResPosticketsItem, 'id'> | null): boolean {
    return o1 && o2 ? this.getResPosticketsItemIdentifier(o1) === this.getResPosticketsItemIdentifier(o2) : o1 === o2;
  }

  addResPosticketsItemToCollectionIfMissing<Type extends Pick<IResPosticketsItem, 'id'>>(
    resPosticketsItemCollection: Type[],
    ...resPosticketsItemsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const resPosticketsItems: Type[] = resPosticketsItemsToCheck.filter(isPresent);
    if (resPosticketsItems.length > 0) {
      const resPosticketsItemCollectionIdentifiers = resPosticketsItemCollection.map(resPosticketsItemItem =>
        this.getResPosticketsItemIdentifier(resPosticketsItemItem),
      );
      const resPosticketsItemsToAdd = resPosticketsItems.filter(resPosticketsItemItem => {
        const resPosticketsItemIdentifier = this.getResPosticketsItemIdentifier(resPosticketsItemItem);
        if (resPosticketsItemCollectionIdentifiers.includes(resPosticketsItemIdentifier)) {
          return false;
        }
        resPosticketsItemCollectionIdentifiers.push(resPosticketsItemIdentifier);
        return true;
      });
      return [...resPosticketsItemsToAdd, ...resPosticketsItemCollection];
    }
    return resPosticketsItemCollection;
  }
}
