import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IResTable } from '../res-table.model';

@Component({
  standalone: true,
  selector: 'jhi-res-table-detail',
  templateUrl: './res-table-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class ResTableDetailComponent {
  resTable = input<IResTable | null>(null);

  previousState(): void {
    window.history.back();
  }
}
