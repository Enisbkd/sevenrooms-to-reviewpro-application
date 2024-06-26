import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IResCustomField, NewResCustomField } from '../res-custom-field.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IResCustomField for edit and NewResCustomFieldFormGroupInput for create.
 */
type ResCustomFieldFormGroupInput = IResCustomField | PartialWithRequiredKeyOf<NewResCustomField>;

type ResCustomFieldFormDefaults = Pick<NewResCustomField, 'id'>;

type ResCustomFieldFormGroupContent = {
  id: FormControl<IResCustomField['id'] | NewResCustomField['id']>;
  systemName: FormControl<IResCustomField['systemName']>;
  displayOrder: FormControl<IResCustomField['displayOrder']>;
  name: FormControl<IResCustomField['name']>;
  value: FormControl<IResCustomField['value']>;
  reservation: FormControl<IResCustomField['reservation']>;
};

export type ResCustomFieldFormGroup = FormGroup<ResCustomFieldFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ResCustomFieldFormService {
  createResCustomFieldFormGroup(resCustomField: ResCustomFieldFormGroupInput = { id: null }): ResCustomFieldFormGroup {
    const resCustomFieldRawValue = {
      ...this.getFormDefaults(),
      ...resCustomField,
    };
    return new FormGroup<ResCustomFieldFormGroupContent>({
      id: new FormControl(
        { value: resCustomFieldRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      systemName: new FormControl(resCustomFieldRawValue.systemName),
      displayOrder: new FormControl(resCustomFieldRawValue.displayOrder),
      name: new FormControl(resCustomFieldRawValue.name),
      value: new FormControl(resCustomFieldRawValue.value),
      reservation: new FormControl(resCustomFieldRawValue.reservation),
    });
  }

  getResCustomField(form: ResCustomFieldFormGroup): IResCustomField | NewResCustomField {
    return form.getRawValue() as IResCustomField | NewResCustomField;
  }

  resetForm(form: ResCustomFieldFormGroup, resCustomField: ResCustomFieldFormGroupInput): void {
    const resCustomFieldRawValue = { ...this.getFormDefaults(), ...resCustomField };
    form.reset(
      {
        ...resCustomFieldRawValue,
        id: { value: resCustomFieldRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ResCustomFieldFormDefaults {
    return {
      id: null,
    };
  }
}
