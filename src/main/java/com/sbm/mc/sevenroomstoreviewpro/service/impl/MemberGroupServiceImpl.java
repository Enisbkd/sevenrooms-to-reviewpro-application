package com.sbm.mc.sevenroomstoreviewpro.service.impl;

import com.sbm.mc.sevenroomstoreviewpro.domain.MemberGroup;
import com.sbm.mc.sevenroomstoreviewpro.repository.MemberGroupRepository;
import com.sbm.mc.sevenroomstoreviewpro.service.MemberGroupService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.MemberGroup}.
 */
@Service
public class MemberGroupServiceImpl implements MemberGroupService {

    private static final Logger log = LoggerFactory.getLogger(MemberGroupServiceImpl.class);

    private final MemberGroupRepository memberGroupRepository;

    public MemberGroupServiceImpl(MemberGroupRepository memberGroupRepository) {
        this.memberGroupRepository = memberGroupRepository;
    }

    @Override
    public MemberGroup save(MemberGroup memberGroup) {
        log.debug("Request to save MemberGroup : {}", memberGroup);
        return memberGroupRepository.save(memberGroup);
    }

    @Override
    public MemberGroup update(MemberGroup memberGroup) {
        log.debug("Request to update MemberGroup : {}", memberGroup);
        return memberGroupRepository.save(memberGroup);
    }

    @Override
    public Optional<MemberGroup> partialUpdate(MemberGroup memberGroup) {
        log.debug("Request to partially update MemberGroup : {}", memberGroup);

        return memberGroupRepository.findById(memberGroup.getId()).map(memberGroupRepository::save);
    }

    @Override
    public Page<MemberGroup> findAll(Pageable pageable) {
        log.debug("Request to get all MemberGroups");
        return memberGroupRepository.findAll(pageable);
    }

    @Override
    public Optional<MemberGroup> findOne(String id) {
        log.debug("Request to get MemberGroup : {}", id);
        return memberGroupRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete MemberGroup : {}", id);
        memberGroupRepository.deleteById(id);
    }
}
