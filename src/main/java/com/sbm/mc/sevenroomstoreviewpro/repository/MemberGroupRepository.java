package com.sbm.mc.sevenroomstoreviewpro.repository;

import com.sbm.mc.sevenroomstoreviewpro.domain.MemberGroup;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the MemberGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MemberGroupRepository extends MongoRepository<MemberGroup, String> {}
