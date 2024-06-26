import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { ResTagDetailComponent } from './res-tag-detail.component';

describe('ResTag Management Detail Component', () => {
  let comp: ResTagDetailComponent;
  let fixture: ComponentFixture<ResTagDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ResTagDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: ResTagDetailComponent,
              resolve: { resTag: () => of({ id: 'ABC' }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(ResTagDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ResTagDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load resTag on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', ResTagDetailComponent);

      // THEN
      expect(instance.resTag()).toEqual(expect.objectContaining({ id: 'ABC' }));
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
