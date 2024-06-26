import { IBookingName, NewBookingName } from './booking-name.model';

export const sampleWithRequiredData: IBookingName = {
  id: '8e24dbd1-61d9-476f-b9b7-7d8dc3b9c916',
};

export const sampleWithPartialData: IBookingName = {
  id: 'e86fea20-2e23-4369-ae6f-e1b5a18bae99',
};

export const sampleWithFullData: IBookingName = {
  id: '71d727c6-100a-4baa-ad0e-a9388c3de98f',
  name: 'acidly',
};

export const sampleWithNewData: NewBookingName = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
