import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideHttpClient, HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { IResPosTicket } from 'app/entities/res-pos-ticket/res-pos-ticket.model';
import { ResPosTicketService } from 'app/entities/res-pos-ticket/service/res-pos-ticket.service';
import { ResPosticketsItemService } from '../service/res-postickets-item.service';
import { IResPosticketsItem } from '../res-postickets-item.model';
import { ResPosticketsItemFormService } from './res-postickets-item-form.service';

import { ResPosticketsItemUpdateComponent } from './res-postickets-item-update.component';

describe('ResPosticketsItem Management Update Component', () => {
  let comp: ResPosticketsItemUpdateComponent;
  let fixture: ComponentFixture<ResPosticketsItemUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let resPosticketsItemFormService: ResPosticketsItemFormService;
  let resPosticketsItemService: ResPosticketsItemService;
  let resPosTicketService: ResPosTicketService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ResPosticketsItemUpdateComponent],
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
      .overrideTemplate(ResPosticketsItemUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ResPosticketsItemUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    resPosticketsItemFormService = TestBed.inject(ResPosticketsItemFormService);
    resPosticketsItemService = TestBed.inject(ResPosticketsItemService);
    resPosTicketService = TestBed.inject(ResPosTicketService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call ResPosTicket query and add missing value', () => {
      const resPosticketsItem: IResPosticketsItem = { id: 'CBA' };
      const resPosTicket: IResPosTicket = { id: '50406b53-745f-4519-b1f5-cb53f3da12e3' };
      resPosticketsItem.resPosTicket = resPosTicket;

      const resPosTicketCollection: IResPosTicket[] = [{ id: '4bd5c894-be83-4d5d-bfbe-be13864c6e1c' }];
      jest.spyOn(resPosTicketService, 'query').mockReturnValue(of(new HttpResponse({ body: resPosTicketCollection })));
      const additionalResPosTickets = [resPosTicket];
      const expectedCollection: IResPosTicket[] = [...additionalResPosTickets, ...resPosTicketCollection];
      jest.spyOn(resPosTicketService, 'addResPosTicketToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ resPosticketsItem });
      comp.ngOnInit();

      expect(resPosTicketService.query).toHaveBeenCalled();
      expect(resPosTicketService.addResPosTicketToCollectionIfMissing).toHaveBeenCalledWith(
        resPosTicketCollection,
        ...additionalResPosTickets.map(expect.objectContaining),
      );
      expect(comp.resPosTicketsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const resPosticketsItem: IResPosticketsItem = { id: 'CBA' };
      const resPosTicket: IResPosTicket = { id: '9185ae71-0933-4883-9814-6e034e3d91d1' };
      resPosticketsItem.resPosTicket = resPosTicket;

      activatedRoute.data = of({ resPosticketsItem });
      comp.ngOnInit();

      expect(comp.resPosTicketsSharedCollection).toContain(resPosTicket);
      expect(comp.resPosticketsItem).toEqual(resPosticketsItem);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IResPosticketsItem>>();
      const resPosticketsItem = { id: 'ABC' };
      jest.spyOn(resPosticketsItemFormService, 'getResPosticketsItem').mockReturnValue(resPosticketsItem);
      jest.spyOn(resPosticketsItemService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ resPosticketsItem });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: resPosticketsItem }));
      saveSubject.complete();

      // THEN
      expect(resPosticketsItemFormService.getResPosticketsItem).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(resPosticketsItemService.update).toHaveBeenCalledWith(expect.objectContaining(resPosticketsItem));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IResPosticketsItem>>();
      const resPosticketsItem = { id: 'ABC' };
      jest.spyOn(resPosticketsItemFormService, 'getResPosticketsItem').mockReturnValue({ id: null });
      jest.spyOn(resPosticketsItemService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ resPosticketsItem: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: resPosticketsItem }));
      saveSubject.complete();

      // THEN
      expect(resPosticketsItemFormService.getResPosticketsItem).toHaveBeenCalled();
      expect(resPosticketsItemService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IResPosticketsItem>>();
      const resPosticketsItem = { id: 'ABC' };
      jest.spyOn(resPosticketsItemService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ resPosticketsItem });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(resPosticketsItemService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareResPosTicket', () => {
      it('Should forward to resPosTicketService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(resPosTicketService, 'compareResPosTicket');
        comp.compareResPosTicket(entity, entity2);
        expect(resPosTicketService.compareResPosTicket).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
