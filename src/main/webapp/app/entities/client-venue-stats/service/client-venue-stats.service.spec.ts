import { TestBed } from '@angular/core/testing';
import { provideHttpClientTesting, HttpTestingController } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IClientVenueStats } from '../client-venue-stats.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../client-venue-stats.test-samples';

import { ClientVenueStatsService } from './client-venue-stats.service';

const requireRestSample: IClientVenueStats = {
  ...sampleWithRequiredData,
};

describe('ClientVenueStats Service', () => {
  let service: ClientVenueStatsService;
  let httpMock: HttpTestingController;
  let expectedResult: IClientVenueStats | IClientVenueStats[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(ClientVenueStatsService);
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

    it('should create a ClientVenueStats', () => {
      const clientVenueStats = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(clientVenueStats).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ClientVenueStats', () => {
      const clientVenueStats = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(clientVenueStats).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a ClientVenueStats', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of ClientVenueStats', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a ClientVenueStats', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addClientVenueStatsToCollectionIfMissing', () => {
      it('should add a ClientVenueStats to an empty array', () => {
        const clientVenueStats: IClientVenueStats = sampleWithRequiredData;
        expectedResult = service.addClientVenueStatsToCollectionIfMissing([], clientVenueStats);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(clientVenueStats);
      });

      it('should not add a ClientVenueStats to an array that contains it', () => {
        const clientVenueStats: IClientVenueStats = sampleWithRequiredData;
        const clientVenueStatsCollection: IClientVenueStats[] = [
          {
            ...clientVenueStats,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addClientVenueStatsToCollectionIfMissing(clientVenueStatsCollection, clientVenueStats);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ClientVenueStats to an array that doesn't contain it", () => {
        const clientVenueStats: IClientVenueStats = sampleWithRequiredData;
        const clientVenueStatsCollection: IClientVenueStats[] = [sampleWithPartialData];
        expectedResult = service.addClientVenueStatsToCollectionIfMissing(clientVenueStatsCollection, clientVenueStats);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(clientVenueStats);
      });

      it('should add only unique ClientVenueStats to an array', () => {
        const clientVenueStatsArray: IClientVenueStats[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const clientVenueStatsCollection: IClientVenueStats[] = [sampleWithRequiredData];
        expectedResult = service.addClientVenueStatsToCollectionIfMissing(clientVenueStatsCollection, ...clientVenueStatsArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const clientVenueStats: IClientVenueStats = sampleWithRequiredData;
        const clientVenueStats2: IClientVenueStats = sampleWithPartialData;
        expectedResult = service.addClientVenueStatsToCollectionIfMissing([], clientVenueStats, clientVenueStats2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(clientVenueStats);
        expect(expectedResult).toContain(clientVenueStats2);
      });

      it('should accept null and undefined values', () => {
        const clientVenueStats: IClientVenueStats = sampleWithRequiredData;
        expectedResult = service.addClientVenueStatsToCollectionIfMissing([], null, clientVenueStats, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(clientVenueStats);
      });

      it('should return initial array if no ClientVenueStats is added', () => {
        const clientVenueStatsCollection: IClientVenueStats[] = [sampleWithRequiredData];
        expectedResult = service.addClientVenueStatsToCollectionIfMissing(clientVenueStatsCollection, undefined, null);
        expect(expectedResult).toEqual(clientVenueStatsCollection);
      });
    });

    describe('compareClientVenueStats', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareClientVenueStats(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareClientVenueStats(entity1, entity2);
        const compareResult2 = service.compareClientVenueStats(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareClientVenueStats(entity1, entity2);
        const compareResult2 = service.compareClientVenueStats(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareClientVenueStats(entity1, entity2);
        const compareResult2 = service.compareClientVenueStats(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
