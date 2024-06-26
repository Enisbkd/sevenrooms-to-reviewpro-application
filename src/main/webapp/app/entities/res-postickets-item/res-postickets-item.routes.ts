import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ResPosticketsItemComponent } from './list/res-postickets-item.component';
import { ResPosticketsItemDetailComponent } from './detail/res-postickets-item-detail.component';
import { ResPosticketsItemUpdateComponent } from './update/res-postickets-item-update.component';
import ResPosticketsItemResolve from './route/res-postickets-item-routing-resolve.service';

const resPosticketsItemRoute: Routes = [
  {
    path: '',
    component: ResPosticketsItemComponent,
    data: {},
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ResPosticketsItemDetailComponent,
    resolve: {
      resPosticketsItem: ResPosticketsItemResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ResPosticketsItemUpdateComponent,
    resolve: {
      resPosticketsItem: ResPosticketsItemResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ResPosticketsItemUpdateComponent,
    resolve: {
      resPosticketsItem: ResPosticketsItemResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default resPosticketsItemRoute;
