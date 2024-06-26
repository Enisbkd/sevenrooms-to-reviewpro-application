import { Component, inject, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IRvpGuest } from '../rvp-guest.model';
import { RvpGuestService } from '../service/rvp-guest.service';
import { RvpGuestFormService, RvpGuestFormGroup } from './rvp-guest-form.service';

@Component({
  standalone: true,
  selector: 'jhi-rvp-guest-update',
  templateUrl: './rvp-guest-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class RvpGuestUpdateComponent implements OnInit {
  isSaving = false;
  rvpGuest: IRvpGuest | null = null;

  protected rvpGuestService = inject(RvpGuestService);
  protected rvpGuestFormService = inject(RvpGuestFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: RvpGuestFormGroup = this.rvpGuestFormService.createRvpGuestFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rvpGuest }) => {
      this.rvpGuest = rvpGuest;
      if (rvpGuest) {
        this.updateForm(rvpGuest);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const rvpGuest = this.rvpGuestFormService.getRvpGuest(this.editForm);
    if (rvpGuest.id !== null) {
      this.subscribeToSaveResponse(this.rvpGuestService.update(rvpGuest));
    } else {
      this.subscribeToSaveResponse(this.rvpGuestService.create(rvpGuest));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRvpGuest>>): void {
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

  protected updateForm(rvpGuest: IRvpGuest): void {
    this.rvpGuest = rvpGuest;
    this.rvpGuestFormService.resetForm(this.editForm, rvpGuest);
  }
}
