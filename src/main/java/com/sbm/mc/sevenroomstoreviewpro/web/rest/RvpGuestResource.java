package com.sbm.mc.sevenroomstoreviewpro.web.rest;

import com.sbm.mc.sevenroomstoreviewpro.domain.RvpGuest;
import com.sbm.mc.sevenroomstoreviewpro.repository.RvpGuestRepository;
import com.sbm.mc.sevenroomstoreviewpro.service.RvpGuestService;
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
 * REST controller for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.RvpGuest}.
 */
@RestController
@RequestMapping("/api/rvp-guests")
public class RvpGuestResource {

    private static final Logger log = LoggerFactory.getLogger(RvpGuestResource.class);

    private static final String ENTITY_NAME = "rvpGuest";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RvpGuestService rvpGuestService;

    private final RvpGuestRepository rvpGuestRepository;

    public RvpGuestResource(RvpGuestService rvpGuestService, RvpGuestRepository rvpGuestRepository) {
        this.rvpGuestService = rvpGuestService;
        this.rvpGuestRepository = rvpGuestRepository;
    }

    /**
     * {@code POST  /rvp-guests} : Create a new rvpGuest.
     *
     * @param rvpGuest the rvpGuest to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rvpGuest, or with status {@code 400 (Bad Request)} if the rvpGuest has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<RvpGuest> createRvpGuest(@RequestBody RvpGuest rvpGuest) throws URISyntaxException {
        log.debug("REST request to save RvpGuest : {}", rvpGuest);
        if (rvpGuest.getId() != null) {
            throw new BadRequestAlertException("A new rvpGuest cannot already have an ID", ENTITY_NAME, "idexists");
        }
        rvpGuest = rvpGuestService.save(rvpGuest);
        return ResponseEntity.created(new URI("/api/rvp-guests/" + rvpGuest.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, rvpGuest.getId()))
            .body(rvpGuest);
    }

    /**
     * {@code PUT  /rvp-guests/:id} : Updates an existing rvpGuest.
     *
     * @param id the id of the rvpGuest to save.
     * @param rvpGuest the rvpGuest to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rvpGuest,
     * or with status {@code 400 (Bad Request)} if the rvpGuest is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rvpGuest couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<RvpGuest> updateRvpGuest(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody RvpGuest rvpGuest
    ) throws URISyntaxException {
        log.debug("REST request to update RvpGuest : {}, {}", id, rvpGuest);
        if (rvpGuest.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rvpGuest.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rvpGuestRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        rvpGuest = rvpGuestService.update(rvpGuest);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rvpGuest.getId()))
            .body(rvpGuest);
    }

    /**
     * {@code PATCH  /rvp-guests/:id} : Partial updates given fields of an existing rvpGuest, field will ignore if it is null
     *
     * @param id the id of the rvpGuest to save.
     * @param rvpGuest the rvpGuest to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rvpGuest,
     * or with status {@code 400 (Bad Request)} if the rvpGuest is not valid,
     * or with status {@code 404 (Not Found)} if the rvpGuest is not found,
     * or with status {@code 500 (Internal Server Error)} if the rvpGuest couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RvpGuest> partialUpdateRvpGuest(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody RvpGuest rvpGuest
    ) throws URISyntaxException {
        log.debug("REST request to partial update RvpGuest partially : {}, {}", id, rvpGuest);
        if (rvpGuest.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rvpGuest.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rvpGuestRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RvpGuest> result = rvpGuestService.partialUpdate(rvpGuest);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rvpGuest.getId())
        );
    }

    /**
     * {@code GET  /rvp-guests} : get all the rvpGuests.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rvpGuests in body.
     */
    @GetMapping("")
    public List<RvpGuest> getAllRvpGuests() {
        log.debug("REST request to get all RvpGuests");
        return rvpGuestService.findAll();
    }

    /**
     * {@code GET  /rvp-guests/:id} : get the "id" rvpGuest.
     *
     * @param id the id of the rvpGuest to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rvpGuest, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RvpGuest> getRvpGuest(@PathVariable("id") String id) {
        log.debug("REST request to get RvpGuest : {}", id);
        Optional<RvpGuest> rvpGuest = rvpGuestService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rvpGuest);
    }

    /**
     * {@code DELETE  /rvp-guests/:id} : delete the "id" rvpGuest.
     *
     * @param id the id of the rvpGuest to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRvpGuest(@PathVariable("id") String id) {
        log.debug("REST request to delete RvpGuest : {}", id);
        rvpGuestService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
