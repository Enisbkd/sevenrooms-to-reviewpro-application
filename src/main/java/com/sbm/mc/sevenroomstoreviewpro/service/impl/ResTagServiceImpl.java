package com.sbm.mc.sevenroomstoreviewpro.service.impl;

import com.sbm.mc.sevenroomstoreviewpro.domain.ResTag;
import com.sbm.mc.sevenroomstoreviewpro.repository.ResTagRepository;
import com.sbm.mc.sevenroomstoreviewpro.service.ResTagService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.ResTag}.
 */
@Service
public class ResTagServiceImpl implements ResTagService {

    private static final Logger log = LoggerFactory.getLogger(ResTagServiceImpl.class);

    private final ResTagRepository resTagRepository;

    public ResTagServiceImpl(ResTagRepository resTagRepository) {
        this.resTagRepository = resTagRepository;
    }

    @Override
    public ResTag save(ResTag resTag) {
        log.debug("Request to save ResTag : {}", resTag);
        return resTagRepository.save(resTag);
    }

    @Override
    public ResTag update(ResTag resTag) {
        log.debug("Request to update ResTag : {}", resTag);
        return resTagRepository.save(resTag);
    }

    @Override
    public Optional<ResTag> partialUpdate(ResTag resTag) {
        log.debug("Request to partially update ResTag : {}", resTag);

        return resTagRepository
            .findById(resTag.getId())
            .map(existingResTag -> {
                if (resTag.getTag() != null) {
                    existingResTag.setTag(resTag.getTag());
                }
                if (resTag.getTagDisplay() != null) {
                    existingResTag.setTagDisplay(resTag.getTagDisplay());
                }
                if (resTag.getGroup() != null) {
                    existingResTag.setGroup(resTag.getGroup());
                }
                if (resTag.getGroupDisplay() != null) {
                    existingResTag.setGroupDisplay(resTag.getGroupDisplay());
                }
                if (resTag.getColor() != null) {
                    existingResTag.setColor(resTag.getColor());
                }
                if (resTag.getTagSearchQuery() != null) {
                    existingResTag.setTagSearchQuery(resTag.getTagSearchQuery());
                }

                return existingResTag;
            })
            .map(resTagRepository::save);
    }

    @Override
    public Page<ResTag> findAll(Pageable pageable) {
        log.debug("Request to get all ResTags");
        return resTagRepository.findAll(pageable);
    }

    @Override
    public Optional<ResTag> findOne(String id) {
        log.debug("Request to get ResTag : {}", id);
        return resTagRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete ResTag : {}", id);
        resTagRepository.deleteById(id);
    }
}
