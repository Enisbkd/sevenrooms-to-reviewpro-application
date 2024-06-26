import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ResTableComponent } from './list/res-table.component';
import { ResTableDetailComponent } from './detail/res-table-detail.component';
import { ResTableUpdateComponent } from './update/res-table-update.component';
import ResTableResolve from './route/res-table-routing-resolve.service';

const resTableRoute: Routes = [
  {
    path: '',
    component: ResTableComponent,
    data: {},
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ResTableDetailComponent,
    resolve: {
      resTable: ResTableResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ResTableUpdateComponent,
    resolve: {
      resTable: ResTableResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ResTableUpdateComponent,
    resolve: {
      resTable: ResTableResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default resTableRoute;
