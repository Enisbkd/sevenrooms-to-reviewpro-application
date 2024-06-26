package com.sbm.mc.sevenroomstoreviewpro.service.impl;

import com.sbm.mc.sevenroomstoreviewpro.domain.ClientVenueStats;
import com.sbm.mc.sevenroomstoreviewpro.repository.ClientVenueStatsRepository;
import com.sbm.mc.sevenroomstoreviewpro.service.ClientVenueStatsService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.ClientVenueStats}.
 */
@Service
public class ClientVenueStatsServiceImpl implements ClientVenueStatsService {

    private static final Logger log = LoggerFactory.getLogger(ClientVenueStatsServiceImpl.class);

    private final ClientVenueStatsRepository clientVenueStatsRepository;

    public ClientVenueStatsServiceImpl(ClientVenueStatsRepository clientVenueStatsRepository) {
        this.clientVenueStatsRepository = clientVenueStatsRepository;
    }

    @Override
    public ClientVenueStats save(ClientVenueStats clientVenueStats) {
        log.debug("Request to save ClientVenueStats : {}", clientVenueStats);
        return clientVenueStatsRepository.save(clientVenueStats);
    }

    @Override
    public ClientVenueStats update(ClientVenueStats clientVenueStats) {
        log.debug("Request to update ClientVenueStats : {}", clientVenueStats);
        return clientVenueStatsRepository.save(clientVenueStats);
    }

    @Override
    public Optional<ClientVenueStats> partialUpdate(ClientVenueStats clientVenueStats) {
        log.debug("Request to partially update ClientVenueStats : {}", clientVenueStats);

        return clientVenueStatsRepository
            .findById(clientVenueStats.getId())
            .map(existingClientVenueStats -> {
                if (clientVenueStats.getTotalSpendLocalperCover() != null) {
                    existingClientVenueStats.setTotalSpendLocalperCover(clientVenueStats.getTotalSpendLocalperCover());
                }
                if (clientVenueStats.getLastVisitDate() != null) {
                    existingClientVenueStats.setLastVisitDate(clientVenueStats.getLastVisitDate());
                }
                if (clientVenueStats.getTotalCancellations() != null) {
                    existingClientVenueStats.setTotalCancellations(clientVenueStats.getTotalCancellations());
                }
                if (clientVenueStats.getTotalCovers() != null) {
                    existingClientVenueStats.setTotalCovers(clientVenueStats.getTotalCovers());
                }
                if (clientVenueStats.getAvgRating() != null) {
                    existingClientVenueStats.setAvgRating(clientVenueStats.getAvgRating());
                }
                if (clientVenueStats.getTotalSpendperCover() != null) {
                    existingClientVenueStats.setTotalSpendperCover(clientVenueStats.getTotalSpendperCover());
                }
                if (clientVenueStats.getTotalSpend() != null) {
                    existingClientVenueStats.setTotalSpend(clientVenueStats.getTotalSpend());
                }
                if (clientVenueStats.getTotalNoShows() != null) {
                    existingClientVenueStats.setTotalNoShows(clientVenueStats.getTotalNoShows());
                }
                if (clientVenueStats.getNumRatings() != null) {
                    existingClientVenueStats.setNumRatings(clientVenueStats.getNumRatings());
                }
                if (clientVenueStats.getTotalSpendPerVisit() != null) {
                    existingClientVenueStats.setTotalSpendPerVisit(clientVenueStats.getTotalSpendPerVisit());
                }
                if (clientVenueStats.getTotalSpendLocal() != null) {
                    existingClientVenueStats.setTotalSpendLocal(clientVenueStats.getTotalSpendLocal());
                }
                if (clientVenueStats.getTotalSpendLocalPerVisit() != null) {
                    existingClientVenueStats.setTotalSpendLocalPerVisit(clientVenueStats.getTotalSpendLocalPerVisit());
                }
                if (clientVenueStats.getTotalVisits() != null) {
                    existingClientVenueStats.setTotalVisits(clientVenueStats.getTotalVisits());
                }
                if (clientVenueStats.getGrossTotal() != null) {
                    existingClientVenueStats.setGrossTotal(clientVenueStats.getGrossTotal());
                }
                if (clientVenueStats.getTotalOrderCount() != null) {
                    existingClientVenueStats.setTotalOrderCount(clientVenueStats.getTotalOrderCount());
                }
                if (clientVenueStats.getTotalOrderCancellations() != null) {
                    existingClientVenueStats.setTotalOrderCancellations(clientVenueStats.getTotalOrderCancellations());
                }
                if (clientVenueStats.getTotalOrderSpend() != null) {
                    existingClientVenueStats.setTotalOrderSpend(clientVenueStats.getTotalOrderSpend());
                }
                if (clientVenueStats.getGrossOrderTotal() != null) {
                    existingClientVenueStats.setGrossOrderTotal(clientVenueStats.getGrossOrderTotal());
                }
                if (clientVenueStats.getTotalOrderSpendLocal() != null) {
                    existingClientVenueStats.setTotalOrderSpendLocal(clientVenueStats.getTotalOrderSpendLocal());
                }
                if (clientVenueStats.getLastOrderDate() != null) {
                    existingClientVenueStats.setLastOrderDate(clientVenueStats.getLastOrderDate());
                }
                if (clientVenueStats.getTotalSpendperOrder() != null) {
                    existingClientVenueStats.setTotalSpendperOrder(clientVenueStats.getTotalSpendperOrder());
                }
                if (clientVenueStats.getTotalSpendLocalperOrder() != null) {
                    existingClientVenueStats.setTotalSpendLocalperOrder(clientVenueStats.getTotalSpendLocalperOrder());
                }
                if (clientVenueStats.getVenueId() != null) {
                    existingClientVenueStats.setVenueId(clientVenueStats.getVenueId());
                }
                if (clientVenueStats.getVenueMarketingOptin() != null) {
                    existingClientVenueStats.setVenueMarketingOptin(clientVenueStats.getVenueMarketingOptin());
                }
                if (clientVenueStats.getVenueMarketingOptints() != null) {
                    existingClientVenueStats.setVenueMarketingOptints(clientVenueStats.getVenueMarketingOptints());
                }

                return existingClientVenueStats;
            })
            .map(clientVenueStatsRepository::save);
    }

    @Override
    public Page<ClientVenueStats> findAll(Pageable pageable) {
        log.debug("Request to get all ClientVenueStats");
        return clientVenueStatsRepository.findAll(pageable);
    }

    /**
     *  Get all the clientVenueStats where Client is {@code null}.
     *  @return the list of entities.
     */

    public List<ClientVenueStats> findAllWhereClientIsNull() {
        log.debug("Request to get all clientVenueStats where Client is null");
        return StreamSupport.stream(clientVenueStatsRepository.findAll().spliterator(), false)
            .filter(clientVenueStats -> clientVenueStats.getClient() == null)
            .toList();
    }

    @Override
    public Optional<ClientVenueStats> findOne(String id) {
        log.debug("Request to get ClientVenueStats : {}", id);
        return clientVenueStatsRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete ClientVenueStats : {}", id);
        clientVenueStatsRepository.deleteById(id);
    }
}
