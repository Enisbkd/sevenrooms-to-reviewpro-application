import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideHttpClient, HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { IReservation } from 'app/entities/reservation/reservation.model';
import { ReservationService } from 'app/entities/reservation/service/reservation.service';
import { ResTagService } from '../service/res-tag.service';
import { IResTag } from '../res-tag.model';
import { ResTagFormService } from './res-tag-form.service';

import { ResTagUpdateComponent } from './res-tag-update.component';

describe('ResTag Management Update Component', () => {
  let comp: ResTagUpdateComponent;
  let fixture: ComponentFixture<ResTagUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let resTagFormService: ResTagFormService;
  let resTagService: ResTagService;
  let reservationService: ReservationService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ResTagUpdateComponent],
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
      .overrideTemplate(ResTagUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ResTagUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    resTagFormService = TestBed.inject(ResTagFormService);
    resTagService = TestBed.inject(ResTagService);
    reservationService = TestBed.inject(ReservationService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Reservation query and add missing value', () => {
      const resTag: IResTag = { id: 'CBA' };
      const reservation: IReservation = { id: 'ccee1f7c-26d5-40ca-9768-078c189a7d01' };
      resTag.reservation = reservation;

      const reservationCollection: IReservation[] = [{ id: 'f4287977-c712-40e9-a8ee-716edbc76b4e' }];
      jest.spyOn(reservationService, 'query').mockReturnValue(of(new HttpResponse({ body: reservationCollection })));
      const additionalReservations = [reservation];
      const expectedCollection: IReservation[] = [...additionalReservations, ...reservationCollection];
      jest.spyOn(reservationService, 'addReservationToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ resTag });
      comp.ngOnInit();

      expect(reservationService.query).toHaveBeenCalled();
      expect(reservationService.addReservationToCollectionIfMissing).toHaveBeenCalledWith(
        reservationCollection,
        ...additionalReservations.map(expect.objectContaining),
      );
      expect(comp.reservationsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const resTag: IResTag = { id: 'CBA' };
      const reservation: IReservation = { id: '5292b32e-4409-44d5-bbfe-0d8abebfb277' };
      resTag.reservation = reservation;

      activatedRoute.data = of({ resTag });
      comp.ngOnInit();

      expect(comp.reservationsSharedCollection).toContain(reservation);
      expect(comp.resTag).toEqual(resTag);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IResTag>>();
      const resTag = { id: 'ABC' };
      jest.spyOn(resTagFormService, 'getResTag').mockReturnValue(resTag);
      jest.spyOn(resTagService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ resTag });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: resTag }));
      saveSubject.complete();

      // THEN
      expect(resTagFormService.getResTag).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(resTagService.update).toHaveBeenCalledWith(expect.objectContaining(resTag));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IResTag>>();
      const resTag = { id: 'ABC' };
      jest.spyOn(resTagFormService, 'getResTag').mockReturnValue({ id: null });
      jest.spyOn(resTagService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ resTag: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: resTag }));
      saveSubject.complete();

      // THEN
      expect(resTagFormService.getResTag).toHaveBeenCalled();
      expect(resTagService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IResTag>>();
      const resTag = { id: 'ABC' };
      jest.spyOn(resTagService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ resTag });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(resTagService.update).toHaveBeenCalled();
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
