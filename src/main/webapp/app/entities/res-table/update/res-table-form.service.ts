import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IResTable, NewResTable } from '../res-table.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IResTable for edit and NewResTableFormGroupInput for create.
 */
type ResTableFormGroupInput = IResTable | PartialWithRequiredKeyOf<NewResTable>;

type ResTableFormDefaults = Pick<NewResTable, 'id'>;

type ResTableFormGroupContent = {
  id: FormControl<IResTable['id'] | NewResTable['id']>;
  tableNumber: FormControl<IResTable['tableNumber']>;
  reservation: FormControl<IResTable['reservation']>;
};

export type ResTableFormGroup = FormGroup<ResTableFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ResTableFormService {
  createResTableFormGroup(resTable: ResTableFormGroupInput = { id: null }): ResTableFormGroup {
    const resTableRawValue = {
      ...this.getFormDefaults(),
      ...resTable,
    };
    return new FormGroup<ResTableFormGroupContent>({
      id: new FormControl(
        { value: resTableRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      tableNumber: new FormControl(resTableRawValue.tableNumber),
      reservation: new FormControl(resTableRawValue.reservation),
    });
  }

  getResTable(form: ResTableFormGroup): IResTable | NewResTable {
    return form.getRawValue() as IResTable | NewResTable;
  }

  resetForm(form: ResTableFormGroup, resTable: ResTableFormGroupInput): void {
    const resTableRawValue = { ...this.getFormDefaults(), ...resTable };
    form.reset(
      {
        ...resTableRawValue,
        id: { value: resTableRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ResTableFormDefaults {
    return {
      id: null,
    };
  }
}
