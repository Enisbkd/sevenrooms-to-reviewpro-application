import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IResPosTicket } from '../res-pos-ticket.model';
import { ResPosTicketService } from '../service/res-pos-ticket.service';

@Component({
  standalone: true,
  templateUrl: './res-pos-ticket-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class ResPosTicketDeleteDialogComponent {
  resPosTicket?: IResPosTicket;

  protected resPosTicketService = inject(ResPosTicketService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.resPosTicketService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
