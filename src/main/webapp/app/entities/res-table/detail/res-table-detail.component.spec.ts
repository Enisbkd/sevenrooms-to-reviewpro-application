import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { ResTableDetailComponent } from './res-table-detail.component';

describe('ResTable Management Detail Component', () => {
  let comp: ResTableDetailComponent;
  let fixture: ComponentFixture<ResTableDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ResTableDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: ResTableDetailComponent,
              resolve: { resTable: () => of({ id: 'ABC' }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(ResTableDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ResTableDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load resTable on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', ResTableDetailComponent);

      // THEN
      expect(instance.resTable()).toEqual(expect.objectContaining({ id: 'ABC' }));
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
