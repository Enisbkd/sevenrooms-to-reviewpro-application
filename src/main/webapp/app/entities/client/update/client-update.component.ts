import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IClientPhoto } from 'app/entities/client-photo/client-photo.model';
import { ClientPhotoService } from 'app/entities/client-photo/service/client-photo.service';
import { IClientVenueStats } from 'app/entities/client-venue-stats/client-venue-stats.model';
import { ClientVenueStatsService } from 'app/entities/client-venue-stats/service/client-venue-stats.service';
import { ClientService } from '../service/client.service';
import { IClient } from '../client.model';
import { ClientFormService, ClientFormGroup } from './client-form.service';

@Component({
  standalone: true,
  selector: 'jhi-client-update',
  templateUrl: './client-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ClientUpdateComponent implements OnInit {
  isSaving = false;
  client: IClient | null = null;

  clientPhotosCollection: IClientPhoto[] = [];
  clientVenueStatsCollection: IClientVenueStats[] = [];

  protected clientService = inject(ClientService);
  protected clientFormService = inject(ClientFormService);
  protected clientPhotoService = inject(ClientPhotoService);
  protected clientVenueStatsService = inject(ClientVenueStatsService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: ClientFormGroup = this.clientFormService.createClientFormGroup();

  compareClientPhoto = (o1: IClientPhoto | null, o2: IClientPhoto | null): boolean => this.clientPhotoService.compareClientPhoto(o1, o2);

  compareClientVenueStats = (o1: IClientVenueStats | null, o2: IClientVenueStats | null): boolean =>
    this.clientVenueStatsService.compareClientVenueStats(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ client }) => {
      this.client = client;
      if (client) {
        this.updateForm(client);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const client = this.clientFormService.getClient(this.editForm);
    if (client.id !== null) {
      this.subscribeToSaveResponse(this.clientService.update(client));
    } else {
      this.subscribeToSaveResponse(this.clientService.create(client));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IClient>>): void {
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

  protected updateForm(client: IClient): void {
    this.client = client;
    this.clientFormService.resetForm(this.editForm, client);

    this.clientPhotosCollection = this.clientPhotoService.addClientPhotoToCollectionIfMissing<IClientPhoto>(
      this.clientPhotosCollection,
      client.clientPhoto,
    );
    this.clientVenueStatsCollection = this.clientVenueStatsService.addClientVenueStatsToCollectionIfMissing<IClientVenueStats>(
      this.clientVenueStatsCollection,
      client.clientVenueStats,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.clientPhotoService
      .query({ filter: 'client-is-null' })
      .pipe(map((res: HttpResponse<IClientPhoto[]>) => res.body ?? []))
      .pipe(
        map((clientPhotos: IClientPhoto[]) =>
          this.clientPhotoService.addClientPhotoToCollectionIfMissing<IClientPhoto>(clientPhotos, this.client?.clientPhoto),
        ),
      )
      .subscribe((clientPhotos: IClientPhoto[]) => (this.clientPhotosCollection = clientPhotos));

    this.clientVenueStatsService
      .query({ filter: 'client-is-null' })
      .pipe(map((res: HttpResponse<IClientVenueStats[]>) => res.body ?? []))
      .pipe(
        map((clientVenueStats: IClientVenueStats[]) =>
          this.clientVenueStatsService.addClientVenueStatsToCollectionIfMissing<IClientVenueStats>(
            clientVenueStats,
            this.client?.clientVenueStats,
          ),
        ),
      )
      .subscribe((clientVenueStats: IClientVenueStats[]) => (this.clientVenueStatsCollection = clientVenueStats));
  }
}
