import { IResPosTicket } from 'app/entities/res-pos-ticket/res-pos-ticket.model';

export interface IResPosticketsItem {
  id: string;
  price?: number | null;
  name?: string | null;
  quantity?: number | null;
  resPosTicket?: IResPosTicket | null;
}

export type NewResPosticketsItem = Omit<IResPosticketsItem, 'id'> & { id: null };
