import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IResTag } from '../res-tag.model';
import { ResTagService } from '../service/res-tag.service';

@Component({
  standalone: true,
  templateUrl: './res-tag-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class ResTagDeleteDialogComponent {
  resTag?: IResTag;

  protected resTagService = inject(ResTagService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.resTagService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
