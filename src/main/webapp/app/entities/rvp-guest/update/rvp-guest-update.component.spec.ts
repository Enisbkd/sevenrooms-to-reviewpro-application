import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideHttpClient, HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { RvpGuestService } from '../service/rvp-guest.service';
import { IRvpGuest } from '../rvp-guest.model';
import { RvpGuestFormService } from './rvp-guest-form.service';

import { RvpGuestUpdateComponent } from './rvp-guest-update.component';

describe('RvpGuest Management Update Component', () => {
  let comp: RvpGuestUpdateComponent;
  let fixture: ComponentFixture<RvpGuestUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let rvpGuestFormService: RvpGuestFormService;
  let rvpGuestService: RvpGuestService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RvpGuestUpdateComponent],
      providers: [
        provideHttpClient(),
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(RvpGuestUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(RvpGuestUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    rvpGuestFormService = TestBed.inject(RvpGuestFormService);
    rvpGuestService = TestBed.inject(RvpGuestService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const rvpGuest: IRvpGuest = { id: 'CBA' };

      activatedRoute.data = of({ rvpGuest });
      comp.ngOnInit();

      expect(comp.rvpGuest).toEqual(rvpGuest);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRvpGuest>>();
      const rvpGuest = { id: 'ABC' };
      jest.spyOn(rvpGuestFormService, 'getRvpGuest').mockReturnValue(rvpGuest);
      jest.spyOn(rvpGuestService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ rvpGuest });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: rvpGuest }));
      saveSubject.complete();

      // THEN
      expect(rvpGuestFormService.getRvpGuest).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(rvpGuestService.update).toHaveBeenCalledWith(expect.objectContaining(rvpGuest));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRvpGuest>>();
      const rvpGuest = { id: 'ABC' };
      jest.spyOn(rvpGuestFormService, 'getRvpGuest').mockReturnValue({ id: null });
      jest.spyOn(rvpGuestService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ rvpGuest: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: rvpGuest }));
      saveSubject.complete();

      // THEN
      expect(rvpGuestFormService.getRvpGuest).toHaveBeenCalled();
      expect(rvpGuestService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRvpGuest>>();
      const rvpGuest = { id: 'ABC' };
      jest.spyOn(rvpGuestService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ rvpGuest });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(rvpGuestService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
