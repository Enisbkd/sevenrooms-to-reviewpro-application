package com.sbm.mc.sevenroomstoreviewpro.service.impl;

import com.sbm.mc.sevenroomstoreviewpro.domain.Client;
import com.sbm.mc.sevenroomstoreviewpro.repository.ClientRepository;
import com.sbm.mc.sevenroomstoreviewpro.service.ClientService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.Client}.
 */
@Service
public class ClientServiceImpl implements ClientService {

    private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client save(Client client) {
        log.debug("Request to save Client : {}", client);
        return clientRepository.save(client);
    }

    @Override
    public Client update(Client client) {
        log.debug("Request to update Client : {}", client);
        return clientRepository.save(client);
    }

    @Override
    public Optional<Client> partialUpdate(Client client) {
        log.debug("Request to partially update Client : {}", client);

        return clientRepository
            .findById(client.getId())
            .map(existingClient -> {
                if (client.getClientId() != null) {
                    existingClient.setClientId(client.getClientId());
                }
                if (client.getCreatedDate() != null) {
                    existingClient.setCreatedDate(client.getCreatedDate());
                }
                if (client.getUpdatedDate() != null) {
                    existingClient.setUpdatedDate(client.getUpdatedDate());
                }
                if (client.getDeletedDate() != null) {
                    existingClient.setDeletedDate(client.getDeletedDate());
                }
                if (client.getLastname() != null) {
                    existingClient.setLastname(client.getLastname());
                }
                if (client.getFirstname() != null) {
                    existingClient.setFirstname(client.getFirstname());
                }
                if (client.getGender() != null) {
                    existingClient.setGender(client.getGender());
                }
                if (client.getSalutation() != null) {
                    existingClient.setSalutation(client.getSalutation());
                }
                if (client.getTitle() != null) {
                    existingClient.setTitle(client.getTitle());
                }
                if (client.getBirthdayDay() != null) {
                    existingClient.setBirthdayDay(client.getBirthdayDay());
                }
                if (client.getBirthdayMonth() != null) {
                    existingClient.setBirthdayMonth(client.getBirthdayMonth());
                }
                if (client.getBirthdayAltMonth() != null) {
                    existingClient.setBirthdayAltMonth(client.getBirthdayAltMonth());
                }
                if (client.getAnniversaryDay() != null) {
                    existingClient.setAnniversaryDay(client.getAnniversaryDay());
                }
                if (client.getAnniversaryMonth() != null) {
                    existingClient.setAnniversaryMonth(client.getAnniversaryMonth());
                }
                if (client.getCompany() != null) {
                    existingClient.setCompany(client.getCompany());
                }
                if (client.getEmail() != null) {
                    existingClient.setEmail(client.getEmail());
                }
                if (client.getEmailAlt() != null) {
                    existingClient.setEmailAlt(client.getEmailAlt());
                }
                if (client.getPhoneNumber() != null) {
                    existingClient.setPhoneNumber(client.getPhoneNumber());
                }
                if (client.getPhoneNumberlocale() != null) {
                    existingClient.setPhoneNumberlocale(client.getPhoneNumberlocale());
                }
                if (client.getPhoneNumberalt() != null) {
                    existingClient.setPhoneNumberalt(client.getPhoneNumberalt());
                }
                if (client.getPhoneNumberaltlocale() != null) {
                    existingClient.setPhoneNumberaltlocale(client.getPhoneNumberaltlocale());
                }
                if (client.getAddress() != null) {
                    existingClient.setAddress(client.getAddress());
                }
                if (client.getAddress2() != null) {
                    existingClient.setAddress2(client.getAddress2());
                }
                if (client.getCity() != null) {
                    existingClient.setCity(client.getCity());
                }
                if (client.getPostalCode() != null) {
                    existingClient.setPostalCode(client.getPostalCode());
                }
                if (client.getState() != null) {
                    existingClient.setState(client.getState());
                }
                if (client.getCountry() != null) {
                    existingClient.setCountry(client.getCountry());
                }
                if (client.getIsContactPrivate() != null) {
                    existingClient.setIsContactPrivate(client.getIsContactPrivate());
                }
                if (client.getIsOnetimeGuest() != null) {
                    existingClient.setIsOnetimeGuest(client.getIsOnetimeGuest());
                }
                if (client.getStatus() != null) {
                    existingClient.setStatus(client.getStatus());
                }
                if (client.getLoyaltyId() != null) {
                    existingClient.setLoyaltyId(client.getLoyaltyId());
                }
                if (client.getLoyaltyRank() != null) {
                    existingClient.setLoyaltyRank(client.getLoyaltyRank());
                }
                if (client.getLoyaltyTier() != null) {
                    existingClient.setLoyaltyTier(client.getLoyaltyTier());
                }
                if (client.getMarketingOptin() != null) {
                    existingClient.setMarketingOptin(client.getMarketingOptin());
                }
                if (client.getMarketingOptints() != null) {
                    existingClient.setMarketingOptints(client.getMarketingOptints());
                }
                if (client.getMarketingOptOutts() != null) {
                    existingClient.setMarketingOptOutts(client.getMarketingOptOutts());
                }
                if (client.getHasBillingProfile() != null) {
                    existingClient.setHasBillingProfile(client.getHasBillingProfile());
                }
                if (client.getNotes() != null) {
                    existingClient.setNotes(client.getNotes());
                }
                if (client.getPrivateNotes() != null) {
                    existingClient.setPrivateNotes(client.getPrivateNotes());
                }
                if (client.getTags() != null) {
                    existingClient.setTags(client.getTags());
                }
                if (client.getTotalVisits() != null) {
                    existingClient.setTotalVisits(client.getTotalVisits());
                }
                if (client.getTotalCovers() != null) {
                    existingClient.setTotalCovers(client.getTotalCovers());
                }
                if (client.getTotalCancellations() != null) {
                    existingClient.setTotalCancellations(client.getTotalCancellations());
                }
                if (client.getTotalNoShows() != null) {
                    existingClient.setTotalNoShows(client.getTotalNoShows());
                }
                if (client.getTotalSpend() != null) {
                    existingClient.setTotalSpend(client.getTotalSpend());
                }
                if (client.getTotalSpendPerCover() != null) {
                    existingClient.setTotalSpendPerCover(client.getTotalSpendPerCover());
                }
                if (client.getTotalspendPerVisit() != null) {
                    existingClient.setTotalspendPerVisit(client.getTotalspendPerVisit());
                }
                if (client.getAvgRating() != null) {
                    existingClient.setAvgRating(client.getAvgRating());
                }
                if (client.getReferenceCode() != null) {
                    existingClient.setReferenceCode(client.getReferenceCode());
                }
                if (client.getExternalUserId() != null) {
                    existingClient.setExternalUserId(client.getExternalUserId());
                }
                if (client.getVenueGroupId() != null) {
                    existingClient.setVenueGroupId(client.getVenueGroupId());
                }
                if (client.getBirthdayAltDay() != null) {
                    existingClient.setBirthdayAltDay(client.getBirthdayAltDay());
                }
                if (client.getUserId() != null) {
                    existingClient.setUserId(client.getUserId());
                }
                if (client.getUserName() != null) {
                    existingClient.setUserName(client.getUserName());
                }
                if (client.getTotalOrderCount() != null) {
                    existingClient.setTotalOrderCount(client.getTotalOrderCount());
                }
                if (client.getPreferredLanguageCode() != null) {
                    existingClient.setPreferredLanguageCode(client.getPreferredLanguageCode());
                }

                return existingClient;
            })
            .map(clientRepository::save);
    }

    @Override
    public Page<Client> findAll(Pageable pageable) {
        log.debug("Request to get all Clients");
        return clientRepository.findAll(pageable);
    }

    @Override
    public Optional<Client> findOne(String id) {
        log.debug("Request to get Client : {}", id);
        return clientRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Client : {}", id);
        clientRepository.deleteById(id);
    }
}
