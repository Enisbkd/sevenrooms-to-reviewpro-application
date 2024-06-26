package com.sbm.mc.sevenroomstoreviewpro.service;

import com.sbm.mc.sevenroomstoreviewpro.domain.ResTag;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.ResTag}.
 */
public interface ResTagService {
    /**
     * Save a resTag.
     *
     * @param resTag the entity to save.
     * @return the persisted entity.
     */
    ResTag save(ResTag resTag);

    /**
     * Updates a resTag.
     *
     * @param resTag the entity to update.
     * @return the persisted entity.
     */
    ResTag update(ResTag resTag);

    /**
     * Partially updates a resTag.
     *
     * @param resTag the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ResTag> partialUpdate(ResTag resTag);

    /**
     * Get all the resTags.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ResTag> findAll(Pageable pageable);

    /**
     * Get the "id" resTag.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ResTag> findOne(String id);

    /**
     * Delete the "id" resTag.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
