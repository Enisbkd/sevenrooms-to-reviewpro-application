import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { ResPosTicketDetailComponent } from './res-pos-ticket-detail.component';

describe('ResPosTicket Management Detail Component', () => {
  let comp: ResPosTicketDetailComponent;
  let fixture: ComponentFixture<ResPosTicketDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ResPosTicketDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: ResPosTicketDetailComponent,
              resolve: { resPosTicket: () => of({ id: 'ABC' }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(ResPosTicketDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ResPosTicketDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load resPosTicket on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', ResPosTicketDetailComponent);

      // THEN
      expect(instance.resPosTicket()).toEqual(expect.objectContaining({ id: 'ABC' }));
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
