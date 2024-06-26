import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IClient } from 'app/entities/client/client.model';
import { ClientService } from 'app/entities/client/service/client.service';
import { ICustomField } from '../custom-field.model';
import { CustomFieldService } from '../service/custom-field.service';
import { CustomFieldFormService, CustomFieldFormGroup } from './custom-field-form.service';

@Component({
  standalone: true,
  selector: 'jhi-custom-field-update',
  templateUrl: './custom-field-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class CustomFieldUpdateComponent implements OnInit {
  isSaving = false;
  customField: ICustomField | null = null;

  clientsSharedCollection: IClient[] = [];

  protected customFieldService = inject(CustomFieldService);
  protected customFieldFormService = inject(CustomFieldFormService);
  protected clientService = inject(ClientService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: CustomFieldFormGroup = this.customFieldFormService.createCustomFieldFormGroup();

  compareClient = (o1: IClient | null, o2: IClient | null): boolean => this.clientService.compareClient(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ customField }) => {
      this.customField = customField;
      if (customField) {
        this.updateForm(customField);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const customField = this.customFieldFormService.getCustomField(this.editForm);
    if (customField.id !== null) {
      this.subscribeToSaveResponse(this.customFieldService.update(customField));
    } else {
      this.subscribeToSaveResponse(this.customFieldService.create(customField));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICustomField>>): void {
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

  protected updateForm(customField: ICustomField): void {
    this.customField = customField;
    this.customFieldFormService.resetForm(this.editForm, customField);

    this.clientsSharedCollection = this.clientService.addClientToCollectionIfMissing<IClient>(
      this.clientsSharedCollection,
      customField.client,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.clientService
      .query()
      .pipe(map((res: HttpResponse<IClient[]>) => res.body ?? []))
      .pipe(map((clients: IClient[]) => this.clientService.addClientToCollectionIfMissing<IClient>(clients, this.customField?.client)))
      .subscribe((clients: IClient[]) => (this.clientsSharedCollection = clients));
  }
}
