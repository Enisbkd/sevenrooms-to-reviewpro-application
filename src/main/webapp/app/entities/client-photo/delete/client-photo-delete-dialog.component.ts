import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IClientPhoto } from '../client-photo.model';
import { ClientPhotoService } from '../service/client-photo.service';

@Component({
  standalone: true,
  templateUrl: './client-photo-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class ClientPhotoDeleteDialogComponent {
  clientPhoto?: IClientPhoto;

  protected clientPhotoService = inject(ClientPhotoService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.clientPhotoService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
