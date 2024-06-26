import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { ClientVenueStatsDetailComponent } from './client-venue-stats-detail.component';

describe('ClientVenueStats Management Detail Component', () => {
  let comp: ClientVenueStatsDetailComponent;
  let fixture: ComponentFixture<ClientVenueStatsDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ClientVenueStatsDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: ClientVenueStatsDetailComponent,
              resolve: { clientVenueStats: () => of({ id: 'ABC' }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(ClientVenueStatsDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientVenueStatsDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load clientVenueStats on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', ClientVenueStatsDetailComponent);

      // THEN
      expect(instance.clientVenueStats()).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });

  describe('PreviousState', () => {
    it('Should navigate to previous state', () => {
      jest.spyOn(window.history, 'back');
      comp.previousState();
      expect(window.history.back).toHaveBeenCalled();
    });
  });
});
