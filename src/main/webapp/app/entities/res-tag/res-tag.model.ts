import { IReservation } from 'app/entities/reservation/reservation.model';

export interface IResTag {
  id: string;
  tag?: string | null;
  tagDisplay?: string | null;
  group?: string | null;
  groupDisplay?: string | null;
  color?: string | null;
  tagSearchQuery?: string | null;
  reservation?: IReservation | null;
}

export type NewResTag = Omit<IResTag, 'id'> & { id: null };
