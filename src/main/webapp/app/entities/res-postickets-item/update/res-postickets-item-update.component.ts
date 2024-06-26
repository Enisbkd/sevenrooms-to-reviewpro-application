import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IResPosTicket } from 'app/entities/res-pos-ticket/res-pos-ticket.model';
import { ResPosTicketService } from 'app/entities/res-pos-ticket/service/res-pos-ticket.service';
import { IResPosticketsItem } from '../res-postickets-item.model';
import { ResPosticketsItemService } from '../service/res-postickets-item.service';
import { ResPosticketsItemFormService, ResPosticketsItemFormGroup } from './res-postickets-item-form.service';

@Component({
  standalone: true,
  selector: 'jhi-res-postickets-item-update',
  templateUrl: './res-postickets-item-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ResPosticketsItemUpdateComponent implements OnInit {
  isSaving = false;
  resPosticketsItem: IResPosticketsItem | null = null;

  resPosTicketsSharedCollection: IResPosTicket[] = [];

  protected resPosticketsItemService = inject(ResPosticketsItemService);
  protected resPosticketsItemFormService = inject(ResPosticketsItemFormService);
  protected resPosTicketService = inject(ResPosTicketService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: ResPosticketsItemFormGroup = this.resPosticketsItemFormService.createResPosticketsItemFormGroup();

  compareResPosTicket = (o1: IResPosTicket | null, o2: IResPosTicket | null): boolean =>
    this.resPosTicketService.compareResPosTicket(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ resPosticketsItem }) => {
      this.resPosticketsItem = resPosticketsItem;
      if (resPosticketsItem) {
        this.updateForm(resPosticketsItem);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const resPosticketsItem = this.resPosticketsItemFormService.getResPosticketsItem(this.editForm);
    if (resPosticketsItem.id !== null) {
      this.subscribeToSaveResponse(this.resPosticketsItemService.update(resPosticketsItem));
    } else {
      this.subscribeToSaveResponse(this.resPosticketsItemService.create(resPosticketsItem));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IResPosticketsItem>>): void {
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

  protected updateForm(resPosticketsItem: IResPosticketsItem): void {
    this.resPosticketsItem = resPosticketsItem;
    this.resPosticketsItemFormService.resetForm(this.editForm, resPosticketsItem);

    this.resPosTicketsSharedCollection = this.resPosTicketService.addResPosTicketToCollectionIfMissing<IResPosTicket>(
      this.resPosTicketsSharedCollection,
      resPosticketsItem.resPosTicket,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.resPosTicketService
      .query()
      .pipe(map((res: HttpResponse<IResPosTicket[]>) => res.body ?? []))
      .pipe(
        map((resPosTickets: IResPosTicket[]) =>
          this.resPosTicketService.addResPosTicketToCollectionIfMissing<IResPosTicket>(resPosTickets, this.resPosticketsItem?.resPosTicket),
        ),
      )
      .subscribe((resPosTickets: IResPosTicket[]) => (this.resPosTicketsSharedCollection = resPosTickets));
  }
}
