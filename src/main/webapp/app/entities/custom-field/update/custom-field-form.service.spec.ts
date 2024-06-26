import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../custom-field.test-samples';

import { CustomFieldFormService } from './custom-field-form.service';

describe('CustomField Form Service', () => {
  let service: CustomFieldFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CustomFieldFormService);
  });

  describe('Service methods', () => {
    describe('createCustomFieldFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCustomFieldFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            systemName: expect.any(Object),
            displayOrder: expect.any(Object),
            name: expect.any(Object),
            value: expect.any(Object),
            client: expect.any(Object),
          }),
        );
      });

      it('passing ICustomField should create a new form with FormGroup', () => {
        const formGroup = service.createCustomFieldFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            systemName: expect.any(Object),
            displayOrder: expect.any(Object),
            name: expect.any(Object),
            value: expect.any(Object),
            client: expect.any(Object),
          }),
        );
      });
    });

    describe('getCustomField', () => {
      it('should return NewCustomField for default CustomField initial value', () => {
        const formGroup = service.createCustomFieldFormGroup(sampleWithNewData);

        const customField = service.getCustomField(formGroup) as any;

        expect(customField).toMatchObject(sampleWithNewData);
      });

      it('should return NewCustomField for empty CustomField initial value', () => {
        const formGroup = service.createCustomFieldFormGroup();

        const customField = service.getCustomField(formGroup) as any;

        expect(customField).toMatchObject({});
      });

      it('should return ICustomField', () => {
        const formGroup = service.createCustomFieldFormGroup(sampleWithRequiredData);

        const customField = service.getCustomField(formGroup) as any;

        expect(customField).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICustomField should not enable id FormControl', () => {
        const formGroup = service.createCustomFieldFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCustomField should disable id FormControl', () => {
        const formGroup = service.createCustomFieldFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
