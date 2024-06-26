import { TestBed } from '@angular/core/testing';
import { provideHttpClientTesting, HttpTestingController } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IBookingName } from '../booking-name.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../booking-name.test-samples';

import { BookingNameService } from './booking-name.service';

const requireRestSample: IBookingName = {
  ...sampleWithRequiredData,
};

describe('BookingName Service', () => {
  let service: BookingNameService;
  let httpMock: HttpTestingController;
  let expectedResult: IBookingName | IBookingName[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(BookingNameService);
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

    it('should create a BookingName', () => {
      const bookingName = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(bookingName).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a BookingName', () => {
      const bookingName = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(bookingName).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a BookingName', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of BookingName', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a BookingName', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addBookingNameToCollectionIfMissing', () => {
      it('should add a BookingName to an empty array', () => {
        const bookingName: IBookingName = sampleWithRequiredData;
        expectedResult = service.addBookingNameToCollectionIfMissing([], bookingName);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(bookingName);
      });

      it('should not add a BookingName to an array that contains it', () => {
        const bookingName: IBookingName = sampleWithRequiredData;
        const bookingNameCollection: IBookingName[] = [
          {
            ...bookingName,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addBookingNameToCollectionIfMissing(bookingNameCollection, bookingName);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a BookingName to an array that doesn't contain it", () => {
        const bookingName: IBookingName = sampleWithRequiredData;
        const bookingNameCollection: IBookingName[] = [sampleWithPartialData];
        expectedResult = service.addBookingNameToCollectionIfMissing(bookingNameCollection, bookingName);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(bookingName);
      });

      it('should add only unique BookingName to an array', () => {
        const bookingNameArray: IBookingName[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const bookingNameCollection: IBookingName[] = [sampleWithRequiredData];
        expectedResult = service.addBookingNameToCollectionIfMissing(bookingNameCollection, ...bookingNameArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const bookingName: IBookingName = sampleWithRequiredData;
        const bookingName2: IBookingName = sampleWithPartialData;
        expectedResult = service.addBookingNameToCollectionIfMissing([], bookingName, bookingName2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(bookingName);
        expect(expectedResult).toContain(bookingName2);
      });

      it('should accept null and undefined values', () => {
        const bookingName: IBookingName = sampleWithRequiredData;
        expectedResult = service.addBookingNameToCollectionIfMissing([], null, bookingName, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(bookingName);
      });

      it('should return initial array if no BookingName is added', () => {
        const bookingNameCollection: IBookingName[] = [sampleWithRequiredData];
        expectedResult = service.addBookingNameToCollectionIfMissing(bookingNameCollection, undefined, null);
        expect(expectedResult).toEqual(bookingNameCollection);
      });
    });

    describe('compareBookingName', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareBookingName(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareBookingName(entity1, entity2);
        const compareResult2 = service.compareBookingName(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareBookingName(entity1, entity2);
        const compareResult2 = service.compareBookingName(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareBookingName(entity1, entity2);
        const compareResult2 = service.compareBookingName(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
