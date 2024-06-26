import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IRvpGuest, NewRvpGuest } from '../rvp-guest.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IRvpGuest for edit and NewRvpGuestFormGroupInput for create.
 */
type RvpGuestFormGroupInput = IRvpGuest | PartialWithRequiredKeyOf<NewRvpGuest>;

type RvpGuestFormDefaults = Pick<NewRvpGuest, 'id'>;

type RvpGuestFormGroupContent = {
  id: FormControl<IRvpGuest['id'] | NewRvpGuest['id']>;
  pmsId: FormControl<IRvpGuest['pmsId']>;
  firstName: FormControl<IRvpGuest['firstName']>;
  lastName: FormControl<IRvpGuest['lastName']>;
  language: FormControl<IRvpGuest['language']>;
  checkin: FormControl<IRvpGuest['checkin']>;
  checkout: FormControl<IRvpGuest['checkout']>;
  email: FormControl<IRvpGuest['email']>;
  emailAlt: FormControl<IRvpGuest['emailAlt']>;
  salutation: FormControl<IRvpGuest['salutation']>;
};

export type RvpGuestFormGroup = FormGroup<RvpGuestFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class RvpGuestFormService {
  createRvpGuestFormGroup(rvpGuest: RvpGuestFormGroupInput = { id: null }): RvpGuestFormGroup {
    const rvpGuestRawValue = {
      ...this.getFormDefaults(),
      ...rvpGuest,
    };
    return new FormGroup<RvpGuestFormGroupContent>({
      id: new FormControl(
        { value: rvpGuestRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      pmsId: new FormControl(rvpGuestRawValue.pmsId),
      firstName: new FormControl(rvpGuestRawValue.firstName),
      lastName: new FormControl(rvpGuestRawValue.lastName),
      language: new FormControl(rvpGuestRawValue.language),
      checkin: new FormControl(rvpGuestRawValue.checkin),
      checkout: new FormControl(rvpGuestRawValue.checkout),
      email: new FormControl(rvpGuestRawValue.email),
      emailAlt: new FormControl(rvpGuestRawValue.emailAlt),
      salutation: new FormControl(rvpGuestRawValue.salutation),
    });
  }

  getRvpGuest(form: RvpGuestFormGroup): IRvpGuest | NewRvpGuest {
    return form.getRawValue() as IRvpGuest | NewRvpGuest;
  }

  resetForm(form: RvpGuestFormGroup, rvpGuest: RvpGuestFormGroupInput): void {
    const rvpGuestRawValue = { ...this.getFormDefaults(), ...rvpGuest };
    form.reset(
      {
        ...rvpGuestRawValue,
        id: { value: rvpGuestRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): RvpGuestFormDefaults {
    return {
      id: null,
    };
  }
}
