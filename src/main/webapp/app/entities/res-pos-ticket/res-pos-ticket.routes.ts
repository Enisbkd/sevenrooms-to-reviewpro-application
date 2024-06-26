import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ResPosTicketComponent } from './list/res-pos-ticket.component';
import { ResPosTicketDetailComponent } from './detail/res-pos-ticket-detail.component';
import { ResPosTicketUpdateComponent } from './update/res-pos-ticket-update.component';
import ResPosTicketResolve from './route/res-pos-ticket-routing-resolve.service';

const resPosTicketRoute: Routes = [
  {
    path: '',
    component: ResPosTicketComponent,
    data: {},
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ResPosTicketDetailComponent,
    resolve: {
      resPosTicket: ResPosTicketResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ResPosTicketUpdateComponent,
    resolve: {
      resPosTicket: ResPosTicketResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ResPosTicketUpdateComponent,
    resolve: {
      resPosTicket: ResPosTicketResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default resPosTicketRoute;
