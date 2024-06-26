import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IClientVenueStats } from 'app/entities/client-venue-stats/client-venue-stats.model';
import { ClientVenueStatsService } from 'app/entities/client-venue-stats/service/client-venue-stats.service';
import { IBookingName } from '../booking-name.model';
import { BookingNameService } from '../service/booking-name.service';
import { BookingNameFormService, BookingNameFormGroup } from './booking-name-form.service';

@Component({
  standalone: true,
  selector: 'jhi-booking-name-update',
  templateUrl: './booking-name-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class BookingNameUpdateComponent implements OnInit {
  isSaving = false;
  bookingName: IBookingName | null = null;

  clientVenueStatsSharedCollection: IClientVenueStats[] = [];

  protected bookingNameService = inject(BookingNameService);
  protected bookingNameFormService = inject(BookingNameFormService);
  protected clientVenueStatsService = inject(ClientVenueStatsService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: BookingNameFormGroup = this.bookingNameFormService.createBookingNameFormGroup();

  compareClientVenueStats = (o1: IClientVenueStats | null, o2: IClientVenueStats | null): boolean =>
    this.clientVenueStatsService.compareClientVenueStats(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bookingName }) => {
      this.bookingName = bookingName;
      if (bookingName) {
        this.updateForm(bookingName);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bookingName = this.bookingNameFormService.getBookingName(this.editForm);
    if (bookingName.id !== null) {
      this.subscribeToSaveResponse(this.bookingNameService.update(bookingName));
    } else {
      this.subscribeToSaveResponse(this.bookingNameService.create(bookingName));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBookingName>>): void {
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

  protected updateForm(bookingName: IBookingName): void {
    this.bookingName = bookingName;
    this.bookingNameFormService.resetForm(this.editForm, bookingName);

    this.clientVenueStatsSharedCollection = this.clientVenueStatsService.addClientVenueStatsToCollectionIfMissing<IClientVenueStats>(
      this.clientVenueStatsSharedCollection,
      bookingName.clientVenueStats,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.clientVenueStatsService
      .query()
      .pipe(map((res: HttpResponse<IClientVenueStats[]>) => res.body ?? []))
      .pipe(
        map((clientVenueStats: IClientVenueStats[]) =>
          this.clientVenueStatsService.addClientVenueStatsToCollectionIfMissing<IClientVenueStats>(
            clientVenueStats,
            this.bookingName?.clientVenueStats,
          ),
        ),
      )
      .subscribe((clientVenueStats: IClientVenueStats[]) => (this.clientVenueStatsSharedCollection = clientVenueStats));
  }
}
