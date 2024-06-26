import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { RvpGuestComponent } from './list/rvp-guest.component';
import { RvpGuestDetailComponent } from './detail/rvp-guest-detail.component';
import { RvpGuestUpdateComponent } from './update/rvp-guest-update.component';
import RvpGuestResolve from './route/rvp-guest-routing-resolve.service';

const rvpGuestRoute: Routes = [
  {
    path: '',
    component: RvpGuestComponent,
    data: {},
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RvpGuestDetailComponent,
    resolve: {
      rvpGuest: RvpGuestResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RvpGuestUpdateComponent,
    resolve: {
      rvpGuest: RvpGuestResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RvpGuestUpdateComponent,
    resolve: {
      rvpGuest: RvpGuestResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default rvpGuestRoute;
