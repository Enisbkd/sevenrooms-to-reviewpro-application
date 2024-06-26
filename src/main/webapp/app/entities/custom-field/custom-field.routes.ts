import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { CustomFieldComponent } from './list/custom-field.component';
import { CustomFieldDetailComponent } from './detail/custom-field-detail.component';
import { CustomFieldUpdateComponent } from './update/custom-field-update.component';
import CustomFieldResolve from './route/custom-field-routing-resolve.service';

const customFieldRoute: Routes = [
  {
    path: '',
    component: CustomFieldComponent,
    data: {},
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CustomFieldDetailComponent,
    resolve: {
      customField: CustomFieldResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CustomFieldUpdateComponent,
    resolve: {
      customField: CustomFieldResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CustomFieldUpdateComponent,
    resolve: {
      customField: CustomFieldResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default customFieldRoute;
