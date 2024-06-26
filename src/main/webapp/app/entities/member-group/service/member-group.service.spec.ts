import { TestBed } from '@angular/core/testing';
import { provideHttpClientTesting, HttpTestingController } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IMemberGroup } from '../member-group.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../member-group.test-samples';

import { MemberGroupService } from './member-group.service';

const requireRestSample: IMemberGroup = {
  ...sampleWithRequiredData,
};

describe('MemberGroup Service', () => {
  let service: MemberGroupService;
  let httpMock: HttpTestingController;
  let expectedResult: IMemberGroup | IMemberGroup[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(MemberGroupService);
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

    it('should create a MemberGroup', () => {
      const memberGroup = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(memberGroup).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a MemberGroup', () => {
      const memberGroup = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(memberGroup).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a MemberGroup', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of MemberGroup', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a MemberGroup', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addMemberGroupToCollectionIfMissing', () => {
      it('should add a MemberGroup to an empty array', () => {
        const memberGroup: IMemberGroup = sampleWithRequiredData;
        expectedResult = service.addMemberGroupToCollectionIfMissing([], memberGroup);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(memberGroup);
      });

      it('should not add a MemberGroup to an array that contains it', () => {
        const memberGroup: IMemberGroup = sampleWithRequiredData;
        const memberGroupCollection: IMemberGroup[] = [
          {
            ...memberGroup,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addMemberGroupToCollectionIfMissing(memberGroupCollection, memberGroup);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a MemberGroup to an array that doesn't contain it", () => {
        const memberGroup: IMemberGroup = sampleWithRequiredData;
        const memberGroupCollection: IMemberGroup[] = [sampleWithPartialData];
        expectedResult = service.addMemberGroupToCollectionIfMissing(memberGroupCollection, memberGroup);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(memberGroup);
      });

      it('should add only unique MemberGroup to an array', () => {
        const memberGroupArray: IMemberGroup[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const memberGroupCollection: IMemberGroup[] = [sampleWithRequiredData];
        expectedResult = service.addMemberGroupToCollectionIfMissing(memberGroupCollection, ...memberGroupArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const memberGroup: IMemberGroup = sampleWithRequiredData;
        const memberGroup2: IMemberGroup = sampleWithPartialData;
        expectedResult = service.addMemberGroupToCollectionIfMissing([], memberGroup, memberGroup2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(memberGroup);
        expect(expectedResult).toContain(memberGroup2);
      });

      it('should accept null and undefined values', () => {
        const memberGroup: IMemberGroup = sampleWithRequiredData;
        expectedResult = service.addMemberGroupToCollectionIfMissing([], null, memberGroup, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(memberGroup);
      });

      it('should return initial array if no MemberGroup is added', () => {
        const memberGroupCollection: IMemberGroup[] = [sampleWithRequiredData];
        expectedResult = service.addMemberGroupToCollectionIfMissing(memberGroupCollection, undefined, null);
        expect(expectedResult).toEqual(memberGroupCollection);
      });
    });

    describe('compareMemberGroup', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareMemberGroup(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareMemberGroup(entity1, entity2);
        const compareResult2 = service.compareMemberGroup(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareMemberGroup(entity1, entity2);
        const compareResult2 = service.compareMemberGroup(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareMemberGroup(entity1, entity2);
        const compareResult2 = service.compareMemberGroup(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
