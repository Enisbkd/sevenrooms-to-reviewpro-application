import { IClientVenueStats } from 'app/entities/client-venue-stats/client-venue-stats.model';

export interface IBookingName {
  id: string;
  name?: string | null;
  clientVenueStats?: IClientVenueStats | null;
}

export type NewBookingName = Omit<IBookingName, 'id'> & { id: null };
