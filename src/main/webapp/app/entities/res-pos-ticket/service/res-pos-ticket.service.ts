import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IResPosTicket, NewResPosTicket } from '../res-pos-ticket.model';

export type PartialUpdateResPosTicket = Partial<IResPosTicket> & Pick<IResPosTicket, 'id'>;

export type EntityResponseType = HttpResponse<IResPosTicket>;
export type EntityArrayResponseType = HttpResponse<IResPosTicket[]>;

@Injectable({ providedIn: 'root' })
export class ResPosTicketService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/res-pos-tickets');

  create(resPosTicket: NewResPosTicket): Observable<EntityResponseType> {
    return this.http.post<IResPosTicket>(this.resourceUrl, resPosTicket, { observe: 'response' });
  }

  update(resPosTicket: IResPosTicket): Observable<EntityResponseType> {
    return this.http.put<IResPosTicket>(`${this.resourceUrl}/${this.getResPosTicketIdentifier(resPosTicket)}`, resPosTicket, {
      observe: 'response',
    });
  }

  partialUpdate(resPosTicket: PartialUpdateResPosTicket): Observable<EntityResponseType> {
    return this.http.patch<IResPosTicket>(`${this.resourceUrl}/${this.getResPosTicketIdentifier(resPosTicket)}`, resPosTicket, {
      observe: 'response',
    });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IResPosTicket>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IResPosTicket[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getResPosTicketIdentifier(resPosTicket: Pick<IResPosTicket, 'id'>): string {
    return resPosTicket.id;
  }

  compareResPosTicket(o1: Pick<IResPosTicket, 'id'> | null, o2: Pick<IResPosTicket, 'id'> | null): boolean {
    return o1 && o2 ? this.getResPosTicketIdentifier(o1) === this.getResPosTicketIdentifier(o2) : o1 === o2;
  }

  addResPosTicketToCollectionIfMissing<Type extends Pick<IResPosTicket, 'id'>>(
    resPosTicketCollection: Type[],
    ...resPosTicketsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const resPosTickets: Type[] = resPosTicketsToCheck.filter(isPresent);
    if (resPosTickets.length > 0) {
      const resPosTicketCollectionIdentifiers = resPosTicketCollection.map(resPosTicketItem =>
        this.getResPosTicketIdentifier(resPosTicketItem),
      );
      const resPosTicketsToAdd = resPosTickets.filter(resPosTicketItem => {
        const resPosTicketIdentifier = this.getResPosTicketIdentifier(resPosTicketItem);
        if (resPosTicketCollectionIdentifiers.includes(resPosTicketIdentifier)) {
          return false;
        }
        resPosTicketCollectionIdentifiers.push(resPosTicketIdentifier);
        return true;
      });
      return [...resPosTicketsToAdd, ...resPosTicketCollection];
    }
    return resPosTicketCollection;
  }
}
