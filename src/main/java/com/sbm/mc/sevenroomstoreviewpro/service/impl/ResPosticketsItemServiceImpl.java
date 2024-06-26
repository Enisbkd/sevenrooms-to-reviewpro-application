package com.sbm.mc.sevenroomstoreviewpro.service.impl;

import com.sbm.mc.sevenroomstoreviewpro.domain.ResPosticketsItem;
import com.sbm.mc.sevenroomstoreviewpro.repository.ResPosticketsItemRepository;
import com.sbm.mc.sevenroomstoreviewpro.service.ResPosticketsItemService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.ResPosticketsItem}.
 */
@Service
public class ResPosticketsItemServiceImpl implements ResPosticketsItemService {

    private static final Logger log = LoggerFactory.getLogger(ResPosticketsItemServiceImpl.class);

    private final ResPosticketsItemRepository resPosticketsItemRepository;

    public ResPosticketsItemServiceImpl(ResPosticketsItemRepository resPosticketsItemRepository) {
        this.resPosticketsItemRepository = resPosticketsItemRepository;
    }

    @Override
    public ResPosticketsItem save(ResPosticketsItem resPosticketsItem) {
        log.debug("Request to save ResPosticketsItem : {}", resPosticketsItem);
        return resPosticketsItemRepository.save(resPosticketsItem);
    }

    @Override
    public ResPosticketsItem update(ResPosticketsItem resPosticketsItem) {
        log.debug("Request to update ResPosticketsItem : {}", resPosticketsItem);
        return resPosticketsItemRepository.save(resPosticketsItem);
    }

    @Override
    public Optional<ResPosticketsItem> partialUpdate(ResPosticketsItem resPosticketsItem) {
        log.debug("Request to partially update ResPosticketsItem : {}", resPosticketsItem);

        return resPosticketsItemRepository
            .findById(resPosticketsItem.getId())
            .map(existingResPosticketsItem -> {
                if (resPosticketsItem.getPrice() != null) {
                    existingResPosticketsItem.setPrice(resPosticketsItem.getPrice());
                }
                if (resPosticketsItem.getName() != null) {
                    existingResPosticketsItem.setName(resPosticketsItem.getName());
                }
                if (resPosticketsItem.getQuantity() != null) {
                    existingResPosticketsItem.setQuantity(resPosticketsItem.getQuantity());
                }

                return existingResPosticketsItem;
            })
            .map(resPosticketsItemRepository::save);
    }

    @Override
    public Page<ResPosticketsItem> findAll(Pageable pageable) {
        log.debug("Request to get all ResPosticketsItems");
        return resPosticketsItemRepository.findAll(pageable);
    }

    @Override
    public Optional<ResPosticketsItem> findOne(String id) {
        log.debug("Request to get ResPosticketsItem : {}", id);
        return resPosticketsItemRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete ResPosticketsItem : {}", id);
        resPosticketsItemRepository.deleteById(id);
    }
}
