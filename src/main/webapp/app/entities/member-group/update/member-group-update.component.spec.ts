import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideHttpClient, HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { IClient } from 'app/entities/client/client.model';
import { ClientService } from 'app/entities/client/service/client.service';
import { MemberGroupService } from '../service/member-group.service';
import { IMemberGroup } from '../member-group.model';
import { MemberGroupFormService } from './member-group-form.service';

import { MemberGroupUpdateComponent } from './member-group-update.component';

describe('MemberGroup Management Update Component', () => {
  let comp: MemberGroupUpdateComponent;
  let fixture: ComponentFixture<MemberGroupUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let memberGroupFormService: MemberGroupFormService;
  let memberGroupService: MemberGroupService;
  let clientService: ClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [MemberGroupUpdateComponent],
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
      .overrideTemplate(MemberGroupUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(MemberGroupUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    memberGroupFormService = TestBed.inject(MemberGroupFormService);
    memberGroupService = TestBed.inject(MemberGroupService);
    clientService = TestBed.inject(ClientService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Client query and add missing value', () => {
      const memberGroup: IMemberGroup = { id: 'CBA' };
      const client: IClient = { id: '67977255-4cba-41c2-be09-0c72bb427211' };
      memberGroup.client = client;

      const clientCollection: IClient[] = [{ id: 'f01804e7-c348-46cd-aede-43f85cf1b5bf' }];
      jest.spyOn(clientService, 'query').mockReturnValue(of(new HttpResponse({ body: clientCollection })));
      const additionalClients = [client];
      const expectedCollection: IClient[] = [...additionalClients, ...clientCollection];
      jest.spyOn(clientService, 'addClientToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ memberGroup });
      comp.ngOnInit();

      expect(clientService.query).toHaveBeenCalled();
      expect(clientService.addClientToCollectionIfMissing).toHaveBeenCalledWith(
        clientCollection,
        ...additionalClients.map(expect.objectContaining),
      );
      expect(comp.clientsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const memberGroup: IMemberGroup = { id: 'CBA' };
      const client: IClient = { id: 'c36afa62-4331-43ba-864a-dfa94ae874c9' };
      memberGroup.client = client;

      activatedRoute.data = of({ memberGroup });
      comp.ngOnInit();

      expect(comp.clientsSharedCollection).toContain(client);
      expect(comp.memberGroup).toEqual(memberGroup);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMemberGroup>>();
      const memberGroup = { id: 'ABC' };
      jest.spyOn(memberGroupFormService, 'getMemberGroup').mockReturnValue(memberGroup);
      jest.spyOn(memberGroupService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ memberGroup });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: memberGroup }));
      saveSubject.complete();

      // THEN
      expect(memberGroupFormService.getMemberGroup).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(memberGroupService.update).toHaveBeenCalledWith(expect.objectContaining(memberGroup));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMemberGroup>>();
      const memberGroup = { id: 'ABC' };
      jest.spyOn(memberGroupFormService, 'getMemberGroup').mockReturnValue({ id: null });
      jest.spyOn(memberGroupService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ memberGroup: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: memberGroup }));
      saveSubject.complete();

      // THEN
      expect(memberGroupFormService.getMemberGroup).toHaveBeenCalled();
      expect(memberGroupService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMemberGroup>>();
      const memberGroup = { id: 'ABC' };
      jest.spyOn(memberGroupService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ memberGroup });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(memberGroupService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareClient', () => {
      it('Should forward to clientService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(clientService, 'compareClient');
        comp.compareClient(entity, entity2);
        expect(clientService.compareClient).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
