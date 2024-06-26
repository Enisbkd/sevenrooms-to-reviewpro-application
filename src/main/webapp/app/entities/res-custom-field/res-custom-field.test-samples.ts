import { IResCustomField, NewResCustomField } from './res-custom-field.model';

export const sampleWithRequiredData: IResCustomField = {
  id: '496bb765-aa77-4b6d-af30-a479aa20bff6',
};

export const sampleWithPartialData: IResCustomField = {
  id: '12c5aa0b-4201-4688-8b37-88205ca11226',
  systemName: 'leaflet loan',
  value: 'drat',
};

export const sampleWithFullData: IResCustomField = {
  id: 'f28de269-b396-4870-8b57-4f313d668be5',
  systemName: 'finally',
  displayOrder: 7088,
  name: 'productive phooey lever',
  value: 'few',
};

export const sampleWithNewData: NewResCustomField = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
