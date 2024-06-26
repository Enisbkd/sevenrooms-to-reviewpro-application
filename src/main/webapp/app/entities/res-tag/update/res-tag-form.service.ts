import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IResTag, NewResTag } from '../res-tag.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IResTag for edit and NewResTagFormGroupInput for create.
 */
type ResTagFormGroupInput = IResTag | PartialWithRequiredKeyOf<NewResTag>;

type ResTagFormDefaults = Pick<NewResTag, 'id'>;

type ResTagFormGroupContent = {
  id: FormControl<IResTag['id'] | NewResTag['id']>;
  tag: FormControl<IResTag['tag']>;
  tagDisplay: FormControl<IResTag['tagDisplay']>;
  group: FormControl<IResTag['group']>;
  groupDisplay: FormControl<IResTag['groupDisplay']>;
  color: FormControl<IResTag['color']>;
  tagSearchQuery: FormControl<IResTag['tagSearchQuery']>;
  reservation: FormControl<IResTag['reservation']>;
};

export type ResTagFormGroup = FormGroup<ResTagFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ResTagFormService {
  createResTagFormGroup(resTag: ResTagFormGroupInput = { id: null }): ResTagFormGroup {
    const resTagRawValue = {
      ...this.getFormDefaults(),
      ...resTag,
    };
    return new FormGroup<ResTagFormGroupContent>({
      id: new FormControl(
        { value: resTagRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      tag: new FormControl(resTagRawValue.tag),
      tagDisplay: new FormControl(resTagRawValue.tagDisplay),
      group: new FormControl(resTagRawValue.group),
      groupDisplay: new FormControl(resTagRawValue.groupDisplay),
      color: new FormControl(resTagRawValue.color),
      tagSearchQuery: new FormControl(resTagRawValue.tagSearchQuery),
      reservation: new FormControl(resTagRawValue.reservation),
    });
  }

  getResTag(form: ResTagFormGroup): IResTag | NewResTag {
    return form.getRawValue() as IResTag | NewResTag;
  }

  resetForm(form: ResTagFormGroup, resTag: ResTagFormGroupInput): void {
    const resTagRawValue = { ...this.getFormDefaults(), ...resTag };
    form.reset(
      {
        ...resTagRawValue,
        id: { value: resTagRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ResTagFormDefaults {
    return {
      id: null,
    };
  }
}
