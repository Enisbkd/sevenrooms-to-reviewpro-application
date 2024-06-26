import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IReservation, NewReservation } from '../reservation.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IReservation for edit and NewReservationFormGroupInput for create.
 */
type ReservationFormGroupInput = IReservation | PartialWithRequiredKeyOf<NewReservation>;

type ReservationFormDefaults = Pick<NewReservation, 'id' | 'isvip' | 'reservationSmsOptin' | 'sendReminderEmail' | 'sendreminderSms'>;

type ReservationFormGroupContent = {
  id: FormControl<IReservation['id'] | NewReservation['id']>;
  resvId: FormControl<IReservation['resvId']>;
  created: FormControl<IReservation['created']>;
  updated: FormControl<IReservation['updated']>;
  deleted: FormControl<IReservation['deleted']>;
  venueGroupClientId: FormControl<IReservation['venueGroupClientId']>;
  venueGroupId: FormControl<IReservation['venueGroupId']>;
  venueId: FormControl<IReservation['venueId']>;
  date: FormControl<IReservation['date']>;
  duration: FormControl<IReservation['duration']>;
  checkNumbers: FormControl<IReservation['checkNumbers']>;
  shiftCategory: FormControl<IReservation['shiftCategory']>;
  shiftPersistentId: FormControl<IReservation['shiftPersistentId']>;
  maxGuests: FormControl<IReservation['maxGuests']>;
  mfratioMale: FormControl<IReservation['mfratioMale']>;
  mfratioFemale: FormControl<IReservation['mfratioFemale']>;
  status: FormControl<IReservation['status']>;
  statusDisplay: FormControl<IReservation['statusDisplay']>;
  statusSimple: FormControl<IReservation['statusSimple']>;
  accessPersistentId: FormControl<IReservation['accessPersistentId']>;
  arrivedGuests: FormControl<IReservation['arrivedGuests']>;
  isvip: FormControl<IReservation['isvip']>;
  bookedby: FormControl<IReservation['bookedby']>;
  clientReferenceCode: FormControl<IReservation['clientReferenceCode']>;
  lastname: FormControl<IReservation['lastname']>;
  firstname: FormControl<IReservation['firstname']>;
  email: FormControl<IReservation['email']>;
  phoneNumber: FormControl<IReservation['phoneNumber']>;
  address: FormControl<IReservation['address']>;
  address2: FormControl<IReservation['address2']>;
  city: FormControl<IReservation['city']>;
  postalCode: FormControl<IReservation['postalCode']>;
  state: FormControl<IReservation['state']>;
  country: FormControl<IReservation['country']>;
  loyaltyId: FormControl<IReservation['loyaltyId']>;
  loyaltyRank: FormControl<IReservation['loyaltyRank']>;
  loyaltyTier: FormControl<IReservation['loyaltyTier']>;
  notes: FormControl<IReservation['notes']>;
  arrivalTime: FormControl<IReservation['arrivalTime']>;
  seatedTime: FormControl<IReservation['seatedTime']>;
  leftTime: FormControl<IReservation['leftTime']>;
  clientRequests: FormControl<IReservation['clientRequests']>;
  comps: FormControl<IReservation['comps']>;
  compsPriceType: FormControl<IReservation['compsPriceType']>;
  costOption: FormControl<IReservation['costOption']>;
  policy: FormControl<IReservation['policy']>;
  minPrice: FormControl<IReservation['minPrice']>;
  prePayment: FormControl<IReservation['prePayment']>;
  onsitePayment: FormControl<IReservation['onsitePayment']>;
  totalPayment: FormControl<IReservation['totalPayment']>;
  paidBy: FormControl<IReservation['paidBy']>;
  servedBy: FormControl<IReservation['servedBy']>;
  rating: FormControl<IReservation['rating']>;
  problems: FormControl<IReservation['problems']>;
  autoAssignments: FormControl<IReservation['autoAssignments']>;
  externalClientId: FormControl<IReservation['externalClientId']>;
  externalId: FormControl<IReservation['externalId']>;
  externalReferenceCode: FormControl<IReservation['externalReferenceCode']>;
  externalUserId: FormControl<IReservation['externalUserId']>;
  modifyReservationLink: FormControl<IReservation['modifyReservationLink']>;
  referenceCode: FormControl<IReservation['referenceCode']>;
  reservationSmsOptin: FormControl<IReservation['reservationSmsOptin']>;
  reservationType: FormControl<IReservation['reservationType']>;
  sendReminderEmail: FormControl<IReservation['sendReminderEmail']>;
  sendreminderSms: FormControl<IReservation['sendreminderSms']>;
  sourceClientId: FormControl<IReservation['sourceClientId']>;
  userId: FormControl<IReservation['userId']>;
  userName: FormControl<IReservation['userName']>;
};

export type ReservationFormGroup = FormGroup<ReservationFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ReservationFormService {
  createReservationFormGroup(reservation: ReservationFormGroupInput = { id: null }): ReservationFormGroup {
    const reservationRawValue = {
      ...this.getFormDefaults(),
      ...reservation,
    };
    return new FormGroup<ReservationFormGroupContent>({
      id: new FormControl(
        { value: reservationRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      resvId: new FormControl(reservationRawValue.resvId),
      created: new FormControl(reservationRawValue.created),
      updated: new FormControl(reservationRawValue.updated),
      deleted: new FormControl(reservationRawValue.deleted),
      venueGroupClientId: new FormControl(reservationRawValue.venueGroupClientId),
      venueGroupId: new FormControl(reservationRawValue.venueGroupId),
      venueId: new FormControl(reservationRawValue.venueId),
      date: new FormControl(reservationRawValue.date),
      duration: new FormControl(reservationRawValue.duration),
      checkNumbers: new FormControl(reservationRawValue.checkNumbers),
      shiftCategory: new FormControl(reservationRawValue.shiftCategory),
      shiftPersistentId: new FormControl(reservationRawValue.shiftPersistentId),
      maxGuests: new FormControl(reservationRawValue.maxGuests),
      mfratioMale: new FormControl(reservationRawValue.mfratioMale),
      mfratioFemale: new FormControl(reservationRawValue.mfratioFemale),
      status: new FormControl(reservationRawValue.status),
      statusDisplay: new FormControl(reservationRawValue.statusDisplay),
      statusSimple: new FormControl(reservationRawValue.statusSimple),
      accessPersistentId: new FormControl(reservationRawValue.accessPersistentId),
      arrivedGuests: new FormControl(reservationRawValue.arrivedGuests),
      isvip: new FormControl(reservationRawValue.isvip),
      bookedby: new FormControl(reservationRawValue.bookedby),
      clientReferenceCode: new FormControl(reservationRawValue.clientReferenceCode),
      lastname: new FormControl(reservationRawValue.lastname),
      firstname: new FormControl(reservationRawValue.firstname),
      email: new FormControl(reservationRawValue.email),
      phoneNumber: new FormControl(reservationRawValue.phoneNumber),
      address: new FormControl(reservationRawValue.address),
      address2: new FormControl(reservationRawValue.address2),
      city: new FormControl(reservationRawValue.city),
      postalCode: new FormControl(reservationRawValue.postalCode),
      state: new FormControl(reservationRawValue.state),
      country: new FormControl(reservationRawValue.country),
      loyaltyId: new FormControl(reservationRawValue.loyaltyId),
      loyaltyRank: new FormControl(reservationRawValue.loyaltyRank),
      loyaltyTier: new FormControl(reservationRawValue.loyaltyTier),
      notes: new FormControl(reservationRawValue.notes),
      arrivalTime: new FormControl(reservationRawValue.arrivalTime),
      seatedTime: new FormControl(reservationRawValue.seatedTime),
      leftTime: new FormControl(reservationRawValue.leftTime),
      clientRequests: new FormControl(reservationRawValue.clientRequests),
      comps: new FormControl(reservationRawValue.comps),
      compsPriceType: new FormControl(reservationRawValue.compsPriceType),
      costOption: new FormControl(reservationRawValue.costOption),
      policy: new FormControl(reservationRawValue.policy),
      minPrice: new FormControl(reservationRawValue.minPrice),
      prePayment: new FormControl(reservationRawValue.prePayment),
      onsitePayment: new FormControl(reservationRawValue.onsitePayment),
      totalPayment: new FormControl(reservationRawValue.totalPayment),
      paidBy: new FormControl(reservationRawValue.paidBy),
      servedBy: new FormControl(reservationRawValue.servedBy),
      rating: new FormControl(reservationRawValue.rating),
      problems: new FormControl(reservationRawValue.problems),
      autoAssignments: new FormControl(reservationRawValue.autoAssignments),
      externalClientId: new FormControl(reservationRawValue.externalClientId),
      externalId: new FormControl(reservationRawValue.externalId),
      externalReferenceCode: new FormControl(reservationRawValue.externalReferenceCode),
      externalUserId: new FormControl(reservationRawValue.externalUserId),
      modifyReservationLink: new FormControl(reservationRawValue.modifyReservationLink),
      referenceCode: new FormControl(reservationRawValue.referenceCode),
      reservationSmsOptin: new FormControl(reservationRawValue.reservationSmsOptin),
      reservationType: new FormControl(reservationRawValue.reservationType),
      sendReminderEmail: new FormControl(reservationRawValue.sendReminderEmail),
      sendreminderSms: new FormControl(reservationRawValue.sendreminderSms),
      sourceClientId: new FormControl(reservationRawValue.sourceClientId),
      userId: new FormControl(reservationRawValue.userId),
      userName: new FormControl(reservationRawValue.userName),
    });
  }

  getReservation(form: ReservationFormGroup): IReservation | NewReservation {
    return form.getRawValue() as IReservation | NewReservation;
  }

  resetForm(form: ReservationFormGroup, reservation: ReservationFormGroupInput): void {
    const reservationRawValue = { ...this.getFormDefaults(), ...reservation };
    form.reset(
      {
        ...reservationRawValue,
        id: { value: reservationRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ReservationFormDefaults {
    return {
      id: null,
      isvip: false,
      reservationSmsOptin: false,
      sendReminderEmail: false,
      sendreminderSms: false,
    };
  }
}
