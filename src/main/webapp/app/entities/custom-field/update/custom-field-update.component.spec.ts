import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideHttpClient, HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { IClient } from 'app/entities/client/client.model';
import { ClientService } from 'app/entities/client/service/client.service';
import { CustomFieldService } from '../service/custom-field.service';
import { ICustomField } from '../custom-field.model';
import { CustomFieldFormService } from './custom-field-form.service';

import { CustomFieldUpdateComponent } from './custom-field-update.component';

describe('CustomField Management Update Component', () => {
  let comp: CustomFieldUpdateComponent;
  let fixture: ComponentFixture<CustomFieldUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let customFieldFormService: CustomFieldFormService;
  let customFieldService: CustomFieldService;
  let clientService: ClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [CustomFieldUpdateComponent],
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
      .overrideTemplate(CustomFieldUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CustomFieldUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    customFieldFormService = TestBed.inject(CustomFieldFormService);
    customFieldService = TestBed.inject(CustomFieldService);
    clientService = TestBed.inject(ClientService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Client query and add missing value', () => {
      const customField: ICustomField = { id: 'CBA' };
      const client: IClient = { id: 'c5476d40-e540-44c3-b95d-18e3c5c2b592' };
      customField.client = client;

      const clientCollection: IClient[] = [{ id: 'b7398dc9-f47e-4238-8052-b320499e1561' }];
      jest.spyOn(clientService, 'query').mockReturnValue(of(new HttpResponse({ body: clientCollection })));
      const additionalClients = [client];
      const expectedCollection: IClient[] = [...additionalClients, ...clientCollection];
      jest.spyOn(clientService, 'addClientToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ customField });
      comp.ngOnInit();

      expect(clientService.query).toHaveBeenCalled();
      expect(clientService.addClientToCollectionIfMissing).toHaveBeenCalledWith(
        clientCollection,
        ...additionalClients.map(expect.objectContaining),
      );
      expect(comp.clientsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const customField: ICustomField = { id: 'CBA' };
      const client: IClient = { id: 'a2efaf6a-453e-42d6-bf98-74e0ef231b47' };
      customField.client = client;

      activatedRoute.data = of({ customField });
      comp.ngOnInit();

      expect(comp.clientsSharedCollection).toContain(client);
      expect(comp.customField).toEqual(customField);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICustomField>>();
      const customField = { id: 'ABC' };
      jest.spyOn(customFieldFormService, 'getCustomField').mockReturnValue(customField);
      jest.spyOn(customFieldService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ customField });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: customField }));
      saveSubject.complete();

      // THEN
      expect(customFieldFormService.getCustomField).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(customFieldService.update).toHaveBeenCalledWith(expect.objectContaining(customField));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICustomField>>();
      const customField = { id: 'ABC' };
      jest.spyOn(customFieldFormService, 'getCustomField').mockReturnValue({ id: null });
      jest.spyOn(customFieldService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ customField: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: customField }));
      saveSubject.complete();

      // THEN
      expect(customFieldFormService.getCustomField).toHaveBeenCalled();
      expect(customFieldService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICustomField>>();
      const customField = { id: 'ABC' };
      jest.spyOn(customFieldService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ customField });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(customFieldService.update).toHaveBeenCalled();
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
