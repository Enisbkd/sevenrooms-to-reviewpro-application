import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../client.test-samples';

import { ClientFormService } from './client-form.service';

describe('Client Form Service', () => {
  let service: ClientFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClientFormService);
  });

  describe('Service methods', () => {
    describe('createClientFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createClientFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            clientId: expect.any(Object),
            createdDate: expect.any(Object),
            updatedDate: expect.any(Object),
            deletedDate: expect.any(Object),
            lastname: expect.any(Object),
            firstname: expect.any(Object),
            gender: expect.any(Object),
            salutation: expect.any(Object),
            title: expect.any(Object),
            birthdayDay: expect.any(Object),
            birthdayMonth: expect.any(Object),
            birthdayAltMonth: expect.any(Object),
            anniversaryDay: expect.any(Object),
            anniversaryMonth: expect.any(Object),
            company: expect.any(Object),
            email: expect.any(Object),
            emailAlt: expect.any(Object),
            phoneNumber: expect.any(Object),
            phoneNumberlocale: expect.any(Object),
            phoneNumberalt: expect.any(Object),
            phoneNumberaltlocale: expect.any(Object),
            address: expect.any(Object),
            address2: expect.any(Object),
            city: expect.any(Object),
            postalCode: expect.any(Object),
            state: expect.any(Object),
            country: expect.any(Object),
            isContactPrivate: expect.any(Object),
            isOnetimeGuest: expect.any(Object),
            status: expect.any(Object),
            loyaltyId: expect.any(Object),
            loyaltyRank: expect.any(Object),
            loyaltyTier: expect.any(Object),
            marketingOptin: expect.any(Object),
            marketingOptints: expect.any(Object),
            marketingOptOutts: expect.any(Object),
            hasBillingProfile: expect.any(Object),
            notes: expect.any(Object),
            privateNotes: expect.any(Object),
            tags: expect.any(Object),
            totalVisits: expect.any(Object),
            totalCovers: expect.any(Object),
            totalCancellations: expect.any(Object),
            totalNoShows: expect.any(Object),
            totalSpend: expect.any(Object),
            totalSpendPerCover: expect.any(Object),
            totalspendPerVisit: expect.any(Object),
            avgRating: expect.any(Object),
            referenceCode: expect.any(Object),
            externalUserId: expect.any(Object),
            venueGroupId: expect.any(Object),
            birthdayAltDay: expect.any(Object),
            userId: expect.any(Object),
            userName: expect.any(Object),
            totalOrderCount: expect.any(Object),
            preferredLanguageCode: expect.any(Object),
            clientPhoto: expect.any(Object),
            clientVenueStats: expect.any(Object),
          }),
        );
      });

      it('passing IClient should create a new form with FormGroup', () => {
        const formGroup = service.createClientFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            clientId: expect.any(Object),
            createdDate: expect.any(Object),
            updatedDate: expect.any(Object),
            deletedDate: expect.any(Object),
            lastname: expect.any(Object),
            firstname: expect.any(Object),
            gender: expect.any(Object),
            salutation: expect.any(Object),
            title: expect.any(Object),
            birthdayDay: expect.any(Object),
            birthdayMonth: expect.any(Object),
            birthdayAltMonth: expect.any(Object),
            anniversaryDay: expect.any(Object),
            anniversaryMonth: expect.any(Object),
            company: expect.any(Object),
            email: expect.any(Object),
            emailAlt: expect.any(Object),
            phoneNumber: expect.any(Object),
            phoneNumberlocale: expect.any(Object),
            phoneNumberalt: expect.any(Object),
            phoneNumberaltlocale: expect.any(Object),
            address: expect.any(Object),
            address2: expect.any(Object),
            city: expect.any(Object),
            postalCode: expect.any(Object),
            state: expect.any(Object),
            country: expect.any(Object),
            isContactPrivate: expect.any(Object),
            isOnetimeGuest: expect.any(Object),
            status: expect.any(Object),
            loyaltyId: expect.any(Object),
            loyaltyRank: expect.any(Object),
            loyaltyTier: expect.any(Object),
            marketingOptin: expect.any(Object),
            marketingOptints: expect.any(Object),
            marketingOptOutts: expect.any(Object),
            hasBillingProfile: expect.any(Object),
            notes: expect.any(Object),
            privateNotes: expect.any(Object),
            tags: expect.any(Object),
            totalVisits: expect.any(Object),
            totalCovers: expect.any(Object),
            totalCancellations: expect.any(Object),
            totalNoShows: expect.any(Object),
            totalSpend: expect.any(Object),
            totalSpendPerCover: expect.any(Object),
            totalspendPerVisit: expect.any(Object),
            avgRating: expect.any(Object),
            referenceCode: expect.any(Object),
            externalUserId: expect.any(Object),
            venueGroupId: expect.any(Object),
            birthdayAltDay: expect.any(Object),
            userId: expect.any(Object),
            userName: expect.any(Object),
            totalOrderCount: expect.any(Object),
            preferredLanguageCode: expect.any(Object),
            clientPhoto: expect.any(Object),
            clientVenueStats: expect.any(Object),
          }),
        );
      });
    });

    describe('getClient', () => {
      it('should return NewClient for default Client initial value', () => {
        const formGroup = service.createClientFormGroup(sampleWithNewData);

        const client = service.getClient(formGroup) as any;

        expect(client).toMatchObject(sampleWithNewData);
      });

      it('should return NewClient for empty Client initial value', () => {
        const formGroup = service.createClientFormGroup();

        const client = service.getClient(formGroup) as any;

        expect(client).toMatchObject({});
      });

      it('should return IClient', () => {
        const formGroup = service.createClientFormGroup(sampleWithRequiredData);

        const client = service.getClient(formGroup) as any;

        expect(client).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IClient should not enable id FormControl', () => {
        const formGroup = service.createClientFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewClient should disable id FormControl', () => {
        const formGroup = service.createClientFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
