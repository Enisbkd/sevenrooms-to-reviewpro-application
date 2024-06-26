package com.sbm.mc.sevenroomstoreviewpro.repository;

import com.sbm.mc.sevenroomstoreviewpro.domain.ResTag;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the ResTag entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResTagRepository extends MongoRepository<ResTag, String> {}
