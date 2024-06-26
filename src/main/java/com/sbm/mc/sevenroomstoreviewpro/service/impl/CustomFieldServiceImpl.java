package com.sbm.mc.sevenroomstoreviewpro.service.impl;

import com.sbm.mc.sevenroomstoreviewpro.domain.CustomField;
import com.sbm.mc.sevenroomstoreviewpro.repository.CustomFieldRepository;
import com.sbm.mc.sevenroomstoreviewpro.service.CustomFieldService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.CustomField}.
 */
@Service
public class CustomFieldServiceImpl implements CustomFieldService {

    private static final Logger log = LoggerFactory.getLogger(CustomFieldServiceImpl.class);

    private final CustomFieldRepository customFieldRepository;

    public CustomFieldServiceImpl(CustomFieldRepository customFieldRepository) {
        this.customFieldRepository = customFieldRepository;
    }

    @Override
    public CustomField save(CustomField customField) {
        log.debug("Request to save CustomField : {}", customField);
        return customFieldRepository.save(customField);
    }

    @Override
    public CustomField update(CustomField customField) {
        log.debug("Request to update CustomField : {}", customField);
        return customFieldRepository.save(customField);
    }

    @Override
    public Optional<CustomField> partialUpdate(CustomField customField) {
        log.debug("Request to partially update CustomField : {}", customField);

        return customFieldRepository
            .findById(customField.getId())
            .map(existingCustomField -> {
                if (customField.getSystemName() != null) {
                    existingCustomField.setSystemName(customField.getSystemName());
                }
                if (customField.getDisplayOrder() != null) {
                    existingCustomField.setDisplayOrder(customField.getDisplayOrder());
                }
                if (customField.getName() != null) {
                    existingCustomField.setName(customField.getName());
                }
                if (customField.getValue() != null) {
                    existingCustomField.setValue(customField.getValue());
                }

                return existingCustomField;
            })
            .map(customFieldRepository::save);
    }

    @Override
    public Page<CustomField> findAll(Pageable pageable) {
        log.debug("Request to get all CustomFields");
        return customFieldRepository.findAll(pageable);
    }

    @Override
    public Optional<CustomField> findOne(String id) {
        log.debug("Request to get CustomField : {}", id);
        return customFieldRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete CustomField : {}", id);
        customFieldRepository.deleteById(id);
    }
}
