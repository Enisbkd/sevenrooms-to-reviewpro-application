package com.sbm.mc.sevenroomstoreviewpro.web.rest;

import com.sbm.mc.sevenroomstoreviewpro.domain.Venue;
import com.sbm.mc.sevenroomstoreviewpro.repository.VenueRepository;
import com.sbm.mc.sevenroomstoreviewpro.service.VenueService;
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
 * REST controller for managing {@link com.sbm.mc.sevenroomstoreviewpro.domain.Venue}.
 */
@RestController
@RequestMapping("/api/venues")
public class VenueResource {

    private static final Logger log = LoggerFactory.getLogger(VenueResource.class);

    private static final String ENTITY_NAME = "venue";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VenueService venueService;

    private final VenueRepository venueRepository;

    public VenueResource(VenueService venueService, VenueRepository venueRepository) {
        this.venueService = venueService;
        this.venueRepository = venueRepository;
    }

    /**
     * {@code POST  /venues} : Create a new venue.
     *
     * @param venue the venue to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new venue, or with status {@code 400 (Bad Request)} if the venue has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Venue> createVenue(@RequestBody Venue venue) throws URISyntaxException {
        log.debug("REST request to save Venue : {}", venue);
        if (venue.getId() != null) {
            throw new BadRequestAlertException("A new venue cannot already have an ID", ENTITY_NAME, "idexists");
        }
        venue = venueService.save(venue);
        return ResponseEntity.created(new URI("/api/venues/" + venue.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, venue.getId()))
            .body(venue);
    }

    /**
     * {@code PUT  /venues/:id} : Updates an existing venue.
     *
     * @param id the id of the venue to save.
     * @param venue the venue to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated venue,
     * or with status {@code 400 (Bad Request)} if the venue is not valid,
     * or with status {@code 500 (Internal Server Error)} if the venue couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Venue> updateVenue(@PathVariable(value = "id", required = false) final String id, @RequestBody Venue venue)
        throws URISyntaxException {
        log.debug("REST request to update Venue : {}, {}", id, venue);
        if (venue.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, venue.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!venueRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        venue = venueService.update(venue);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, venue.getId()))
            .body(venue);
    }

    /**
     * {@code PATCH  /venues/:id} : Partial updates given fields of an existing venue, field will ignore if it is null
     *
     * @param id the id of the venue to save.
     * @param venue the venue to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated venue,
     * or with status {@code 400 (Bad Request)} if the venue is not valid,
     * or with status {@code 404 (Not Found)} if the venue is not found,
     * or with status {@code 500 (Internal Server Error)} if the venue couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Venue> partialUpdateVenue(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody Venue venue
    ) throws URISyntaxException {
        log.debug("REST request to partial update Venue partially : {}, {}", id, venue);
        if (venue.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, venue.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!venueRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Venue> result = venueService.partialUpdate(venue);

        return ResponseUtil.wrapOrNotFound(result, HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, venue.getId()));
    }

    /**
     * {@code GET  /venues} : get all the venues.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of venues in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Venue>> getAllVenues(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Venues");
        Page<Venue> page = venueService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /venues/:id} : get the "id" venue.
     *
     * @param id the id of the venue to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the venue, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Venue> getVenue(@PathVariable("id") String id) {
        log.debug("REST request to get Venue : {}", id);
        Optional<Venue> venue = venueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(venue);
    }

    /**
     * {@code DELETE  /venues/:id} : delete the "id" venue.
     *
     * @param id the id of the venue to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenue(@PathVariable("id") String id) {
        log.debug("REST request to delete Venue : {}", id);
        venueService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
