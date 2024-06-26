import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IResPosticketsItem } from '../res-postickets-item.model';
import { ResPosticketsItemService } from '../service/res-postickets-item.service';

@Component({
  standalone: true,
  templateUrl: './res-postickets-item-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class ResPosticketsItemDeleteDialogComponent {
  resPosticketsItem?: IResPosticketsItem;

  protected resPosticketsItemService = inject(ResPosticketsItemService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.resPosticketsItemService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
