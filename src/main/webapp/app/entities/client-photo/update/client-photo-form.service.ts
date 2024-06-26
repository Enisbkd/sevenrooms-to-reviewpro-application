import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IClientPhoto, NewClientPhoto } from '../client-photo.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IClientPhoto for edit and NewClientPhotoFormGroupInput for create.
 */
type ClientPhotoFormGroupInput = IClientPhoto | PartialWithRequiredKeyOf<NewClientPhoto>;

type ClientPhotoFormDefaults = Pick<NewClientPhoto, 'id'>;

type ClientPhotoFormGroupContent = {
  id: FormControl<IClientPhoto['id'] | NewClientPhoto['id']>;
  large: FormControl<IClientPhoto['large']>;
  largeHeight: FormControl<IClientPhoto['largeHeight']>;
  largeWidth: FormControl<IClientPhoto['largeWidth']>;
  medium: FormControl<IClientPhoto['medium']>;
  mediumHeight: FormControl<IClientPhoto['mediumHeight']>;
  mediumWidth: FormControl<IClientPhoto['mediumWidth']>;
  small: FormControl<IClientPhoto['small']>;
  smallHeight: FormControl<IClientPhoto['smallHeight']>;
  smallWidth: FormControl<IClientPhoto['smallWidth']>;
  raw: FormControl<IClientPhoto['raw']>;
  cropx: FormControl<IClientPhoto['cropx']>;
  cropy: FormControl<IClientPhoto['cropy']>;
  cropHeight: FormControl<IClientPhoto['cropHeight']>;
  cropWidth: FormControl<IClientPhoto['cropWidth']>;
};

export type ClientPhotoFormGroup = FormGroup<ClientPhotoFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ClientPhotoFormService {
  createClientPhotoFormGroup(clientPhoto: ClientPhotoFormGroupInput = { id: null }): ClientPhotoFormGroup {
    const clientPhotoRawValue = {
      ...this.getFormDefaults(),
      ...clientPhoto,
    };
    return new FormGroup<ClientPhotoFormGroupContent>({
      id: new FormControl(
        { value: clientPhotoRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      large: new FormControl(clientPhotoRawValue.large),
      largeHeight: new FormControl(clientPhotoRawValue.largeHeight),
      largeWidth: new FormControl(clientPhotoRawValue.largeWidth),
      medium: new FormControl(clientPhotoRawValue.medium),
      mediumHeight: new FormControl(clientPhotoRawValue.mediumHeight),
      mediumWidth: new FormControl(clientPhotoRawValue.mediumWidth),
      small: new FormControl(clientPhotoRawValue.small),
      smallHeight: new FormControl(clientPhotoRawValue.smallHeight),
      smallWidth: new FormControl(clientPhotoRawValue.smallWidth),
      raw: new FormControl(clientPhotoRawValue.raw),
      cropx: new FormControl(clientPhotoRawValue.cropx),
      cropy: new FormControl(clientPhotoRawValue.cropy),
      cropHeight: new FormControl(clientPhotoRawValue.cropHeight),
      cropWidth: new FormControl(clientPhotoRawValue.cropWidth),
    });
  }

  getClientPhoto(form: ClientPhotoFormGroup): IClientPhoto | NewClientPhoto {
    return form.getRawValue() as IClientPhoto | NewClientPhoto;
  }

  resetForm(form: ClientPhotoFormGroup, clientPhoto: ClientPhotoFormGroupInput): void {
    const clientPhotoRawValue = { ...this.getFormDefaults(), ...clientPhoto };
    form.reset(
      {
        ...clientPhotoRawValue,
        id: { value: clientPhotoRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ClientPhotoFormDefaults {
    return {
      id: null,
    };
  }
}
