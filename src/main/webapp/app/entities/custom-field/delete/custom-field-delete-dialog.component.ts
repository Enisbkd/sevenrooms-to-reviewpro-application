import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ICustomField } from '../custom-field.model';
import { CustomFieldService } from '../service/custom-field.service';

@Component({
  standalone: true,
  templateUrl: './custom-field-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class CustomFieldDeleteDialogComponent {
  customField?: ICustomField;

  protected customFieldService = inject(CustomFieldService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.customFieldService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
