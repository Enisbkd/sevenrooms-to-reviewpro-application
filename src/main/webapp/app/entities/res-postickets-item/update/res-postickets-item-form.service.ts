import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IResPosticketsItem, NewResPosticketsItem } from '../res-postickets-item.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IResPosticketsItem for edit and NewResPosticketsItemFormGroupInput for create.
 */
type ResPosticketsItemFormGroupInput = IResPosticketsItem | PartialWithRequiredKeyOf<NewResPosticketsItem>;

type ResPosticketsItemFormDefaults = Pick<NewResPosticketsItem, 'id'>;

type ResPosticketsItemFormGroupContent = {
  id: FormControl<IResPosticketsItem['id'] | NewResPosticketsItem['id']>;
  price: FormControl<IResPosticketsItem['price']>;
  name: FormControl<IResPosticketsItem['name']>;
  quantity: FormControl<IResPosticketsItem['quantity']>;
  resPosTicket: FormControl<IResPosticketsItem['resPosTicket']>;
};

export type ResPosticketsItemFormGroup = FormGroup<ResPosticketsItemFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ResPosticketsItemFormService {
  createResPosticketsItemFormGroup(resPosticketsItem: ResPosticketsItemFormGroupInput = { id: null }): ResPosticketsItemFormGroup {
    const resPosticketsItemRawValue = {
      ...this.getFormDefaults(),
      ...resPosticketsItem,
    };
    return new FormGroup<ResPosticketsItemFormGroupContent>({
      id: new FormControl(
        { value: resPosticketsItemRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      price: new FormControl(resPosticketsItemRawValue.price),
      name: new FormControl(resPosticketsItemRawValue.name),
      quantity: new FormControl(resPosticketsItemRawValue.quantity),
      resPosTicket: new FormControl(resPosticketsItemRawValue.resPosTicket),
    });
  }

  getResPosticketsItem(form: ResPosticketsItemFormGroup): IResPosticketsItem | NewResPosticketsItem {
    return form.getRawValue() as IResPosticketsItem | NewResPosticketsItem;
  }

  resetForm(form: ResPosticketsItemFormGroup, resPosticketsItem: ResPosticketsItemFormGroupInput): void {
    const resPosticketsItemRawValue = { ...this.getFormDefaults(), ...resPosticketsItem };
    form.reset(
      {
        ...resPosticketsItemRawValue,
        id: { value: resPosticketsItemRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ResPosticketsItemFormDefaults {
    return {
      id: null,
    };
  }
}
