import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IResTag } from '../res-tag.model';

@Component({
  standalone: true,
  selector: 'jhi-res-tag-detail',
  templateUrl: './res-tag-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class ResTagDetailComponent {
  resTag = input<IResTag | null>(null);

  previousState(): void {
    window.history.back();
  }
}
