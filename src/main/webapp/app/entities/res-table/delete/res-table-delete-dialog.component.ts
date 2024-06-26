import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IResTable } from '../res-table.model';
import { ResTableService } from '../service/res-table.service';

@Component({
  standalone: true,
  templateUrl: './res-table-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class ResTableDeleteDialogComponent {
  resTable?: IResTable;

  protected resTableService = inject(ResTableService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.resTableService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
