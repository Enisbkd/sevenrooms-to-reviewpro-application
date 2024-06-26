package com.sbm.mc.sevenroomstoreviewpro.web.rest;

import com.sbm.mc.sevenroomstoreviewpro.domain.ResPosTicket;
import com.sbm.mc.sevenroomstoreviewpro.repository.ResPosTicketRepository;
import com.sbm.mc.sevenroomstoreviewpro.service.ResPosTicketService;
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
 * REST controller for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.ResPosTicket}.
 */
@RestController
@RequestMapping("/api/res-pos-tickets")
public class ResPosTicketResource {

    private static final Logger log = LoggerFactory.getLogger(ResPosTicketResource.class);

    private static final String ENTITY_NAME = "resPosTicket";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ResPosTicketService resPosTicketService;

    private final ResPosTicketRepository resPosTicketRepository;

    public ResPosTicketResource(ResPosTicketService resPosTicketService, ResPosTicketRepository resPosTicketRepository) {
        this.resPosTicketService = resPosTicketService;
        this.resPosTicketRepository = resPosTicketRepository;
    }

    /**
     * {@code POST  /res-pos-tickets} : Create a new resPosTicket.
     *
     * @param resPosTicket the resPosTicket to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new resPosTicket, or with status {@code 400 (Bad Request)} if the resPosTicket has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ResPosTicket> createResPosTicket(@RequestBody ResPosTicket resPosTicket) throws URISyntaxException {
        log.debug("REST request to save ResPosTicket : {}", resPosTicket);
        if (resPosTicket.getId() != null) {
            throw new BadRequestAlertException("A new resPosTicket cannot already have an ID", ENTITY_NAME, "idexists");
        }
        resPosTicket = resPosTicketService.save(resPosTicket);
        return ResponseEntity.created(new URI("/api/res-pos-tickets/" + resPosTicket.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, resPosTicket.getId()))
            .body(resPosTicket);
    }

    /**
     * {@code PUT  /res-pos-tickets/:id} : Updates an existing resPosTicket.
     *
     * @param id the id of the resPosTicket to save.
     * @param resPosTicket the resPosTicket to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resPosTicket,
     * or with status {@code 400 (Bad Request)} if the resPosTicket is not valid,
     * or with status {@code 500 (Internal Server Error)} if the resPosTicket couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResPosTicket> updateResPosTicket(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ResPosTicket resPosTicket
    ) throws URISyntaxException {
        log.debug("REST request to update ResPosTicket : {}, {}", id, resPosTicket);
        if (resPosTicket.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resPosTicket.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resPosTicketRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        resPosTicket = resPosTicketService.update(resPosTicket);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resPosTicket.getId()))
            .body(resPosTicket);
    }

    /**
     * {@code PATCH  /res-pos-tickets/:id} : Partial updates given fields of an existing resPosTicket, field will ignore if it is null
     *
     * @param id the id of the resPosTicket to save.
     * @param resPosTicket the resPosTicket to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resPosTicket,
     * or with status {@code 400 (Bad Request)} if the resPosTicket is not valid,
     * or with status {@code 404 (Not Found)} if the resPosTicket is not found,
     * or with status {@code 500 (Internal Server Error)} if the resPosTicket couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ResPosTicket> partialUpdateResPosTicket(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ResPosTicket resPosTicket
    ) throws URISyntaxException {
        log.debug("REST request to partial update ResPosTicket partially : {}, {}", id, resPosTicket);
        if (resPosTicket.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resPosTicket.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resPosTicketRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ResPosTicket> result = resPosTicketService.partialUpdate(resPosTicket);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resPosTicket.getId())
        );
    }

    /**
     * {@code GET  /res-pos-tickets} : get all the resPosTickets.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of resPosTickets in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ResPosTicket>> getAllResPosTickets(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of ResPosTickets");
        Page<ResPosTicket> page = resPosTicketService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /res-pos-tickets/:id} : get the "id" resPosTicket.
     *
     * @param id the id of the resPosTicket to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the resPosTicket, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResPosTicket> getResPosTicket(@PathVariable("id") String id) {
        log.debug("REST request to get ResPosTicket : {}", id);
        Optional<ResPosTicket> resPosTicket = resPosTicketService.findOne(id);
        return ResponseUtil.wrapOrNotFound(resPosTicket);
    }

    /**
     * {@code DELETE  /res-pos-tickets/:id} : delete the "id" resPosTicket.
     *
     * @param id the id of the resPosTicket to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResPosTicket(@PathVariable("id") String id) {
        log.debug("REST request to delete ResPosTicket : {}", id);
        resPosTicketService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
