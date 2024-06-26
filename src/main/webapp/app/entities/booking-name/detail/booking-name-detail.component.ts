import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IBookingName } from '../booking-name.model';

@Component({
  standalone: true,
  selector: 'jhi-booking-name-detail',
  templateUrl: './booking-name-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class BookingNameDetailComponent {
  bookingName = input<IBookingName | null>(null);

  previousState(): void {
    window.history.back();
  }
}
