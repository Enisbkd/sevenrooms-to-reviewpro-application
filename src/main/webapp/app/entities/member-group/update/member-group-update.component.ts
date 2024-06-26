import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IClient } from 'app/entities/client/client.model';
import { ClientService } from 'app/entities/client/service/client.service';
import { IMemberGroup } from '../member-group.model';
import { MemberGroupService } from '../service/member-group.service';
import { MemberGroupFormService, MemberGroupFormGroup } from './member-group-form.service';

@Component({
  standalone: true,
  selector: 'jhi-member-group-update',
  templateUrl: './member-group-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class MemberGroupUpdateComponent implements OnInit {
  isSaving = false;
  memberGroup: IMemberGroup | null = null;

  clientsSharedCollection: IClient[] = [];

  protected memberGroupService = inject(MemberGroupService);
  protected memberGroupFormService = inject(MemberGroupFormService);
  protected clientService = inject(ClientService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: MemberGroupFormGroup = this.memberGroupFormService.createMemberGroupFormGroup();

  compareClient = (o1: IClient | null, o2: IClient | null): boolean => this.clientService.compareClient(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ memberGroup }) => {
      this.memberGroup = memberGroup;
      if (memberGroup) {
        this.updateForm(memberGroup);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const memberGroup = this.memberGroupFormService.getMemberGroup(this.editForm);
    if (memberGroup.id !== null) {
      this.subscribeToSaveResponse(this.memberGroupService.update(memberGroup));
    } else {
      this.subscribeToSaveResponse(this.memberGroupService.create(memberGroup));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMemberGroup>>): void {
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

  protected updateForm(memberGroup: IMemberGroup): void {
    this.memberGroup = memberGroup;
    this.memberGroupFormService.resetForm(this.editForm, memberGroup);

    this.clientsSharedCollection = this.clientService.addClientToCollectionIfMissing<IClient>(
      this.clientsSharedCollection,
      memberGroup.client,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.clientService
      .query()
      .pipe(map((res: HttpResponse<IClient[]>) => res.body ?? []))
      .pipe(map((clients: IClient[]) => this.clientService.addClientToCollectionIfMissing<IClient>(clients, this.memberGroup?.client)))
      .subscribe((clients: IClient[]) => (this.clientsSharedCollection = clients));
  }
}
