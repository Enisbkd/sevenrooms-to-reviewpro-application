import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IResCustomField } from '../res-custom-field.model';
import { ResCustomFieldService } from '../service/res-custom-field.service';

@Component({
  standalone: true,
  templateUrl: './res-custom-field-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class ResCustomFieldDeleteDialogComponent {
  resCustomField?: IResCustomField;

  protected resCustomFieldService = inject(ResCustomFieldService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.resCustomFieldService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
