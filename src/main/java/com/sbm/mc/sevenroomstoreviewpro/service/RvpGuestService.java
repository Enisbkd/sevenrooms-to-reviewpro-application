package com.sbm.mc.sevenroomstoreviewpro.service;

import com.sbm.mc.sevenroomstoreviewpro.domain.RvpGuest;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.RvpGuest}.
 */
public interface RvpGuestService {
    /**
     * Save a rvpGuest.
     *
     * @param rvpGuest the entity to save.
     * @return the persisted entity.
     */
    RvpGuest save(RvpGuest rvpGuest);

    /**
     * Updates a rvpGuest.
     *
     * @param rvpGuest the entity to update.
     * @return the persisted entity.
     */
    RvpGuest update(RvpGuest rvpGuest);

    /**
     * Partially updates a rvpGuest.
     *
     * @param rvpGuest the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RvpGuest> partialUpdate(RvpGuest rvpGuest);

    /**
     * Get all the rvpGuests.
     *
     * @return the list of entities.
     */
    List<RvpGuest> findAll();

    /**
     * Get the "id" rvpGuest.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RvpGuest> findOne(String id);

    /**
     * Delete the "id" rvpGuest.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
