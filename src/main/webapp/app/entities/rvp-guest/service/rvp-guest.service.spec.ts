import { TestBed } from '@angular/core/testing';
import { provideHttpClientTesting, HttpTestingController } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IRvpGuest } from '../rvp-guest.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../rvp-guest.test-samples';

import { RvpGuestService, RestRvpGuest } from './rvp-guest.service';

const requireRestSample: RestRvpGuest = {
  ...sampleWithRequiredData,
  checkin: sampleWithRequiredData.checkin?.format(DATE_FORMAT),
  checkout: sampleWithRequiredData.checkout?.format(DATE_FORMAT),
};

describe('RvpGuest Service', () => {
  let service: RvpGuestService;
  let httpMock: HttpTestingController;
  let expectedResult: IRvpGuest | IRvpGuest[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(RvpGuestService);
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

    it('should create a RvpGuest', () => {
      const rvpGuest = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(rvpGuest).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a RvpGuest', () => {
      const rvpGuest = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(rvpGuest).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a RvpGuest', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of RvpGuest', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a RvpGuest', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addRvpGuestToCollectionIfMissing', () => {
      it('should add a RvpGuest to an empty array', () => {
        const rvpGuest: IRvpGuest = sampleWithRequiredData;
        expectedResult = service.addRvpGuestToCollectionIfMissing([], rvpGuest);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(rvpGuest);
      });

      it('should not add a RvpGuest to an array that contains it', () => {
        const rvpGuest: IRvpGuest = sampleWithRequiredData;
        const rvpGuestCollection: IRvpGuest[] = [
          {
            ...rvpGuest,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addRvpGuestToCollectionIfMissing(rvpGuestCollection, rvpGuest);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a RvpGuest to an array that doesn't contain it", () => {
        const rvpGuest: IRvpGuest = sampleWithRequiredData;
        const rvpGuestCollection: IRvpGuest[] = [sampleWithPartialData];
        expectedResult = service.addRvpGuestToCollectionIfMissing(rvpGuestCollection, rvpGuest);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(rvpGuest);
      });

      it('should add only unique RvpGuest to an array', () => {
        const rvpGuestArray: IRvpGuest[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const rvpGuestCollection: IRvpGuest[] = [sampleWithRequiredData];
        expectedResult = service.addRvpGuestToCollectionIfMissing(rvpGuestCollection, ...rvpGuestArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const rvpGuest: IRvpGuest = sampleWithRequiredData;
        const rvpGuest2: IRvpGuest = sampleWithPartialData;
        expectedResult = service.addRvpGuestToCollectionIfMissing([], rvpGuest, rvpGuest2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(rvpGuest);
        expect(expectedResult).toContain(rvpGuest2);
      });

      it('should accept null and undefined values', () => {
        const rvpGuest: IRvpGuest = sampleWithRequiredData;
        expectedResult = service.addRvpGuestToCollectionIfMissing([], null, rvpGuest, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(rvpGuest);
      });

      it('should return initial array if no RvpGuest is added', () => {
        const rvpGuestCollection: IRvpGuest[] = [sampleWithRequiredData];
        expectedResult = service.addRvpGuestToCollectionIfMissing(rvpGuestCollection, undefined, null);
        expect(expectedResult).toEqual(rvpGuestCollection);
      });
    });

    describe('compareRvpGuest', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareRvpGuest(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareRvpGuest(entity1, entity2);
        const compareResult2 = service.compareRvpGuest(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareRvpGuest(entity1, entity2);
        const compareResult2 = service.compareRvpGuest(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareRvpGuest(entity1, entity2);
        const compareResult2 = service.compareRvpGuest(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
