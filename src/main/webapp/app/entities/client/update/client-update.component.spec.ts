import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideHttpClient, HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { IClientPhoto } from 'app/entities/client-photo/client-photo.model';
import { ClientPhotoService } from 'app/entities/client-photo/service/client-photo.service';
import { IClientVenueStats } from 'app/entities/client-venue-stats/client-venue-stats.model';
import { ClientVenueStatsService } from 'app/entities/client-venue-stats/service/client-venue-stats.service';
import { IClient } from '../client.model';
import { ClientService } from '../service/client.service';
import { ClientFormService } from './client-form.service';

import { ClientUpdateComponent } from './client-update.component';

describe('Client Management Update Component', () => {
  let comp: ClientUpdateComponent;
  let fixture: ComponentFixture<ClientUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let clientFormService: ClientFormService;
  let clientService: ClientService;
  let clientPhotoService: ClientPhotoService;
  let clientVenueStatsService: ClientVenueStatsService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ClientUpdateComponent],
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
      .overrideTemplate(ClientUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ClientUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    clientFormService = TestBed.inject(ClientFormService);
    clientService = TestBed.inject(ClientService);
    clientPhotoService = TestBed.inject(ClientPhotoService);
    clientVenueStatsService = TestBed.inject(ClientVenueStatsService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call clientPhoto query and add missing value', () => {
      const client: IClient = { id: 'CBA' };
      const clientPhoto: IClientPhoto = { id: '026e100a-0e40-4610-8980-7ac25977d956' };
      client.clientPhoto = clientPhoto;

      const clientPhotoCollection: IClientPhoto[] = [{ id: '81cf090b-1a60-47f0-ade0-5a5bae6cc53d' }];
      jest.spyOn(clientPhotoService, 'query').mockReturnValue(of(new HttpResponse({ body: clientPhotoCollection })));
      const expectedCollection: IClientPhoto[] = [clientPhoto, ...clientPhotoCollection];
      jest.spyOn(clientPhotoService, 'addClientPhotoToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ client });
      comp.ngOnInit();

      expect(clientPhotoService.query).toHaveBeenCalled();
      expect(clientPhotoService.addClientPhotoToCollectionIfMissing).toHaveBeenCalledWith(clientPhotoCollection, clientPhoto);
      expect(comp.clientPhotosCollection).toEqual(expectedCollection);
    });

    it('Should call clientVenueStats query and add missing value', () => {
      const client: IClient = { id: 'CBA' };
      const clientVenueStats: IClientVenueStats = { id: 'ea61cceb-2a28-4519-b535-663e0a5071e3' };
      client.clientVenueStats = clientVenueStats;

      const clientVenueStatsCollection: IClientVenueStats[] = [{ id: '72217ae7-b03f-4f63-ae85-1e22fd403dc5' }];
      jest.spyOn(clientVenueStatsService, 'query').mockReturnValue(of(new HttpResponse({ body: clientVenueStatsCollection })));
      const expectedCollection: IClientVenueStats[] = [clientVenueStats, ...clientVenueStatsCollection];
      jest.spyOn(clientVenueStatsService, 'addClientVenueStatsToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ client });
      comp.ngOnInit();

      expect(clientVenueStatsService.query).toHaveBeenCalled();
      expect(clientVenueStatsService.addClientVenueStatsToCollectionIfMissing).toHaveBeenCalledWith(
        clientVenueStatsCollection,
        clientVenueStats,
      );
      expect(comp.clientVenueStatsCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const client: IClient = { id: 'CBA' };
      const clientPhoto: IClientPhoto = { id: 'fb646e19-56d5-4815-8d3a-235e3153051c' };
      client.clientPhoto = clientPhoto;
      const clientVenueStats: IClientVenueStats = { id: '533320d7-73d4-4d6a-9c54-01fcaa86de2a' };
      client.clientVenueStats = clientVenueStats;

      activatedRoute.data = of({ client });
      comp.ngOnInit();

      expect(comp.clientPhotosCollection).toContain(clientPhoto);
      expect(comp.clientVenueStatsCollection).toContain(clientVenueStats);
      expect(comp.client).toEqual(client);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IClient>>();
      const client = { id: 'ABC' };
      jest.spyOn(clientFormService, 'getClient').mockReturnValue(client);
      jest.spyOn(clientService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ client });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: client }));
      saveSubject.complete();

      // THEN
      expect(clientFormService.getClient).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(clientService.update).toHaveBeenCalledWith(expect.objectContaining(client));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IClient>>();
      const client = { id: 'ABC' };
      jest.spyOn(clientFormService, 'getClient').mockReturnValue({ id: null });
      jest.spyOn(clientService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ client: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: client }));
      saveSubject.complete();

      // THEN
      expect(clientFormService.getClient).toHaveBeenCalled();
      expect(clientService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IClient>>();
      const client = { id: 'ABC' };
      jest.spyOn(clientService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ client });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(clientService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareClientPhoto', () => {
      it('Should forward to clientPhotoService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(clientPhotoService, 'compareClientPhoto');
        comp.compareClientPhoto(entity, entity2);
        expect(clientPhotoService.compareClientPhoto).toHaveBeenCalledWith(entity, entity2);
      });
    });

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
