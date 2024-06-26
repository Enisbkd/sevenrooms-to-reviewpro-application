import { IResPosticketsItem, NewResPosticketsItem } from './res-postickets-item.model';

export const sampleWithRequiredData: IResPosticketsItem = {
  id: '2a18e3a6-4499-4dcb-ae7f-abef05a8ccd5',
};

export const sampleWithPartialData: IResPosticketsItem = {
  id: '78d0e404-2421-412f-96cd-28fea2c25fc9',
  quantity: 13101,
};

export const sampleWithFullData: IResPosticketsItem = {
  id: '4b60a293-3d4f-46bc-bca8-5427238cc326',
  price: 29869.16,
  name: 'cordon eek',
  quantity: 8139,
};

export const sampleWithNewData: NewResPosticketsItem = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
