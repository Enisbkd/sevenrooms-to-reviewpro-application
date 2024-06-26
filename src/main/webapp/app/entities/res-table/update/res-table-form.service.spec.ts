import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../res-table.test-samples';

import { ResTableFormService } from './res-table-form.service';

describe('ResTable Form Service', () => {
  let service: ResTableFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ResTableFormService);
  });

  describe('Service methods', () => {
    describe('createResTableFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createResTableFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tableNumber: expect.any(Object),
            reservation: expect.any(Object),
          }),
        );
      });

      it('passing IResTable should create a new form with FormGroup', () => {
        const formGroup = service.createResTableFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tableNumber: expect.any(Object),
            reservation: expect.any(Object),
          }),
        );
      });
    });

    describe('getResTable', () => {
      it('should return NewResTable for default ResTable initial value', () => {
        const formGroup = service.createResTableFormGroup(sampleWithNewData);

        const resTable = service.getResTable(formGroup) as any;

        expect(resTable).toMatchObject(sampleWithNewData);
      });

      it('should return NewResTable for empty ResTable initial value', () => {
        const formGroup = service.createResTableFormGroup();

        const resTable = service.getResTable(formGroup) as any;

        expect(resTable).toMatchObject({});
      });

      it('should return IResTable', () => {
        const formGroup = service.createResTableFormGroup(sampleWithRequiredData);

        const resTable = service.getResTable(formGroup) as any;

        expect(resTable).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IResTable should not enable id FormControl', () => {
        const formGroup = service.createResTableFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewResTable should disable id FormControl', () => {
        const formGroup = service.createResTableFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
