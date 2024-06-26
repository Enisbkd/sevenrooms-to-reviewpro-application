package com.sbm.mc.sevenroomstoreviewpro.repository;

import com.sbm.mc.sevenroomstoreviewpro.domain.ClientVenueStats;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the ClientVenueStats entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientVenueStatsRepository extends MongoRepository<ClientVenueStats, String> {}
