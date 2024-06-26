import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../res-postickets-item.test-samples';

import { ResPosticketsItemFormService } from './res-postickets-item-form.service';

describe('ResPosticketsItem Form Service', () => {
  let service: ResPosticketsItemFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ResPosticketsItemFormService);
  });

  describe('Service methods', () => {
    describe('createResPosticketsItemFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createResPosticketsItemFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            price: expect.any(Object),
            name: expect.any(Object),
            quantity: expect.any(Object),
            resPosTicket: expect.any(Object),
          }),
        );
      });

      it('passing IResPosticketsItem should create a new form with FormGroup', () => {
        const formGroup = service.createResPosticketsItemFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            price: expect.any(Object),
            name: expect.any(Object),
            quantity: expect.any(Object),
            resPosTicket: expect.any(Object),
          }),
        );
      });
    });

    describe('getResPosticketsItem', () => {
      it('should return NewResPosticketsItem for default ResPosticketsItem initial value', () => {
        const formGroup = service.createResPosticketsItemFormGroup(sampleWithNewData);

        const resPosticketsItem = service.getResPosticketsItem(formGroup) as any;

        expect(resPosticketsItem).toMatchObject(sampleWithNewData);
      });

      it('should return NewResPosticketsItem for empty ResPosticketsItem initial value', () => {
        const formGroup = service.createResPosticketsItemFormGroup();

        const resPosticketsItem = service.getResPosticketsItem(formGroup) as any;

        expect(resPosticketsItem).toMatchObject({});
      });

      it('should return IResPosticketsItem', () => {
        const formGroup = service.createResPosticketsItemFormGroup(sampleWithRequiredData);

        const resPosticketsItem = service.getResPosticketsItem(formGroup) as any;

        expect(resPosticketsItem).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IResPosticketsItem should not enable id FormControl', () => {
        const formGroup = service.createResPosticketsItemFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewResPosticketsItem should disable id FormControl', () => {
        const formGroup = service.createResPosticketsItemFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
