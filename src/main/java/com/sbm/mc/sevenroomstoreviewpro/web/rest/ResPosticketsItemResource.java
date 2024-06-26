package com.sbm.mc.sevenroomstoreviewpro.web.rest;

import com.sbm.mc.sevenroomstoreviewpro.domain.ResPosticketsItem;
import com.sbm.mc.sevenroomstoreviewpro.repository.ResPosticketsItemRepository;
import com.sbm.mc.sevenroomstoreviewpro.service.ResPosticketsItemService;
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
 * REST controller for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.ResPosticketsItem}.
 */
@RestController
@RequestMapping("/api/res-postickets-items")
public class ResPosticketsItemResource {

    private static final Logger log = LoggerFactory.getLogger(ResPosticketsItemResource.class);

    private static final String ENTITY_NAME = "resPosticketsItem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ResPosticketsItemService resPosticketsItemService;

    private final ResPosticketsItemRepository resPosticketsItemRepository;

    public ResPosticketsItemResource(
        ResPosticketsItemService resPosticketsItemService,
        ResPosticketsItemRepository resPosticketsItemRepository
    ) {
        this.resPosticketsItemService = resPosticketsItemService;
        this.resPosticketsItemRepository = resPosticketsItemRepository;
    }

    /**
     * {@code POST  /res-postickets-items} : Create a new resPosticketsItem.
     *
     * @param resPosticketsItem the resPosticketsItem to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new resPosticketsItem, or with status {@code 400 (Bad Request)} if the resPosticketsItem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ResPosticketsItem> createResPosticketsItem(@RequestBody ResPosticketsItem resPosticketsItem)
        throws URISyntaxException {
        log.debug("REST request to save ResPosticketsItem : {}", resPosticketsItem);
        if (resPosticketsItem.getId() != null) {
            throw new BadRequestAlertException("A new resPosticketsItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        resPosticketsItem = resPosticketsItemService.save(resPosticketsItem);
        return ResponseEntity.created(new URI("/api/res-postickets-items/" + resPosticketsItem.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, resPosticketsItem.getId()))
            .body(resPosticketsItem);
    }

    /**
     * {@code PUT  /res-postickets-items/:id} : Updates an existing resPosticketsItem.
     *
     * @param id the id of the resPosticketsItem to save.
     * @param resPosticketsItem the resPosticketsItem to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resPosticketsItem,
     * or with status {@code 400 (Bad Request)} if the resPosticketsItem is not valid,
     * or with status {@code 500 (Internal Server Error)} if the resPosticketsItem couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResPosticketsItem> updateResPosticketsItem(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ResPosticketsItem resPosticketsItem
    ) throws URISyntaxException {
        log.debug("REST request to update ResPosticketsItem : {}, {}", id, resPosticketsItem);
        if (resPosticketsItem.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resPosticketsItem.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resPosticketsItemRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        resPosticketsItem = resPosticketsItemService.update(resPosticketsItem);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resPosticketsItem.getId()))
            .body(resPosticketsItem);
    }

    /**
     * {@code PATCH  /res-postickets-items/:id} : Partial updates given fields of an existing resPosticketsItem, field will ignore if it is null
     *
     * @param id the id of the resPosticketsItem to save.
     * @param resPosticketsItem the resPosticketsItem to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resPosticketsItem,
     * or with status {@code 400 (Bad Request)} if the resPosticketsItem is not valid,
     * or with status {@code 404 (Not Found)} if the resPosticketsItem is not found,
     * or with status {@code 500 (Internal Server Error)} if the resPosticketsItem couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ResPosticketsItem> partialUpdateResPosticketsItem(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ResPosticketsItem resPosticketsItem
    ) throws URISyntaxException {
        log.debug("REST request to partial update ResPosticketsItem partially : {}, {}", id, resPosticketsItem);
        if (resPosticketsItem.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resPosticketsItem.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resPosticketsItemRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ResPosticketsItem> result = resPosticketsItemService.partialUpdate(resPosticketsItem);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resPosticketsItem.getId())
        );
    }

    /**
     * {@code GET  /res-postickets-items} : get all the resPosticketsItems.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of resPosticketsItems in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ResPosticketsItem>> getAllResPosticketsItems(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ResPosticketsItems");
        Page<ResPosticketsItem> page = resPosticketsItemService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /res-postickets-items/:id} : get the "id" resPosticketsItem.
     *
     * @param id the id of the resPosticketsItem to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the resPosticketsItem, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResPosticketsItem> getResPosticketsItem(@PathVariable("id") String id) {
        log.debug("REST request to get ResPosticketsItem : {}", id);
        Optional<ResPosticketsItem> resPosticketsItem = resPosticketsItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(resPosticketsItem);
    }

    /**
     * {@code DELETE  /res-postickets-items/:id} : delete the "id" resPosticketsItem.
     *
     * @param id the id of the resPosticketsItem to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResPosticketsItem(@PathVariable("id") String id) {
        log.debug("REST request to delete ResPosticketsItem : {}", id);
        resPosticketsItemService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
