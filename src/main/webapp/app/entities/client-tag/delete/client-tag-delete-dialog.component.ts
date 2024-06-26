import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IClientTag } from '../client-tag.model';
import { ClientTagService } from '../service/client-tag.service';

@Component({
  standalone: true,
  templateUrl: './client-tag-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class ClientTagDeleteDialogComponent {
  clientTag?: IClientTag;

  protected clientTagService = inject(ClientTagService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.clientTagService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
