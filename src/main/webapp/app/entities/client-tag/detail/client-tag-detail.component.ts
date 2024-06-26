import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IClientTag } from '../client-tag.model';

@Component({
  standalone: true,
  selector: 'jhi-client-tag-detail',
  templateUrl: './client-tag-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class ClientTagDetailComponent {
  clientTag = input<IClientTag | null>(null);

  previousState(): void {
    window.history.back();
  }
}
