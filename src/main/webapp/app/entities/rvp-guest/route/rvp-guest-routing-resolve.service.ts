import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IRvpGuest } from '../rvp-guest.model';
import { RvpGuestService } from '../service/rvp-guest.service';

const rvpGuestResolve = (route: ActivatedRouteSnapshot): Observable<null | IRvpGuest> => {
  const id = route.params['id'];
  if (id) {
    return inject(RvpGuestService)
      .find(id)
      .pipe(
        mergeMap((rvpGuest: HttpResponse<IRvpGuest>) => {
          if (rvpGuest.body) {
            return of(rvpGuest.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default rvpGuestResolve;
