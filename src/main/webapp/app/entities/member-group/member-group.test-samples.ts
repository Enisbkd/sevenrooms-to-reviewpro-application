import { IMemberGroup, NewMemberGroup } from './member-group.model';

export const sampleWithRequiredData: IMemberGroup = {
  id: 'a5de0d2a-3d07-43b5-9173-1747c268b8e5',
};

export const sampleWithPartialData: IMemberGroup = {
  id: '324aa752-6d7c-45b9-9f56-6233952da6a1',
};

export const sampleWithFullData: IMemberGroup = {
  id: 'dd4b19c8-c67f-462f-8bd5-c5339ad62952',
};

export const sampleWithNewData: NewMemberGroup = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
