import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IBookingName, NewBookingName } from '../booking-name.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IBookingName for edit and NewBookingNameFormGroupInput for create.
 */
type BookingNameFormGroupInput = IBookingName | PartialWithRequiredKeyOf<NewBookingName>;

type BookingNameFormDefaults = Pick<NewBookingName, 'id'>;

type BookingNameFormGroupContent = {
  id: FormControl<IBookingName['id'] | NewBookingName['id']>;
  name: FormControl<IBookingName['name']>;
  clientVenueStats: FormControl<IBookingName['clientVenueStats']>;
};

export type BookingNameFormGroup = FormGroup<BookingNameFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class BookingNameFormService {
  createBookingNameFormGroup(bookingName: BookingNameFormGroupInput = { id: null }): BookingNameFormGroup {
    const bookingNameRawValue = {
      ...this.getFormDefaults(),
      ...bookingName,
    };
    return new FormGroup<BookingNameFormGroupContent>({
      id: new FormControl(
        { value: bookingNameRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      name: new FormControl(bookingNameRawValue.name),
      clientVenueStats: new FormControl(bookingNameRawValue.clientVenueStats),
    });
  }

  getBookingName(form: BookingNameFormGroup): IBookingName | NewBookingName {
    return form.getRawValue() as IBookingName | NewBookingName;
  }

  resetForm(form: BookingNameFormGroup, bookingName: BookingNameFormGroupInput): void {
    const bookingNameRawValue = { ...this.getFormDefaults(), ...bookingName };
    form.reset(
      {
        ...bookingNameRawValue,
        id: { value: bookingNameRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): BookingNameFormDefaults {
    return {
      id: null,
    };
  }
}
