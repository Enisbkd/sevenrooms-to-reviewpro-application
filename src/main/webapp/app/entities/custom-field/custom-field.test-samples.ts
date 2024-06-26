import { ICustomField, NewCustomField } from './custom-field.model';

export const sampleWithRequiredData: ICustomField = {
  id: 'd7f92aeb-a1f9-485d-b226-737a142236b3',
};

export const sampleWithPartialData: ICustomField = {
  id: '57e5c1df-601a-4ee0-afa5-c775a676bd4b',
  systemName: 'um disorientate chap',
  value: 'jovially via',
};

export const sampleWithFullData: ICustomField = {
  id: 'b15fd28e-84d9-4ab2-88d9-85ca63d756aa',
  systemName: 'aha amongst querulous',
  displayOrder: 17103,
  name: 'whoever',
  value: 'versus phew',
};

export const sampleWithNewData: NewCustomField = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
