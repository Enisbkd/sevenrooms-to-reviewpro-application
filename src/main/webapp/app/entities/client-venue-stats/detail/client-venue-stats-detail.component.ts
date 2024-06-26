import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IClientVenueStats } from '../client-venue-stats.model';

@Component({
  standalone: true,
  selector: 'jhi-client-venue-stats-detail',
  templateUrl: './client-venue-stats-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class ClientVenueStatsDetailComponent {
  clientVenueStats = input<IClientVenueStats | null>(null);

  previousState(): void {
    window.history.back();
  }
}
