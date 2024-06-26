import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IResTag } from '../res-tag.model';
import { ResTagService } from '../service/res-tag.service';

const resTagResolve = (route: ActivatedRouteSnapshot): Observable<null | IResTag> => {
  const id = route.params['id'];
  if (id) {
    return inject(ResTagService)
      .find(id)
      .pipe(
        mergeMap((resTag: HttpResponse<IResTag>) => {
          if (resTag.body) {
            return of(resTag.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default resTagResolve;
