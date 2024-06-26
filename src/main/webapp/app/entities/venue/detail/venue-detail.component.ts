import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IVenue } from '../venue.model';

@Component({
  standalone: true,
  selector: 'jhi-venue-detail',
  templateUrl: './venue-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class VenueDetailComponent {
  venue = input<IVenue | null>(null);

  previousState(): void {
    window.history.back();
  }
}
