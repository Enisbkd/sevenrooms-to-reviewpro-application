import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../booking-name.test-samples';

import { BookingNameFormService } from './booking-name-form.service';

describe('BookingName Form Service', () => {
  let service: BookingNameFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BookingNameFormService);
  });

  describe('Service methods', () => {
    describe('createBookingNameFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createBookingNameFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
            clientVenueStats: expect.any(Object),
          }),
        );
      });

      it('passing IBookingName should create a new form with FormGroup', () => {
        const formGroup = service.createBookingNameFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            name: expect.any(Object),
            clientVenueStats: expect.any(Object),
          }),
        );
      });
    });

    describe('getBookingName', () => {
      it('should return NewBookingName for default BookingName initial value', () => {
        const formGroup = service.createBookingNameFormGroup(sampleWithNewData);

        const bookingName = service.getBookingName(formGroup) as any;

        expect(bookingName).toMatchObject(sampleWithNewData);
      });

      it('should return NewBookingName for empty BookingName initial value', () => {
        const formGroup = service.createBookingNameFormGroup();

        const bookingName = service.getBookingName(formGroup) as any;

        expect(bookingName).toMatchObject({});
      });

      it('should return IBookingName', () => {
        const formGroup = service.createBookingNameFormGroup(sampleWithRequiredData);

        const bookingName = service.getBookingName(formGroup) as any;

        expect(bookingName).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IBookingName should not enable id FormControl', () => {
        const formGroup = service.createBookingNameFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewBookingName should disable id FormControl', () => {
        const formGroup = service.createBookingNameFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
