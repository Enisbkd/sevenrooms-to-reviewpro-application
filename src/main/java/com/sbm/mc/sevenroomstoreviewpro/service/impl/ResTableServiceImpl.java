package com.sbm.mc.sevenroomstoreviewpro.service.impl;

import com.sbm.mc.sevenroomstoreviewpro.domain.ResTable;
import com.sbm.mc.sevenroomstoreviewpro.repository.ResTableRepository;
import com.sbm.mc.sevenroomstoreviewpro.service.ResTableService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.ResTable}.
 */
@Service
public class ResTableServiceImpl implements ResTableService {

    private static final Logger log = LoggerFactory.getLogger(ResTableServiceImpl.class);

    private final ResTableRepository resTableRepository;

    public ResTableServiceImpl(ResTableRepository resTableRepository) {
        this.resTableRepository = resTableRepository;
    }

    @Override
    public ResTable save(ResTable resTable) {
        log.debug("Request to save ResTable : {}", resTable);
        return resTableRepository.save(resTable);
    }

    @Override
    public ResTable update(ResTable resTable) {
        log.debug("Request to update ResTable : {}", resTable);
        return resTableRepository.save(resTable);
    }

    @Override
    public Optional<ResTable> partialUpdate(ResTable resTable) {
        log.debug("Request to partially update ResTable : {}", resTable);

        return resTableRepository
            .findById(resTable.getId())
            .map(existingResTable -> {
                if (resTable.getTableNumber() != null) {
                    existingResTable.setTableNumber(resTable.getTableNumber());
                }

                return existingResTable;
            })
            .map(resTableRepository::save);
    }

    @Override
    public List<ResTable> findAll() {
        log.debug("Request to get all ResTables");
        return resTableRepository.findAll();
    }

    @Override
    public Optional<ResTable> findOne(String id) {
        log.debug("Request to get ResTable : {}", id);
        return resTableRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete ResTable : {}", id);
        resTableRepository.deleteById(id);
    }
}
