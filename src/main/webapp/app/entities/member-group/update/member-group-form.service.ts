import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IMemberGroup, NewMemberGroup } from '../member-group.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IMemberGroup for edit and NewMemberGroupFormGroupInput for create.
 */
type MemberGroupFormGroupInput = IMemberGroup | PartialWithRequiredKeyOf<NewMemberGroup>;

type MemberGroupFormDefaults = Pick<NewMemberGroup, 'id'>;

type MemberGroupFormGroupContent = {
  id: FormControl<IMemberGroup['id'] | NewMemberGroup['id']>;
  client: FormControl<IMemberGroup['client']>;
};

export type MemberGroupFormGroup = FormGroup<MemberGroupFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class MemberGroupFormService {
  createMemberGroupFormGroup(memberGroup: MemberGroupFormGroupInput = { id: null }): MemberGroupFormGroup {
    const memberGroupRawValue = {
      ...this.getFormDefaults(),
      ...memberGroup,
    };
    return new FormGroup<MemberGroupFormGroupContent>({
      id: new FormControl(
        { value: memberGroupRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      client: new FormControl(memberGroupRawValue.client),
    });
  }

  getMemberGroup(form: MemberGroupFormGroup): IMemberGroup | NewMemberGroup {
    return form.getRawValue() as IMemberGroup | NewMemberGroup;
  }

  resetForm(form: MemberGroupFormGroup, memberGroup: MemberGroupFormGroupInput): void {
    const memberGroupRawValue = { ...this.getFormDefaults(), ...memberGroup };
    form.reset(
      {
        ...memberGroupRawValue,
        id: { value: memberGroupRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): MemberGroupFormDefaults {
    return {
      id: null,
    };
  }
}
