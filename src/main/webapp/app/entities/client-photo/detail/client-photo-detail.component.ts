import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe } from 'app/shared/date';
import { IClientPhoto } from '../client-photo.model';

@Component({
  standalone: true,
  selector: 'jhi-client-photo-detail',
  templateUrl: './client-photo-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class ClientPhotoDetailComponent {
  clientPhoto = input<IClientPhoto | null>(null);

  previousState(): void {
    window.history.back();
  }
}
