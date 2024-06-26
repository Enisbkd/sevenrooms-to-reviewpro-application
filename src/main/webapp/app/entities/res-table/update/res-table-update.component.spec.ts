import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideHttpClient, HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { IReservation } from 'app/entities/reservation/reservation.model';
import { ReservationService } from 'app/entities/reservation/service/reservation.service';
import { ResTableService } from '../service/res-table.service';
import { IResTable } from '../res-table.model';
import { ResTableFormService } from './res-table-form.service';

import { ResTableUpdateComponent } from './res-table-update.component';

describe('ResTable Management Update Component', () => {
  let comp: ResTableUpdateComponent;
  let fixture: ComponentFixture<ResTableUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let resTableFormService: ResTableFormService;
  let resTableService: ResTableService;
  let reservationService: ReservationService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ResTableUpdateComponent],
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
      .overrideTemplate(ResTableUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ResTableUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    resTableFormService = TestBed.inject(ResTableFormService);
    resTableService = TestBed.inject(ResTableService);
    reservationService = TestBed.inject(ReservationService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Reservation query and add missing value', () => {
      const resTable: IResTable = { id: 'CBA' };
      const reservation: IReservation = { id: '2696bbb2-ed98-41c7-b695-6675de9441de' };
      resTable.reservation = reservation;

      const reservationCollection: IReservation[] = [{ id: 'd303f9bf-4581-4c3c-8bf0-9ab49b19abdb' }];
      jest.spyOn(reservationService, 'query').mockReturnValue(of(new HttpResponse({ body: reservationCollection })));
      const additionalReservations = [reservation];
      const expectedCollection: IReservation[] = [...additionalReservations, ...reservationCollection];
      jest.spyOn(reservationService, 'addReservationToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ resTable });
      comp.ngOnInit();

      expect(reservationService.query).toHaveBeenCalled();
      expect(reservationService.addReservationToCollectionIfMissing).toHaveBeenCalledWith(
        reservationCollection,
        ...additionalReservations.map(expect.objectContaining),
      );
      expect(comp.reservationsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const resTable: IResTable = { id: 'CBA' };
      const reservation: IReservation = { id: '8e5c24dd-1afa-435f-8203-19dceb87eed0' };
      resTable.reservation = reservation;

      activatedRoute.data = of({ resTable });
      comp.ngOnInit();

      expect(comp.reservationsSharedCollection).toContain(reservation);
      expect(comp.resTable).toEqual(resTable);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IResTable>>();
      const resTable = { id: 'ABC' };
      jest.spyOn(resTableFormService, 'getResTable').mockReturnValue(resTable);
      jest.spyOn(resTableService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ resTable });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: resTable }));
      saveSubject.complete();

      // THEN
      expect(resTableFormService.getResTable).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(resTableService.update).toHaveBeenCalledWith(expect.objectContaining(resTable));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IResTable>>();
      const resTable = { id: 'ABC' };
      jest.spyOn(resTableFormService, 'getResTable').mockReturnValue({ id: null });
      jest.spyOn(resTableService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ resTable: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: resTable }));
      saveSubject.complete();

      // THEN
      expect(resTableFormService.getResTable).toHaveBeenCalled();
      expect(resTableService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IResTable>>();
      const resTable = { id: 'ABC' };
      jest.spyOn(resTableService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ resTable });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(resTableService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareReservation', () => {
      it('Should forward to reservationService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(reservationService, 'compareReservation');
        comp.compareReservation(entity, entity2);
        expect(reservationService.compareReservation).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
