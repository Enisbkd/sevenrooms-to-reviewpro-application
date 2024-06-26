package com.sbm.mc.sevenroomstoreviewpro.service.impl;

import com.sbm.mc.sevenroomstoreviewpro.domain.ResCustomField;
import com.sbm.mc.sevenroomstoreviewpro.repository.ResCustomFieldRepository;
import com.sbm.mc.sevenroomstoreviewpro.service.ResCustomFieldService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.ResCustomField}.
 */
@Service
public class ResCustomFieldServiceImpl implements ResCustomFieldService {

    private static final Logger log = LoggerFactory.getLogger(ResCustomFieldServiceImpl.class);

    private final ResCustomFieldRepository resCustomFieldRepository;

    public ResCustomFieldServiceImpl(ResCustomFieldRepository resCustomFieldRepository) {
        this.resCustomFieldRepository = resCustomFieldRepository;
    }

    @Override
    public ResCustomField save(ResCustomField resCustomField) {
        log.debug("Request to save ResCustomField : {}", resCustomField);
        return resCustomFieldRepository.save(resCustomField);
    }

    @Override
    public ResCustomField update(ResCustomField resCustomField) {
        log.debug("Request to update ResCustomField : {}", resCustomField);
        return resCustomFieldRepository.save(resCustomField);
    }

    @Override
    public Optional<ResCustomField> partialUpdate(ResCustomField resCustomField) {
        log.debug("Request to partially update ResCustomField : {}", resCustomField);

        return resCustomFieldRepository
            .findById(resCustomField.getId())
            .map(existingResCustomField -> {
                if (resCustomField.getSystemName() != null) {
                    existingResCustomField.setSystemName(resCustomField.getSystemName());
                }
                if (resCustomField.getDisplayOrder() != null) {
                    existingResCustomField.setDisplayOrder(resCustomField.getDisplayOrder());
                }
                if (resCustomField.getName() != null) {
                    existingResCustomField.setName(resCustomField.getName());
                }
                if (resCustomField.getValue() != null) {
                    existingResCustomField.setValue(resCustomField.getValue());
                }

                return existingResCustomField;
            })
            .map(resCustomFieldRepository::save);
    }

    @Override
    public Page<ResCustomField> findAll(Pageable pageable) {
        log.debug("Request to get all ResCustomFields");
        return resCustomFieldRepository.findAll(pageable);
    }

    @Override
    public Optional<ResCustomField> findOne(String id) {
        log.debug("Request to get ResCustomField : {}", id);
        return resCustomFieldRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete ResCustomField : {}", id);
        resCustomFieldRepository.deleteById(id);
    }
}
