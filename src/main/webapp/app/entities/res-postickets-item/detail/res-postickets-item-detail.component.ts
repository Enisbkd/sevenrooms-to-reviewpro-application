import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IResPosticketsItem } from '../res-postickets-item.model';

@Component({
  standalone: true,
  selector: 'jhi-res-postickets-item-detail',
  templateUrl: './res-postickets-item-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class ResPosticketsItemDetailComponent {
  resPosticketsItem = input<IResPosticketsItem | null>(null);

  previousState(): void {
    window.history.back();
  }
}
