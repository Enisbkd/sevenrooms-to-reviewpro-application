package com.sbm.mc.sevenroomstoreviewpro.service.impl;

import com.sbm.mc.sevenroomstoreviewpro.domain.RvpGuest;
import com.sbm.mc.sevenroomstoreviewpro.repository.RvpGuestRepository;
import com.sbm.mc.sevenroomstoreviewpro.service.RvpGuestService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.RvpGuest}.
 */
@Service
public class RvpGuestServiceImpl implements RvpGuestService {

    private static final Logger log = LoggerFactory.getLogger(RvpGuestServiceImpl.class);

    private final RvpGuestRepository rvpGuestRepository;

    public RvpGuestServiceImpl(RvpGuestRepository rvpGuestRepository) {
        this.rvpGuestRepository = rvpGuestRepository;
    }

    @Override
    public RvpGuest save(RvpGuest rvpGuest) {
        log.debug("Request to save RvpGuest : {}", rvpGuest);
        return rvpGuestRepository.save(rvpGuest);
    }

    @Override
    public RvpGuest update(RvpGuest rvpGuest) {
        log.debug("Request to update RvpGuest : {}", rvpGuest);
        return rvpGuestRepository.save(rvpGuest);
    }

    @Override
    public Optional<RvpGuest> partialUpdate(RvpGuest rvpGuest) {
        log.debug("Request to partially update RvpGuest : {}", rvpGuest);

        return rvpGuestRepository
            .findById(rvpGuest.getId())
            .map(existingRvpGuest -> {
                if (rvpGuest.getPmsId() != null) {
                    existingRvpGuest.setPmsId(rvpGuest.getPmsId());
                }
                if (rvpGuest.getFirstName() != null) {
                    existingRvpGuest.setFirstName(rvpGuest.getFirstName());
                }
                if (rvpGuest.getLastName() != null) {
                    existingRvpGuest.setLastName(rvpGuest.getLastName());
                }
                if (rvpGuest.getLanguage() != null) {
                    existingRvpGuest.setLanguage(rvpGuest.getLanguage());
                }
                if (rvpGuest.getCheckin() != null) {
                    existingRvpGuest.setCheckin(rvpGuest.getCheckin());
                }
                if (rvpGuest.getCheckout() != null) {
                    existingRvpGuest.setCheckout(rvpGuest.getCheckout());
                }
                if (rvpGuest.getEmail() != null) {
                    existingRvpGuest.setEmail(rvpGuest.getEmail());
                }
                if (rvpGuest.getEmailAlt() != null) {
                    existingRvpGuest.setEmailAlt(rvpGuest.getEmailAlt());
                }
                if (rvpGuest.getSalutation() != null) {
                    existingRvpGuest.setSalutation(rvpGuest.getSalutation());
                }

                return existingRvpGuest;
            })
            .map(rvpGuestRepository::save);
    }

    @Override
    public List<RvpGuest> findAll() {
        log.debug("Request to get all RvpGuests");
        return rvpGuestRepository.findAll();
    }

    @Override
    public Optional<RvpGuest> findOne(String id) {
        log.debug("Request to get RvpGuest : {}", id);
        return rvpGuestRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete RvpGuest : {}", id);
        rvpGuestRepository.deleteById(id);
    }
}
