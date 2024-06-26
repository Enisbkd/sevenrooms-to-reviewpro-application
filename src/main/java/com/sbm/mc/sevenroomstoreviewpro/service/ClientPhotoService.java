package com.sbm.mc.sevenroomstoreviewpro.service;

import com.sbm.mc.sevenroomstoreviewpro.domain.ClientPhoto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.ClientPhoto}.
 */
public interface ClientPhotoService {
    /**
     * Save a clientPhoto.
     *
     * @param clientPhoto the entity to save.
     * @return the persisted entity.
     */
    ClientPhoto save(ClientPhoto clientPhoto);

    /**
     * Updates a clientPhoto.
     *
     * @param clientPhoto the entity to update.
     * @return the persisted entity.
     */
    ClientPhoto update(ClientPhoto clientPhoto);

    /**
     * Partially updates a clientPhoto.
     *
     * @param clientPhoto the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ClientPhoto> partialUpdate(ClientPhoto clientPhoto);

    /**
     * Get all the clientPhotos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ClientPhoto> findAll(Pageable pageable);

    /**
     * Get all the ClientPhoto where Client is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<ClientPhoto> findAllWhereClientIsNull();

    /**
     * Get the "id" clientPhoto.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClientPhoto> findOne(String id);

    /**
     * Delete the "id" clientPhoto.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
