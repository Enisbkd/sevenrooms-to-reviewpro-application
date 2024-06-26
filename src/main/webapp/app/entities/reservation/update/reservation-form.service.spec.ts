import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../reservation.test-samples';

import { ReservationFormService } from './reservation-form.service';

describe('Reservation Form Service', () => {
  let service: ReservationFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReservationFormService);
  });

  describe('Service methods', () => {
    describe('createReservationFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createReservationFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            resvId: expect.any(Object),
            created: expect.any(Object),
            updated: expect.any(Object),
            deleted: expect.any(Object),
            venueGroupClientId: expect.any(Object),
            venueGroupId: expect.any(Object),
            venueId: expect.any(Object),
            date: expect.any(Object),
            duration: expect.any(Object),
            checkNumbers: expect.any(Object),
            shiftCategory: expect.any(Object),
            shiftPersistentId: expect.any(Object),
            maxGuests: expect.any(Object),
            mfratioMale: expect.any(Object),
            mfratioFemale: expect.any(Object),
            status: expect.any(Object),
            statusDisplay: expect.any(Object),
            statusSimple: expect.any(Object),
            accessPersistentId: expect.any(Object),
            arrivedGuests: expect.any(Object),
            isvip: expect.any(Object),
            bookedby: expect.any(Object),
            clientReferenceCode: expect.any(Object),
            lastname: expect.any(Object),
            firstname: expect.any(Object),
            email: expect.any(Object),
            phoneNumber: expect.any(Object),
            address: expect.any(Object),
            address2: expect.any(Object),
            city: expect.any(Object),
            postalCode: expect.any(Object),
            state: expect.any(Object),
            country: expect.any(Object),
            loyaltyId: expect.any(Object),
            loyaltyRank: expect.any(Object),
            loyaltyTier: expect.any(Object),
            notes: expect.any(Object),
            arrivalTime: expect.any(Object),
            seatedTime: expect.any(Object),
            leftTime: expect.any(Object),
            clientRequests: expect.any(Object),
            comps: expect.any(Object),
            compsPriceType: expect.any(Object),
            costOption: expect.any(Object),
            policy: expect.any(Object),
            minPrice: expect.any(Object),
            prePayment: expect.any(Object),
            onsitePayment: expect.any(Object),
            totalPayment: expect.any(Object),
            paidBy: expect.any(Object),
            servedBy: expect.any(Object),
            rating: expect.any(Object),
            problems: expect.any(Object),
            autoAssignments: expect.any(Object),
            externalClientId: expect.any(Object),
            externalId: expect.any(Object),
            externalReferenceCode: expect.any(Object),
            externalUserId: expect.any(Object),
            modifyReservationLink: expect.any(Object),
            referenceCode: expect.any(Object),
            reservationSmsOptin: expect.any(Object),
            reservationType: expect.any(Object),
            sendReminderEmail: expect.any(Object),
            sendreminderSms: expect.any(Object),
            sourceClientId: expect.any(Object),
            userId: expect.any(Object),
            userName: expect.any(Object),
          }),
        );
      });

      it('passing IReservation should create a new form with FormGroup', () => {
        const formGroup = service.createReservationFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            resvId: expect.any(Object),
            created: expect.any(Object),
            updated: expect.any(Object),
            deleted: expect.any(Object),
            venueGroupClientId: expect.any(Object),
            venueGroupId: expect.any(Object),
            venueId: expect.any(Object),
            date: expect.any(Object),
            duration: expect.any(Object),
            checkNumbers: expect.any(Object),
            shiftCategory: expect.any(Object),
            shiftPersistentId: expect.any(Object),
            maxGuests: expect.any(Object),
            mfratioMale: expect.any(Object),
            mfratioFemale: expect.any(Object),
            status: expect.any(Object),
            statusDisplay: expect.any(Object),
            statusSimple: expect.any(Object),
            accessPersistentId: expect.any(Object),
            arrivedGuests: expect.any(Object),
            isvip: expect.any(Object),
            bookedby: expect.any(Object),
            clientReferenceCode: expect.any(Object),
            lastname: expect.any(Object),
            firstname: expect.any(Object),
            email: expect.any(Object),
            phoneNumber: expect.any(Object),
            address: expect.any(Object),
            address2: expect.any(Object),
            city: expect.any(Object),
            postalCode: expect.any(Object),
            state: expect.any(Object),
            country: expect.any(Object),
            loyaltyId: expect.any(Object),
            loyaltyRank: expect.any(Object),
            loyaltyTier: expect.any(Object),
            notes: expect.any(Object),
            arrivalTime: expect.any(Object),
            seatedTime: expect.any(Object),
            leftTime: expect.any(Object),
            clientRequests: expect.any(Object),
            comps: expect.any(Object),
            compsPriceType: expect.any(Object),
            costOption: expect.any(Object),
            policy: expect.any(Object),
            minPrice: expect.any(Object),
            prePayment: expect.any(Object),
            onsitePayment: expect.any(Object),
            totalPayment: expect.any(Object),
            paidBy: expect.any(Object),
            servedBy: expect.any(Object),
            rating: expect.any(Object),
            problems: expect.any(Object),
            autoAssignments: expect.any(Object),
            externalClientId: expect.any(Object),
            externalId: expect.any(Object),
            externalReferenceCode: expect.any(Object),
            externalUserId: expect.any(Object),
            modifyReservationLink: expect.any(Object),
            referenceCode: expect.any(Object),
            reservationSmsOptin: expect.any(Object),
            reservationType: expect.any(Object),
            sendReminderEmail: expect.any(Object),
            sendreminderSms: expect.any(Object),
            sourceClientId: expect.any(Object),
            userId: expect.any(Object),
            userName: expect.any(Object),
          }),
        );
      });
    });

    describe('getReservation', () => {
      it('should return NewReservation for default Reservation initial value', () => {
        const formGroup = service.createReservationFormGroup(sampleWithNewData);

        const reservation = service.getReservation(formGroup) as any;

        expect(reservation).toMatchObject(sampleWithNewData);
      });

      it('should return NewReservation for empty Reservation initial value', () => {
        const formGroup = service.createReservationFormGroup();

        const reservation = service.getReservation(formGroup) as any;

        expect(reservation).toMatchObject({});
      });

      it('should return IReservation', () => {
        const formGroup = service.createReservationFormGroup(sampleWithRequiredData);

        const reservation = service.getReservation(formGroup) as any;

        expect(reservation).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IReservation should not enable id FormControl', () => {
        const formGroup = service.createReservationFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewReservation should disable id FormControl', () => {
        const formGroup = service.createReservationFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
