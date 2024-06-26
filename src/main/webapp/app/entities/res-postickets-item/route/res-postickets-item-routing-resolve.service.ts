import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IResPosticketsItem } from '../res-postickets-item.model';
import { ResPosticketsItemService } from '../service/res-postickets-item.service';

const resPosticketsItemResolve = (route: ActivatedRouteSnapshot): Observable<null | IResPosticketsItem> => {
  const id = route.params['id'];
  if (id) {
    return inject(ResPosticketsItemService)
      .find(id)
      .pipe(
        mergeMap((resPosticketsItem: HttpResponse<IResPosticketsItem>) => {
          if (resPosticketsItem.body) {
            return of(resPosticketsItem.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default resPosticketsItemResolve;
