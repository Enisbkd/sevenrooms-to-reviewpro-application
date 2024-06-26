import { TestBed } from '@angular/core/testing';
import { provideHttpClientTesting, HttpTestingController } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IResPosTicket } from '../res-pos-ticket.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../res-pos-ticket.test-samples';

import { ResPosTicketService } from './res-pos-ticket.service';

const requireRestSample: IResPosTicket = {
  ...sampleWithRequiredData,
};

describe('ResPosTicket Service', () => {
  let service: ResPosTicketService;
  let httpMock: HttpTestingController;
  let expectedResult: IResPosTicket | IResPosTicket[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(ResPosTicketService);
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

    it('should create a ResPosTicket', () => {
      const resPosTicket = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(resPosTicket).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ResPosTicket', () => {
      const resPosTicket = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(resPosTicket).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a ResPosTicket', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of ResPosTicket', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a ResPosTicket', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addResPosTicketToCollectionIfMissing', () => {
      it('should add a ResPosTicket to an empty array', () => {
        const resPosTicket: IResPosTicket = sampleWithRequiredData;
        expectedResult = service.addResPosTicketToCollectionIfMissing([], resPosTicket);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(resPosTicket);
      });

      it('should not add a ResPosTicket to an array that contains it', () => {
        const resPosTicket: IResPosTicket = sampleWithRequiredData;
        const resPosTicketCollection: IResPosTicket[] = [
          {
            ...resPosTicket,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addResPosTicketToCollectionIfMissing(resPosTicketCollection, resPosTicket);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ResPosTicket to an array that doesn't contain it", () => {
        const resPosTicket: IResPosTicket = sampleWithRequiredData;
        const resPosTicketCollection: IResPosTicket[] = [sampleWithPartialData];
        expectedResult = service.addResPosTicketToCollectionIfMissing(resPosTicketCollection, resPosTicket);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(resPosTicket);
      });

      it('should add only unique ResPosTicket to an array', () => {
        const resPosTicketArray: IResPosTicket[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const resPosTicketCollection: IResPosTicket[] = [sampleWithRequiredData];
        expectedResult = service.addResPosTicketToCollectionIfMissing(resPosTicketCollection, ...resPosTicketArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const resPosTicket: IResPosTicket = sampleWithRequiredData;
        const resPosTicket2: IResPosTicket = sampleWithPartialData;
        expectedResult = service.addResPosTicketToCollectionIfMissing([], resPosTicket, resPosTicket2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(resPosTicket);
        expect(expectedResult).toContain(resPosTicket2);
      });

      it('should accept null and undefined values', () => {
        const resPosTicket: IResPosTicket = sampleWithRequiredData;
        expectedResult = service.addResPosTicketToCollectionIfMissing([], null, resPosTicket, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(resPosTicket);
      });

      it('should return initial array if no ResPosTicket is added', () => {
        const resPosTicketCollection: IResPosTicket[] = [sampleWithRequiredData];
        expectedResult = service.addResPosTicketToCollectionIfMissing(resPosTicketCollection, undefined, null);
        expect(expectedResult).toEqual(resPosTicketCollection);
      });
    });

    describe('compareResPosTicket', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareResPosTicket(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareResPosTicket(entity1, entity2);
        const compareResult2 = service.compareResPosTicket(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareResPosTicket(entity1, entity2);
        const compareResult2 = service.compareResPosTicket(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareResPosTicket(entity1, entity2);
        const compareResult2 = service.compareResPosTicket(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
