import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IClientVenueStats, NewClientVenueStats } from '../client-venue-stats.model';

export type PartialUpdateClientVenueStats = Partial<IClientVenueStats> & Pick<IClientVenueStats, 'id'>;

export type EntityResponseType = HttpResponse<IClientVenueStats>;
export type EntityArrayResponseType = HttpResponse<IClientVenueStats[]>;

@Injectable({ providedIn: 'root' })
export class ClientVenueStatsService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/client-venue-stats');

  create(clientVenueStats: NewClientVenueStats): Observable<EntityResponseType> {
    return this.http.post<IClientVenueStats>(this.resourceUrl, clientVenueStats, { observe: 'response' });
  }

  update(clientVenueStats: IClientVenueStats): Observable<EntityResponseType> {
    return this.http.put<IClientVenueStats>(
      `${this.resourceUrl}/${this.getClientVenueStatsIdentifier(clientVenueStats)}`,
      clientVenueStats,
      { observe: 'response' },
    );
  }

  partialUpdate(clientVenueStats: PartialUpdateClientVenueStats): Observable<EntityResponseType> {
    return this.http.patch<IClientVenueStats>(
      `${this.resourceUrl}/${this.getClientVenueStatsIdentifier(clientVenueStats)}`,
      clientVenueStats,
      { observe: 'response' },
    );
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IClientVenueStats>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IClientVenueStats[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getClientVenueStatsIdentifier(clientVenueStats: Pick<IClientVenueStats, 'id'>): string {
    return clientVenueStats.id;
  }

  compareClientVenueStats(o1: Pick<IClientVenueStats, 'id'> | null, o2: Pick<IClientVenueStats, 'id'> | null): boolean {
    return o1 && o2 ? this.getClientVenueStatsIdentifier(o1) === this.getClientVenueStatsIdentifier(o2) : o1 === o2;
  }

  addClientVenueStatsToCollectionIfMissing<Type extends Pick<IClientVenueStats, 'id'>>(
    clientVenueStatsCollection: Type[],
    ...clientVenueStatsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const clientVenueStats: Type[] = clientVenueStatsToCheck.filter(isPresent);
    if (clientVenueStats.length > 0) {
      const clientVenueStatsCollectionIdentifiers = clientVenueStatsCollection.map(clientVenueStatsItem =>
        this.getClientVenueStatsIdentifier(clientVenueStatsItem),
      );
      const clientVenueStatsToAdd = clientVenueStats.filter(clientVenueStatsItem => {
        const clientVenueStatsIdentifier = this.getClientVenueStatsIdentifier(clientVenueStatsItem);
        if (clientVenueStatsCollectionIdentifiers.includes(clientVenueStatsIdentifier)) {
          return false;
        }
        clientVenueStatsCollectionIdentifiers.push(clientVenueStatsIdentifier);
        return true;
      });
      return [...clientVenueStatsToAdd, ...clientVenueStatsCollection];
    }
    return clientVenueStatsCollection;
  }
}
