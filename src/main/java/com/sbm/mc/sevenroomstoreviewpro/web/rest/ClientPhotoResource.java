package com.sbm.mc.sevenroomstoreviewpro.web.rest;

import com.sbm.mc.sevenroomstoreviewpro.domain.ClientPhoto;
import com.sbm.mc.sevenroomstoreviewpro.repository.ClientPhotoRepository;
import com.sbm.mc.sevenroomstoreviewpro.service.ClientPhotoService;
import com.sbm.mc.sevenroomstoreviewpro.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.ClientPhoto}.
 */
@RestController
@RequestMapping("/api/client-photos")
public class ClientPhotoResource {

    private static final Logger log = LoggerFactory.getLogger(ClientPhotoResource.class);

    private static final String ENTITY_NAME = "clientPhoto";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClientPhotoService clientPhotoService;

    private final ClientPhotoRepository clientPhotoRepository;

    public ClientPhotoResource(ClientPhotoService clientPhotoService, ClientPhotoRepository clientPhotoRepository) {
        this.clientPhotoService = clientPhotoService;
        this.clientPhotoRepository = clientPhotoRepository;
    }

    /**
     * {@code POST  /client-photos} : Create a new clientPhoto.
     *
     * @param clientPhoto the clientPhoto to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new clientPhoto, or with status {@code 400 (Bad Request)} if the clientPhoto has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ClientPhoto> createClientPhoto(@RequestBody ClientPhoto clientPhoto) throws URISyntaxException {
        log.debug("REST request to save ClientPhoto : {}", clientPhoto);
        if (clientPhoto.getId() != null) {
            throw new BadRequestAlertException("A new clientPhoto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        clientPhoto = clientPhotoService.save(clientPhoto);
        return ResponseEntity.created(new URI("/api/client-photos/" + clientPhoto.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, clientPhoto.getId()))
            .body(clientPhoto);
    }

    /**
     * {@code PUT  /client-photos/:id} : Updates an existing clientPhoto.
     *
     * @param id the id of the clientPhoto to save.
     * @param clientPhoto the clientPhoto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated clientPhoto,
     * or with status {@code 400 (Bad Request)} if the clientPhoto is not valid,
     * or with status {@code 500 (Internal Server Error)} if the clientPhoto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClientPhoto> updateClientPhoto(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ClientPhoto clientPhoto
    ) throws URISyntaxException {
        log.debug("REST request to update ClientPhoto : {}, {}", id, clientPhoto);
        if (clientPhoto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, clientPhoto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!clientPhotoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        clientPhoto = clientPhotoService.update(clientPhoto);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, clientPhoto.getId()))
            .body(clientPhoto);
    }

    /**
     * {@code PATCH  /client-photos/:id} : Partial updates given fields of an existing clientPhoto, field will ignore if it is null
     *
     * @param id the id of the clientPhoto to save.
     * @param clientPhoto the clientPhoto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated clientPhoto,
     * or with status {@code 400 (Bad Request)} if the clientPhoto is not valid,
     * or with status {@code 404 (Not Found)} if the clientPhoto is not found,
     * or with status {@code 500 (Internal Server Error)} if the clientPhoto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ClientPhoto> partialUpdateClientPhoto(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ClientPhoto clientPhoto
    ) throws URISyntaxException {
        log.debug("REST request to partial update ClientPhoto partially : {}, {}", id, clientPhoto);
        if (clientPhoto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, clientPhoto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!clientPhotoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ClientPhoto> result = clientPhotoService.partialUpdate(clientPhoto);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, clientPhoto.getId())
        );
    }

    /**
     * {@code GET  /client-photos} : get all the clientPhotos.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of clientPhotos in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ClientPhoto>> getAllClientPhotos(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(name = "filter", required = false) String filter
    ) {
        if ("client-is-null".equals(filter)) {
            log.debug("REST request to get all ClientPhotos where client is null");
            return new ResponseEntity<>(clientPhotoService.findAllWhereClientIsNull(), HttpStatus.OK);
        }
        log.debug("REST request to get a page of ClientPhotos");
        Page<ClientPhoto> page = clientPhotoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /client-photos/:id} : get the "id" clientPhoto.
     *
     * @param id the id of the clientPhoto to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the clientPhoto, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClientPhoto> getClientPhoto(@PathVariable("id") String id) {
        log.debug("REST request to get ClientPhoto : {}", id);
        Optional<ClientPhoto> clientPhoto = clientPhotoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(clientPhoto);
    }

    /**
     * {@code DELETE  /client-photos/:id} : delete the "id" clientPhoto.
     *
     * @param id the id of the clientPhoto to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClientPhoto(@PathVariable("id") String id) {
        log.debug("REST request to delete ClientPhoto : {}", id);
        clientPhotoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
