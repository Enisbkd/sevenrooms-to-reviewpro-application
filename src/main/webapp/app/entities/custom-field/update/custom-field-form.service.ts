import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ICustomField, NewCustomField } from '../custom-field.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICustomField for edit and NewCustomFieldFormGroupInput for create.
 */
type CustomFieldFormGroupInput = ICustomField | PartialWithRequiredKeyOf<NewCustomField>;

type CustomFieldFormDefaults = Pick<NewCustomField, 'id'>;

type CustomFieldFormGroupContent = {
  id: FormControl<ICustomField['id'] | NewCustomField['id']>;
  systemName: FormControl<ICustomField['systemName']>;
  displayOrder: FormControl<ICustomField['displayOrder']>;
  name: FormControl<ICustomField['name']>;
  value: FormControl<ICustomField['value']>;
  client: FormControl<ICustomField['client']>;
};

export type CustomFieldFormGroup = FormGroup<CustomFieldFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CustomFieldFormService {
  createCustomFieldFormGroup(customField: CustomFieldFormGroupInput = { id: null }): CustomFieldFormGroup {
    const customFieldRawValue = {
      ...this.getFormDefaults(),
      ...customField,
    };
    return new FormGroup<CustomFieldFormGroupContent>({
      id: new FormControl(
        { value: customFieldRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      systemName: new FormControl(customFieldRawValue.systemName),
      displayOrder: new FormControl(customFieldRawValue.displayOrder),
      name: new FormControl(customFieldRawValue.name),
      value: new FormControl(customFieldRawValue.value),
      client: new FormControl(customFieldRawValue.client),
    });
  }

  getCustomField(form: CustomFieldFormGroup): ICustomField | NewCustomField {
    return form.getRawValue() as ICustomField | NewCustomField;
  }

  resetForm(form: CustomFieldFormGroup, customField: CustomFieldFormGroupInput): void {
    const customFieldRawValue = { ...this.getFormDefaults(), ...customField };
    form.reset(
      {
        ...customFieldRawValue,
        id: { value: customFieldRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): CustomFieldFormDefaults {
    return {
      id: null,
    };
  }
}
