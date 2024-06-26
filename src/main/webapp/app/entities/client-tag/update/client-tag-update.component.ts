import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IClient } from 'app/entities/client/client.model';
import { ClientService } from 'app/entities/client/service/client.service';
import { IClientTag } from '../client-tag.model';
import { ClientTagService } from '../service/client-tag.service';
import { ClientTagFormService, ClientTagFormGroup } from './client-tag-form.service';

@Component({
  standalone: true,
  selector: 'jhi-client-tag-update',
  templateUrl: './client-tag-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ClientTagUpdateComponent implements OnInit {
  isSaving = false;
  clientTag: IClientTag | null = null;

  clientsSharedCollection: IClient[] = [];

  protected clientTagService = inject(ClientTagService);
  protected clientTagFormService = inject(ClientTagFormService);
  protected clientService = inject(ClientService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: ClientTagFormGroup = this.clientTagFormService.createClientTagFormGroup();

  compareClient = (o1: IClient | null, o2: IClient | null): boolean => this.clientService.compareClient(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ clientTag }) => {
      this.clientTag = clientTag;
      if (clientTag) {
        this.updateForm(clientTag);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const clientTag = this.clientTagFormService.getClientTag(this.editForm);
    if (clientTag.id !== null) {
      this.subscribeToSaveResponse(this.clientTagService.update(clientTag));
    } else {
      this.subscribeToSaveResponse(this.clientTagService.create(clientTag));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IClientTag>>): void {
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

  protected updateForm(clientTag: IClientTag): void {
    this.clientTag = clientTag;
    this.clientTagFormService.resetForm(this.editForm, clientTag);

    this.clientsSharedCollection = this.clientService.addClientToCollectionIfMissing<IClient>(
      this.clientsSharedCollection,
      clientTag.client,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.clientService
      .query()
      .pipe(map((res: HttpResponse<IClient[]>) => res.body ?? []))
      .pipe(map((clients: IClient[]) => this.clientService.addClientToCollectionIfMissing<IClient>(clients, this.clientTag?.client)))
      .subscribe((clients: IClient[]) => (this.clientsSharedCollection = clients));
  }
}
