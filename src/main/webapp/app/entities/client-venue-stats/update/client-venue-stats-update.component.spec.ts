import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideHttpClient, HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { ClientVenueStatsService } from '../service/client-venue-stats.service';
import { IClientVenueStats } from '../client-venue-stats.model';
import { ClientVenueStatsFormService } from './client-venue-stats-form.service';

import { ClientVenueStatsUpdateComponent } from './client-venue-stats-update.component';

describe('ClientVenueStats Management Update Component', () => {
  let comp: ClientVenueStatsUpdateComponent;
  let fixture: ComponentFixture<ClientVenueStatsUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let clientVenueStatsFormService: ClientVenueStatsFormService;
  let clientVenueStatsService: ClientVenueStatsService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ClientVenueStatsUpdateComponent],
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
      .overrideTemplate(ClientVenueStatsUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ClientVenueStatsUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    clientVenueStatsFormService = TestBed.inject(ClientVenueStatsFormService);
    clientVenueStatsService = TestBed.inject(ClientVenueStatsService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const clientVenueStats: IClientVenueStats = { id: 'CBA' };

      activatedRoute.data = of({ clientVenueStats });
      comp.ngOnInit();

      expect(comp.clientVenueStats).toEqual(clientVenueStats);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IClientVenueStats>>();
      const clientVenueStats = { id: 'ABC' };
      jest.spyOn(clientVenueStatsFormService, 'getClientVenueStats').mockReturnValue(clientVenueStats);
      jest.spyOn(clientVenueStatsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ clientVenueStats });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: clientVenueStats }));
      saveSubject.complete();

      // THEN
      expect(clientVenueStatsFormService.getClientVenueStats).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(clientVenueStatsService.update).toHaveBeenCalledWith(expect.objectContaining(clientVenueStats));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IClientVenueStats>>();
      const clientVenueStats = { id: 'ABC' };
      jest.spyOn(clientVenueStatsFormService, 'getClientVenueStats').mockReturnValue({ id: null });
      jest.spyOn(clientVenueStatsService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ clientVenueStats: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: clientVenueStats }));
      saveSubject.complete();

      // THEN
      expect(clientVenueStatsFormService.getClientVenueStats).toHaveBeenCalled();
      expect(clientVenueStatsService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IClientVenueStats>>();
      const clientVenueStats = { id: 'ABC' };
      jest.spyOn(clientVenueStatsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ clientVenueStats });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(clientVenueStatsService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
