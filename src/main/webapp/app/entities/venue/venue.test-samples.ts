import { IVenue, NewVenue } from './venue.model';

export const sampleWithRequiredData: IVenue = {
  id: 'd12ca8cc-30bd-4260-9ae0-d056640d6264',
};

export const sampleWithPartialData: IVenue = {
  id: '1389bc6f-f29c-4c4e-a99c-6aed3e4ee982',
  country: 'Switzerland',
  currencyCode: 'CVE',
  membershipEnabled: true,
  neighborhood: 'athwart because gosh',
  phoneNumber: 'smug er calculating',
  policy: 'menopause wearily beyond',
  state: 'while supposing choosing',
  uniqueConfirmationPrefix: 'sink',
  venueClass: 'limp pardon lest',
  venueGroupName: 'footwear ugh',
  venueUrlKey: 'cloudy provided',
};

export const sampleWithFullData: IVenue = {
  id: '96ce5ee7-f33a-43ec-8fe2-8e240d9d96a6',
  address: 'oh level',
  blackLogo: 'self-assured overrun',
  country: 'Mexico',
  crossStreet: 'gear',
  currencyCode: 'SHP',
  externalVenueId: 'instantly officially fancy',
  fullDiningBackend: false,
  gridEnabled: false,
  venueId: 'if',
  internalName: 'tank uncomfortable',
  membershipEnabled: true,
  name: 'bare neatly',
  neighborhood: 'consequently',
  phoneNumber: 'boohoo whoever yowza',
  policy: 'forbear coast',
  postalCode: 'cartwheel gosh enlightened',
  primaryColor: 'enervate jealously',
  secondaryColor: 'ouch pfft but',
  state: 'afterwards demobilise research',
  uniqueConfirmationPrefix: 'beside round',
  venueClass: 'when active a',
  venueGroupId: 'deck',
  venueGroupName: 'zowie supposing',
  venueUrlKey: 'leafy',
  website: 'brr',
  whiteLogo: 'mmm',
};

export const sampleWithNewData: NewVenue = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
