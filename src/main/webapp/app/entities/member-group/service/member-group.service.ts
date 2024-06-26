import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IMemberGroup, NewMemberGroup } from '../member-group.model';

export type PartialUpdateMemberGroup = Partial<IMemberGroup> & Pick<IMemberGroup, 'id'>;

export type EntityResponseType = HttpResponse<IMemberGroup>;
export type EntityArrayResponseType = HttpResponse<IMemberGroup[]>;

@Injectable({ providedIn: 'root' })
export class MemberGroupService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/member-groups');

  create(memberGroup: NewMemberGroup): Observable<EntityResponseType> {
    return this.http.post<IMemberGroup>(this.resourceUrl, memberGroup, { observe: 'response' });
  }

  update(memberGroup: IMemberGroup): Observable<EntityResponseType> {
    return this.http.put<IMemberGroup>(`${this.resourceUrl}/${this.getMemberGroupIdentifier(memberGroup)}`, memberGroup, {
      observe: 'response',
    });
  }

  partialUpdate(memberGroup: PartialUpdateMemberGroup): Observable<EntityResponseType> {
    return this.http.patch<IMemberGroup>(`${this.resourceUrl}/${this.getMemberGroupIdentifier(memberGroup)}`, memberGroup, {
      observe: 'response',
    });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IMemberGroup>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMemberGroup[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getMemberGroupIdentifier(memberGroup: Pick<IMemberGroup, 'id'>): string {
    return memberGroup.id;
  }

  compareMemberGroup(o1: Pick<IMemberGroup, 'id'> | null, o2: Pick<IMemberGroup, 'id'> | null): boolean {
    return o1 && o2 ? this.getMemberGroupIdentifier(o1) === this.getMemberGroupIdentifier(o2) : o1 === o2;
  }

  addMemberGroupToCollectionIfMissing<Type extends Pick<IMemberGroup, 'id'>>(
    memberGroupCollection: Type[],
    ...memberGroupsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const memberGroups: Type[] = memberGroupsToCheck.filter(isPresent);
    if (memberGroups.length > 0) {
      const memberGroupCollectionIdentifiers = memberGroupCollection.map(memberGroupItem => this.getMemberGroupIdentifier(memberGroupItem));
      const memberGroupsToAdd = memberGroups.filter(memberGroupItem => {
        const memberGroupIdentifier = this.getMemberGroupIdentifier(memberGroupItem);
        if (memberGroupCollectionIdentifiers.includes(memberGroupIdentifier)) {
          return false;
        }
        memberGroupCollectionIdentifiers.push(memberGroupIdentifier);
        return true;
      });
      return [...memberGroupsToAdd, ...memberGroupCollection];
    }
    return memberGroupCollection;
  }
}
