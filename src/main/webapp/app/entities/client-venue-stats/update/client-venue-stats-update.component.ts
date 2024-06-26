import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IClientVenueStats } from '../client-venue-stats.model';
import { ClientVenueStatsService } from '../service/client-venue-stats.service';
import { ClientVenueStatsFormService, ClientVenueStatsFormGroup } from './client-venue-stats-form.service';

@Component({
  standalone: true,
  selector: 'jhi-client-venue-stats-update',
  templateUrl: './client-venue-stats-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ClientVenueStatsUpdateComponent implements OnInit {
  isSaving = false;
  clientVenueStats: IClientVenueStats | null = null;

  protected clientVenueStatsService = inject(ClientVenueStatsService);
  protected clientVenueStatsFormService = inject(ClientVenueStatsFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: ClientVenueStatsFormGroup = this.clientVenueStatsFormService.createClientVenueStatsFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ clientVenueStats }) => {
      this.clientVenueStats = clientVenueStats;
      if (clientVenueStats) {
        this.updateForm(clientVenueStats);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const clientVenueStats = this.clientVenueStatsFormService.getClientVenueStats(this.editForm);
    if (clientVenueStats.id !== null) {
      this.subscribeToSaveResponse(this.clientVenueStatsService.update(clientVenueStats));
    } else {
      this.subscribeToSaveResponse(this.clientVenueStatsService.create(clientVenueStats));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IClientVenueStats>>): void {
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

  protected updateForm(clientVenueStats: IClientVenueStats): void {
    this.clientVenueStats = clientVenueStats;
    this.clientVenueStatsFormService.resetForm(this.editForm, clientVenueStats);
  }
}
