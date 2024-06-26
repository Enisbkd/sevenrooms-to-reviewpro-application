import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IVenue, NewVenue } from '../venue.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IVenue for edit and NewVenueFormGroupInput for create.
 */
type VenueFormGroupInput = IVenue | PartialWithRequiredKeyOf<NewVenue>;

type VenueFormDefaults = Pick<NewVenue, 'id' | 'fullDiningBackend' | 'gridEnabled' | 'membershipEnabled'>;

type VenueFormGroupContent = {
  id: FormControl<IVenue['id'] | NewVenue['id']>;
  address: FormControl<IVenue['address']>;
  blackLogo: FormControl<IVenue['blackLogo']>;
  country: FormControl<IVenue['country']>;
  crossStreet: FormControl<IVenue['crossStreet']>;
  currencyCode: FormControl<IVenue['currencyCode']>;
  externalVenueId: FormControl<IVenue['externalVenueId']>;
  fullDiningBackend: FormControl<IVenue['fullDiningBackend']>;
  gridEnabled: FormControl<IVenue['gridEnabled']>;
  venueId: FormControl<IVenue['venueId']>;
  internalName: FormControl<IVenue['internalName']>;
  membershipEnabled: FormControl<IVenue['membershipEnabled']>;
  name: FormControl<IVenue['name']>;
  neighborhood: FormControl<IVenue['neighborhood']>;
  phoneNumber: FormControl<IVenue['phoneNumber']>;
  policy: FormControl<IVenue['policy']>;
  postalCode: FormControl<IVenue['postalCode']>;
  primaryColor: FormControl<IVenue['primaryColor']>;
  secondaryColor: FormControl<IVenue['secondaryColor']>;
  state: FormControl<IVenue['state']>;
  uniqueConfirmationPrefix: FormControl<IVenue['uniqueConfirmationPrefix']>;
  venueClass: FormControl<IVenue['venueClass']>;
  venueGroupId: FormControl<IVenue['venueGroupId']>;
  venueGroupName: FormControl<IVenue['venueGroupName']>;
  venueUrlKey: FormControl<IVenue['venueUrlKey']>;
  website: FormControl<IVenue['website']>;
  whiteLogo: FormControl<IVenue['whiteLogo']>;
};

export type VenueFormGroup = FormGroup<VenueFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class VenueFormService {
  createVenueFormGroup(venue: VenueFormGroupInput = { id: null }): VenueFormGroup {
    const venueRawValue = {
      ...this.getFormDefaults(),
      ...venue,
    };
    return new FormGroup<VenueFormGroupContent>({
      id: new FormControl(
        { value: venueRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      address: new FormControl(venueRawValue.address),
      blackLogo: new FormControl(venueRawValue.blackLogo),
      country: new FormControl(venueRawValue.country),
      crossStreet: new FormControl(venueRawValue.crossStreet),
      currencyCode: new FormControl(venueRawValue.currencyCode),
      externalVenueId: new FormControl(venueRawValue.externalVenueId),
      fullDiningBackend: new FormControl(venueRawValue.fullDiningBackend),
      gridEnabled: new FormControl(venueRawValue.gridEnabled),
      venueId: new FormControl(venueRawValue.venueId),
      internalName: new FormControl(venueRawValue.internalName),
      membershipEnabled: new FormControl(venueRawValue.membershipEnabled),
      name: new FormControl(venueRawValue.name),
      neighborhood: new FormControl(venueRawValue.neighborhood),
      phoneNumber: new FormControl(venueRawValue.phoneNumber),
      policy: new FormControl(venueRawValue.policy),
      postalCode: new FormControl(venueRawValue.postalCode),
      primaryColor: new FormControl(venueRawValue.primaryColor),
      secondaryColor: new FormControl(venueRawValue.secondaryColor),
      state: new FormControl(venueRawValue.state),
      uniqueConfirmationPrefix: new FormControl(venueRawValue.uniqueConfirmationPrefix),
      venueClass: new FormControl(venueRawValue.venueClass),
      venueGroupId: new FormControl(venueRawValue.venueGroupId),
      venueGroupName: new FormControl(venueRawValue.venueGroupName),
      venueUrlKey: new FormControl(venueRawValue.venueUrlKey),
      website: new FormControl(venueRawValue.website),
      whiteLogo: new FormControl(venueRawValue.whiteLogo),
    });
  }

  getVenue(form: VenueFormGroup): IVenue | NewVenue {
    return form.getRawValue() as IVenue | NewVenue;
  }

  resetForm(form: VenueFormGroup, venue: VenueFormGroupInput): void {
    const venueRawValue = { ...this.getFormDefaults(), ...venue };
    form.reset(
      {
        ...venueRawValue,
        id: { value: venueRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): VenueFormDefaults {
    return {
      id: null,
      fullDiningBackend: false,
      gridEnabled: false,
      membershipEnabled: false,
    };
  }
}
