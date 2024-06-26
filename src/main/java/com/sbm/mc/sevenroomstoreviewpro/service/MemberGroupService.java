package com.sbm.mc.sevenroomstoreviewpro.service;

import com.sbm.mc.sevenroomstoreviewpro.domain.MemberGroup;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.MemberGroup}.
 */
public interface MemberGroupService {
    /**
     * Save a memberGroup.
     *
     * @param memberGroup the entity to save.
     * @return the persisted entity.
     */
    MemberGroup save(MemberGroup memberGroup);

    /**
     * Updates a memberGroup.
     *
     * @param memberGroup the entity to update.
     * @return the persisted entity.
     */
    MemberGroup update(MemberGroup memberGroup);

    /**
     * Partially updates a memberGroup.
     *
     * @param memberGroup the entity to update partially.
     * @return the persisted entity.
     */
    Optional<MemberGroup> partialUpdate(MemberGroup memberGroup);

    /**
     * Get all the memberGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MemberGroup> findAll(Pageable pageable);

    /**
     * Get the "id" memberGroup.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MemberGroup> findOne(String id);

    /**
     * Delete the "id" memberGroup.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
