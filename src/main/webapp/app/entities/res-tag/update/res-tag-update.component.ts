import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IReservation } from 'app/entities/reservation/reservation.model';
import { ReservationService } from 'app/entities/reservation/service/reservation.service';
import { IResTag } from '../res-tag.model';
import { ResTagService } from '../service/res-tag.service';
import { ResTagFormService, ResTagFormGroup } from './res-tag-form.service';

@Component({
  standalone: true,
  selector: 'jhi-res-tag-update',
  templateUrl: './res-tag-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ResTagUpdateComponent implements OnInit {
  isSaving = false;
  resTag: IResTag | null = null;

  reservationsSharedCollection: IReservation[] = [];

  protected resTagService = inject(ResTagService);
  protected resTagFormService = inject(ResTagFormService);
  protected reservationService = inject(ReservationService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: ResTagFormGroup = this.resTagFormService.createResTagFormGroup();

  compareReservation = (o1: IReservation | null, o2: IReservation | null): boolean => this.reservationService.compareReservation(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ resTag }) => {
      this.resTag = resTag;
      if (resTag) {
        this.updateForm(resTag);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const resTag = this.resTagFormService.getResTag(this.editForm);
    if (resTag.id !== null) {
      this.subscribeToSaveResponse(this.resTagService.update(resTag));
    } else {
      this.subscribeToSaveResponse(this.resTagService.create(resTag));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IResTag>>): void {
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

  protected updateForm(resTag: IResTag): void {
    this.resTag = resTag;
    this.resTagFormService.resetForm(this.editForm, resTag);

    this.reservationsSharedCollection = this.reservationService.addReservationToCollectionIfMissing<IReservation>(
      this.reservationsSharedCollection,
      resTag.reservation,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.reservationService
      .query()
      .pipe(map((res: HttpResponse<IReservation[]>) => res.body ?? []))
      .pipe(
        map((reservations: IReservation[]) =>
          this.reservationService.addReservationToCollectionIfMissing<IReservation>(reservations, this.resTag?.reservation),
        ),
      )
      .subscribe((reservations: IReservation[]) => (this.reservationsSharedCollection = reservations));
  }
}
