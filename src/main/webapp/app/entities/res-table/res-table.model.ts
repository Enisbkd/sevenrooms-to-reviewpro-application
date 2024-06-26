import { IReservation } from 'app/entities/reservation/reservation.model';

export interface IResTable {
  id: string;
  tableNumber?: string | null;
  reservation?: IReservation | null;
}

export type NewResTable = Omit<IResTable, 'id'> & { id: null };
