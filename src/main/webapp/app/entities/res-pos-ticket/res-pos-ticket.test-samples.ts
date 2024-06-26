import { IResPosTicket, NewResPosTicket } from './res-pos-ticket.model';

export const sampleWithRequiredData: IResPosTicket = {
  id: '11e91468-f756-4adc-8f58-78f062f0abbe',
};

export const sampleWithPartialData: IResPosTicket = {
  id: '4f173186-1339-4e5e-90a6-aa2eafd7a3a8',
  adminFee: 14724.95,
  code: 22772,
  tax: 21473.52,
  businessId: 22151,
  total: 20804.71,
  startTime: 'patiently',
};

export const sampleWithFullData: IResPosTicket = {
  id: 'c42cf87e-1e08-4cb2-a2cc-2dca30f70296',
  status: 'hmph independence',
  adminFee: 18671.36,
  code: 32494,
  tableNo: 'yet',
  tax: 8227.53,
  businessId: 5511,
  ticketId: 5895,
  localPosticketId: 'even omelet instrument',
  employeeName: 'whose',
  total: 10276.87,
  subtotal: 28717.39,
  startTime: 'save brr wherever',
  serviceCharge: 27340.47,
  endtime: 'apropos meh till',
};

export const sampleWithNewData: NewResPosTicket = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
