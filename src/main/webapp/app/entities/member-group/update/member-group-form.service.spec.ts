import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../member-group.test-samples';

import { MemberGroupFormService } from './member-group-form.service';

describe('MemberGroup Form Service', () => {
  let service: MemberGroupFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MemberGroupFormService);
  });

  describe('Service methods', () => {
    describe('createMemberGroupFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createMemberGroupFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            client: expect.any(Object),
          }),
        );
      });

      it('passing IMemberGroup should create a new form with FormGroup', () => {
        const formGroup = service.createMemberGroupFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            client: expect.any(Object),
          }),
        );
      });
    });

    describe('getMemberGroup', () => {
      it('should return NewMemberGroup for default MemberGroup initial value', () => {
        const formGroup = service.createMemberGroupFormGroup(sampleWithNewData);

        const memberGroup = service.getMemberGroup(formGroup) as any;

        expect(memberGroup).toMatchObject(sampleWithNewData);
      });

      it('should return NewMemberGroup for empty MemberGroup initial value', () => {
        const formGroup = service.createMemberGroupFormGroup();

        const memberGroup = service.getMemberGroup(formGroup) as any;

        expect(memberGroup).toMatchObject({});
      });

      it('should return IMemberGroup', () => {
        const formGroup = service.createMemberGroupFormGroup(sampleWithRequiredData);

        const memberGroup = service.getMemberGroup(formGroup) as any;

        expect(memberGroup).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IMemberGroup should not enable id FormControl', () => {
        const formGroup = service.createMemberGroupFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewMemberGroup should disable id FormControl', () => {
        const formGroup = service.createMemberGroupFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
