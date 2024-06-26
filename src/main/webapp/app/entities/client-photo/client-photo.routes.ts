import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ClientPhotoComponent } from './list/client-photo.component';
import { ClientPhotoDetailComponent } from './detail/client-photo-detail.component';
import { ClientPhotoUpdateComponent } from './update/client-photo-update.component';
import ClientPhotoResolve from './route/client-photo-routing-resolve.service';

const clientPhotoRoute: Routes = [
  {
    path: '',
    component: ClientPhotoComponent,
    data: {},
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ClientPhotoDetailComponent,
    resolve: {
      clientPhoto: ClientPhotoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ClientPhotoUpdateComponent,
    resolve: {
      clientPhoto: ClientPhotoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ClientPhotoUpdateComponent,
    resolve: {
      clientPhoto: ClientPhotoResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default clientPhotoRoute;
