import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../client-tag.test-samples';

import { ClientTagFormService } from './client-tag-form.service';

describe('ClientTag Form Service', () => {
  let service: ClientTagFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClientTagFormService);
  });

  describe('Service methods', () => {
    describe('createClientTagFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createClientTagFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tag: expect.any(Object),
            tagDisplay: expect.any(Object),
            group: expect.any(Object),
            groupDisplay: expect.any(Object),
            color: expect.any(Object),
            tagSearchQuery: expect.any(Object),
            client: expect.any(Object),
          }),
        );
      });

      it('passing IClientTag should create a new form with FormGroup', () => {
        const formGroup = service.createClientTagFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            tag: expect.any(Object),
            tagDisplay: expect.any(Object),
            group: expect.any(Object),
            groupDisplay: expect.any(Object),
            color: expect.any(Object),
            tagSearchQuery: expect.any(Object),
            client: expect.any(Object),
          }),
        );
      });
    });

    describe('getClientTag', () => {
      it('should return NewClientTag for default ClientTag initial value', () => {
        const formGroup = service.createClientTagFormGroup(sampleWithNewData);

        const clientTag = service.getClientTag(formGroup) as any;

        expect(clientTag).toMatchObject(sampleWithNewData);
      });

      it('should return NewClientTag for empty ClientTag initial value', () => {
        const formGroup = service.createClientTagFormGroup();

        const clientTag = service.getClientTag(formGroup) as any;

        expect(clientTag).toMatchObject({});
      });

      it('should return IClientTag', () => {
        const formGroup = service.createClientTagFormGroup(sampleWithRequiredData);

        const clientTag = service.getClientTag(formGroup) as any;

        expect(clientTag).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IClientTag should not enable id FormControl', () => {
        const formGroup = service.createClientTagFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewClientTag should disable id FormControl', () => {
        const formGroup = service.createClientTagFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
