import { IReservation } from 'app/entities/reservation/reservation.model';

export interface IResPosTicket {
  id: string;
  status?: string | null;
  adminFee?: number | null;
  code?: number | null;
  tableNo?: string | null;
  tax?: number | null;
  businessId?: number | null;
  ticketId?: number | null;
  localPosticketId?: string | null;
  employeeName?: string | null;
  total?: number | null;
  subtotal?: number | null;
  startTime?: string | null;
  serviceCharge?: number | null;
  endtime?: string | null;
  reservation?: IReservation | null;
}

export type NewResPosTicket = Omit<IResPosTicket, 'id'> & { id: null };
