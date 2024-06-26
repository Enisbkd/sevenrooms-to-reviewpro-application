package com.sbm.mc.sevenroomstoreviewpro.service;

import com.sbm.mc.sevenroomstoreviewpro.domain.CustomField;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.CustomField}.
 */
public interface CustomFieldService {
    /**
     * Save a customField.
     *
     * @param customField the entity to save.
     * @return the persisted entity.
     */
    CustomField save(CustomField customField);

    /**
     * Updates a customField.
     *
     * @param customField the entity to update.
     * @return the persisted entity.
     */
    CustomField update(CustomField customField);

    /**
     * Partially updates a customField.
     *
     * @param customField the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CustomField> partialUpdate(CustomField customField);

    /**
     * Get all the customFields.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CustomField> findAll(Pageable pageable);

    /**
     * Get the "id" customField.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CustomField> findOne(String id);

    /**
     * Delete the "id" customField.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
