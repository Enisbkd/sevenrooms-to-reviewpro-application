import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IMemberGroup } from '../member-group.model';
import { MemberGroupService } from '../service/member-group.service';

const memberGroupResolve = (route: ActivatedRouteSnapshot): Observable<null | IMemberGroup> => {
  const id = route.params['id'];
  if (id) {
    return inject(MemberGroupService)
      .find(id)
      .pipe(
        mergeMap((memberGroup: HttpResponse<IMemberGroup>) => {
          if (memberGroup.body) {
            return of(memberGroup.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default memberGroupResolve;
