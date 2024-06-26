import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IMemberGroup } from '../member-group.model';
import { MemberGroupService } from '../service/member-group.service';

@Component({
  standalone: true,
  templateUrl: './member-group-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class MemberGroupDeleteDialogComponent {
  memberGroup?: IMemberGroup;

  protected memberGroupService = inject(MemberGroupService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.memberGroupService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
