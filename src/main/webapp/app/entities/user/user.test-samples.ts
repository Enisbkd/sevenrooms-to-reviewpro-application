import { IUser } from './user.model';

export const sampleWithRequiredData: IUser = {
  id: '2d39fdbc-9234-4a9f-9255-20740386b5fa',
  login: '_@3e\\KBSd3Hl\\=Pc\\(s\\)k',
};

export const sampleWithPartialData: IUser = {
  id: 'bba93f72-93f4-4456-858f-ff3b3a50db89',
  login: 'v3r',
};

export const sampleWithFullData: IUser = {
  id: '5b582e41-f775-41bd-bee2-c04e00216aec',
  login: 'PT!Zw{@13QDve\\:7IfN',
};
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
