import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICustomField } from '../custom-field.model';
import { CustomFieldService } from '../service/custom-field.service';

const customFieldResolve = (route: ActivatedRouteSnapshot): Observable<null | ICustomField> => {
  const id = route.params['id'];
  if (id) {
    return inject(CustomFieldService)
      .find(id)
      .pipe(
        mergeMap((customField: HttpResponse<ICustomField>) => {
          if (customField.body) {
            return of(customField.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default customFieldResolve;
