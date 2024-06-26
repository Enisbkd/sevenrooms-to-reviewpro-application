import { TestBed } from '@angular/core/testing';
import { provideHttpClientTesting, HttpTestingController } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IClientTag } from '../client-tag.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../client-tag.test-samples';

import { ClientTagService } from './client-tag.service';

const requireRestSample: IClientTag = {
  ...sampleWithRequiredData,
};

describe('ClientTag Service', () => {
  let service: ClientTagService;
  let httpMock: HttpTestingController;
  let expectedResult: IClientTag | IClientTag[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(ClientTagService);
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

    it('should create a ClientTag', () => {
      const clientTag = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(clientTag).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ClientTag', () => {
      const clientTag = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(clientTag).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a ClientTag', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of ClientTag', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a ClientTag', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addClientTagToCollectionIfMissing', () => {
      it('should add a ClientTag to an empty array', () => {
        const clientTag: IClientTag = sampleWithRequiredData;
        expectedResult = service.addClientTagToCollectionIfMissing([], clientTag);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(clientTag);
      });

      it('should not add a ClientTag to an array that contains it', () => {
        const clientTag: IClientTag = sampleWithRequiredData;
        const clientTagCollection: IClientTag[] = [
          {
            ...clientTag,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addClientTagToCollectionIfMissing(clientTagCollection, clientTag);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ClientTag to an array that doesn't contain it", () => {
        const clientTag: IClientTag = sampleWithRequiredData;
        const clientTagCollection: IClientTag[] = [sampleWithPartialData];
        expectedResult = service.addClientTagToCollectionIfMissing(clientTagCollection, clientTag);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(clientTag);
      });

      it('should add only unique ClientTag to an array', () => {
        const clientTagArray: IClientTag[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const clientTagCollection: IClientTag[] = [sampleWithRequiredData];
        expectedResult = service.addClientTagToCollectionIfMissing(clientTagCollection, ...clientTagArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const clientTag: IClientTag = sampleWithRequiredData;
        const clientTag2: IClientTag = sampleWithPartialData;
        expectedResult = service.addClientTagToCollectionIfMissing([], clientTag, clientTag2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(clientTag);
        expect(expectedResult).toContain(clientTag2);
      });

      it('should accept null and undefined values', () => {
        const clientTag: IClientTag = sampleWithRequiredData;
        expectedResult = service.addClientTagToCollectionIfMissing([], null, clientTag, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(clientTag);
      });

      it('should return initial array if no ClientTag is added', () => {
        const clientTagCollection: IClientTag[] = [sampleWithRequiredData];
        expectedResult = service.addClientTagToCollectionIfMissing(clientTagCollection, undefined, null);
        expect(expectedResult).toEqual(clientTagCollection);
      });
    });

    describe('compareClientTag', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareClientTag(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareClientTag(entity1, entity2);
        const compareResult2 = service.compareClientTag(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareClientTag(entity1, entity2);
        const compareResult2 = service.compareClientTag(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareClientTag(entity1, entity2);
        const compareResult2 = service.compareClientTag(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
