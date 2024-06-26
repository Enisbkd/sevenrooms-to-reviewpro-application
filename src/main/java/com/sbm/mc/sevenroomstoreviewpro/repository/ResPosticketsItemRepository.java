package com.sbm.mc.sevenroomstoreviewpro.repository;

import com.sbm.mc.sevenroomstoreviewpro.domain.ResPosticketsItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the ResPosticketsItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResPosticketsItemRepository extends MongoRepository<ResPosticketsItem, String> {}
