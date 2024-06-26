import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ResTagComponent } from './list/res-tag.component';
import { ResTagDetailComponent } from './detail/res-tag-detail.component';
import { ResTagUpdateComponent } from './update/res-tag-update.component';
import ResTagResolve from './route/res-tag-routing-resolve.service';

const resTagRoute: Routes = [
  {
    path: '',
    component: ResTagComponent,
    data: {},
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ResTagDetailComponent,
    resolve: {
      resTag: ResTagResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ResTagUpdateComponent,
    resolve: {
      resTag: ResTagResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ResTagUpdateComponent,
    resolve: {
      resTag: ResTagResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default resTagRoute;
