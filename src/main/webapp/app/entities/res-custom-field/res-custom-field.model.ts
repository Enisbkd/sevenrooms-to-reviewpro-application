import { IReservation } from 'app/entities/reservation/reservation.model';

export interface IResCustomField {
  id: string;
  systemName?: string | null;
  displayOrder?: number | null;
  name?: string | null;
  value?: string | null;
  reservation?: IReservation | null;
}

export type NewResCustomField = Omit<IResCustomField, 'id'> & { id: null };
