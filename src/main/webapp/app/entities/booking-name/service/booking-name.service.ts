import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IBookingName, NewBookingName } from '../booking-name.model';

export type PartialUpdateBookingName = Partial<IBookingName> & Pick<IBookingName, 'id'>;

export type EntityResponseType = HttpResponse<IBookingName>;
export type EntityArrayResponseType = HttpResponse<IBookingName[]>;

@Injectable({ providedIn: 'root' })
export class BookingNameService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/booking-names');

  create(bookingName: NewBookingName): Observable<EntityResponseType> {
    return this.http.post<IBookingName>(this.resourceUrl, bookingName, { observe: 'response' });
  }

  update(bookingName: IBookingName): Observable<EntityResponseType> {
    return this.http.put<IBookingName>(`${this.resourceUrl}/${this.getBookingNameIdentifier(bookingName)}`, bookingName, {
      observe: 'response',
    });
  }

  partialUpdate(bookingName: PartialUpdateBookingName): Observable<EntityResponseType> {
    return this.http.patch<IBookingName>(`${this.resourceUrl}/${this.getBookingNameIdentifier(bookingName)}`, bookingName, {
      observe: 'response',
    });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IBookingName>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBookingName[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getBookingNameIdentifier(bookingName: Pick<IBookingName, 'id'>): string {
    return bookingName.id;
  }

  compareBookingName(o1: Pick<IBookingName, 'id'> | null, o2: Pick<IBookingName, 'id'> | null): boolean {
    return o1 && o2 ? this.getBookingNameIdentifier(o1) === this.getBookingNameIdentifier(o2) : o1 === o2;
  }

  addBookingNameToCollectionIfMissing<Type extends Pick<IBookingName, 'id'>>(
    bookingNameCollection: Type[],
    ...bookingNamesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const bookingNames: Type[] = bookingNamesToCheck.filter(isPresent);
    if (bookingNames.length > 0) {
      const bookingNameCollectionIdentifiers = bookingNameCollection.map(bookingNameItem => this.getBookingNameIdentifier(bookingNameItem));
      const bookingNamesToAdd = bookingNames.filter(bookingNameItem => {
        const bookingNameIdentifier = this.getBookingNameIdentifier(bookingNameItem);
        if (bookingNameCollectionIdentifiers.includes(bookingNameIdentifier)) {
          return false;
        }
        bookingNameCollectionIdentifiers.push(bookingNameIdentifier);
        return true;
      });
      return [...bookingNamesToAdd, ...bookingNameCollection];
    }
    return bookingNameCollection;
  }
}
