package com.sbm.mc.sevenroomstoreviewpro.web.rest;

import com.sbm.mc.sevenroomstoreviewpro.domain.ResCustomField;
import com.sbm.mc.sevenroomstoreviewpro.repository.ResCustomFieldRepository;
import com.sbm.mc.sevenroomstoreviewpro.service.ResCustomFieldService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.ResCustomField}.
 */
@RestController
@RequestMapping("/api/res-custom-fields")
public class ResCustomFieldResource {

    private static final Logger log = LoggerFactory.getLogger(ResCustomFieldResource.class);

    private static final String ENTITY_NAME = "resCustomField";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ResCustomFieldService resCustomFieldService;

    private final ResCustomFieldRepository resCustomFieldRepository;

    public ResCustomFieldResource(ResCustomFieldService resCustomFieldService, ResCustomFieldRepository resCustomFieldRepository) {
        this.resCustomFieldService = resCustomFieldService;
        this.resCustomFieldRepository = resCustomFieldRepository;
    }

    /**
     * {@code POST  /res-custom-fields} : Create a new resCustomField.
     *
     * @param resCustomField the resCustomField to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new resCustomField, or with status {@code 400 (Bad Request)} if the resCustomField has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ResCustomField> createResCustomField(@RequestBody ResCustomField resCustomField) throws URISyntaxException {
        log.debug("REST request to save ResCustomField : {}", resCustomField);
        if (resCustomField.getId() != null) {
            throw new BadRequestAlertException("A new resCustomField cannot already have an ID", ENTITY_NAME, "idexists");
        }
        resCustomField = resCustomFieldService.save(resCustomField);
        return ResponseEntity.created(new URI("/api/res-custom-fields/" + resCustomField.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, resCustomField.getId()))
            .body(resCustomField);
    }

    /**
     * {@code PUT  /res-custom-fields/:id} : Updates an existing resCustomField.
     *
     * @param id the id of the resCustomField to save.
     * @param resCustomField the resCustomField to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resCustomField,
     * or with status {@code 400 (Bad Request)} if the resCustomField is not valid,
     * or with status {@code 500 (Internal Server Error)} if the resCustomField couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResCustomField> updateResCustomField(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ResCustomField resCustomField
    ) throws URISyntaxException {
        log.debug("REST request to update ResCustomField : {}, {}", id, resCustomField);
        if (resCustomField.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resCustomField.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resCustomFieldRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        resCustomField = resCustomFieldService.update(resCustomField);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resCustomField.getId()))
            .body(resCustomField);
    }

    /**
     * {@code PATCH  /res-custom-fields/:id} : Partial updates given fields of an existing resCustomField, field will ignore if it is null
     *
     * @param id the id of the resCustomField to save.
     * @param resCustomField the resCustomField to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resCustomField,
     * or with status {@code 400 (Bad Request)} if the resCustomField is not valid,
     * or with status {@code 404 (Not Found)} if the resCustomField is not found,
     * or with status {@code 500 (Internal Server Error)} if the resCustomField couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ResCustomField> partialUpdateResCustomField(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ResCustomField resCustomField
    ) throws URISyntaxException {
        log.debug("REST request to partial update ResCustomField partially : {}, {}", id, resCustomField);
        if (resCustomField.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resCustomField.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resCustomFieldRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ResCustomField> result = resCustomFieldService.partialUpdate(resCustomField);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resCustomField.getId())
        );
    }

    /**
     * {@code GET  /res-custom-fields} : get all the resCustomFields.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of resCustomFields in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ResCustomField>> getAllResCustomFields(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of ResCustomFields");
        Page<ResCustomField> page = resCustomFieldService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /res-custom-fields/:id} : get the "id" resCustomField.
     *
     * @param id the id of the resCustomField to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the resCustomField, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResCustomField> getResCustomField(@PathVariable("id") String id) {
        log.debug("REST request to get ResCustomField : {}", id);
        Optional<ResCustomField> resCustomField = resCustomFieldService.findOne(id);
        return ResponseUtil.wrapOrNotFound(resCustomField);
    }

    /**
     * {@code DELETE  /res-custom-fields/:id} : delete the "id" resCustomField.
     *
     * @param id the id of the resCustomField to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResCustomField(@PathVariable("id") String id) {
        log.debug("REST request to delete ResCustomField : {}", id);
        resCustomFieldService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
