import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICustomField, NewCustomField } from '../custom-field.model';

export type PartialUpdateCustomField = Partial<ICustomField> & Pick<ICustomField, 'id'>;

export type EntityResponseType = HttpResponse<ICustomField>;
export type EntityArrayResponseType = HttpResponse<ICustomField[]>;

@Injectable({ providedIn: 'root' })
export class CustomFieldService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/custom-fields');

  create(customField: NewCustomField): Observable<EntityResponseType> {
    return this.http.post<ICustomField>(this.resourceUrl, customField, { observe: 'response' });
  }

  update(customField: ICustomField): Observable<EntityResponseType> {
    return this.http.put<ICustomField>(`${this.resourceUrl}/${this.getCustomFieldIdentifier(customField)}`, customField, {
      observe: 'response',
    });
  }

  partialUpdate(customField: PartialUpdateCustomField): Observable<EntityResponseType> {
    return this.http.patch<ICustomField>(`${this.resourceUrl}/${this.getCustomFieldIdentifier(customField)}`, customField, {
      observe: 'response',
    });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<ICustomField>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICustomField[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCustomFieldIdentifier(customField: Pick<ICustomField, 'id'>): string {
    return customField.id;
  }

  compareCustomField(o1: Pick<ICustomField, 'id'> | null, o2: Pick<ICustomField, 'id'> | null): boolean {
    return o1 && o2 ? this.getCustomFieldIdentifier(o1) === this.getCustomFieldIdentifier(o2) : o1 === o2;
  }

  addCustomFieldToCollectionIfMissing<Type extends Pick<ICustomField, 'id'>>(
    customFieldCollection: Type[],
    ...customFieldsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const customFields: Type[] = customFieldsToCheck.filter(isPresent);
    if (customFields.length > 0) {
      const customFieldCollectionIdentifiers = customFieldCollection.map(customFieldItem => this.getCustomFieldIdentifier(customFieldItem));
      const customFieldsToAdd = customFields.filter(customFieldItem => {
        const customFieldIdentifier = this.getCustomFieldIdentifier(customFieldItem);
        if (customFieldCollectionIdentifiers.includes(customFieldIdentifier)) {
          return false;
        }
        customFieldCollectionIdentifiers.push(customFieldIdentifier);
        return true;
      });
      return [...customFieldsToAdd, ...customFieldCollection];
    }
    return customFieldCollection;
  }
}
