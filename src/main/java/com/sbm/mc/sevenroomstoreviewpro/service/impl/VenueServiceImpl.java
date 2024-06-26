package com.sbm.mc.sevenroomstoreviewpro.service.impl;

import com.sbm.mc.sevenroomstoreviewpro.domain.Venue;
import com.sbm.mc.sevenroomstoreviewpro.repository.VenueRepository;
import com.sbm.mc.sevenroomstoreviewpro.service.VenueService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.Venue}.
 */
@Service
public class VenueServiceImpl implements VenueService {

    private static final Logger log = LoggerFactory.getLogger(VenueServiceImpl.class);

    private final VenueRepository venueRepository;

    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public Venue save(Venue venue) {
        log.debug("Request to save Venue : {}", venue);
        return venueRepository.save(venue);
    }

    @Override
    public Venue update(Venue venue) {
        log.debug("Request to update Venue : {}", venue);
        return venueRepository.save(venue);
    }

    @Override
    public Optional<Venue> partialUpdate(Venue venue) {
        log.debug("Request to partially update Venue : {}", venue);

        return venueRepository
            .findById(venue.getId())
            .map(existingVenue -> {
                if (venue.getAddress() != null) {
                    existingVenue.setAddress(venue.getAddress());
                }
                if (venue.getBlackLogo() != null) {
                    existingVenue.setBlackLogo(venue.getBlackLogo());
                }
                if (venue.getCountry() != null) {
                    existingVenue.setCountry(venue.getCountry());
                }
                if (venue.getCrossStreet() != null) {
                    existingVenue.setCrossStreet(venue.getCrossStreet());
                }
                if (venue.getCurrencyCode() != null) {
                    existingVenue.setCurrencyCode(venue.getCurrencyCode());
                }
                if (venue.getExternalVenueId() != null) {
                    existingVenue.setExternalVenueId(venue.getExternalVenueId());
                }
                if (venue.getFullDiningBackend() != null) {
                    existingVenue.setFullDiningBackend(venue.getFullDiningBackend());
                }
                if (venue.getGridEnabled() != null) {
                    existingVenue.setGridEnabled(venue.getGridEnabled());
                }
                if (venue.getVenueId() != null) {
                    existingVenue.setVenueId(venue.getVenueId());
                }
                if (venue.getInternalName() != null) {
                    existingVenue.setInternalName(venue.getInternalName());
                }
                if (venue.getMembershipEnabled() != null) {
                    existingVenue.setMembershipEnabled(venue.getMembershipEnabled());
                }
                if (venue.getName() != null) {
                    existingVenue.setName(venue.getName());
                }
                if (venue.getNeighborhood() != null) {
                    existingVenue.setNeighborhood(venue.getNeighborhood());
                }
                if (venue.getPhoneNumber() != null) {
                    existingVenue.setPhoneNumber(venue.getPhoneNumber());
                }
                if (venue.getPolicy() != null) {
                    existingVenue.setPolicy(venue.getPolicy());
                }
                if (venue.getPostalCode() != null) {
                    existingVenue.setPostalCode(venue.getPostalCode());
                }
                if (venue.getPrimaryColor() != null) {
                    existingVenue.setPrimaryColor(venue.getPrimaryColor());
                }
                if (venue.getSecondaryColor() != null) {
                    existingVenue.setSecondaryColor(venue.getSecondaryColor());
                }
                if (venue.getState() != null) {
                    existingVenue.setState(venue.getState());
                }
                if (venue.getUniqueConfirmationPrefix() != null) {
                    existingVenue.setUniqueConfirmationPrefix(venue.getUniqueConfirmationPrefix());
                }
                if (venue.getVenueClass() != null) {
                    existingVenue.setVenueClass(venue.getVenueClass());
                }
                if (venue.getVenueGroupId() != null) {
                    existingVenue.setVenueGroupId(venue.getVenueGroupId());
                }
                if (venue.getVenueGroupName() != null) {
                    existingVenue.setVenueGroupName(venue.getVenueGroupName());
                }
                if (venue.getVenueUrlKey() != null) {
                    existingVenue.setVenueUrlKey(venue.getVenueUrlKey());
                }
                if (venue.getWebsite() != null) {
                    existingVenue.setWebsite(venue.getWebsite());
                }
                if (venue.getWhiteLogo() != null) {
                    existingVenue.setWhiteLogo(venue.getWhiteLogo());
                }

                return existingVenue;
            })
            .map(venueRepository::save);
    }

    @Override
    public Page<Venue> findAll(Pageable pageable) {
        log.debug("Request to get all Venues");
        return venueRepository.findAll(pageable);
    }

    @Override
    public Optional<Venue> findOne(String id) {
        log.debug("Request to get Venue : {}", id);
        return venueRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Venue : {}", id);
        venueRepository.deleteById(id);
    }
}
