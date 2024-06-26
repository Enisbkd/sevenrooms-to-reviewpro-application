package com.sbm.mc.sevenroomstoreviewpro.service;

import com.sbm.mc.sevenroomstoreviewpro.domain.ResPosticketsItem;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.ResPosticketsItem}.
 */
public interface ResPosticketsItemService {
    /**
     * Save a resPosticketsItem.
     *
     * @param resPosticketsItem the entity to save.
     * @return the persisted entity.
     */
    ResPosticketsItem save(ResPosticketsItem resPosticketsItem);

    /**
     * Updates a resPosticketsItem.
     *
     * @param resPosticketsItem the entity to update.
     * @return the persisted entity.
     */
    ResPosticketsItem update(ResPosticketsItem resPosticketsItem);

    /**
     * Partially updates a resPosticketsItem.
     *
     * @param resPosticketsItem the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ResPosticketsItem> partialUpdate(ResPosticketsItem resPosticketsItem);

    /**
     * Get all the resPosticketsItems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ResPosticketsItem> findAll(Pageable pageable);

    /**
     * Get the "id" resPosticketsItem.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ResPosticketsItem> findOne(String id);

    /**
     * Delete the "id" resPosticketsItem.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
