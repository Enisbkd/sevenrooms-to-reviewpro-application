package com.sbm.mc.sevenroomstoreviewpro.service;

import com.sbm.mc.sevenroomstoreviewpro.domain.Venue;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.Venue}.
 */
public interface VenueService {
    /**
     * Save a venue.
     *
     * @param venue the entity to save.
     * @return the persisted entity.
     */
    Venue save(Venue venue);

    /**
     * Updates a venue.
     *
     * @param venue the entity to update.
     * @return the persisted entity.
     */
    Venue update(Venue venue);

    /**
     * Partially updates a venue.
     *
     * @param venue the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Venue> partialUpdate(Venue venue);

    /**
     * Get all the venues.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Venue> findAll(Pageable pageable);

    /**
     * Get the "id" venue.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Venue> findOne(String id);

    /**
     * Delete the "id" venue.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
