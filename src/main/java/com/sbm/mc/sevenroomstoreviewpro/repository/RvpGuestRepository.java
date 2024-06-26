package com.sbm.mc.sevenroomstoreviewpro.repository;

import com.sbm.mc.sevenroomstoreviewpro.domain.RvpGuest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the RvpGuest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RvpGuestRepository extends MongoRepository<RvpGuest, String> {}
