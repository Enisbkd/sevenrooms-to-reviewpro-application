import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IReservation } from 'app/entities/reservation/reservation.model';
import { ReservationService } from 'app/entities/reservation/service/reservation.service';
import { IResCustomField } from '../res-custom-field.model';
import { ResCustomFieldService } from '../service/res-custom-field.service';
import { ResCustomFieldFormService, ResCustomFieldFormGroup } from './res-custom-field-form.service';

@Component({
  standalone: true,
  selector: 'jhi-res-custom-field-update',
  templateUrl: './res-custom-field-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ResCustomFieldUpdateComponent implements OnInit {
  isSaving = false;
  resCustomField: IResCustomField | null = null;

  reservationsSharedCollection: IReservation[] = [];

  protected resCustomFieldService = inject(ResCustomFieldService);
  protected resCustomFieldFormService = inject(ResCustomFieldFormService);
  protected reservationService = inject(ReservationService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: ResCustomFieldFormGroup = this.resCustomFieldFormService.createResCustomFieldFormGroup();

  compareReservation = (o1: IReservation | null, o2: IReservation | null): boolean => this.reservationService.compareReservation(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ resCustomField }) => {
      this.resCustomField = resCustomField;
      if (resCustomField) {
        this.updateForm(resCustomField);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const resCustomField = this.resCustomFieldFormService.getResCustomField(this.editForm);
    if (resCustomField.id !== null) {
      this.subscribeToSaveResponse(this.resCustomFieldService.update(resCustomField));
    } else {
      this.subscribeToSaveResponse(this.resCustomFieldService.create(resCustomField));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IResCustomField>>): void {
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

  protected updateForm(resCustomField: IResCustomField): void {
    this.resCustomField = resCustomField;
    this.resCustomFieldFormService.resetForm(this.editForm, resCustomField);

    this.reservationsSharedCollection = this.reservationService.addReservationToCollectionIfMissing<IReservation>(
      this.reservationsSharedCollection,
      resCustomField.reservation,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.reservationService
      .query()
      .pipe(map((res: HttpResponse<IReservation[]>) => res.body ?? []))
      .pipe(
        map((reservations: IReservation[]) =>
          this.reservationService.addReservationToCollectionIfMissing<IReservation>(reservations, this.resCustomField?.reservation),
        ),
      )
      .subscribe((reservations: IReservation[]) => (this.reservationsSharedCollection = reservations));
  }
}
