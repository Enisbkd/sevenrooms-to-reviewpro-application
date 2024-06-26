import { IClientTag, NewClientTag } from './client-tag.model';

export const sampleWithRequiredData: IClientTag = {
  id: '1c10d4cf-7a85-4869-a58b-37118fed84ba',
};

export const sampleWithPartialData: IClientTag = {
  id: '21c92120-2a79-424a-aada-4cda0915814b',
  tag: 'perch spit',
  tagDisplay: 'puzzled pier',
  color: 'gold',
};

export const sampleWithFullData: IClientTag = {
  id: '4e6a628f-cfd8-4666-a358-49af0439c655',
  tag: 'doubter spider only',
  tagDisplay: 'great-grandfather crossly',
  group: 'afore',
  groupDisplay: 'afore whose',
  color: 'azure',
  tagSearchQuery: 'gosh furthermore',
};

export const sampleWithNewData: NewClientTag = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
