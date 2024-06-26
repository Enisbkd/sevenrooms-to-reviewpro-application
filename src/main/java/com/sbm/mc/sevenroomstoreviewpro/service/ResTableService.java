package com.sbm.mc.sevenroomstoreviewpro.service;

import com.sbm.mc.sevenroomstoreviewpro.domain.ResTable;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.ResTable}.
 */
public interface ResTableService {
    /**
     * Save a resTable.
     *
     * @param resTable the entity to save.
     * @return the persisted entity.
     */
    ResTable save(ResTable resTable);

    /**
     * Updates a resTable.
     *
     * @param resTable the entity to update.
     * @return the persisted entity.
     */
    ResTable update(ResTable resTable);

    /**
     * Partially updates a resTable.
     *
     * @param resTable the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ResTable> partialUpdate(ResTable resTable);

    /**
     * Get all the resTables.
     *
     * @return the list of entities.
     */
    List<ResTable> findAll();

    /**
     * Get the "id" resTable.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ResTable> findOne(String id);

    /**
     * Delete the "id" resTable.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
