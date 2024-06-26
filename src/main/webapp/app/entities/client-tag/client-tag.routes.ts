import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ClientTagComponent } from './list/client-tag.component';
import { ClientTagDetailComponent } from './detail/client-tag-detail.component';
import { ClientTagUpdateComponent } from './update/client-tag-update.component';
import ClientTagResolve from './route/client-tag-routing-resolve.service';

const clientTagRoute: Routes = [
  {
    path: '',
    component: ClientTagComponent,
    data: {},
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ClientTagDetailComponent,
    resolve: {
      clientTag: ClientTagResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ClientTagUpdateComponent,
    resolve: {
      clientTag: ClientTagResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ClientTagUpdateComponent,
    resolve: {
      clientTag: ClientTagResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default clientTagRoute;
