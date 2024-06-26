import { IResTable, NewResTable } from './res-table.model';

export const sampleWithRequiredData: IResTable = {
  id: '0aa5fc7f-bd77-4c20-8627-57af5ac5eb9f',
};

export const sampleWithPartialData: IResTable = {
  id: '8b303de3-c286-4bc5-afb4-caef8153f500',
  tableNumber: 'classic whenever politicise',
};

export const sampleWithFullData: IResTable = {
  id: 'c54bcfd9-0fd0-4a99-ac00-91ea06ccae8f',
  tableNumber: 'boohoo safely aw',
};

export const sampleWithNewData: NewResTable = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
