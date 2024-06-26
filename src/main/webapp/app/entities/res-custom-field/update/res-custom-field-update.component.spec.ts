import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideHttpClient, HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { IReservation } from 'app/entities/reservation/reservation.model';
import { ReservationService } from 'app/entities/reservation/service/reservation.service';
import { ResCustomFieldService } from '../service/res-custom-field.service';
import { IResCustomField } from '../res-custom-field.model';
import { ResCustomFieldFormService } from './res-custom-field-form.service';

import { ResCustomFieldUpdateComponent } from './res-custom-field-update.component';

describe('ResCustomField Management Update Component', () => {
  let comp: ResCustomFieldUpdateComponent;
  let fixture: ComponentFixture<ResCustomFieldUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let resCustomFieldFormService: ResCustomFieldFormService;
  let resCustomFieldService: ResCustomFieldService;
  let reservationService: ReservationService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ResCustomFieldUpdateComponent],
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
      .overrideTemplate(ResCustomFieldUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ResCustomFieldUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    resCustomFieldFormService = TestBed.inject(ResCustomFieldFormService);
    resCustomFieldService = TestBed.inject(ResCustomFieldService);
    reservationService = TestBed.inject(ReservationService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Reservation query and add missing value', () => {
      const resCustomField: IResCustomField = { id: 'CBA' };
      const reservation: IReservation = { id: 'f6947b31-4d6c-4057-ac1e-71fa45926a73' };
      resCustomField.reservation = reservation;

      const reservationCollection: IReservation[] = [{ id: '5d87c0df-06a1-4d99-aaec-8f20f412cefa' }];
      jest.spyOn(reservationService, 'query').mockReturnValue(of(new HttpResponse({ body: reservationCollection })));
      const additionalReservations = [reservation];
      const expectedCollection: IReservation[] = [...additionalReservations, ...reservationCollection];
      jest.spyOn(reservationService, 'addReservationToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ resCustomField });
      comp.ngOnInit();

      expect(reservationService.query).toHaveBeenCalled();
      expect(reservationService.addReservationToCollectionIfMissing).toHaveBeenCalledWith(
        reservationCollection,
        ...additionalReservations.map(expect.objectContaining),
      );
      expect(comp.reservationsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const resCustomField: IResCustomField = { id: 'CBA' };
      const reservation: IReservation = { id: '73988c12-9121-4e21-8fde-95a9415f9bbc' };
      resCustomField.reservation = reservation;

      activatedRoute.data = of({ resCustomField });
      comp.ngOnInit();

      expect(comp.reservationsSharedCollection).toContain(reservation);
      expect(comp.resCustomField).toEqual(resCustomField);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IResCustomField>>();
      const resCustomField = { id: 'ABC' };
      jest.spyOn(resCustomFieldFormService, 'getResCustomField').mockReturnValue(resCustomField);
      jest.spyOn(resCustomFieldService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ resCustomField });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: resCustomField }));
      saveSubject.complete();

      // THEN
      expect(resCustomFieldFormService.getResCustomField).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(resCustomFieldService.update).toHaveBeenCalledWith(expect.objectContaining(resCustomField));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IResCustomField>>();
      const resCustomField = { id: 'ABC' };
      jest.spyOn(resCustomFieldFormService, 'getResCustomField').mockReturnValue({ id: null });
      jest.spyOn(resCustomFieldService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ resCustomField: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: resCustomField }));
      saveSubject.complete();

      // THEN
      expect(resCustomFieldFormService.getResCustomField).toHaveBeenCalled();
      expect(resCustomFieldService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IResCustomField>>();
      const resCustomField = { id: 'ABC' };
      jest.spyOn(resCustomFieldService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ resCustomField });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(resCustomFieldService.update).toHaveBeenCalled();
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
