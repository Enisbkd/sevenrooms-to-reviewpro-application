import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IClientVenueStats } from '../client-venue-stats.model';
import { ClientVenueStatsService } from '../service/client-venue-stats.service';

@Component({
  standalone: true,
  templateUrl: './client-venue-stats-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class ClientVenueStatsDeleteDialogComponent {
  clientVenueStats?: IClientVenueStats;

  protected clientVenueStatsService = inject(ClientVenueStatsService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.clientVenueStatsService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
