package com.sbm.mc.sevenroomstoreviewpro.repository;

import com.sbm.mc.sevenroomstoreviewpro.domain.Venue;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Venue entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VenueRepository extends MongoRepository<Venue, String> {}
