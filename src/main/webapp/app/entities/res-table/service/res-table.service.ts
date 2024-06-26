import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IResTable, NewResTable } from '../res-table.model';

export type PartialUpdateResTable = Partial<IResTable> & Pick<IResTable, 'id'>;

export type EntityResponseType = HttpResponse<IResTable>;
export type EntityArrayResponseType = HttpResponse<IResTable[]>;

@Injectable({ providedIn: 'root' })
export class ResTableService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/res-tables');

  create(resTable: NewResTable): Observable<EntityResponseType> {
    return this.http.post<IResTable>(this.resourceUrl, resTable, { observe: 'response' });
  }

  update(resTable: IResTable): Observable<EntityResponseType> {
    return this.http.put<IResTable>(`${this.resourceUrl}/${this.getResTableIdentifier(resTable)}`, resTable, { observe: 'response' });
  }

  partialUpdate(resTable: PartialUpdateResTable): Observable<EntityResponseType> {
    return this.http.patch<IResTable>(`${this.resourceUrl}/${this.getResTableIdentifier(resTable)}`, resTable, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IResTable>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IResTable[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getResTableIdentifier(resTable: Pick<IResTable, 'id'>): string {
    return resTable.id;
  }

  compareResTable(o1: Pick<IResTable, 'id'> | null, o2: Pick<IResTable, 'id'> | null): boolean {
    return o1 && o2 ? this.getResTableIdentifier(o1) === this.getResTableIdentifier(o2) : o1 === o2;
  }

  addResTableToCollectionIfMissing<Type extends Pick<IResTable, 'id'>>(
    resTableCollection: Type[],
    ...resTablesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const resTables: Type[] = resTablesToCheck.filter(isPresent);
    if (resTables.length > 0) {
      const resTableCollectionIdentifiers = resTableCollection.map(resTableItem => this.getResTableIdentifier(resTableItem));
      const resTablesToAdd = resTables.filter(resTableItem => {
        const resTableIdentifier = this.getResTableIdentifier(resTableItem);
        if (resTableCollectionIdentifiers.includes(resTableIdentifier)) {
          return false;
        }
        resTableCollectionIdentifiers.push(resTableIdentifier);
        return true;
      });
      return [...resTablesToAdd, ...resTableCollection];
    }
    return resTableCollection;
  }
}
