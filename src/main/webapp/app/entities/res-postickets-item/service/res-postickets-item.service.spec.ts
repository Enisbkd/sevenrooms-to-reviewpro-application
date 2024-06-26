import { TestBed } from '@angular/core/testing';
import { provideHttpClientTesting, HttpTestingController } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IResPosticketsItem } from '../res-postickets-item.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../res-postickets-item.test-samples';

import { ResPosticketsItemService } from './res-postickets-item.service';

const requireRestSample: IResPosticketsItem = {
  ...sampleWithRequiredData,
};

describe('ResPosticketsItem Service', () => {
  let service: ResPosticketsItemService;
  let httpMock: HttpTestingController;
  let expectedResult: IResPosticketsItem | IResPosticketsItem[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(ResPosticketsItemService);
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

    it('should create a ResPosticketsItem', () => {
      const resPosticketsItem = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(resPosticketsItem).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ResPosticketsItem', () => {
      const resPosticketsItem = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(resPosticketsItem).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a ResPosticketsItem', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of ResPosticketsItem', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a ResPosticketsItem', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addResPosticketsItemToCollectionIfMissing', () => {
      it('should add a ResPosticketsItem to an empty array', () => {
        const resPosticketsItem: IResPosticketsItem = sampleWithRequiredData;
        expectedResult = service.addResPosticketsItemToCollectionIfMissing([], resPosticketsItem);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(resPosticketsItem);
      });

      it('should not add a ResPosticketsItem to an array that contains it', () => {
        const resPosticketsItem: IResPosticketsItem = sampleWithRequiredData;
        const resPosticketsItemCollection: IResPosticketsItem[] = [
          {
            ...resPosticketsItem,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addResPosticketsItemToCollectionIfMissing(resPosticketsItemCollection, resPosticketsItem);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ResPosticketsItem to an array that doesn't contain it", () => {
        const resPosticketsItem: IResPosticketsItem = sampleWithRequiredData;
        const resPosticketsItemCollection: IResPosticketsItem[] = [sampleWithPartialData];
        expectedResult = service.addResPosticketsItemToCollectionIfMissing(resPosticketsItemCollection, resPosticketsItem);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(resPosticketsItem);
      });

      it('should add only unique ResPosticketsItem to an array', () => {
        const resPosticketsItemArray: IResPosticketsItem[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const resPosticketsItemCollection: IResPosticketsItem[] = [sampleWithRequiredData];
        expectedResult = service.addResPosticketsItemToCollectionIfMissing(resPosticketsItemCollection, ...resPosticketsItemArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const resPosticketsItem: IResPosticketsItem = sampleWithRequiredData;
        const resPosticketsItem2: IResPosticketsItem = sampleWithPartialData;
        expectedResult = service.addResPosticketsItemToCollectionIfMissing([], resPosticketsItem, resPosticketsItem2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(resPosticketsItem);
        expect(expectedResult).toContain(resPosticketsItem2);
      });

      it('should accept null and undefined values', () => {
        const resPosticketsItem: IResPosticketsItem = sampleWithRequiredData;
        expectedResult = service.addResPosticketsItemToCollectionIfMissing([], null, resPosticketsItem, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(resPosticketsItem);
      });

      it('should return initial array if no ResPosticketsItem is added', () => {
        const resPosticketsItemCollection: IResPosticketsItem[] = [sampleWithRequiredData];
        expectedResult = service.addResPosticketsItemToCollectionIfMissing(resPosticketsItemCollection, undefined, null);
        expect(expectedResult).toEqual(resPosticketsItemCollection);
      });
    });

    describe('compareResPosticketsItem', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareResPosticketsItem(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareResPosticketsItem(entity1, entity2);
        const compareResult2 = service.compareResPosticketsItem(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareResPosticketsItem(entity1, entity2);
        const compareResult2 = service.compareResPosticketsItem(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareResPosticketsItem(entity1, entity2);
        const compareResult2 = service.compareResPosticketsItem(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
