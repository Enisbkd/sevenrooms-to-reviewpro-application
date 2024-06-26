import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../res-tag.test-samples';

import { ResTagFormService } from './res-tag-form.service';

describe('ResTag Form Service', () => {
  let service: ResTagFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ResTagFormService);
  });

  describe('Service methods', () => {
    describe('createResTagFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createResTagFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tag: expect.any(Object),
            tagDisplay: expect.any(Object),
            group: expect.any(Object),
            groupDisplay: expect.any(Object),
            color: expect.any(Object),
            tagSearchQuery: expect.any(Object),
            reservation: expect.any(Object),
          }),
        );
      });

      it('passing IResTag should create a new form with FormGroup', () => {
        const formGroup = service.createResTagFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tag: expect.any(Object),
            tagDisplay: expect.any(Object),
            group: expect.any(Object),
            groupDisplay: expect.any(Object),
            color: expect.any(Object),
            tagSearchQuery: expect.any(Object),
            reservation: expect.any(Object),
          }),
        );
      });
    });

    describe('getResTag', () => {
      it('should return NewResTag for default ResTag initial value', () => {
        const formGroup = service.createResTagFormGroup(sampleWithNewData);

        const resTag = service.getResTag(formGroup) as any;

        expect(resTag).toMatchObject(sampleWithNewData);
      });

      it('should return NewResTag for empty ResTag initial value', () => {
        const formGroup = service.createResTagFormGroup();

        const resTag = service.getResTag(formGroup) as any;

        expect(resTag).toMatchObject({});
      });

      it('should return IResTag', () => {
        const formGroup = service.createResTagFormGroup(sampleWithRequiredData);

        const resTag = service.getResTag(formGroup) as any;

        expect(resTag).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IResTag should not enable id FormControl', () => {
        const formGroup = service.createResTagFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewResTag should disable id FormControl', () => {
        const formGroup = service.createResTagFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
