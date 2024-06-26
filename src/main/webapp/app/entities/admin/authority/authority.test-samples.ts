import { IAuthority, NewAuthority } from './authority.model';

export const sampleWithRequiredData: IAuthority = {
  name: '301528af-dd95-4a80-8b67-3801670ad088',
};

export const sampleWithPartialData: IAuthority = {
  name: '7247fa89-2c72-426c-8be3-5a5acbba3630',
};

export const sampleWithFullData: IAuthority = {
  name: '4dee1788-98a4-4a47-a87d-361361a07b69',
};

export const sampleWithNewData: NewAuthority = {
  name: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
