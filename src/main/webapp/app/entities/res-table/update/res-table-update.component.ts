import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IReservation } from 'app/entities/reservation/reservation.model';
import { ReservationService } from 'app/entities/reservation/service/reservation.service';
import { IResTable } from '../res-table.model';
import { ResTableService } from '../service/res-table.service';
import { ResTableFormService, ResTableFormGroup } from './res-table-form.service';

@Component({
  standalone: true,
  selector: 'jhi-res-table-update',
  templateUrl: './res-table-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ResTableUpdateComponent implements OnInit {
  isSaving = false;
  resTable: IResTable | null = null;

  reservationsSharedCollection: IReservation[] = [];

  protected resTableService = inject(ResTableService);
  protected resTableFormService = inject(ResTableFormService);
  protected reservationService = inject(ReservationService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: ResTableFormGroup = this.resTableFormService.createResTableFormGroup();

  compareReservation = (o1: IReservation | null, o2: IReservation | null): boolean => this.reservationService.compareReservation(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ resTable }) => {
      this.resTable = resTable;
      if (resTable) {
        this.updateForm(resTable);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const resTable = this.resTableFormService.getResTable(this.editForm);
    if (resTable.id !== null) {
      this.subscribeToSaveResponse(this.resTableService.update(resTable));
    } else {
      this.subscribeToSaveResponse(this.resTableService.create(resTable));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IResTable>>): void {
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

  protected updateForm(resTable: IResTable): void {
    this.resTable = resTable;
    this.resTableFormService.resetForm(this.editForm, resTable);

    this.reservationsSharedCollection = this.reservationService.addReservationToCollectionIfMissing<IReservation>(
      this.reservationsSharedCollection,
      resTable.reservation,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.reservationService
      .query()
      .pipe(map((res: HttpResponse<IReservation[]>) => res.body ?? []))
      .pipe(
        map((reservations: IReservation[]) =>
          this.reservationService.addReservationToCollectionIfMissing<IReservation>(reservations, this.resTable?.reservation),
        ),
      )
      .subscribe((reservations: IReservation[]) => (this.reservationsSharedCollection = reservations));
  }
}
