package com.sbm.mc.sevenroomstoreviewpro.repository;

import com.sbm.mc.sevenroomstoreviewpro.domain.ResTable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the ResTable entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResTableRepository extends MongoRepository<ResTable, String> {}
