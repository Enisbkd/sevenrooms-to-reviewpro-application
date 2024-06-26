import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IClientVenueStats, NewClientVenueStats } from '../client-venue-stats.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IClientVenueStats for edit and NewClientVenueStatsFormGroupInput for create.
 */
type ClientVenueStatsFormGroupInput = IClientVenueStats | PartialWithRequiredKeyOf<NewClientVenueStats>;

type ClientVenueStatsFormDefaults = Pick<NewClientVenueStats, 'id' | 'venueMarketingOptin'>;

type ClientVenueStatsFormGroupContent = {
  id: FormControl<IClientVenueStats['id'] | NewClientVenueStats['id']>;
  totalSpendLocalperCover: FormControl<IClientVenueStats['totalSpendLocalperCover']>;
  lastVisitDate: FormControl<IClientVenueStats['lastVisitDate']>;
  totalCancellations: FormControl<IClientVenueStats['totalCancellations']>;
  totalCovers: FormControl<IClientVenueStats['totalCovers']>;
  avgRating: FormControl<IClientVenueStats['avgRating']>;
  totalSpendperCover: FormControl<IClientVenueStats['totalSpendperCover']>;
  totalSpend: FormControl<IClientVenueStats['totalSpend']>;
  totalNoShows: FormControl<IClientVenueStats['totalNoShows']>;
  numRatings: FormControl<IClientVenueStats['numRatings']>;
  totalSpendPerVisit: FormControl<IClientVenueStats['totalSpendPerVisit']>;
  totalSpendLocal: FormControl<IClientVenueStats['totalSpendLocal']>;
  totalSpendLocalPerVisit: FormControl<IClientVenueStats['totalSpendLocalPerVisit']>;
  totalVisits: FormControl<IClientVenueStats['totalVisits']>;
  grossTotal: FormControl<IClientVenueStats['grossTotal']>;
  totalOrderCount: FormControl<IClientVenueStats['totalOrderCount']>;
  totalOrderCancellations: FormControl<IClientVenueStats['totalOrderCancellations']>;
  totalOrderSpend: FormControl<IClientVenueStats['totalOrderSpend']>;
  grossOrderTotal: FormControl<IClientVenueStats['grossOrderTotal']>;
  totalOrderSpendLocal: FormControl<IClientVenueStats['totalOrderSpendLocal']>;
  lastOrderDate: FormControl<IClientVenueStats['lastOrderDate']>;
  totalSpendperOrder: FormControl<IClientVenueStats['totalSpendperOrder']>;
  totalSpendLocalperOrder: FormControl<IClientVenueStats['totalSpendLocalperOrder']>;
  venueId: FormControl<IClientVenueStats['venueId']>;
  venueMarketingOptin: FormControl<IClientVenueStats['venueMarketingOptin']>;
  venueMarketingOptints: FormControl<IClientVenueStats['venueMarketingOptints']>;
};

export type ClientVenueStatsFormGroup = FormGroup<ClientVenueStatsFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ClientVenueStatsFormService {
  createClientVenueStatsFormGroup(clientVenueStats: ClientVenueStatsFormGroupInput = { id: null }): ClientVenueStatsFormGroup {
    const clientVenueStatsRawValue = {
      ...this.getFormDefaults(),
      ...clientVenueStats,
    };
    return new FormGroup<ClientVenueStatsFormGroupContent>({
      id: new FormControl(
        { value: clientVenueStatsRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      totalSpendLocalperCover: new FormControl(clientVenueStatsRawValue.totalSpendLocalperCover),
      lastVisitDate: new FormControl(clientVenueStatsRawValue.lastVisitDate),
      totalCancellations: new FormControl(clientVenueStatsRawValue.totalCancellations),
      totalCovers: new FormControl(clientVenueStatsRawValue.totalCovers),
      avgRating: new FormControl(clientVenueStatsRawValue.avgRating),
      totalSpendperCover: new FormControl(clientVenueStatsRawValue.totalSpendperCover),
      totalSpend: new FormControl(clientVenueStatsRawValue.totalSpend),
      totalNoShows: new FormControl(clientVenueStatsRawValue.totalNoShows),
      numRatings: new FormControl(clientVenueStatsRawValue.numRatings),
      totalSpendPerVisit: new FormControl(clientVenueStatsRawValue.totalSpendPerVisit),
      totalSpendLocal: new FormControl(clientVenueStatsRawValue.totalSpendLocal),
      totalSpendLocalPerVisit: new FormControl(clientVenueStatsRawValue.totalSpendLocalPerVisit),
      totalVisits: new FormControl(clientVenueStatsRawValue.totalVisits),
      grossTotal: new FormControl(clientVenueStatsRawValue.grossTotal),
      totalOrderCount: new FormControl(clientVenueStatsRawValue.totalOrderCount),
      totalOrderCancellations: new FormControl(clientVenueStatsRawValue.totalOrderCancellations),
      totalOrderSpend: new FormControl(clientVenueStatsRawValue.totalOrderSpend),
      grossOrderTotal: new FormControl(clientVenueStatsRawValue.grossOrderTotal),
      totalOrderSpendLocal: new FormControl(clientVenueStatsRawValue.totalOrderSpendLocal),
      lastOrderDate: new FormControl(clientVenueStatsRawValue.lastOrderDate),
      totalSpendperOrder: new FormControl(clientVenueStatsRawValue.totalSpendperOrder),
      totalSpendLocalperOrder: new FormControl(clientVenueStatsRawValue.totalSpendLocalperOrder),
      venueId: new FormControl(clientVenueStatsRawValue.venueId),
      venueMarketingOptin: new FormControl(clientVenueStatsRawValue.venueMarketingOptin),
      venueMarketingOptints: new FormControl(clientVenueStatsRawValue.venueMarketingOptints),
    });
  }

  getClientVenueStats(form: ClientVenueStatsFormGroup): IClientVenueStats | NewClientVenueStats {
    return form.getRawValue() as IClientVenueStats | NewClientVenueStats;
  }

  resetForm(form: ClientVenueStatsFormGroup, clientVenueStats: ClientVenueStatsFormGroupInput): void {
    const clientVenueStatsRawValue = { ...this.getFormDefaults(), ...clientVenueStats };
    form.reset(
      {
        ...clientVenueStatsRawValue,
        id: { value: clientVenueStatsRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): ClientVenueStatsFormDefaults {
    return {
      id: null,
      venueMarketingOptin: false,
    };
  }
}
