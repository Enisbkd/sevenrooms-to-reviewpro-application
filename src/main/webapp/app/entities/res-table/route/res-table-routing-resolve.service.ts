import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IResTable } from '../res-table.model';
import { ResTableService } from '../service/res-table.service';

const resTableResolve = (route: ActivatedRouteSnapshot): Observable<null | IResTable> => {
  const id = route.params['id'];
  if (id) {
    return inject(ResTableService)
      .find(id)
      .pipe(
        mergeMap((resTable: HttpResponse<IResTable>) => {
          if (resTable.body) {
            return of(resTable.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default resTableResolve;
