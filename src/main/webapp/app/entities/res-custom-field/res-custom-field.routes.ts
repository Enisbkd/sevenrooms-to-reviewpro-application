import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ResCustomFieldComponent } from './list/res-custom-field.component';
import { ResCustomFieldDetailComponent } from './detail/res-custom-field-detail.component';
import { ResCustomFieldUpdateComponent } from './update/res-custom-field-update.component';
import ResCustomFieldResolve from './route/res-custom-field-routing-resolve.service';

const resCustomFieldRoute: Routes = [
  {
    path: '',
    component: ResCustomFieldComponent,
    data: {},
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ResCustomFieldDetailComponent,
    resolve: {
      resCustomField: ResCustomFieldResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ResCustomFieldUpdateComponent,
    resolve: {
      resCustomField: ResCustomFieldResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ResCustomFieldUpdateComponent,
    resolve: {
      resCustomField: ResCustomFieldResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default resCustomFieldRoute;
