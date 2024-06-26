import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IMemberGroup } from '../member-group.model';

@Component({
  standalone: true,
  selector: 'jhi-member-group-detail',
  templateUrl: './member-group-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class MemberGroupDetailComponent {
  memberGroup = input<IMemberGroup | null>(null);

  previousState(): void {
    window.history.back();
  }
}
