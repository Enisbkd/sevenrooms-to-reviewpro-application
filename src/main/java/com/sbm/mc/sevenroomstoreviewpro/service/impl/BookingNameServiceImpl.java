package com.sbm.mc.sevenroomstoreviewpro.service.impl;

import com.sbm.mc.sevenroomstoreviewpro.domain.BookingName;
import com.sbm.mc.sevenroomstoreviewpro.repository.BookingNameRepository;
import com.sbm.mc.sevenroomstoreviewpro.service.BookingNameService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.BookingName}.
 */
@Service
public class BookingNameServiceImpl implements BookingNameService {

    private static final Logger log = LoggerFactory.getLogger(BookingNameServiceImpl.class);

    private final BookingNameRepository bookingNameRepository;

    public BookingNameServiceImpl(BookingNameRepository bookingNameRepository) {
        this.bookingNameRepository = bookingNameRepository;
    }

    @Override
    public BookingName save(BookingName bookingName) {
        log.debug("Request to save BookingName : {}", bookingName);
        return bookingNameRepository.save(bookingName);
    }

    @Override
    public BookingName update(BookingName bookingName) {
        log.debug("Request to update BookingName : {}", bookingName);
        return bookingNameRepository.save(bookingName);
    }

    @Override
    public Optional<BookingName> partialUpdate(BookingName bookingName) {
        log.debug("Request to partially update BookingName : {}", bookingName);

        return bookingNameRepository
            .findById(bookingName.getId())
            .map(existingBookingName -> {
                if (bookingName.getName() != null) {
                    existingBookingName.setName(bookingName.getName());
                }

                return existingBookingName;
            })
            .map(bookingNameRepository::save);
    }

    @Override
    public Page<BookingName> findAll(Pageable pageable) {
        log.debug("Request to get all BookingNames");
        return bookingNameRepository.findAll(pageable);
    }

    @Override
    public Optional<BookingName> findOne(String id) {
        log.debug("Request to get BookingName : {}", id);
        return bookingNameRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete BookingName : {}", id);
        bookingNameRepository.deleteById(id);
    }
}
