import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IClientTag, NewClientTag } from '../client-tag.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IClientTag for edit and NewClientTagFormGroupInput for create.
 */
type ClientTagFormGroupInput = IClientTag | PartialWithRequiredKeyOf<NewClientTag>;

type ClientTagFormDefaults = Pick<NewClientTag, 'id'>;

type ClientTagFormGroupContent = {
  id: FormControl<IClientTag['id'] | NewClientTag['id']>;
  tag: FormControl<IClientTag['tag']>;
  tagDisplay: FormControl<IClientTag['tagDisplay']>;
  group: FormControl<IClientTag['group']>;
  groupDisplay: FormControl<IClientTag['groupDisplay']>;
  color: FormControl<IClientTag['color']>;
  tagSearchQuery: FormControl<IClientTag['tagSearchQuery']>;
  client: FormControl<IClientTag['client']>;
};

export type ClientTagFormGroup = FormGroup<ClientTagFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ClientTagFormService {
  createClientTagFormGroup(clientTag: ClientTagFormGroupInput = { id: null }): ClientTagFormGroup {
    const clientTagRawValue = {
      ...this.getFormDefaults(),
      ...clientTag,
    };
    return new FormGroup<ClientTagFormGroupContent>({
      id: new FormControl(
        { value: clientTagRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      tag: new FormControl(clientTagRawValue.tag),
      tagDisplay: new FormControl(clientTagRawValue.tagDisplay),
      group: new FormControl(clientTagRawValue.group),
      groupDisplay: new FormControl(clientTagRawValue.groupDisplay),
      color: new FormControl(clientTagRawValue.color),
      tagSearchQuery: new FormControl(clientTagRawValue.tagSearchQuery),
      client: new FormControl(clientTagRawValue.client),
    });
  }

  getClientTag(form: ClientTagFormGroup): IClientTag | NewClientTag {
    return form.getRawValue() as IClientTag | NewClientTag;
  }

  resetForm(form: ClientTagFormGroup, clientTag: ClientTagFormGroupInput): void {
    const clientTagRawValue = { ...this.getFormDefaults(), ...clientTag };
    form.reset(
      {
        ...clientTagRawValue,
        id: { value: clientTagRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ClientTagFormDefaults {
    return {
      id: null,
    };
  }
}
