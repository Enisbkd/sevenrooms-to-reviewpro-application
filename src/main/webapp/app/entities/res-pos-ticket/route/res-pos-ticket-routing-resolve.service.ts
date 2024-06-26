import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IResPosTicket } from '../res-pos-ticket.model';
import { ResPosTicketService } from '../service/res-pos-ticket.service';

const resPosTicketResolve = (route: ActivatedRouteSnapshot): Observable<null | IResPosTicket> => {
  const id = route.params['id'];
  if (id) {
    return inject(ResPosTicketService)
      .find(id)
      .pipe(
        mergeMap((resPosTicket: HttpResponse<IResPosTicket>) => {
          if (resPosTicket.body) {
            return of(resPosTicket.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default resPosTicketResolve;
