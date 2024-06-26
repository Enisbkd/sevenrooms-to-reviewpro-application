import { IResTag, NewResTag } from './res-tag.model';

export const sampleWithRequiredData: IResTag = {
  id: '530a9953-8e67-4195-b515-713709b75797',
};

export const sampleWithPartialData: IResTag = {
  id: 'b1631918-d3a0-4bcd-84b9-b6caa1b3493a',
  tag: 'until querulous',
  group: 'emcee',
  groupDisplay: 'how like',
  color: 'salmon',
  tagSearchQuery: 'around abnegate',
};

export const sampleWithFullData: IResTag = {
  id: '4dd88597-c836-4aae-a3c1-10dbc573ee48',
  tag: 'next',
  tagDisplay: 'coaxingly bitten',
  group: 'hardhat',
  groupDisplay: 'likewise coaxingly',
  color: 'black',
  tagSearchQuery: 'finally exterminate',
};

export const sampleWithNewData: NewResTag = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
