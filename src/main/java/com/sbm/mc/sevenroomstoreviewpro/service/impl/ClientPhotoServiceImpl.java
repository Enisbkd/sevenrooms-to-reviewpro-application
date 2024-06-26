package com.sbm.mc.sevenroomstoreviewpro.service.impl;

import com.sbm.mc.sevenroomstoreviewpro.domain.ClientPhoto;
import com.sbm.mc.sevenroomstoreviewpro.repository.ClientPhotoRepository;
import com.sbm.mc.sevenroomstoreviewpro.service.ClientPhotoService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.ClientPhoto}.
 */
@Service
public class ClientPhotoServiceImpl implements ClientPhotoService {

    private static final Logger log = LoggerFactory.getLogger(ClientPhotoServiceImpl.class);

    private final ClientPhotoRepository clientPhotoRepository;

    public ClientPhotoServiceImpl(ClientPhotoRepository clientPhotoRepository) {
        this.clientPhotoRepository = clientPhotoRepository;
    }

    @Override
    public ClientPhoto save(ClientPhoto clientPhoto) {
        log.debug("Request to save ClientPhoto : {}", clientPhoto);
        return clientPhotoRepository.save(clientPhoto);
    }

    @Override
    public ClientPhoto update(ClientPhoto clientPhoto) {
        log.debug("Request to update ClientPhoto : {}", clientPhoto);
        return clientPhotoRepository.save(clientPhoto);
    }

    @Override
    public Optional<ClientPhoto> partialUpdate(ClientPhoto clientPhoto) {
        log.debug("Request to partially update ClientPhoto : {}", clientPhoto);

        return clientPhotoRepository
            .findById(clientPhoto.getId())
            .map(existingClientPhoto -> {
                if (clientPhoto.getLarge() != null) {
                    existingClientPhoto.setLarge(clientPhoto.getLarge());
                }
                if (clientPhoto.getLargeHeight() != null) {
                    existingClientPhoto.setLargeHeight(clientPhoto.getLargeHeight());
                }
                if (clientPhoto.getLargeWidth() != null) {
                    existingClientPhoto.setLargeWidth(clientPhoto.getLargeWidth());
                }
                if (clientPhoto.getMedium() != null) {
                    existingClientPhoto.setMedium(clientPhoto.getMedium());
                }
                if (clientPhoto.getMediumHeight() != null) {
                    existingClientPhoto.setMediumHeight(clientPhoto.getMediumHeight());
                }
                if (clientPhoto.getMediumWidth() != null) {
                    existingClientPhoto.setMediumWidth(clientPhoto.getMediumWidth());
                }
                if (clientPhoto.getSmall() != null) {
                    existingClientPhoto.setSmall(clientPhoto.getSmall());
                }
                if (clientPhoto.getSmallHeight() != null) {
                    existingClientPhoto.setSmallHeight(clientPhoto.getSmallHeight());
                }
                if (clientPhoto.getSmallWidth() != null) {
                    existingClientPhoto.setSmallWidth(clientPhoto.getSmallWidth());
                }
                if (clientPhoto.getRaw() != null) {
                    existingClientPhoto.setRaw(clientPhoto.getRaw());
                }
                if (clientPhoto.getCropx() != null) {
                    existingClientPhoto.setCropx(clientPhoto.getCropx());
                }
                if (clientPhoto.getCropy() != null) {
                    existingClientPhoto.setCropy(clientPhoto.getCropy());
                }
                if (clientPhoto.getCropHeight() != null) {
                    existingClientPhoto.setCropHeight(clientPhoto.getCropHeight());
                }
                if (clientPhoto.getCropWidth() != null) {
                    existingClientPhoto.setCropWidth(clientPhoto.getCropWidth());
                }

                return existingClientPhoto;
            })
            .map(clientPhotoRepository::save);
    }

    @Override
    public Page<ClientPhoto> findAll(Pageable pageable) {
        log.debug("Request to get all ClientPhotos");
        return clientPhotoRepository.findAll(pageable);
    }

    /**
     *  Get all the clientPhotos where Client is {@code null}.
     *  @return the list of entities.
     */

    public List<ClientPhoto> findAllWhereClientIsNull() {
        log.debug("Request to get all clientPhotos where Client is null");
        return StreamSupport.stream(clientPhotoRepository.findAll().spliterator(), false)
            .filter(clientPhoto -> clientPhoto.getClient() == null)
            .toList();
    }

    @Override
    public Optional<ClientPhoto> findOne(String id) {
        log.debug("Request to get ClientPhoto : {}", id);
        return clientPhotoRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete ClientPhoto : {}", id);
        clientPhotoRepository.deleteById(id);
    }
}
