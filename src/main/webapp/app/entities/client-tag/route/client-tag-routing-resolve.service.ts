import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IClientTag } from '../client-tag.model';
import { ClientTagService } from '../service/client-tag.service';

const clientTagResolve = (route: ActivatedRouteSnapshot): Observable<null | IClientTag> => {
  const id = route.params['id'];
  if (id) {
    return inject(ClientTagService)
      .find(id)
      .pipe(
        mergeMap((clientTag: HttpResponse<IClientTag>) => {
          if (clientTag.body) {
            return of(clientTag.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default clientTagResolve;
