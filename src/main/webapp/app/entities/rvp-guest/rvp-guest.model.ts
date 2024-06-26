import dayjs from 'dayjs/esm';

export interface IRvpGuest {
  id: string;
  pmsId?: string | null;
  firstName?: string | null;
  lastName?: string | null;
  language?: string | null;
  checkin?: dayjs.Dayjs | null;
  checkout?: dayjs.Dayjs | null;
  email?: string | null;
  emailAlt?: string | null;
  salutation?: string | null;
}

export type NewRvpGuest = Omit<IRvpGuest, 'id'> & { id: null };
