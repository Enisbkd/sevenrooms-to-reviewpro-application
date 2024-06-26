import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { RvpGuestDetailComponent } from './rvp-guest-detail.component';

describe('RvpGuest Management Detail Component', () => {
  let comp: RvpGuestDetailComponent;
  let fixture: ComponentFixture<RvpGuestDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RvpGuestDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: RvpGuestDetailComponent,
              resolve: { rvpGuest: () => of({ id: 'ABC' }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(RvpGuestDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RvpGuestDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load rvpGuest on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', RvpGuestDetailComponent);

      // THEN
      expect(instance.rvpGuest()).toEqual(expect.objectContaining({ id: 'ABC' }));
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
