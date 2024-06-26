package com.sbm.mc.sevenroomstoreviewpro.service.impl;

import com.sbm.mc.sevenroomstoreviewpro.domain.ResPosTicket;
import com.sbm.mc.sevenroomstoreviewpro.repository.ResPosTicketRepository;
import com.sbm.mc.sevenroomstoreviewpro.service.ResPosTicketService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.ResPosTicket}.
 */
@Service
public class ResPosTicketServiceImpl implements ResPosTicketService {

    private static final Logger log = LoggerFactory.getLogger(ResPosTicketServiceImpl.class);

    private final ResPosTicketRepository resPosTicketRepository;

    public ResPosTicketServiceImpl(ResPosTicketRepository resPosTicketRepository) {
        this.resPosTicketRepository = resPosTicketRepository;
    }

    @Override
    public ResPosTicket save(ResPosTicket resPosTicket) {
        log.debug("Request to save ResPosTicket : {}", resPosTicket);
        return resPosTicketRepository.save(resPosTicket);
    }

    @Override
    public ResPosTicket update(ResPosTicket resPosTicket) {
        log.debug("Request to update ResPosTicket : {}", resPosTicket);
        return resPosTicketRepository.save(resPosTicket);
    }

    @Override
    public Optional<ResPosTicket> partialUpdate(ResPosTicket resPosTicket) {
        log.debug("Request to partially update ResPosTicket : {}", resPosTicket);

        return resPosTicketRepository
            .findById(resPosTicket.getId())
            .map(existingResPosTicket -> {
                if (resPosTicket.getStatus() != null) {
                    existingResPosTicket.setStatus(resPosTicket.getStatus());
                }
                if (resPosTicket.getAdminFee() != null) {
                    existingResPosTicket.setAdminFee(resPosTicket.getAdminFee());
                }
                if (resPosTicket.getCode() != null) {
                    existingResPosTicket.setCode(resPosTicket.getCode());
                }
                if (resPosTicket.getTableNo() != null) {
                    existingResPosTicket.setTableNo(resPosTicket.getTableNo());
                }
                if (resPosTicket.getTax() != null) {
                    existingResPosTicket.setTax(resPosTicket.getTax());
                }
                if (resPosTicket.getBusinessId() != null) {
                    existingResPosTicket.setBusinessId(resPosTicket.getBusinessId());
                }
                if (resPosTicket.getTicketId() != null) {
                    existingResPosTicket.setTicketId(resPosTicket.getTicketId());
                }
                if (resPosTicket.getLocalPosticketId() != null) {
                    existingResPosTicket.setLocalPosticketId(resPosTicket.getLocalPosticketId());
                }
                if (resPosTicket.getEmployeeName() != null) {
                    existingResPosTicket.setEmployeeName(resPosTicket.getEmployeeName());
                }
                if (resPosTicket.getTotal() != null) {
                    existingResPosTicket.setTotal(resPosTicket.getTotal());
                }
                if (resPosTicket.getSubtotal() != null) {
                    existingResPosTicket.setSubtotal(resPosTicket.getSubtotal());
                }
                if (resPosTicket.getStartTime() != null) {
                    existingResPosTicket.setStartTime(resPosTicket.getStartTime());
                }
                if (resPosTicket.getServiceCharge() != null) {
                    existingResPosTicket.setServiceCharge(resPosTicket.getServiceCharge());
                }
                if (resPosTicket.getEndtime() != null) {
                    existingResPosTicket.setEndtime(resPosTicket.getEndtime());
                }

                return existingResPosTicket;
            })
            .map(resPosTicketRepository::save);
    }

    @Override
    public Page<ResPosTicket> findAll(Pageable pageable) {
        log.debug("Request to get all ResPosTickets");
        return resPosTicketRepository.findAll(pageable);
    }

    @Override
    public Optional<ResPosTicket> findOne(String id) {
        log.debug("Request to get ResPosTicket : {}", id);
        return resPosTicketRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete ResPosTicket : {}", id);
        resPosTicketRepository.deleteById(id);
    }
}
