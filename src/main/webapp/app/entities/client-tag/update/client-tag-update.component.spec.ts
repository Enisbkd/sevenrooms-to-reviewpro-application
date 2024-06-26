import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideHttpClient, HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { IClient } from 'app/entities/client/client.model';
import { ClientService } from 'app/entities/client/service/client.service';
import { ClientTagService } from '../service/client-tag.service';
import { IClientTag } from '../client-tag.model';
import { ClientTagFormService } from './client-tag-form.service';

import { ClientTagUpdateComponent } from './client-tag-update.component';

describe('ClientTag Management Update Component', () => {
  let comp: ClientTagUpdateComponent;
  let fixture: ComponentFixture<ClientTagUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let clientTagFormService: ClientTagFormService;
  let clientTagService: ClientTagService;
  let clientService: ClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ClientTagUpdateComponent],
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
      .overrideTemplate(ClientTagUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ClientTagUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    clientTagFormService = TestBed.inject(ClientTagFormService);
    clientTagService = TestBed.inject(ClientTagService);
    clientService = TestBed.inject(ClientService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Client query and add missing value', () => {
      const clientTag: IClientTag = { id: 'CBA' };
      const client: IClient = { id: '793b57c7-d75f-4208-bfc5-ed3709064103' };
      clientTag.client = client;

      const clientCollection: IClient[] = [{ id: 'bfb3b025-845d-4eaf-a3d9-7f5a17a223b5' }];
      jest.spyOn(clientService, 'query').mockReturnValue(of(new HttpResponse({ body: clientCollection })));
      const additionalClients = [client];
      const expectedCollection: IClient[] = [...additionalClients, ...clientCollection];
      jest.spyOn(clientService, 'addClientToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ clientTag });
      comp.ngOnInit();

      expect(clientService.query).toHaveBeenCalled();
      expect(clientService.addClientToCollectionIfMissing).toHaveBeenCalledWith(
        clientCollection,
        ...additionalClients.map(expect.objectContaining),
      );
      expect(comp.clientsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const clientTag: IClientTag = { id: 'CBA' };
      const client: IClient = { id: 'e878b926-f6a2-444c-b766-6d3d3ce13e30' };
      clientTag.client = client;

      activatedRoute.data = of({ clientTag });
      comp.ngOnInit();

      expect(comp.clientsSharedCollection).toContain(client);
      expect(comp.clientTag).toEqual(clientTag);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IClientTag>>();
      const clientTag = { id: 'ABC' };
      jest.spyOn(clientTagFormService, 'getClientTag').mockReturnValue(clientTag);
      jest.spyOn(clientTagService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ clientTag });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: clientTag }));
      saveSubject.complete();

      // THEN
      expect(clientTagFormService.getClientTag).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(clientTagService.update).toHaveBeenCalledWith(expect.objectContaining(clientTag));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IClientTag>>();
      const clientTag = { id: 'ABC' };
      jest.spyOn(clientTagFormService, 'getClientTag').mockReturnValue({ id: null });
      jest.spyOn(clientTagService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ clientTag: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: clientTag }));
      saveSubject.complete();

      // THEN
      expect(clientTagFormService.getClientTag).toHaveBeenCalled();
      expect(clientTagService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IClientTag>>();
      const clientTag = { id: 'ABC' };
      jest.spyOn(clientTagService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ clientTag });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(clientTagService.update).toHaveBeenCalled();
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
