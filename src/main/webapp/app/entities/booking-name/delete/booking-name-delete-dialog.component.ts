import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IBookingName } from '../booking-name.model';
import { BookingNameService } from '../service/booking-name.service';

@Component({
  standalone: true,
  templateUrl: './booking-name-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class BookingNameDeleteDialogComponent {
  bookingName?: IBookingName;

  protected bookingNameService = inject(BookingNameService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.bookingNameService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
