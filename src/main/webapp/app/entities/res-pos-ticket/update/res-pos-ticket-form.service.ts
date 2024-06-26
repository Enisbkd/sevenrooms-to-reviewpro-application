import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IResPosTicket, NewResPosTicket } from '../res-pos-ticket.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IResPosTicket for edit and NewResPosTicketFormGroupInput for create.
 */
type ResPosTicketFormGroupInput = IResPosTicket | PartialWithRequiredKeyOf<NewResPosTicket>;

type ResPosTicketFormDefaults = Pick<NewResPosTicket, 'id'>;

type ResPosTicketFormGroupContent = {
  id: FormControl<IResPosTicket['id'] | NewResPosTicket['id']>;
  status: FormControl<IResPosTicket['status']>;
  adminFee: FormControl<IResPosTicket['adminFee']>;
  code: FormControl<IResPosTicket['code']>;
  tableNo: FormControl<IResPosTicket['tableNo']>;
  tax: FormControl<IResPosTicket['tax']>;
  businessId: FormControl<IResPosTicket['businessId']>;
  ticketId: FormControl<IResPosTicket['ticketId']>;
  localPosticketId: FormControl<IResPosTicket['localPosticketId']>;
  employeeName: FormControl<IResPosTicket['employeeName']>;
  total: FormControl<IResPosTicket['total']>;
  subtotal: FormControl<IResPosTicket['subtotal']>;
  startTime: FormControl<IResPosTicket['startTime']>;
  serviceCharge: FormControl<IResPosTicket['serviceCharge']>;
  endtime: FormControl<IResPosTicket['endtime']>;
  reservation: FormControl<IResPosTicket['reservation']>;
};

export type ResPosTicketFormGroup = FormGroup<ResPosTicketFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ResPosTicketFormService {
  createResPosTicketFormGroup(resPosTicket: ResPosTicketFormGroupInput = { id: null }): ResPosTicketFormGroup {
    const resPosTicketRawValue = {
      ...this.getFormDefaults(),
      ...resPosTicket,
    };
    return new FormGroup<ResPosTicketFormGroupContent>({
      id: new FormControl(
        { value: resPosTicketRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      status: new FormControl(resPosTicketRawValue.status),
      adminFee: new FormControl(resPosTicketRawValue.adminFee),
      code: new FormControl(resPosTicketRawValue.code),
      tableNo: new FormControl(resPosTicketRawValue.tableNo),
      tax: new FormControl(resPosTicketRawValue.tax),
      businessId: new FormControl(resPosTicketRawValue.businessId),
      ticketId: new FormControl(resPosTicketRawValue.ticketId),
      localPosticketId: new FormControl(resPosTicketRawValue.localPosticketId),
      employeeName: new FormControl(resPosTicketRawValue.employeeName),
      total: new FormControl(resPosTicketRawValue.total),
      subtotal: new FormControl(resPosTicketRawValue.subtotal),
      startTime: new FormControl(resPosTicketRawValue.startTime),
      serviceCharge: new FormControl(resPosTicketRawValue.serviceCharge),
      endtime: new FormControl(resPosTicketRawValue.endtime),
      reservation: new FormControl(resPosTicketRawValue.reservation),
    });
  }

  getResPosTicket(form: ResPosTicketFormGroup): IResPosTicket | NewResPosTicket {
    return form.getRawValue() as IResPosTicket | NewResPosTicket;
  }

  resetForm(form: ResPosTicketFormGroup, resPosTicket: ResPosTicketFormGroupInput): void {
    const resPosTicketRawValue = { ...this.getFormDefaults(), ...resPosTicket };
    form.reset(
      {
        ...resPosTicketRawValue,
        id: { value: resPosTicketRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ResPosTicketFormDefaults {
    return {
      id: null,
    };
  }
}
