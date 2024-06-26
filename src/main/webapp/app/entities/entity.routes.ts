import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'authority',
    data: { pageTitle: 'sevenRoomsToReviewProApplicationApp.adminAuthority.home.title' },
    loadChildren: () => import('./admin/authority/authority.routes'),
  },
  {
    path: 'rvp-guest',
    data: { pageTitle: 'sevenRoomsToReviewProApplicationApp.rvpGuest.home.title' },
    loadChildren: () => import('./rvp-guest/rvp-guest.routes'),
  },
  {
    path: 'client',
    data: { pageTitle: 'sevenRoomsToReviewProApplicationApp.client.home.title' },
    loadChildren: () => import('./client/client.routes'),
  },
  {
    path: 'member-group',
    data: { pageTitle: 'sevenRoomsToReviewProApplicationApp.memberGroup.home.title' },
    loadChildren: () => import('./member-group/member-group.routes'),
  },
  {
    path: 'client-tag',
    data: { pageTitle: 'sevenRoomsToReviewProApplicationApp.clientTag.home.title' },
    loadChildren: () => import('./client-tag/client-tag.routes'),
  },
  {
    path: 'custom-field',
    data: { pageTitle: 'sevenRoomsToReviewProApplicationApp.customField.home.title' },
    loadChildren: () => import('./custom-field/custom-field.routes'),
  },
  {
    path: 'client-venue-stats',
    data: { pageTitle: 'sevenRoomsToReviewProApplicationApp.clientVenueStats.home.title' },
    loadChildren: () => import('./client-venue-stats/client-venue-stats.routes'),
  },
  {
    path: 'booking-name',
    data: { pageTitle: 'sevenRoomsToReviewProApplicationApp.bookingName.home.title' },
    loadChildren: () => import('./booking-name/booking-name.routes'),
  },
  {
    path: 'client-photo',
    data: { pageTitle: 'sevenRoomsToReviewProApplicationApp.clientPhoto.home.title' },
    loadChildren: () => import('./client-photo/client-photo.routes'),
  },
  {
    path: 'reservation',
    data: { pageTitle: 'sevenRoomsToReviewProApplicationApp.reservation.home.title' },
    loadChildren: () => import('./reservation/reservation.routes'),
  },
  {
    path: 'res-custom-field',
    data: { pageTitle: 'sevenRoomsToReviewProApplicationApp.resCustomField.home.title' },
    loadChildren: () => import('./res-custom-field/res-custom-field.routes'),
  },
  {
    path: 'res-pos-ticket',
    data: { pageTitle: 'sevenRoomsToReviewProApplicationApp.resPosTicket.home.title' },
    loadChildren: () => import('./res-pos-ticket/res-pos-ticket.routes'),
  },
  {
    path: 'res-postickets-item',
    data: { pageTitle: 'sevenRoomsToReviewProApplicationApp.resPosticketsItem.home.title' },
    loadChildren: () => import('./res-postickets-item/res-postickets-item.routes'),
  },
  {
    path: 'res-tag',
    data: { pageTitle: 'sevenRoomsToReviewProApplicationApp.resTag.home.title' },
    loadChildren: () => import('./res-tag/res-tag.routes'),
  },
  {
    path: 'res-table',
    data: { pageTitle: 'sevenRoomsToReviewProApplicationApp.resTable.home.title' },
    loadChildren: () => import('./res-table/res-table.routes'),
  },
  {
    path: 'venue',
    data: { pageTitle: 'sevenRoomsToReviewProApplicationApp.venue.home.title' },
    loadChildren: () => import('./venue/venue.routes'),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;
