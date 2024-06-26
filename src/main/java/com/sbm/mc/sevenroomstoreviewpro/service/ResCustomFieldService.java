package com.sbm.mc.sevenroomstoreviewpro.service;

import com.sbm.mc.sevenroomstoreviewpro.domain.ResCustomField;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.ResCustomField}.
 */
public interface ResCustomFieldService {
    /**
     * Save a resCustomField.
     *
     * @param resCustomField the entity to save.
     * @return the persisted entity.
     */
    ResCustomField save(ResCustomField resCustomField);

    /**
     * Updates a resCustomField.
     *
     * @param resCustomField the entity to update.
     * @return the persisted entity.
     */
    ResCustomField update(ResCustomField resCustomField);

    /**
     * Partially updates a resCustomField.
     *
     * @param resCustomField the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ResCustomField> partialUpdate(ResCustomField resCustomField);

    /**
     * Get all the resCustomFields.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ResCustomField> findAll(Pageable pageable);

    /**
     * Get the "id" resCustomField.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ResCustomField> findOne(String id);

    /**
     * Delete the "id" resCustomField.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
