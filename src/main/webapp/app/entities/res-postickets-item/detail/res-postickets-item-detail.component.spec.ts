import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { ResPosticketsItemDetailComponent } from './res-postickets-item-detail.component';

describe('ResPosticketsItem Management Detail Component', () => {
  let comp: ResPosticketsItemDetailComponent;
  let fixture: ComponentFixture<ResPosticketsItemDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ResPosticketsItemDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: ResPosticketsItemDetailComponent,
              resolve: { resPosticketsItem: () => of({ id: 'ABC' }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(ResPosticketsItemDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ResPosticketsItemDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load resPosticketsItem on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', ResPosticketsItemDetailComponent);

      // THEN
      expect(instance.resPosticketsItem()).toEqual(expect.objectContaining({ id: 'ABC' }));
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
