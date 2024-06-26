import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IReservation } from 'app/entities/reservation/reservation.model';
import { ReservationService } from 'app/entities/reservation/service/reservation.service';
import { IResPosTicket } from '../res-pos-ticket.model';
import { ResPosTicketService } from '../service/res-pos-ticket.service';
import { ResPosTicketFormService, ResPosTicketFormGroup } from './res-pos-ticket-form.service';

@Component({
  standalone: true,
  selector: 'jhi-res-pos-ticket-update',
  templateUrl: './res-pos-ticket-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ResPosTicketUpdateComponent implements OnInit {
  isSaving = false;
  resPosTicket: IResPosTicket | null = null;

  reservationsSharedCollection: IReservation[] = [];

  protected resPosTicketService = inject(ResPosTicketService);
  protected resPosTicketFormService = inject(ResPosTicketFormService);
  protected reservationService = inject(ReservationService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: ResPosTicketFormGroup = this.resPosTicketFormService.createResPosTicketFormGroup();

  compareReservation = (o1: IReservation | null, o2: IReservation | null): boolean => this.reservationService.compareReservation(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ resPosTicket }) => {
      this.resPosTicket = resPosTicket;
      if (resPosTicket) {
        this.updateForm(resPosTicket);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const resPosTicket = this.resPosTicketFormService.getResPosTicket(this.editForm);
    if (resPosTicket.id !== null) {
      this.subscribeToSaveResponse(this.resPosTicketService.update(resPosTicket));
    } else {
      this.subscribeToSaveResponse(this.resPosTicketService.create(resPosTicket));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IResPosTicket>>): void {
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

  protected updateForm(resPosTicket: IResPosTicket): void {
    this.resPosTicket = resPosTicket;
    this.resPosTicketFormService.resetForm(this.editForm, resPosTicket);

    this.reservationsSharedCollection = this.reservationService.addReservationToCollectionIfMissing<IReservation>(
      this.reservationsSharedCollection,
      resPosTicket.reservation,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.reservationService
      .query()
      .pipe(map((res: HttpResponse<IReservation[]>) => res.body ?? []))
      .pipe(
        map((reservations: IReservation[]) =>
          this.reservationService.addReservationToCollectionIfMissing<IReservation>(reservations, this.resPosTicket?.reservation),
        ),
      )
      .subscribe((reservations: IReservation[]) => (this.reservationsSharedCollection = reservations));
  }
}
