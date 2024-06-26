import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IClientPhoto } from '../client-photo.model';
import { ClientPhotoService } from '../service/client-photo.service';

const clientPhotoResolve = (route: ActivatedRouteSnapshot): Observable<null | IClientPhoto> => {
  const id = route.params['id'];
  if (id) {
    return inject(ClientPhotoService)
      .find(id)
      .pipe(
        mergeMap((clientPhoto: HttpResponse<IClientPhoto>) => {
          if (clientPhoto.body) {
            return of(clientPhoto.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default clientPhotoResolve;
