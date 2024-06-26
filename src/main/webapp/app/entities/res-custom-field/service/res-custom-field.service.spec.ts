import { TestBed } from '@angular/core/testing';
import { provideHttpClientTesting, HttpTestingController } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IResCustomField } from '../res-custom-field.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../res-custom-field.test-samples';

import { ResCustomFieldService } from './res-custom-field.service';

const requireRestSample: IResCustomField = {
  ...sampleWithRequiredData,
};

describe('ResCustomField Service', () => {
  let service: ResCustomFieldService;
  let httpMock: HttpTestingController;
  let expectedResult: IResCustomField | IResCustomField[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(ResCustomFieldService);
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

    it('should create a ResCustomField', () => {
      const resCustomField = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(resCustomField).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ResCustomField', () => {
      const resCustomField = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(resCustomField).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a ResCustomField', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of ResCustomField', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a ResCustomField', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addResCustomFieldToCollectionIfMissing', () => {
      it('should add a ResCustomField to an empty array', () => {
        const resCustomField: IResCustomField = sampleWithRequiredData;
        expectedResult = service.addResCustomFieldToCollectionIfMissing([], resCustomField);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(resCustomField);
      });

      it('should not add a ResCustomField to an array that contains it', () => {
        const resCustomField: IResCustomField = sampleWithRequiredData;
        const resCustomFieldCollection: IResCustomField[] = [
          {
            ...resCustomField,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addResCustomFieldToCollectionIfMissing(resCustomFieldCollection, resCustomField);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ResCustomField to an array that doesn't contain it", () => {
        const resCustomField: IResCustomField = sampleWithRequiredData;
        const resCustomFieldCollection: IResCustomField[] = [sampleWithPartialData];
        expectedResult = service.addResCustomFieldToCollectionIfMissing(resCustomFieldCollection, resCustomField);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(resCustomField);
      });

      it('should add only unique ResCustomField to an array', () => {
        const resCustomFieldArray: IResCustomField[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const resCustomFieldCollection: IResCustomField[] = [sampleWithRequiredData];
        expectedResult = service.addResCustomFieldToCollectionIfMissing(resCustomFieldCollection, ...resCustomFieldArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const resCustomField: IResCustomField = sampleWithRequiredData;
        const resCustomField2: IResCustomField = sampleWithPartialData;
        expectedResult = service.addResCustomFieldToCollectionIfMissing([], resCustomField, resCustomField2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(resCustomField);
        expect(expectedResult).toContain(resCustomField2);
      });

      it('should accept null and undefined values', () => {
        const resCustomField: IResCustomField = sampleWithRequiredData;
        expectedResult = service.addResCustomFieldToCollectionIfMissing([], null, resCustomField, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(resCustomField);
      });

      it('should return initial array if no ResCustomField is added', () => {
        const resCustomFieldCollection: IResCustomField[] = [sampleWithRequiredData];
        expectedResult = service.addResCustomFieldToCollectionIfMissing(resCustomFieldCollection, undefined, null);
        expect(expectedResult).toEqual(resCustomFieldCollection);
      });
    });

    describe('compareResCustomField', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareResCustomField(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareResCustomField(entity1, entity2);
        const compareResult2 = service.compareResCustomField(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareResCustomField(entity1, entity2);
        const compareResult2 = service.compareResCustomField(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareResCustomField(entity1, entity2);
        const compareResult2 = service.compareResCustomField(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
