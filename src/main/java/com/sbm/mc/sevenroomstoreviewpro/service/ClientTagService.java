package com.sbm.mc.sevenroomstoreviewpro.service;

import com.sbm.mc.sevenroomstoreviewpro.domain.ClientTag;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.ClientTag}.
 */
public interface ClientTagService {
    /**
     * Save a clientTag.
     *
     * @param clientTag the entity to save.
     * @return the persisted entity.
     */
    ClientTag save(ClientTag clientTag);

    /**
     * Updates a clientTag.
     *
     * @param clientTag the entity to update.
     * @return the persisted entity.
     */
    ClientTag update(ClientTag clientTag);

    /**
     * Partially updates a clientTag.
     *
     * @param clientTag the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ClientTag> partialUpdate(ClientTag clientTag);

    /**
     * Get all the clientTags.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ClientTag> findAll(Pageable pageable);

    /**
     * Get the "id" clientTag.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClientTag> findOne(String id);

    /**
     * Delete the "id" clientTag.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
