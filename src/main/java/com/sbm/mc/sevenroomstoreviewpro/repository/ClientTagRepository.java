package com.sbm.mc.sevenroomstoreviewpro.repository;

import com.sbm.mc.sevenroomstoreviewpro.domain.ClientTag;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the ClientTag entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientTagRepository extends MongoRepository<ClientTag, String> {}
