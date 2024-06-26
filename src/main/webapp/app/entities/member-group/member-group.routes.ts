import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { MemberGroupComponent } from './list/member-group.component';
import { MemberGroupDetailComponent } from './detail/member-group-detail.component';
import { MemberGroupUpdateComponent } from './update/member-group-update.component';
import MemberGroupResolve from './route/member-group-routing-resolve.service';

const memberGroupRoute: Routes = [
  {
    path: '',
    component: MemberGroupComponent,
    data: {},
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MemberGroupDetailComponent,
    resolve: {
      memberGroup: MemberGroupResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MemberGroupUpdateComponent,
    resolve: {
      memberGroup: MemberGroupResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MemberGroupUpdateComponent,
    resolve: {
      memberGroup: MemberGroupResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default memberGroupRoute;
