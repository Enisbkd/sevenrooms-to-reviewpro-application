import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../rvp-guest.test-samples';

import { RvpGuestFormService } from './rvp-guest-form.service';

describe('RvpGuest Form Service', () => {
  let service: RvpGuestFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RvpGuestFormService);
  });

  describe('Service methods', () => {
    describe('createRvpGuestFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createRvpGuestFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            pmsId: expect.any(Object),
            firstName: expect.any(Object),
            lastName: expect.any(Object),
            language: expect.any(Object),
            checkin: expect.any(Object),
            checkout: expect.any(Object),
            email: expect.any(Object),
            emailAlt: expect.any(Object),
            salutation: expect.any(Object),
          }),
        );
      });

      it('passing IRvpGuest should create a new form with FormGroup', () => {
        const formGroup = service.createRvpGuestFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            pmsId: expect.any(Object),
            firstName: expect.any(Object),
            lastName: expect.any(Object),
            language: expect.any(Object),
            checkin: expect.any(Object),
            checkout: expect.any(Object),
            email: expect.any(Object),
            emailAlt: expect.any(Object),
            salutation: expect.any(Object),
          }),
        );
      });
    });

    describe('getRvpGuest', () => {
      it('should return NewRvpGuest for default RvpGuest initial value', () => {
        const formGroup = service.createRvpGuestFormGroup(sampleWithNewData);

        const rvpGuest = service.getRvpGuest(formGroup) as any;

        expect(rvpGuest).toMatchObject(sampleWithNewData);
      });

      it('should return NewRvpGuest for empty RvpGuest initial value', () => {
        const formGroup = service.createRvpGuestFormGroup();

        const rvpGuest = service.getRvpGuest(formGroup) as any;

        expect(rvpGuest).toMatchObject({});
      });

      it('should return IRvpGuest', () => {
        const formGroup = service.createRvpGuestFormGroup(sampleWithRequiredData);

        const rvpGuest = service.getRvpGuest(formGroup) as any;

        expect(rvpGuest).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IRvpGuest should not enable id FormControl', () => {
        const formGroup = service.createRvpGuestFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewRvpGuest should disable id FormControl', () => {
        const formGroup = service.createRvpGuestFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
