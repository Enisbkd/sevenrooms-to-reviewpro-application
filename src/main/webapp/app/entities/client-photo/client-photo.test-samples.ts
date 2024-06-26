import { IClientPhoto, NewClientPhoto } from './client-photo.model';

export const sampleWithRequiredData: IClientPhoto = {
  id: 'b04147b4-c00b-4775-8753-5b5f02421044',
};

export const sampleWithPartialData: IClientPhoto = {
  id: 'd3338a21-4f15-4661-bf18-4dd373d6e703',
  largeWidth: 22824,
  mediumHeight: 6054,
  small: 'hm urgently',
  cropWidth: 15012.58,
};

export const sampleWithFullData: IClientPhoto = {
  id: 'f7d3317a-241a-45d5-89ee-a960133c402a',
  large: 'supposing barring whether',
  largeHeight: 30172,
  largeWidth: 2270,
  medium: 'jab',
  mediumHeight: 16728,
  mediumWidth: 4324,
  small: 'knottily',
  smallHeight: 29340,
  smallWidth: 13507,
  raw: 'forfend vice',
  cropx: 4225,
  cropy: 16907,
  cropHeight: 20658.09,
  cropWidth: 16214.15,
};

export const sampleWithNewData: NewClientPhoto = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
