package com.sbm.mc.sevenroomstoreviewpro.repository;

import com.sbm.mc.sevenroomstoreviewpro.domain.BookingName;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the BookingName entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BookingNameRepository extends MongoRepository<BookingName, String> {}
