import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IResCustomField, NewResCustomField } from '../res-custom-field.model';

export type PartialUpdateResCustomField = Partial<IResCustomField> & Pick<IResCustomField, 'id'>;

export type EntityResponseType = HttpResponse<IResCustomField>;
export type EntityArrayResponseType = HttpResponse<IResCustomField[]>;

@Injectable({ providedIn: 'root' })
export class ResCustomFieldService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/res-custom-fields');

  create(resCustomField: NewResCustomField): Observable<EntityResponseType> {
    return this.http.post<IResCustomField>(this.resourceUrl, resCustomField, { observe: 'response' });
  }

  update(resCustomField: IResCustomField): Observable<EntityResponseType> {
    return this.http.put<IResCustomField>(`${this.resourceUrl}/${this.getResCustomFieldIdentifier(resCustomField)}`, resCustomField, {
      observe: 'response',
    });
  }

  partialUpdate(resCustomField: PartialUpdateResCustomField): Observable<EntityResponseType> {
    return this.http.patch<IResCustomField>(`${this.resourceUrl}/${this.getResCustomFieldIdentifier(resCustomField)}`, resCustomField, {
      observe: 'response',
    });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IResCustomField>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IResCustomField[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getResCustomFieldIdentifier(resCustomField: Pick<IResCustomField, 'id'>): string {
    return resCustomField.id;
  }

  compareResCustomField(o1: Pick<IResCustomField, 'id'> | null, o2: Pick<IResCustomField, 'id'> | null): boolean {
    return o1 && o2 ? this.getResCustomFieldIdentifier(o1) === this.getResCustomFieldIdentifier(o2) : o1 === o2;
  }

  addResCustomFieldToCollectionIfMissing<Type extends Pick<IResCustomField, 'id'>>(
    resCustomFieldCollection: Type[],
    ...resCustomFieldsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const resCustomFields: Type[] = resCustomFieldsToCheck.filter(isPresent);
    if (resCustomFields.length > 0) {
      const resCustomFieldCollectionIdentifiers = resCustomFieldCollection.map(resCustomFieldItem =>
        this.getResCustomFieldIdentifier(resCustomFieldItem),
      );
      const resCustomFieldsToAdd = resCustomFields.filter(resCustomFieldItem => {
        const resCustomFieldIdentifier = this.getResCustomFieldIdentifier(resCustomFieldItem);
        if (resCustomFieldCollectionIdentifiers.includes(resCustomFieldIdentifier)) {
          return false;
        }
        resCustomFieldCollectionIdentifiers.push(resCustomFieldIdentifier);
        return true;
      });
      return [...resCustomFieldsToAdd, ...resCustomFieldCollection];
    }
    return resCustomFieldCollection;
  }
}
