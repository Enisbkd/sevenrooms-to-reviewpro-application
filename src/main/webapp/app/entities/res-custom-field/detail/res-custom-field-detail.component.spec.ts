import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { ResCustomFieldDetailComponent } from './res-custom-field-detail.component';

describe('ResCustomField Management Detail Component', () => {
  let comp: ResCustomFieldDetailComponent;
  let fixture: ComponentFixture<ResCustomFieldDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ResCustomFieldDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: ResCustomFieldDetailComponent,
              resolve: { resCustomField: () => of({ id: 'ABC' }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(ResCustomFieldDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ResCustomFieldDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load resCustomField on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', ResCustomFieldDetailComponent);

      // THEN
      expect(instance.resCustomField()).toEqual(expect.objectContaining({ id: 'ABC' }));
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
