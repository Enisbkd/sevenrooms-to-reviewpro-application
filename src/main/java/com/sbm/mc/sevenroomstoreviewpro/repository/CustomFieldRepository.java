package com.sbm.mc.sevenroomstoreviewpro.repository;

import com.sbm.mc.sevenroomstoreviewpro.domain.CustomField;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the CustomField entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomFieldRepository extends MongoRepository<CustomField, String> {}
