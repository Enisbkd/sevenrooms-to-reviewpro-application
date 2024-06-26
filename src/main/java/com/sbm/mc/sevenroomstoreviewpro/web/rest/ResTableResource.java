package com.sbm.mc.sevenroomstoreviewpro.web.rest;

import com.sbm.mc.sevenroomstoreviewpro.domain.ResTable;
import com.sbm.mc.sevenroomstoreviewpro.repository.ResTableRepository;
import com.sbm.mc.sevenroomstoreviewpro.service.ResTableService;
import com.sbm.mc.sevenroomstoreviewpro.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.ResTable}.
 */
@RestController
@RequestMapping("/api/res-tables")
public class ResTableResource {

    private static final Logger log = LoggerFactory.getLogger(ResTableResource.class);

    private static final String ENTITY_NAME = "resTable";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ResTableService resTableService;

    private final ResTableRepository resTableRepository;

    public ResTableResource(ResTableService resTableService, ResTableRepository resTableRepository) {
        this.resTableService = resTableService;
        this.resTableRepository = resTableRepository;
    }

    /**
     * {@code POST  /res-tables} : Create a new resTable.
     *
     * @param resTable the resTable to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new resTable, or with status {@code 400 (Bad Request)} if the resTable has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ResTable> createResTable(@RequestBody ResTable resTable) throws URISyntaxException {
        log.debug("REST request to save ResTable : {}", resTable);
        if (resTable.getId() != null) {
            throw new BadRequestAlertException("A new resTable cannot already have an ID", ENTITY_NAME, "idexists");
        }
        resTable = resTableService.save(resTable);
        return ResponseEntity.created(new URI("/api/res-tables/" + resTable.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, resTable.getId()))
            .body(resTable);
    }

    /**
     * {@code PUT  /res-tables/:id} : Updates an existing resTable.
     *
     * @param id the id of the resTable to save.
     * @param resTable the resTable to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resTable,
     * or with status {@code 400 (Bad Request)} if the resTable is not valid,
     * or with status {@code 500 (Internal Server Error)} if the resTable couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResTable> updateResTable(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ResTable resTable
    ) throws URISyntaxException {
        log.debug("REST request to update ResTable : {}, {}", id, resTable);
        if (resTable.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resTable.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resTableRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        resTable = resTableService.update(resTable);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resTable.getId()))
            .body(resTable);
    }

    /**
     * {@code PATCH  /res-tables/:id} : Partial updates given fields of an existing resTable, field will ignore if it is null
     *
     * @param id the id of the resTable to save.
     * @param resTable the resTable to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resTable,
     * or with status {@code 400 (Bad Request)} if the resTable is not valid,
     * or with status {@code 404 (Not Found)} if the resTable is not found,
     * or with status {@code 500 (Internal Server Error)} if the resTable couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ResTable> partialUpdateResTable(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ResTable resTable
    ) throws URISyntaxException {
        log.debug("REST request to partial update ResTable partially : {}, {}", id, resTable);
        if (resTable.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resTable.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resTableRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ResTable> result = resTableService.partialUpdate(resTable);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resTable.getId())
        );
    }

    /**
     * {@code GET  /res-tables} : get all the resTables.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of resTables in body.
     */
    @GetMapping("")
    public List<ResTable> getAllResTables() {
        log.debug("REST request to get all ResTables");
        return resTableService.findAll();
    }

    /**
     * {@code GET  /res-tables/:id} : get the "id" resTable.
     *
     * @param id the id of the resTable to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the resTable, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResTable> getResTable(@PathVariable("id") String id) {
        log.debug("REST request to get ResTable : {}", id);
        Optional<ResTable> resTable = resTableService.findOne(id);
        return ResponseUtil.wrapOrNotFound(resTable);
    }

    /**
     * {@code DELETE  /res-tables/:id} : delete the "id" resTable.
     *
     * @param id the id of the resTable to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResTable(@PathVariable("id") String id) {
        log.debug("REST request to delete ResTable : {}", id);
        resTableService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
