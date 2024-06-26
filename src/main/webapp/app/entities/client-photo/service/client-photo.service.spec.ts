import { TestBed } from '@angular/core/testing';
import { provideHttpClientTesting, HttpTestingController } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IClientPhoto } from '../client-photo.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../client-photo.test-samples';

import { ClientPhotoService } from './client-photo.service';

const requireRestSample: IClientPhoto = {
  ...sampleWithRequiredData,
};

describe('ClientPhoto Service', () => {
  let service: ClientPhotoService;
  let httpMock: HttpTestingController;
  let expectedResult: IClientPhoto | IClientPhoto[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(ClientPhotoService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find('ABC').subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a ClientPhoto', () => {
      const clientPhoto = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(clientPhoto).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ClientPhoto', () => {
      const clientPhoto = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(clientPhoto).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a ClientPhoto', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of ClientPhoto', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a ClientPhoto', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addClientPhotoToCollectionIfMissing', () => {
      it('should add a ClientPhoto to an empty array', () => {
        const clientPhoto: IClientPhoto = sampleWithRequiredData;
        expectedResult = service.addClientPhotoToCollectionIfMissing([], clientPhoto);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(clientPhoto);
      });

      it('should not add a ClientPhoto to an array that contains it', () => {
        const clientPhoto: IClientPhoto = sampleWithRequiredData;
        const clientPhotoCollection: IClientPhoto[] = [
          {
            ...clientPhoto,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addClientPhotoToCollectionIfMissing(clientPhotoCollection, clientPhoto);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ClientPhoto to an array that doesn't contain it", () => {
        const clientPhoto: IClientPhoto = sampleWithRequiredData;
        const clientPhotoCollection: IClientPhoto[] = [sampleWithPartialData];
        expectedResult = service.addClientPhotoToCollectionIfMissing(clientPhotoCollection, clientPhoto);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(clientPhoto);
      });

      it('should add only unique ClientPhoto to an array', () => {
        const clientPhotoArray: IClientPhoto[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const clientPhotoCollection: IClientPhoto[] = [sampleWithRequiredData];
        expectedResult = service.addClientPhotoToCollectionIfMissing(clientPhotoCollection, ...clientPhotoArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const clientPhoto: IClientPhoto = sampleWithRequiredData;
        const clientPhoto2: IClientPhoto = sampleWithPartialData;
        expectedResult = service.addClientPhotoToCollectionIfMissing([], clientPhoto, clientPhoto2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(clientPhoto);
        expect(expectedResult).toContain(clientPhoto2);
      });

      it('should accept null and undefined values', () => {
        const clientPhoto: IClientPhoto = sampleWithRequiredData;
        expectedResult = service.addClientPhotoToCollectionIfMissing([], null, clientPhoto, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(clientPhoto);
      });

      it('should return initial array if no ClientPhoto is added', () => {
        const clientPhotoCollection: IClientPhoto[] = [sampleWithRequiredData];
        expectedResult = service.addClientPhotoToCollectionIfMissing(clientPhotoCollection, undefined, null);
        expect(expectedResult).toEqual(clientPhotoCollection);
      });
    });

    describe('compareClientPhoto', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareClientPhoto(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareClientPhoto(entity1, entity2);
        const compareResult2 = service.compareClientPhoto(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareClientPhoto(entity1, entity2);
        const compareResult2 = service.compareClientPhoto(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareClientPhoto(entity1, entity2);
        const compareResult2 = service.compareClientPhoto(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
