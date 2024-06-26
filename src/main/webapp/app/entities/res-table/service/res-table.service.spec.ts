import { TestBed } from '@angular/core/testing';
import { provideHttpClientTesting, HttpTestingController } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IResTable } from '../res-table.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../res-table.test-samples';

import { ResTableService } from './res-table.service';

const requireRestSample: IResTable = {
  ...sampleWithRequiredData,
};

describe('ResTable Service', () => {
  let service: ResTableService;
  let httpMock: HttpTestingController;
  let expectedResult: IResTable | IResTable[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(ResTableService);
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

    it('should create a ResTable', () => {
      const resTable = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(resTable).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ResTable', () => {
      const resTable = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(resTable).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a ResTable', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of ResTable', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a ResTable', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addResTableToCollectionIfMissing', () => {
      it('should add a ResTable to an empty array', () => {
        const resTable: IResTable = sampleWithRequiredData;
        expectedResult = service.addResTableToCollectionIfMissing([], resTable);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(resTable);
      });

      it('should not add a ResTable to an array that contains it', () => {
        const resTable: IResTable = sampleWithRequiredData;
        const resTableCollection: IResTable[] = [
          {
            ...resTable,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addResTableToCollectionIfMissing(resTableCollection, resTable);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ResTable to an array that doesn't contain it", () => {
        const resTable: IResTable = sampleWithRequiredData;
        const resTableCollection: IResTable[] = [sampleWithPartialData];
        expectedResult = service.addResTableToCollectionIfMissing(resTableCollection, resTable);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(resTable);
      });

      it('should add only unique ResTable to an array', () => {
        const resTableArray: IResTable[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const resTableCollection: IResTable[] = [sampleWithRequiredData];
        expectedResult = service.addResTableToCollectionIfMissing(resTableCollection, ...resTableArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const resTable: IResTable = sampleWithRequiredData;
        const resTable2: IResTable = sampleWithPartialData;
        expectedResult = service.addResTableToCollectionIfMissing([], resTable, resTable2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(resTable);
        expect(expectedResult).toContain(resTable2);
      });

      it('should accept null and undefined values', () => {
        const resTable: IResTable = sampleWithRequiredData;
        expectedResult = service.addResTableToCollectionIfMissing([], null, resTable, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(resTable);
      });

      it('should return initial array if no ResTable is added', () => {
        const resTableCollection: IResTable[] = [sampleWithRequiredData];
        expectedResult = service.addResTableToCollectionIfMissing(resTableCollection, undefined, null);
        expect(expectedResult).toEqual(resTableCollection);
      });
    });

    describe('compareResTable', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareResTable(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareResTable(entity1, entity2);
        const compareResult2 = service.compareResTable(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareResTable(entity1, entity2);
        const compareResult2 = service.compareResTable(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareResTable(entity1, entity2);
        const compareResult2 = service.compareResTable(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
