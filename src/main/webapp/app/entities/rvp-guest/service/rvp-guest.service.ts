import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IRvpGuest, NewRvpGuest } from '../rvp-guest.model';

export type PartialUpdateRvpGuest = Partial<IRvpGuest> & Pick<IRvpGuest, 'id'>;

type RestOf<T extends IRvpGuest | NewRvpGuest> = Omit<T, 'checkin' | 'checkout'> & {
  checkin?: string | null;
  checkout?: string | null;
};

export type RestRvpGuest = RestOf<IRvpGuest>;

export type NewRestRvpGuest = RestOf<NewRvpGuest>;

export type PartialUpdateRestRvpGuest = RestOf<PartialUpdateRvpGuest>;

export type EntityResponseType = HttpResponse<IRvpGuest>;
export type EntityArrayResponseType = HttpResponse<IRvpGuest[]>;

@Injectable({ providedIn: 'root' })
export class RvpGuestService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/rvp-guests');

  create(rvpGuest: NewRvpGuest): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(rvpGuest);
    return this.http
      .post<RestRvpGuest>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(rvpGuest: IRvpGuest): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(rvpGuest);
    return this.http
      .put<RestRvpGuest>(`${this.resourceUrl}/${this.getRvpGuestIdentifier(rvpGuest)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(rvpGuest: PartialUpdateRvpGuest): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(rvpGuest);
    return this.http
      .patch<RestRvpGuest>(`${this.resourceUrl}/${this.getRvpGuestIdentifier(rvpGuest)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestRvpGuest>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestRvpGuest[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getRvpGuestIdentifier(rvpGuest: Pick<IRvpGuest, 'id'>): string {
    return rvpGuest.id;
  }

  compareRvpGuest(o1: Pick<IRvpGuest, 'id'> | null, o2: Pick<IRvpGuest, 'id'> | null): boolean {
    return o1 && o2 ? this.getRvpGuestIdentifier(o1) === this.getRvpGuestIdentifier(o2) : o1 === o2;
  }

  addRvpGuestToCollectionIfMissing<Type extends Pick<IRvpGuest, 'id'>>(
    rvpGuestCollection: Type[],
    ...rvpGuestsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const rvpGuests: Type[] = rvpGuestsToCheck.filter(isPresent);
    if (rvpGuests.length > 0) {
      const rvpGuestCollectionIdentifiers = rvpGuestCollection.map(rvpGuestItem => this.getRvpGuestIdentifier(rvpGuestItem));
      const rvpGuestsToAdd = rvpGuests.filter(rvpGuestItem => {
        const rvpGuestIdentifier = this.getRvpGuestIdentifier(rvpGuestItem);
        if (rvpGuestCollectionIdentifiers.includes(rvpGuestIdentifier)) {
          return false;
        }
        rvpGuestCollectionIdentifiers.push(rvpGuestIdentifier);
        return true;
      });
      return [...rvpGuestsToAdd, ...rvpGuestCollection];
    }
    return rvpGuestCollection;
  }

  protected convertDateFromClient<T extends IRvpGuest | NewRvpGuest | PartialUpdateRvpGuest>(rvpGuest: T): RestOf<T> {
    return {
      ...rvpGuest,
      checkin: rvpGuest.checkin?.format(DATE_FORMAT) ?? null,
      checkout: rvpGuest.checkout?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restRvpGuest: RestRvpGuest): IRvpGuest {
    return {
      ...restRvpGuest,
      checkin: restRvpGuest.checkin ? dayjs(restRvpGuest.checkin) : undefined,
      checkout: restRvpGuest.checkout ? dayjs(restRvpGuest.checkout) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestRvpGuest>): HttpResponse<IRvpGuest> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestRvpGuest[]>): HttpResponse<IRvpGuest[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
