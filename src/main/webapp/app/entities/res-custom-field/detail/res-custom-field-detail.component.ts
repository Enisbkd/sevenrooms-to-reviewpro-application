import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IResCustomField } from '../res-custom-field.model';

@Component({
  standalone: true,
  selector: 'jhi-res-custom-field-detail',
  templateUrl: './res-custom-field-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class ResCustomFieldDetailComponent {
  resCustomField = input<IResCustomField | null>(null);

  previousState(): void {
    window.history.back();
  }
}
