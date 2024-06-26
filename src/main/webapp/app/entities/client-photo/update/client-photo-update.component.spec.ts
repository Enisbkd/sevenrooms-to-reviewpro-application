import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideHttpClient, HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject, from } from 'rxjs';

import { ClientPhotoService } from '../service/client-photo.service';
import { IClientPhoto } from '../client-photo.model';
import { ClientPhotoFormService } from './client-photo-form.service';

import { ClientPhotoUpdateComponent } from './client-photo-update.component';

describe('ClientPhoto Management Update Component', () => {
  let comp: ClientPhotoUpdateComponent;
  let fixture: ComponentFixture<ClientPhotoUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let clientPhotoFormService: ClientPhotoFormService;
  let clientPhotoService: ClientPhotoService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ClientPhotoUpdateComponent],
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
      .overrideTemplate(ClientPhotoUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ClientPhotoUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    clientPhotoFormService = TestBed.inject(ClientPhotoFormService);
    clientPhotoService = TestBed.inject(ClientPhotoService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const clientPhoto: IClientPhoto = { id: 'CBA' };

      activatedRoute.data = of({ clientPhoto });
      comp.ngOnInit();

      expect(comp.clientPhoto).toEqual(clientPhoto);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IClientPhoto>>();
      const clientPhoto = { id: 'ABC' };
      jest.spyOn(clientPhotoFormService, 'getClientPhoto').mockReturnValue(clientPhoto);
      jest.spyOn(clientPhotoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ clientPhoto });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: clientPhoto }));
      saveSubject.complete();

      // THEN
      expect(clientPhotoFormService.getClientPhoto).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(clientPhotoService.update).toHaveBeenCalledWith(expect.objectContaining(clientPhoto));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IClientPhoto>>();
      const clientPhoto = { id: 'ABC' };
      jest.spyOn(clientPhotoFormService, 'getClientPhoto').mockReturnValue({ id: null });
      jest.spyOn(clientPhotoService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ clientPhoto: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: clientPhoto }));
      saveSubject.complete();

      // THEN
      expect(clientPhotoFormService.getClientPhoto).toHaveBeenCalled();
      expect(clientPhotoService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IClientPhoto>>();
      const clientPhoto = { id: 'ABC' };
      jest.spyOn(clientPhotoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ clientPhoto });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(clientPhotoService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
