package com.sbm.mc.sevenroomstoreviewpro.service;

import com.sbm.mc.sevenroomstoreviewpro.domain.BookingName;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.BookingName}.
 */
public interface BookingNameService {
    /**
     * Save a bookingName.
     *
     * @param bookingName the entity to save.
     * @return the persisted entity.
     */
    BookingName save(BookingName bookingName);

    /**
     * Updates a bookingName.
     *
     * @param bookingName the entity to update.
     * @return the persisted entity.
     */
    BookingName update(BookingName bookingName);

    /**
     * Partially updates a bookingName.
     *
     * @param bookingName the entity to update partially.
     * @return the persisted entity.
     */
    Optional<BookingName> partialUpdate(BookingName bookingName);

    /**
     * Get all the bookingNames.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BookingName> findAll(Pageable pageable);

    /**
     * Get the "id" bookingName.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BookingName> findOne(String id);

    /**
     * Delete the "id" bookingName.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
