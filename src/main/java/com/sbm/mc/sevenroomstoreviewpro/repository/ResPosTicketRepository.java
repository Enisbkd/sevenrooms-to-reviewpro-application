package com.sbm.mc.sevenroomstoreviewpro.repository;

import com.sbm.mc.sevenroomstoreviewpro.domain.ResPosTicket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the ResPosTicket entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResPosTicketRepository extends MongoRepository<ResPosTicket, String> {}
