import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../client-venue-stats.test-samples';

import { ClientVenueStatsFormService } from './client-venue-stats-form.service';

describe('ClientVenueStats Form Service', () => {
  let service: ClientVenueStatsFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClientVenueStatsFormService);
  });

  describe('Service methods', () => {
    describe('createClientVenueStatsFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createClientVenueStatsFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            totalSpendLocalperCover: expect.any(Object),
            lastVisitDate: expect.any(Object),
            totalCancellations: expect.any(Object),
            totalCovers: expect.any(Object),
            avgRating: expect.any(Object),
            totalSpendperCover: expect.any(Object),
            totalSpend: expect.any(Object),
            totalNoShows: expect.any(Object),
            numRatings: expect.any(Object),
            totalSpendPerVisit: expect.any(Object),
            totalSpendLocal: expect.any(Object),
            totalSpendLocalPerVisit: expect.any(Object),
            totalVisits: expect.any(Object),
            grossTotal: expect.any(Object),
            totalOrderCount: expect.any(Object),
            totalOrderCancellations: expect.any(Object),
            totalOrderSpend: expect.any(Object),
            grossOrderTotal: expect.any(Object),
            totalOrderSpendLocal: expect.any(Object),
            lastOrderDate: expect.any(Object),
            totalSpendperOrder: expect.any(Object),
            totalSpendLocalperOrder: expect.any(Object),
            venueId: expect.any(Object),
            venueMarketingOptin: expect.any(Object),
            venueMarketingOptints: expect.any(Object),
          }),
        );
      });

      it('passing IClientVenueStats should create a new form with FormGroup', () => {
        const formGroup = service.createClientVenueStatsFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            totalSpendLocalperCover: expect.any(Object),
            lastVisitDate: expect.any(Object),
            totalCancellations: expect.any(Object),
            totalCovers: expect.any(Object),
            avgRating: expect.any(Object),
            totalSpendperCover: expect.any(Object),
            totalSpend: expect.any(Object),
            totalNoShows: expect.any(Object),
            numRatings: expect.any(Object),
            totalSpendPerVisit: expect.any(Object),
            totalSpendLocal: expect.any(Object),
            totalSpendLocalPerVisit: expect.any(Object),
            totalVisits: expect.any(Object),
            grossTotal: expect.any(Object),
            totalOrderCount: expect.any(Object),
            totalOrderCancellations: expect.any(Object),
            totalOrderSpend: expect.any(Object),
            grossOrderTotal: expect.any(Object),
            totalOrderSpendLocal: expect.any(Object),
            lastOrderDate: expect.any(Object),
            totalSpendperOrder: expect.any(Object),
            totalSpendLocalperOrder: expect.any(Object),
            venueId: expect.any(Object),
            venueMarketingOptin: expect.any(Object),
            venueMarketingOptints: expect.any(Object),
          }),
        );
      });
    });

    describe('getClientVenueStats', () => {
      it('should return NewClientVenueStats for default ClientVenueStats initial value', () => {
        const formGroup = service.createClientVenueStatsFormGroup(sampleWithNewData);

        const clientVenueStats = service.getClientVenueStats(formGroup) as any;

        expect(clientVenueStats).toMatchObject(sampleWithNewData);
      });

      it('should return NewClientVenueStats for empty ClientVenueStats initial value', () => {
        const formGroup = service.createClientVenueStatsFormGroup();

        const clientVenueStats = service.getClientVenueStats(formGroup) as any;

        expect(clientVenueStats).toMatchObject({});
      });

      it('should return IClientVenueStats', () => {
        const formGroup = service.createClientVenueStatsFormGroup(sampleWithRequiredData);

        const clientVenueStats = service.getClientVenueStats(formGroup) as any;

        expect(clientVenueStats).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IClientVenueStats should not enable id FormControl', () => {
        const formGroup = service.createClientVenueStatsFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewClientVenueStats should disable id FormControl', () => {
        const formGroup = service.createClientVenueStatsFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
