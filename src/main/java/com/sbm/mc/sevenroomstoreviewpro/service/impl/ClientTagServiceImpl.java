package com.sbm.mc.sevenroomstoreviewpro.service.impl;

import com.sbm.mc.sevenroomstoreviewpro.domain.ClientTag;
import com.sbm.mc.sevenroomstoreviewpro.repository.ClientTagRepository;
import com.sbm.mc.sevenroomstoreviewpro.service.ClientTagService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.ClientTag}.
 */
@Service
public class ClientTagServiceImpl implements ClientTagService {

    private static final Logger log = LoggerFactory.getLogger(ClientTagServiceImpl.class);

    private final ClientTagRepository clientTagRepository;

    public ClientTagServiceImpl(ClientTagRepository clientTagRepository) {
        this.clientTagRepository = clientTagRepository;
    }

    @Override
    public ClientTag save(ClientTag clientTag) {
        log.debug("Request to save ClientTag : {}", clientTag);
        return clientTagRepository.save(clientTag);
    }

    @Override
    public ClientTag update(ClientTag clientTag) {
        log.debug("Request to update ClientTag : {}", clientTag);
        return clientTagRepository.save(clientTag);
    }

    @Override
    public Optional<ClientTag> partialUpdate(ClientTag clientTag) {
        log.debug("Request to partially update ClientTag : {}", clientTag);

        return clientTagRepository
            .findById(clientTag.getId())
            .map(existingClientTag -> {
                if (clientTag.getTag() != null) {
                    existingClientTag.setTag(clientTag.getTag());
                }
                if (clientTag.getTagDisplay() != null) {
                    existingClientTag.setTagDisplay(clientTag.getTagDisplay());
                }
                if (clientTag.getGroup() != null) {
                    existingClientTag.setGroup(clientTag.getGroup());
                }
                if (clientTag.getGroupDisplay() != null) {
                    existingClientTag.setGroupDisplay(clientTag.getGroupDisplay());
                }
                if (clientTag.getColor() != null) {
                    existingClientTag.setColor(clientTag.getColor());
                }
                if (clientTag.getTagSearchQuery() != null) {
                    existingClientTag.setTagSearchQuery(clientTag.getTagSearchQuery());
                }

                return existingClientTag;
            })
            .map(clientTagRepository::save);
    }

    @Override
    public Page<ClientTag> findAll(Pageable pageable) {
        log.debug("Request to get all ClientTags");
        return clientTagRepository.findAll(pageable);
    }

    @Override
    public Optional<ClientTag> findOne(String id) {
        log.debug("Request to get ClientTag : {}", id);
        return clientTagRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete ClientTag : {}", id);
        clientTagRepository.deleteById(id);
    }
}
