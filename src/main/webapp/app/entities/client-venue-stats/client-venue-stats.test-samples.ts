import { IClientVenueStats, NewClientVenueStats } from './client-venue-stats.model';

export const sampleWithRequiredData: IClientVenueStats = {
  id: 'f7913d88-1cab-4c0c-9ff4-9dc05f582b7f',
};

export const sampleWithPartialData: IClientVenueStats = {
  id: 'a16f8263-f456-407e-a781-c4150fe96669',
  totalSpendLocalperCover: 2470.19,
  totalCancellations: 13315,
  avgRating: 24012,
  totalNoShows: 29208,
  numRatings: 28753,
  totalSpendPerVisit: 22887.1,
  totalSpendLocalPerVisit: 799.65,
  totalOrderCount: 26517.69,
  totalOrderCancellations: 182.9,
  totalOrderSpend: 618.05,
  totalSpendperOrder: 5455.77,
  venueId: 'salty barring',
  venueMarketingOptints: 'fooey',
};

export const sampleWithFullData: IClientVenueStats = {
  id: '473ac576-b653-45c3-9e84-5dc4558613d9',
  totalSpendLocalperCover: 12022.19,
  lastVisitDate: 'passing boohoo well',
  totalCancellations: 7852,
  totalCovers: 24553,
  avgRating: 18795,
  totalSpendperCover: 30626,
  totalSpend: 22769.59,
  totalNoShows: 12208,
  numRatings: 18538,
  totalSpendPerVisit: 24613.95,
  totalSpendLocal: 14638.93,
  totalSpendLocalPerVisit: 10884.31,
  totalVisits: 15098,
  grossTotal: 11258.09,
  totalOrderCount: 7142.57,
  totalOrderCancellations: 18497.57,
  totalOrderSpend: 23288.96,
  grossOrderTotal: 18031.7,
  totalOrderSpendLocal: 21312.94,
  lastOrderDate: 'rigidly',
  totalSpendperOrder: 14557.98,
  totalSpendLocalperOrder: 16875.29,
  venueId: 'weird gah round',
  venueMarketingOptin: true,
  venueMarketingOptints: 'fooey',
};

export const sampleWithNewData: NewClientVenueStats = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
