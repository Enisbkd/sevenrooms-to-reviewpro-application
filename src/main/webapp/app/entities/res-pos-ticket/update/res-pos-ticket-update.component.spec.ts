import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideHttpClient, HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { IReservation } from 'app/entities/reservation/reservation.model';
import { ReservationService } from 'app/entities/reservation/service/reservation.service';
import { ResPosTicketService } from '../service/res-pos-ticket.service';
import { IResPosTicket } from '../res-pos-ticket.model';
import { ResPosTicketFormService } from './res-pos-ticket-form.service';

import { ResPosTicketUpdateComponent } from './res-pos-ticket-update.component';

describe('ResPosTicket Management Update Component', () => {
  let comp: ResPosTicketUpdateComponent;
  let fixture: ComponentFixture<ResPosTicketUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let resPosTicketFormService: ResPosTicketFormService;
  let resPosTicketService: ResPosTicketService;
  let reservationService: ReservationService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ResPosTicketUpdateComponent],
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
      .overrideTemplate(ResPosTicketUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ResPosTicketUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    resPosTicketFormService = TestBed.inject(ResPosTicketFormService);
    resPosTicketService = TestBed.inject(ResPosTicketService);
    reservationService = TestBed.inject(ReservationService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Reservation query and add missing value', () => {
      const resPosTicket: IResPosTicket = { id: 'CBA' };
      const reservation: IReservation = { id: '51934250-aebc-4d7e-9e18-b0392a114fca' };
      resPosTicket.reservation = reservation;

      const reservationCollection: IReservation[] = [{ id: '6bf1a5f9-d6fa-4d11-930f-a1df11967790' }];
      jest.spyOn(reservationService, 'query').mockReturnValue(of(new HttpResponse({ body: reservationCollection })));
      const additionalReservations = [reservation];
      const expectedCollection: IReservation[] = [...additionalReservations, ...reservationCollection];
      jest.spyOn(reservationService, 'addReservationToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ resPosTicket });
      comp.ngOnInit();

      expect(reservationService.query).toHaveBeenCalled();
      expect(reservationService.addReservationToCollectionIfMissing).toHaveBeenCalledWith(
        reservationCollection,
        ...additionalReservations.map(expect.objectContaining),
      );
      expect(comp.reservationsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const resPosTicket: IResPosTicket = { id: 'CBA' };
      const reservation: IReservation = { id: 'b1960a5c-3a17-4927-a53f-739748565126' };
      resPosTicket.reservation = reservation;

      activatedRoute.data = of({ resPosTicket });
      comp.ngOnInit();

      expect(comp.reservationsSharedCollection).toContain(reservation);
      expect(comp.resPosTicket).toEqual(resPosTicket);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IResPosTicket>>();
      const resPosTicket = { id: 'ABC' };
      jest.spyOn(resPosTicketFormService, 'getResPosTicket').mockReturnValue(resPosTicket);
      jest.spyOn(resPosTicketService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ resPosTicket });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: resPosTicket }));
      saveSubject.complete();

      // THEN
      expect(resPosTicketFormService.getResPosTicket).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(resPosTicketService.update).toHaveBeenCalledWith(expect.objectContaining(resPosTicket));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IResPosTicket>>();
      const resPosTicket = { id: 'ABC' };
      jest.spyOn(resPosTicketFormService, 'getResPosTicket').mockReturnValue({ id: null });
      jest.spyOn(resPosTicketService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ resPosTicket: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: resPosTicket }));
      saveSubject.complete();

      // THEN
      expect(resPosTicketFormService.getResPosTicket).toHaveBeenCalled();
      expect(resPosTicketService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IResPosTicket>>();
      const resPosTicket = { id: 'ABC' };
      jest.spyOn(resPosTicketService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ resPosTicket });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(resPosTicketService.update).toHaveBeenCalled();
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
