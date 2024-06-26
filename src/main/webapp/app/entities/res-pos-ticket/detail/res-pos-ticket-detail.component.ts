import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IResPosTicket } from '../res-pos-ticket.model';

@Component({
  standalone: true,
  selector: 'jhi-res-pos-ticket-detail',
  templateUrl: './res-pos-ticket-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class ResPosTicketDetailComponent {
  resPosTicket = input<IResPosTicket | null>(null);

  previousState(): void {
    window.history.back();
  }
}
