import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IClientVenueStats } from '../client-venue-stats.model';
import { ClientVenueStatsService } from '../service/client-venue-stats.service';

const clientVenueStatsResolve = (route: ActivatedRouteSnapshot): Observable<null | IClientVenueStats> => {
  const id = route.params['id'];
  if (id) {
    return inject(ClientVenueStatsService)
      .find(id)
      .pipe(
        mergeMap((clientVenueStats: HttpResponse<IClientVenueStats>) => {
          if (clientVenueStats.body) {
            return of(clientVenueStats.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default clientVenueStatsResolve;
