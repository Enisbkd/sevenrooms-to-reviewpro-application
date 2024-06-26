package com.sbm.mc.sevenroomstoreviewpro.service;

import com.sbm.mc.sevenroomstoreviewpro.domain.ResPosTicket;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.ResPosTicket}.
 */
public interface ResPosTicketService {
    /**
     * Save a resPosTicket.
     *
     * @param resPosTicket the entity to save.
     * @return the persisted entity.
     */
    ResPosTicket save(ResPosTicket resPosTicket);

    /**
     * Updates a resPosTicket.
     *
     * @param resPosTicket the entity to update.
     * @return the persisted entity.
     */
    ResPosTicket update(ResPosTicket resPosTicket);

    /**
     * Partially updates a resPosTicket.
     *
     * @param resPosTicket the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ResPosTicket> partialUpdate(ResPosTicket resPosTicket);

    /**
     * Get all the resPosTickets.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ResPosTicket> findAll(Pageable pageable);

    /**
     * Get the "id" resPosTicket.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ResPosTicket> findOne(String id);

    /**
     * Delete the "id" resPosTicket.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
