import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IBookingName } from '../booking-name.model';
import { BookingNameService } from '../service/booking-name.service';

const bookingNameResolve = (route: ActivatedRouteSnapshot): Observable<null | IBookingName> => {
  const id = route.params['id'];
  if (id) {
    return inject(BookingNameService)
      .find(id)
      .pipe(
        mergeMap((bookingName: HttpResponse<IBookingName>) => {
          if (bookingName.body) {
            return of(bookingName.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default bookingNameResolve;
