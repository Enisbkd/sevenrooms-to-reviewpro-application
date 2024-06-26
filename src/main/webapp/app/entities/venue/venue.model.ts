export interface IVenue {
  id: string;
  address?: string | null;
  blackLogo?: string | null;
  country?: string | null;
  crossStreet?: string | null;
  currencyCode?: string | null;
  externalVenueId?: string | null;
  fullDiningBackend?: boolean | null;
  gridEnabled?: boolean | null;
  venueId?: string | null;
  internalName?: string | null;
  membershipEnabled?: boolean | null;
  name?: string | null;
  neighborhood?: string | null;
  phoneNumber?: string | null;
  policy?: string | null;
  postalCode?: string | null;
  primaryColor?: string | null;
  secondaryColor?: string | null;
  state?: string | null;
  uniqueConfirmationPrefix?: string | null;
  venueClass?: string | null;
  venueGroupId?: string | null;
  venueGroupName?: string | null;
  venueUrlKey?: string | null;
  website?: string | null;
  whiteLogo?: string | null;
}

export type NewVenue = Omit<IVenue, 'id'> & { id: null };
