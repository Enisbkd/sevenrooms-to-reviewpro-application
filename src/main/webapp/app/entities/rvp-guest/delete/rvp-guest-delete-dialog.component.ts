import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IRvpGuest } from '../rvp-guest.model';
import { RvpGuestService } from '../service/rvp-guest.service';

@Component({
  standalone: true,
  templateUrl: './rvp-guest-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class RvpGuestDeleteDialogComponent {
  rvpGuest?: IRvpGuest;

  protected rvpGuestService = inject(RvpGuestService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.rvpGuestService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
