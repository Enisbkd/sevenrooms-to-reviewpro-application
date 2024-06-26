import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { BookingNameComponent } from './list/booking-name.component';
import { BookingNameDetailComponent } from './detail/booking-name-detail.component';
import { BookingNameUpdateComponent } from './update/booking-name-update.component';
import BookingNameResolve from './route/booking-name-routing-resolve.service';

const bookingNameRoute: Routes = [
  {
    path: '',
    component: BookingNameComponent,
    data: {},
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BookingNameDetailComponent,
    resolve: {
      bookingName: BookingNameResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BookingNameUpdateComponent,
    resolve: {
      bookingName: BookingNameResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BookingNameUpdateComponent,
    resolve: {
      bookingName: BookingNameResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default bookingNameRoute;
