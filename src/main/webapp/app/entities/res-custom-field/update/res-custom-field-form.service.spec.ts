import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../res-custom-field.test-samples';

import { ResCustomFieldFormService } from './res-custom-field-form.service';

describe('ResCustomField Form Service', () => {
  let service: ResCustomFieldFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ResCustomFieldFormService);
  });

  describe('Service methods', () => {
    describe('createResCustomFieldFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createResCustomFieldFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            systemName: expect.any(Object),
            displayOrder: expect.any(Object),
            name: expect.any(Object),
            value: expect.any(Object),
            reservation: expect.any(Object),
          }),
        );
      });

      it('passing IResCustomField should create a new form with FormGroup', () => {
        const formGroup = service.createResCustomFieldFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            systemName: expect.any(Object),
            displayOrder: expect.any(Object),
            name: expect.any(Object),
            value: expect.any(Object),
            reservation: expect.any(Object),
          }),
        );
      });
    });

    describe('getResCustomField', () => {
      it('should return NewResCustomField for default ResCustomField initial value', () => {
        const formGroup = service.createResCustomFieldFormGroup(sampleWithNewData);

        const resCustomField = service.getResCustomField(formGroup) as any;

        expect(resCustomField).toMatchObject(sampleWithNewData);
      });

      it('should return NewResCustomField for empty ResCustomField initial value', () => {
        const formGroup = service.createResCustomFieldFormGroup();

        const resCustomField = service.getResCustomField(formGroup) as any;

        expect(resCustomField).toMatchObject({});
      });

      it('should return IResCustomField', () => {
        const formGroup = service.createResCustomFieldFormGroup(sampleWithRequiredData);

        const resCustomField = service.getResCustomField(formGroup) as any;

        expect(resCustomField).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IResCustomField should not enable id FormControl', () => {
        const formGroup = service.createResCustomFieldFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewResCustomField should disable id FormControl', () => {
        const formGroup = service.createResCustomFieldFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
