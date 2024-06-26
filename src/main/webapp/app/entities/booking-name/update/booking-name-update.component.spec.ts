import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideHttpClient, HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { IClientVenueStats } from 'app/entities/client-venue-stats/client-venue-stats.model';
import { ClientVenueStatsService } from 'app/entities/client-venue-stats/service/client-venue-stats.service';
import { BookingNameService } from '../service/booking-name.service';
import { IBookingName } from '../booking-name.model';
import { BookingNameFormService } from './booking-name-form.service';

import { BookingNameUpdateComponent } from './booking-name-update.component';

describe('BookingName Management Update Component', () => {
  let comp: BookingNameUpdateComponent;
  let fixture: ComponentFixture<BookingNameUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let bookingNameFormService: BookingNameFormService;
  let bookingNameService: BookingNameService;
  let clientVenueStatsService: ClientVenueStatsService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [BookingNameUpdateComponent],
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
      .overrideTemplate(BookingNameUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(BookingNameUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    bookingNameFormService = TestBed.inject(BookingNameFormService);
    bookingNameService = TestBed.inject(BookingNameService);
    clientVenueStatsService = TestBed.inject(ClientVenueStatsService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call ClientVenueStats query and add missing value', () => {
      const bookingName: IBookingName = { id: 'CBA' };
      const clientVenueStats: IClientVenueStats = { id: 'ffbcb340-ae56-4d03-90bd-bdf115343183' };
      bookingName.clientVenueStats = clientVenueStats;

      const clientVenueStatsCollection: IClientVenueStats[] = [{ id: 'cf67fdf2-6775-49cb-abfe-574042aa812a' }];
      jest.spyOn(clientVenueStatsService, 'query').mockReturnValue(of(new HttpResponse({ body: clientVenueStatsCollection })));
      const additionalClientVenueStats = [clientVenueStats];
      const expectedCollection: IClientVenueStats[] = [...additionalClientVenueStats, ...clientVenueStatsCollection];
      jest.spyOn(clientVenueStatsService, 'addClientVenueStatsToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ bookingName });
      comp.ngOnInit();

      expect(clientVenueStatsService.query).toHaveBeenCalled();
      expect(clientVenueStatsService.addClientVenueStatsToCollectionIfMissing).toHaveBeenCalledWith(
        clientVenueStatsCollection,
        ...additionalClientVenueStats.map(expect.objectContaining),
      );
      expect(comp.clientVenueStatsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const bookingName: IBookingName = { id: 'CBA' };
      const clientVenueStats: IClientVenueStats = { id: '83f90d41-44ec-4436-8ded-b6b521086742' };
      bookingName.clientVenueStats = clientVenueStats;

      activatedRoute.data = of({ bookingName });
      comp.ngOnInit();

      expect(comp.clientVenueStatsSharedCollection).toContain(clientVenueStats);
      expect(comp.bookingName).toEqual(bookingName);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBookingName>>();
      const bookingName = { id: 'ABC' };
      jest.spyOn(bookingNameFormService, 'getBookingName').mockReturnValue(bookingName);
      jest.spyOn(bookingNameService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ bookingName });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: bookingName }));
      saveSubject.complete();

      // THEN
      expect(bookingNameFormService.getBookingName).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(bookingNameService.update).toHaveBeenCalledWith(expect.objectContaining(bookingName));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBookingName>>();
      const bookingName = { id: 'ABC' };
      jest.spyOn(bookingNameFormService, 'getBookingName').mockReturnValue({ id: null });
      jest.spyOn(bookingNameService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ bookingName: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: bookingName }));
      saveSubject.complete();

      // THEN
      expect(bookingNameFormService.getBookingName).toHaveBeenCalled();
      expect(bookingNameService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBookingName>>();
      const bookingName = { id: 'ABC' };
      jest.spyOn(bookingNameService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ bookingName });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(bookingNameService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareClientVenueStats', () => {
      it('Should forward to clientVenueStatsService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(clientVenueStatsService, 'compareClientVenueStats');
        comp.compareClientVenueStats(entity, entity2);
        expect(clientVenueStatsService.compareClientVenueStats).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
