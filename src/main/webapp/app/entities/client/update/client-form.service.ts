import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IClient, NewClient } from '../client.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IClient for edit and NewClientFormGroupInput for create.
 */
type ClientFormGroupInput = IClient | PartialWithRequiredKeyOf<NewClient>;

type ClientFormDefaults = Pick<NewClient, 'id' | 'isContactPrivate' | 'isOnetimeGuest' | 'marketingOptin' | 'hasBillingProfile'>;

type ClientFormGroupContent = {
  id: FormControl<IClient['id'] | NewClient['id']>;
  clientId: FormControl<IClient['clientId']>;
  createdDate: FormControl<IClient['createdDate']>;
  updatedDate: FormControl<IClient['updatedDate']>;
  deletedDate: FormControl<IClient['deletedDate']>;
  lastname: FormControl<IClient['lastname']>;
  firstname: FormControl<IClient['firstname']>;
  gender: FormControl<IClient['gender']>;
  salutation: FormControl<IClient['salutation']>;
  title: FormControl<IClient['title']>;
  birthdayDay: FormControl<IClient['birthdayDay']>;
  birthdayMonth: FormControl<IClient['birthdayMonth']>;
  birthdayAltMonth: FormControl<IClient['birthdayAltMonth']>;
  anniversaryDay: FormControl<IClient['anniversaryDay']>;
  anniversaryMonth: FormControl<IClient['anniversaryMonth']>;
  company: FormControl<IClient['company']>;
  email: FormControl<IClient['email']>;
  emailAlt: FormControl<IClient['emailAlt']>;
  phoneNumber: FormControl<IClient['phoneNumber']>;
  phoneNumberlocale: FormControl<IClient['phoneNumberlocale']>;
  phoneNumberalt: FormControl<IClient['phoneNumberalt']>;
  phoneNumberaltlocale: FormControl<IClient['phoneNumberaltlocale']>;
  address: FormControl<IClient['address']>;
  address2: FormControl<IClient['address2']>;
  city: FormControl<IClient['city']>;
  postalCode: FormControl<IClient['postalCode']>;
  state: FormControl<IClient['state']>;
  country: FormControl<IClient['country']>;
  isContactPrivate: FormControl<IClient['isContactPrivate']>;
  isOnetimeGuest: FormControl<IClient['isOnetimeGuest']>;
  status: FormControl<IClient['status']>;
  loyaltyId: FormControl<IClient['loyaltyId']>;
  loyaltyRank: FormControl<IClient['loyaltyRank']>;
  loyaltyTier: FormControl<IClient['loyaltyTier']>;
  marketingOptin: FormControl<IClient['marketingOptin']>;
  marketingOptints: FormControl<IClient['marketingOptints']>;
  marketingOptOutts: FormControl<IClient['marketingOptOutts']>;
  hasBillingProfile: FormControl<IClient['hasBillingProfile']>;
  notes: FormControl<IClient['notes']>;
  privateNotes: FormControl<IClient['privateNotes']>;
  tags: FormControl<IClient['tags']>;
  totalVisits: FormControl<IClient['totalVisits']>;
  totalCovers: FormControl<IClient['totalCovers']>;
  totalCancellations: FormControl<IClient['totalCancellations']>;
  totalNoShows: FormControl<IClient['totalNoShows']>;
  totalSpend: FormControl<IClient['totalSpend']>;
  totalSpendPerCover: FormControl<IClient['totalSpendPerCover']>;
  totalspendPerVisit: FormControl<IClient['totalspendPerVisit']>;
  avgRating: FormControl<IClient['avgRating']>;
  referenceCode: FormControl<IClient['referenceCode']>;
  externalUserId: FormControl<IClient['externalUserId']>;
  venueGroupId: FormControl<IClient['venueGroupId']>;
  birthdayAltDay: FormControl<IClient['birthdayAltDay']>;
  userId: FormControl<IClient['userId']>;
  userName: FormControl<IClient['userName']>;
  totalOrderCount: FormControl<IClient['totalOrderCount']>;
  preferredLanguageCode: FormControl<IClient['preferredLanguageCode']>;
  clientPhoto: FormControl<IClient['clientPhoto']>;
  clientVenueStats: FormControl<IClient['clientVenueStats']>;
};

export type ClientFormGroup = FormGroup<ClientFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ClientFormService {
  createClientFormGroup(client: ClientFormGroupInput = { id: null }): ClientFormGroup {
    const clientRawValue = {
      ...this.getFormDefaults(),
      ...client,
    };
    return new FormGroup<ClientFormGroupContent>({
      id: new FormControl(
        { value: clientRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      clientId: new FormControl(clientRawValue.clientId),
      createdDate: new FormControl(clientRawValue.createdDate),
      updatedDate: new FormControl(clientRawValue.updatedDate),
      deletedDate: new FormControl(clientRawValue.deletedDate),
      lastname: new FormControl(clientRawValue.lastname),
      firstname: new FormControl(clientRawValue.firstname),
      gender: new FormControl(clientRawValue.gender),
      salutation: new FormControl(clientRawValue.salutation),
      title: new FormControl(clientRawValue.title),
      birthdayDay: new FormControl(clientRawValue.birthdayDay),
      birthdayMonth: new FormControl(clientRawValue.birthdayMonth),
      birthdayAltMonth: new FormControl(clientRawValue.birthdayAltMonth),
      anniversaryDay: new FormControl(clientRawValue.anniversaryDay),
      anniversaryMonth: new FormControl(clientRawValue.anniversaryMonth),
      company: new FormControl(clientRawValue.company),
      email: new FormControl(clientRawValue.email),
      emailAlt: new FormControl(clientRawValue.emailAlt),
      phoneNumber: new FormControl(clientRawValue.phoneNumber),
      phoneNumberlocale: new FormControl(clientRawValue.phoneNumberlocale),
      phoneNumberalt: new FormControl(clientRawValue.phoneNumberalt),
      phoneNumberaltlocale: new FormControl(clientRawValue.phoneNumberaltlocale),
      address: new FormControl(clientRawValue.address),
      address2: new FormControl(clientRawValue.address2),
      city: new FormControl(clientRawValue.city),
      postalCode: new FormControl(clientRawValue.postalCode),
      state: new FormControl(clientRawValue.state),
      country: new FormControl(clientRawValue.country),
      isContactPrivate: new FormControl(clientRawValue.isContactPrivate),
      isOnetimeGuest: new FormControl(clientRawValue.isOnetimeGuest),
      status: new FormControl(clientRawValue.status),
      loyaltyId: new FormControl(clientRawValue.loyaltyId),
      loyaltyRank: new FormControl(clientRawValue.loyaltyRank),
      loyaltyTier: new FormControl(clientRawValue.loyaltyTier),
      marketingOptin: new FormControl(clientRawValue.marketingOptin),
      marketingOptints: new FormControl(clientRawValue.marketingOptints),
      marketingOptOutts: new FormControl(clientRawValue.marketingOptOutts),
      hasBillingProfile: new FormControl(clientRawValue.hasBillingProfile),
      notes: new FormControl(clientRawValue.notes),
      privateNotes: new FormControl(clientRawValue.privateNotes),
      tags: new FormControl(clientRawValue.tags),
      totalVisits: new FormControl(clientRawValue.totalVisits),
      totalCovers: new FormControl(clientRawValue.totalCovers),
      totalCancellations: new FormControl(clientRawValue.totalCancellations),
      totalNoShows: new FormControl(clientRawValue.totalNoShows),
      totalSpend: new FormControl(clientRawValue.totalSpend),
      totalSpendPerCover: new FormControl(clientRawValue.totalSpendPerCover),
      totalspendPerVisit: new FormControl(clientRawValue.totalspendPerVisit),
      avgRating: new FormControl(clientRawValue.avgRating),
      referenceCode: new FormControl(clientRawValue.referenceCode),
      externalUserId: new FormControl(clientRawValue.externalUserId),
      venueGroupId: new FormControl(clientRawValue.venueGroupId),
      birthdayAltDay: new FormControl(clientRawValue.birthdayAltDay),
      userId: new FormControl(clientRawValue.userId),
      userName: new FormControl(clientRawValue.userName),
      totalOrderCount: new FormControl(clientRawValue.totalOrderCount),
      preferredLanguageCode: new FormControl(clientRawValue.preferredLanguageCode),
      clientPhoto: new FormControl(clientRawValue.clientPhoto),
      clientVenueStats: new FormControl(clientRawValue.clientVenueStats),
    });
  }

  getClient(form: ClientFormGroup): IClient | NewClient {
    return form.getRawValue() as IClient | NewClient;
  }

  resetForm(form: ClientFormGroup, client: ClientFormGroupInput): void {
    const clientRawValue = { ...this.getFormDefaults(), ...client };
    form.reset(
      {
        ...clientRawValue,
        id: { value: clientRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ClientFormDefaults {
    return {
      id: null,
      isContactPrivate: false,
      isOnetimeGuest: false,
      marketingOptin: false,
      hasBillingProfile: false,
    };
  }
}
