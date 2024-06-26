package com.sbm.mc.sevenroomstoreviewpro.repository;

import com.sbm.mc.sevenroomstoreviewpro.domain.ResCustomField;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the ResCustomField entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResCustomFieldRepository extends MongoRepository<ResCustomField, String> {}
