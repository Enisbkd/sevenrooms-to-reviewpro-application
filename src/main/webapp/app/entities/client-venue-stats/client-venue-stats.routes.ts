import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ClientVenueStatsComponent } from './list/client-venue-stats.component';
import { ClientVenueStatsDetailComponent } from './detail/client-venue-stats-detail.component';
import { ClientVenueStatsUpdateComponent } from './update/client-venue-stats-update.component';
import ClientVenueStatsResolve from './route/client-venue-stats-routing-resolve.service';

const clientVenueStatsRoute: Routes = [
  {
    path: '',
    component: ClientVenueStatsComponent,
    data: {},
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ClientVenueStatsDetailComponent,
    resolve: {
      clientVenueStats: ClientVenueStatsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ClientVenueStatsUpdateComponent,
    resolve: {
      clientVenueStats: ClientVenueStatsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ClientVenueStatsUpdateComponent,
    resolve: {
      clientVenueStats: ClientVenueStatsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default clientVenueStatsRoute;
