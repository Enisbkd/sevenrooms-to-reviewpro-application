import { TestBed } from '@angular/core/testing';
import { provideHttpClientTesting, HttpTestingController } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { ICustomField } from '../custom-field.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../custom-field.test-samples';

import { CustomFieldService } from './custom-field.service';

const requireRestSample: ICustomField = {
  ...sampleWithRequiredData,
};

describe('CustomField Service', () => {
  let service: CustomFieldService;
  let httpMock: HttpTestingController;
  let expectedResult: ICustomField | ICustomField[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(CustomFieldService);
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

    it('should create a CustomField', () => {
      const customField = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(customField).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a CustomField', () => {
      const customField = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(customField).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a CustomField', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of CustomField', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a CustomField', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addCustomFieldToCollectionIfMissing', () => {
      it('should add a CustomField to an empty array', () => {
        const customField: ICustomField = sampleWithRequiredData;
        expectedResult = service.addCustomFieldToCollectionIfMissing([], customField);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(customField);
      });

      it('should not add a CustomField to an array that contains it', () => {
        const customField: ICustomField = sampleWithRequiredData;
        const customFieldCollection: ICustomField[] = [
          {
            ...customField,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addCustomFieldToCollectionIfMissing(customFieldCollection, customField);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a CustomField to an array that doesn't contain it", () => {
        const customField: ICustomField = sampleWithRequiredData;
        const customFieldCollection: ICustomField[] = [sampleWithPartialData];
        expectedResult = service.addCustomFieldToCollectionIfMissing(customFieldCollection, customField);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(customField);
      });

      it('should add only unique CustomField to an array', () => {
        const customFieldArray: ICustomField[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const customFieldCollection: ICustomField[] = [sampleWithRequiredData];
        expectedResult = service.addCustomFieldToCollectionIfMissing(customFieldCollection, ...customFieldArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const customField: ICustomField = sampleWithRequiredData;
        const customField2: ICustomField = sampleWithPartialData;
        expectedResult = service.addCustomFieldToCollectionIfMissing([], customField, customField2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(customField);
        expect(expectedResult).toContain(customField2);
      });

      it('should accept null and undefined values', () => {
        const customField: ICustomField = sampleWithRequiredData;
        expectedResult = service.addCustomFieldToCollectionIfMissing([], null, customField, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(customField);
      });

      it('should return initial array if no CustomField is added', () => {
        const customFieldCollection: ICustomField[] = [sampleWithRequiredData];
        expectedResult = service.addCustomFieldToCollectionIfMissing(customFieldCollection, undefined, null);
        expect(expectedResult).toEqual(customFieldCollection);
      });
    });

    describe('compareCustomField', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareCustomField(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareCustomField(entity1, entity2);
        const compareResult2 = service.compareCustomField(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareCustomField(entity1, entity2);
        const compareResult2 = service.compareCustomField(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareCustomField(entity1, entity2);
        const compareResult2 = service.compareCustomField(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
