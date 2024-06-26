export interface IClientVenueStats {
  id: string;
  totalSpendLocalperCover?: number | null;
  lastVisitDate?: string | null;
  totalCancellations?: number | null;
  totalCovers?: number | null;
  avgRating?: number | null;
  totalSpendperCover?: number | null;
  totalSpend?: number | null;
  totalNoShows?: number | null;
  numRatings?: number | null;
  totalSpendPerVisit?: number | null;
  totalSpendLocal?: number | null;
  totalSpendLocalPerVisit?: number | null;
  totalVisits?: number | null;
  grossTotal?: number | null;
  totalOrderCount?: number | null;
  totalOrderCancellations?: number | null;
  totalOrderSpend?: number | null;
  grossOrderTotal?: number | null;
  totalOrderSpendLocal?: number | null;
  lastOrderDate?: string | null;
  totalSpendperOrder?: number | null;
  totalSpendLocalperOrder?: number | null;
  venueId?: string | null;
  venueMarketingOptin?: boolean | null;
  venueMarketingOptints?: string | null;
}

export type NewClientVenueStats = Omit<IClientVenueStats, 'id'> & { id: null };
