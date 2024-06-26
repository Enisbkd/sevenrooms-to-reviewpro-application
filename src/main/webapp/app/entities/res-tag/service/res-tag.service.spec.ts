import { TestBed } from '@angular/core/testing';
import { provideHttpClientTesting, HttpTestingController } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IResTag } from '../res-tag.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../res-tag.test-samples';

import { ResTagService } from './res-tag.service';

const requireRestSample: IResTag = {
  ...sampleWithRequiredData,
};

describe('ResTag Service', () => {
  let service: ResTagService;
  let httpMock: HttpTestingController;
  let expectedResult: IResTag | IResTag[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(ResTagService);
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

    it('should create a ResTag', () => {
      const resTag = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(resTag).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ResTag', () => {
      const resTag = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(resTag).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a ResTag', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of ResTag', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a ResTag', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addResTagToCollectionIfMissing', () => {
      it('should add a ResTag to an empty array', () => {
        const resTag: IResTag = sampleWithRequiredData;
        expectedResult = service.addResTagToCollectionIfMissing([], resTag);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(resTag);
      });

      it('should not add a ResTag to an array that contains it', () => {
        const resTag: IResTag = sampleWithRequiredData;
        const resTagCollection: IResTag[] = [
          {
            ...resTag,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addResTagToCollectionIfMissing(resTagCollection, resTag);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ResTag to an array that doesn't contain it", () => {
        const resTag: IResTag = sampleWithRequiredData;
        const resTagCollection: IResTag[] = [sampleWithPartialData];
        expectedResult = service.addResTagToCollectionIfMissing(resTagCollection, resTag);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(resTag);
      });

      it('should add only unique ResTag to an array', () => {
        const resTagArray: IResTag[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const resTagCollection: IResTag[] = [sampleWithRequiredData];
        expectedResult = service.addResTagToCollectionIfMissing(resTagCollection, ...resTagArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const resTag: IResTag = sampleWithRequiredData;
        const resTag2: IResTag = sampleWithPartialData;
        expectedResult = service.addResTagToCollectionIfMissing([], resTag, resTag2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(resTag);
        expect(expectedResult).toContain(resTag2);
      });

      it('should accept null and undefined values', () => {
        const resTag: IResTag = sampleWithRequiredData;
        expectedResult = service.addResTagToCollectionIfMissing([], null, resTag, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(resTag);
      });

      it('should return initial array if no ResTag is added', () => {
        const resTagCollection: IResTag[] = [sampleWithRequiredData];
        expectedResult = service.addResTagToCollectionIfMissing(resTagCollection, undefined, null);
        expect(expectedResult).toEqual(resTagCollection);
      });
    });

    describe('compareResTag', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareResTag(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareResTag(entity1, entity2);
        const compareResult2 = service.compareResTag(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareResTag(entity1, entity2);
        const compareResult2 = service.compareResTag(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareResTag(entity1, entity2);
        const compareResult2 = service.compareResTag(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
