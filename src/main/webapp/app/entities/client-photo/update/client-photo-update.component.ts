import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IClientPhoto } from '../client-photo.model';
import { ClientPhotoService } from '../service/client-photo.service';
import { ClientPhotoFormService, ClientPhotoFormGroup } from './client-photo-form.service';

@Component({
  standalone: true,
  selector: 'jhi-client-photo-update',
  templateUrl: './client-photo-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ClientPhotoUpdateComponent implements OnInit {
  isSaving = false;
  clientPhoto: IClientPhoto | null = null;

  protected clientPhotoService = inject(ClientPhotoService);
  protected clientPhotoFormService = inject(ClientPhotoFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: ClientPhotoFormGroup = this.clientPhotoFormService.createClientPhotoFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ clientPhoto }) => {
      this.clientPhoto = clientPhoto;
      if (clientPhoto) {
        this.updateForm(clientPhoto);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const clientPhoto = this.clientPhotoFormService.getClientPhoto(this.editForm);
    if (clientPhoto.id !== null) {
      this.subscribeToSaveResponse(this.clientPhotoService.update(clientPhoto));
    } else {
      this.subscribeToSaveResponse(this.clientPhotoService.create(clientPhoto));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IClientPhoto>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(clientPhoto: IClientPhoto): void {
    this.clientPhoto = clientPhoto;
    this.clientPhotoFormService.resetForm(this.editForm, clientPhoto);
  }
}
