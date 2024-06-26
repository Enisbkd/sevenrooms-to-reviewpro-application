import dayjs from 'dayjs/esm';

import { IRvpGuest, NewRvpGuest } from './rvp-guest.model';

export const sampleWithRequiredData: IRvpGuest = {
  id: 'ebdd9da0-5746-431c-a764-48e7578afe0a',
};

export const sampleWithPartialData: IRvpGuest = {
  id: 'e6d71087-66a9-4735-8714-22fae5aa9e6a',
  pmsId: 'so designer',
  language: 'helpfully',
  emailAlt: 'where amid',
  salutation: 'boo cameo',
};

export const sampleWithFullData: IRvpGuest = {
  id: 'e7b3bbd4-839d-4618-80fd-1639b727a4d2',
  pmsId: 'bean tomorrow hand',
  firstName: 'Joany',
  lastName: 'Homenick',
  language: 'quietly absent gee',
  checkin: dayjs('2024-06-26'),
  checkout: dayjs('2024-06-25'),
  email: 'Ashlee52@hotmail.com',
  emailAlt: 'brr voluntarily',
  salutation: 'inwardly thicken snoopy',
};

export const sampleWithNewData: NewRvpGuest = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
