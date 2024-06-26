import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../client-photo.test-samples';

import { ClientPhotoFormService } from './client-photo-form.service';

describe('ClientPhoto Form Service', () => {
  let service: ClientPhotoFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClientPhotoFormService);
  });

  describe('Service methods', () => {
    describe('createClientPhotoFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createClientPhotoFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            large: expect.any(Object),
            largeHeight: expect.any(Object),
            largeWidth: expect.any(Object),
            medium: expect.any(Object),
            mediumHeight: expect.any(Object),
            mediumWidth: expect.any(Object),
            small: expect.any(Object),
            smallHeight: expect.any(Object),
            smallWidth: expect.any(Object),
            raw: expect.any(Object),
            cropx: expect.any(Object),
            cropy: expect.any(Object),
            cropHeight: expect.any(Object),
            cropWidth: expect.any(Object),
          }),
        );
      });

      it('passing IClientPhoto should create a new form with FormGroup', () => {
        const formGroup = service.createClientPhotoFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            large: expect.any(Object),
            largeHeight: expect.any(Object),
            largeWidth: expect.any(Object),
            medium: expect.any(Object),
            mediumHeight: expect.any(Object),
            mediumWidth: expect.any(Object),
            small: expect.any(Object),
            smallHeight: expect.any(Object),
            smallWidth: expect.any(Object),
            raw: expect.any(Object),
            cropx: expect.any(Object),
            cropy: expect.any(Object),
            cropHeight: expect.any(Object),
            cropWidth: expect.any(Object),
          }),
        );
      });
    });

    describe('getClientPhoto', () => {
      it('should return NewClientPhoto for default ClientPhoto initial value', () => {
        const formGroup = service.createClientPhotoFormGroup(sampleWithNewData);

        const clientPhoto = service.getClientPhoto(formGroup) as any;

        expect(clientPhoto).toMatchObject(sampleWithNewData);
      });

      it('should return NewClientPhoto for empty ClientPhoto initial value', () => {
        const formGroup = service.createClientPhotoFormGroup();

        const clientPhoto = service.getClientPhoto(formGroup) as any;

        expect(clientPhoto).toMatchObject({});
      });

      it('should return IClientPhoto', () => {
        const formGroup = service.createClientPhotoFormGroup(sampleWithRequiredData);

        const clientPhoto = service.getClientPhoto(formGroup) as any;

        expect(clientPhoto).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IClientPhoto should not enable id FormControl', () => {
        const formGroup = service.createClientPhotoFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewClientPhoto should disable id FormControl', () => {
        const formGroup = service.createClientPhotoFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
