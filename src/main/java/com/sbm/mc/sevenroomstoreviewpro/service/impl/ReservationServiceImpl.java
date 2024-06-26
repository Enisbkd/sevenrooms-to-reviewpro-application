package com.sbm.mc.sevenroomstoreviewpro.service.impl;

import com.sbm.mc.sevenroomstoreviewpro.domain.Reservation;
import com.sbm.mc.sevenroomstoreviewpro.repository.ReservationRepository;
import com.sbm.mc.sevenroomstoreviewpro.service.ReservationService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.Reservation}.
 */
@Service
public class ReservationServiceImpl implements ReservationService {

    private static final Logger log = LoggerFactory.getLogger(ReservationServiceImpl.class);

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation save(Reservation reservation) {
        log.debug("Request to save Reservation : {}", reservation);
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation update(Reservation reservation) {
        log.debug("Request to update Reservation : {}", reservation);
        return reservationRepository.save(reservation);
    }

    @Override
    public Optional<Reservation> partialUpdate(Reservation reservation) {
        log.debug("Request to partially update Reservation : {}", reservation);

        return reservationRepository
            .findById(reservation.getId())
            .map(existingReservation -> {
                if (reservation.getResvId() != null) {
                    existingReservation.setResvId(reservation.getResvId());
                }
                if (reservation.getCreated() != null) {
                    existingReservation.setCreated(reservation.getCreated());
                }
                if (reservation.getUpdated() != null) {
                    existingReservation.setUpdated(reservation.getUpdated());
                }
                if (reservation.getDeleted() != null) {
                    existingReservation.setDeleted(reservation.getDeleted());
                }
                if (reservation.getVenueGroupClientId() != null) {
                    existingReservation.setVenueGroupClientId(reservation.getVenueGroupClientId());
                }
                if (reservation.getVenueGroupId() != null) {
                    existingReservation.setVenueGroupId(reservation.getVenueGroupId());
                }
                if (reservation.getVenueId() != null) {
                    existingReservation.setVenueId(reservation.getVenueId());
                }
                if (reservation.getDate() != null) {
                    existingReservation.setDate(reservation.getDate());
                }
                if (reservation.getDuration() != null) {
                    existingReservation.setDuration(reservation.getDuration());
                }
                if (reservation.getCheckNumbers() != null) {
                    existingReservation.setCheckNumbers(reservation.getCheckNumbers());
                }
                if (reservation.getShiftCategory() != null) {
                    existingReservation.setShiftCategory(reservation.getShiftCategory());
                }
                if (reservation.getShiftPersistentId() != null) {
                    existingReservation.setShiftPersistentId(reservation.getShiftPersistentId());
                }
                if (reservation.getMaxGuests() != null) {
                    existingReservation.setMaxGuests(reservation.getMaxGuests());
                }
                if (reservation.getMfratioMale() != null) {
                    existingReservation.setMfratioMale(reservation.getMfratioMale());
                }
                if (reservation.getMfratioFemale() != null) {
                    existingReservation.setMfratioFemale(reservation.getMfratioFemale());
                }
                if (reservation.getStatus() != null) {
                    existingReservation.setStatus(reservation.getStatus());
                }
                if (reservation.getStatusDisplay() != null) {
                    existingReservation.setStatusDisplay(reservation.getStatusDisplay());
                }
                if (reservation.getStatusSimple() != null) {
                    existingReservation.setStatusSimple(reservation.getStatusSimple());
                }
                if (reservation.getAccessPersistentId() != null) {
                    existingReservation.setAccessPersistentId(reservation.getAccessPersistentId());
                }
                if (reservation.getArrivedGuests() != null) {
                    existingReservation.setArrivedGuests(reservation.getArrivedGuests());
                }
                if (reservation.getIsvip() != null) {
                    existingReservation.setIsvip(reservation.getIsvip());
                }
                if (reservation.getBookedby() != null) {
                    existingReservation.setBookedby(reservation.getBookedby());
                }
                if (reservation.getClientReferenceCode() != null) {
                    existingReservation.setClientReferenceCode(reservation.getClientReferenceCode());
                }
                if (reservation.getLastname() != null) {
                    existingReservation.setLastname(reservation.getLastname());
                }
                if (reservation.getFirstname() != null) {
                    existingReservation.setFirstname(reservation.getFirstname());
                }
                if (reservation.getEmail() != null) {
                    existingReservation.setEmail(reservation.getEmail());
                }
                if (reservation.getPhoneNumber() != null) {
                    existingReservation.setPhoneNumber(reservation.getPhoneNumber());
                }
                if (reservation.getAddress() != null) {
                    existingReservation.setAddress(reservation.getAddress());
                }
                if (reservation.getAddress2() != null) {
                    existingReservation.setAddress2(reservation.getAddress2());
                }
                if (reservation.getCity() != null) {
                    existingReservation.setCity(reservation.getCity());
                }
                if (reservation.getPostalCode() != null) {
                    existingReservation.setPostalCode(reservation.getPostalCode());
                }
                if (reservation.getState() != null) {
                    existingReservation.setState(reservation.getState());
                }
                if (reservation.getCountry() != null) {
                    existingReservation.setCountry(reservation.getCountry());
                }
                if (reservation.getLoyaltyId() != null) {
                    existingReservation.setLoyaltyId(reservation.getLoyaltyId());
                }
                if (reservation.getLoyaltyRank() != null) {
                    existingReservation.setLoyaltyRank(reservation.getLoyaltyRank());
                }
                if (reservation.getLoyaltyTier() != null) {
                    existingReservation.setLoyaltyTier(reservation.getLoyaltyTier());
                }
                if (reservation.getNotes() != null) {
                    existingReservation.setNotes(reservation.getNotes());
                }
                if (reservation.getArrivalTime() != null) {
                    existingReservation.setArrivalTime(reservation.getArrivalTime());
                }
                if (reservation.getSeatedTime() != null) {
                    existingReservation.setSeatedTime(reservation.getSeatedTime());
                }
                if (reservation.getLeftTime() != null) {
                    existingReservation.setLeftTime(reservation.getLeftTime());
                }
                if (reservation.getClientRequests() != null) {
                    existingReservation.setClientRequests(reservation.getClientRequests());
                }
                if (reservation.getComps() != null) {
                    existingReservation.setComps(reservation.getComps());
                }
                if (reservation.getCompsPriceType() != null) {
                    existingReservation.setCompsPriceType(reservation.getCompsPriceType());
                }
                if (reservation.getCostOption() != null) {
                    existingReservation.setCostOption(reservation.getCostOption());
                }
                if (reservation.getPolicy() != null) {
                    existingReservation.setPolicy(reservation.getPolicy());
                }
                if (reservation.getMinPrice() != null) {
                    existingReservation.setMinPrice(reservation.getMinPrice());
                }
                if (reservation.getPrePayment() != null) {
                    existingReservation.setPrePayment(reservation.getPrePayment());
                }
                if (reservation.getOnsitePayment() != null) {
                    existingReservation.setOnsitePayment(reservation.getOnsitePayment());
                }
                if (reservation.getTotalPayment() != null) {
                    existingReservation.setTotalPayment(reservation.getTotalPayment());
                }
                if (reservation.getPaidBy() != null) {
                    existingReservation.setPaidBy(reservation.getPaidBy());
                }
                if (reservation.getServedBy() != null) {
                    existingReservation.setServedBy(reservation.getServedBy());
                }
                if (reservation.getRating() != null) {
                    existingReservation.setRating(reservation.getRating());
                }
                if (reservation.getProblems() != null) {
                    existingReservation.setProblems(reservation.getProblems());
                }
                if (reservation.getAutoAssignments() != null) {
                    existingReservation.setAutoAssignments(reservation.getAutoAssignments());
                }
                if (reservation.getExternalClientId() != null) {
                    existingReservation.setExternalClientId(reservation.getExternalClientId());
                }
                if (reservation.getExternalId() != null) {
                    existingReservation.setExternalId(reservation.getExternalId());
                }
                if (reservation.getExternalReferenceCode() != null) {
                    existingReservation.setExternalReferenceCode(reservation.getExternalReferenceCode());
                }
                if (reservation.getExternalUserId() != null) {
                    existingReservation.setExternalUserId(reservation.getExternalUserId());
                }
                if (reservation.getModifyReservationLink() != null) {
                    existingReservation.setModifyReservationLink(reservation.getModifyReservationLink());
                }
                if (reservation.getReferenceCode() != null) {
                    existingReservation.setReferenceCode(reservation.getReferenceCode());
                }
                if (reservation.getReservationSmsOptin() != null) {
                    existingReservation.setReservationSmsOptin(reservation.getReservationSmsOptin());
                }
                if (reservation.getReservationType() != null) {
                    existingReservation.setReservationType(reservation.getReservationType());
                }
                if (reservation.getSendReminderEmail() != null) {
                    existingReservation.setSendReminderEmail(reservation.getSendReminderEmail());
                }
                if (reservation.getSendreminderSms() != null) {
                    existingReservation.setSendreminderSms(reservation.getSendreminderSms());
                }
                if (reservation.getSourceClientId() != null) {
                    existingReservation.setSourceClientId(reservation.getSourceClientId());
                }
                if (reservation.getUserId() != null) {
                    existingReservation.setUserId(reservation.getUserId());
                }
                if (reservation.getUserName() != null) {
                    existingReservation.setUserName(reservation.getUserName());
                }

                return existingReservation;
            })
            .map(reservationRepository::save);
    }

    @Override
    public Page<Reservation> findAll(Pageable pageable) {
        log.debug("Request to get all Reservations");
        return reservationRepository.findAll(pageable);
    }

    @Override
    public Optional<Reservation> findOne(String id) {
        log.debug("Request to get Reservation : {}", id);
        return reservationRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Reservation : {}", id);
        reservationRepository.deleteById(id);
    }
}
