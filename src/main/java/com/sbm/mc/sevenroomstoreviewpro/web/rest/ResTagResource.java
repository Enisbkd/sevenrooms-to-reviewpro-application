package com.sbm.mc.sevenroomstoreviewpro.web.rest;

import com.sbm.mc.sevenroomstoreviewpro.domain.ResTag;
import com.sbm.mc.sevenroomstoreviewpro.repository.ResTagRepository;
import com.sbm.mc.sevenroomstoreviewpro.service.ResTagService;
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
 * REST controller for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.ResTag}.
 */
@RestController
@RequestMapping("/api/res-tags")
public class ResTagResource {

    private static final Logger log = LoggerFactory.getLogger(ResTagResource.class);

    private static final String ENTITY_NAME = "resTag";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ResTagService resTagService;

    private final ResTagRepository resTagRepository;

    public ResTagResource(ResTagService resTagService, ResTagRepository resTagRepository) {
        this.resTagService = resTagService;
        this.resTagRepository = resTagRepository;
    }

    /**
     * {@code POST  /res-tags} : Create a new resTag.
     *
     * @param resTag the resTag to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new resTag, or with status {@code 400 (Bad Request)} if the resTag has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ResTag> createResTag(@RequestBody ResTag resTag) throws URISyntaxException {
        log.debug("REST request to save ResTag : {}", resTag);
        if (resTag.getId() != null) {
            throw new BadRequestAlertException("A new resTag cannot already have an ID", ENTITY_NAME, "idexists");
        }
        resTag = resTagService.save(resTag);
        return ResponseEntity.created(new URI("/api/res-tags/" + resTag.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, resTag.getId()))
            .body(resTag);
    }

    /**
     * {@code PUT  /res-tags/:id} : Updates an existing resTag.
     *
     * @param id the id of the resTag to save.
     * @param resTag the resTag to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resTag,
     * or with status {@code 400 (Bad Request)} if the resTag is not valid,
     * or with status {@code 500 (Internal Server Error)} if the resTag couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResTag> updateResTag(@PathVariable(value = "id", required = false) final String id, @RequestBody ResTag resTag)
        throws URISyntaxException {
        log.debug("REST request to update ResTag : {}, {}", id, resTag);
        if (resTag.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resTag.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resTagRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        resTag = resTagService.update(resTag);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resTag.getId()))
            .body(resTag);
    }

    /**
     * {@code PATCH  /res-tags/:id} : Partial updates given fields of an existing resTag, field will ignore if it is null
     *
     * @param id the id of the resTag to save.
     * @param resTag the resTag to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resTag,
     * or with status {@code 400 (Bad Request)} if the resTag is not valid,
     * or with status {@code 404 (Not Found)} if the resTag is not found,
     * or with status {@code 500 (Internal Server Error)} if the resTag couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ResTag> partialUpdateResTag(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ResTag resTag
    ) throws URISyntaxException {
        log.debug("REST request to partial update ResTag partially : {}, {}", id, resTag);
        if (resTag.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resTag.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resTagRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ResTag> result = resTagService.partialUpdate(resTag);

        return ResponseUtil.wrapOrNotFound(result, HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resTag.getId()));
    }

    /**
     * {@code GET  /res-tags} : get all the resTags.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of resTags in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ResTag>> getAllResTags(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of ResTags");
        Page<ResTag> page = resTagService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /res-tags/:id} : get the "id" resTag.
     *
     * @param id the id of the resTag to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the resTag, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResTag> getResTag(@PathVariable("id") String id) {
        log.debug("REST request to get ResTag : {}", id);
        Optional<ResTag> resTag = resTagService.findOne(id);
        return ResponseUtil.wrapOrNotFound(resTag);
    }

    /**
     * {@code DELETE  /res-tags/:id} : delete the "id" resTag.
     *
     * @param id the id of the resTag to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResTag(@PathVariable("id") String id) {
        log.debug("REST request to delete ResTag : {}", id);
        resTagService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
