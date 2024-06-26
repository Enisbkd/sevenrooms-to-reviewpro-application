package com.sbm.mc.sevenroomstoreviewpro.service;

import com.sbm.mc.sevenroomstoreviewpro.domain.ClientVenueStats;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.ClientVenueStats}.
 */
public interface ClientVenueStatsService {
    /**
     * Save a clientVenueStats.
     *
     * @param clientVenueStats the entity to save.
     * @return the persisted entity.
     */
    ClientVenueStats save(ClientVenueStats clientVenueStats);

    /**
     * Updates a clientVenueStats.
     *
     * @param clientVenueStats the entity to update.
     * @return the persisted entity.
     */
    ClientVenueStats update(ClientVenueStats clientVenueStats);

    /**
     * Partially updates a clientVenueStats.
     *
     * @param clientVenueStats the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ClientVenueStats> partialUpdate(ClientVenueStats clientVenueStats);

    /**
     * Get all the clientVenueStats.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ClientVenueStats> findAll(Pageable pageable);

    /**
     * Get all the ClientVenueStats where Client is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<ClientVenueStats> findAllWhereClientIsNull();

    /**
     * Get the "id" clientVenueStats.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClientVenueStats> findOne(String id);

    /**
     * Delete the "id" clientVenueStats.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
