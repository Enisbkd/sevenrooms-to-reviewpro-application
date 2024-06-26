package com.sbm.mc.sevenroomstoreviewpro.web.rest;

import static com.sbm.mc.sevenroomstoreviewpro.domain.VenueAsserts.*;
import static com.sbm.mc.sevenroomstoreviewpro.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbm.mc.sevenroomstoreviewpro.IntegrationTest;
import com.sbm.mc.sevenroomstoreviewpro.domain.Venue;
import com.sbm.mc.sevenroomstoreviewpro.repository.VenueRepository;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Integration tests for the {@link VenueResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class VenueResourceIT {

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_BLACK_LOGO = "AAAAAAAAAA";
    private static final String UPDATED_BLACK_LOGO = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_CROSS_STREET = "AAAAAAAAAA";
    private static final String UPDATED_CROSS_STREET = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENCY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_EXTERNAL_VENUE_ID = "AAAAAAAAAA";
    private static final String UPDATED_EXTERNAL_VENUE_ID = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FULL_DINING_BACKEND = false;
    private static final Boolean UPDATED_FULL_DINING_BACKEND = true;

    private static final Boolean DEFAULT_GRID_ENABLED = false;
    private static final Boolean UPDATED_GRID_ENABLED = true;

    private static final String DEFAULT_VENUE_ID = "AAAAAAAAAA";
    private static final String UPDATED_VENUE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_INTERNAL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INTERNAL_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_MEMBERSHIP_ENABLED = false;
    private static final Boolean UPDATED_MEMBERSHIP_ENABLED = true;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NEIGHBORHOOD = "AAAAAAAAAA";
    private static final String UPDATED_NEIGHBORHOOD = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_POLICY = "AAAAAAAAAA";
    private static final String UPDATED_POLICY = "BBBBBBBBBB";

    private static final String DEFAULT_POSTAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_POSTAL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_COLOR = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_COLOR = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_UNIQUE_CONFIRMATION_PREFIX = "AAAAAAAAAA";
    private static final String UPDATED_UNIQUE_CONFIRMATION_PREFIX = "BBBBBBBBBB";

    private static final String DEFAULT_VENUE_CLASS = "AAAAAAAAAA";
    private static final String UPDATED_VENUE_CLASS = "BBBBBBBBBB";

    private static final String DEFAULT_VENUE_GROUP_ID = "AAAAAAAAAA";
    private static final String UPDATED_VENUE_GROUP_ID = "BBBBBBBBBB";

    private static final String DEFAULT_VENUE_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_VENUE_GROUP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VENUE_URL_KEY = "AAAAAAAAAA";
    private static final String UPDATED_VENUE_URL_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_WEBSITE = "BBBBBBBBBB";

    private static final String DEFAULT_WHITE_LOGO = "AAAAAAAAAA";
    private static final String UPDATED_WHITE_LOGO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/venues";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private MockMvc restVenueMockMvc;

    private Venue venue;

    private Venue insertedVenue;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Venue createEntity() {
        Venue venue = new Venue()
            .address(DEFAULT_ADDRESS)
            .blackLogo(DEFAULT_BLACK_LOGO)
            .country(DEFAULT_COUNTRY)
            .crossStreet(DEFAULT_CROSS_STREET)
            .currencyCode(DEFAULT_CURRENCY_CODE)
            .externalVenueId(DEFAULT_EXTERNAL_VENUE_ID)
            .fullDiningBackend(DEFAULT_FULL_DINING_BACKEND)
            .gridEnabled(DEFAULT_GRID_ENABLED)
            .venueId(DEFAULT_VENUE_ID)
            .internalName(DEFAULT_INTERNAL_NAME)
            .membershipEnabled(DEFAULT_MEMBERSHIP_ENABLED)
            .name(DEFAULT_NAME)
            .neighborhood(DEFAULT_NEIGHBORHOOD)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .policy(DEFAULT_POLICY)
            .postalCode(DEFAULT_POSTAL_CODE)
            .primaryColor(DEFAULT_PRIMARY_COLOR)
            .secondaryColor(DEFAULT_SECONDARY_COLOR)
            .state(DEFAULT_STATE)
            .uniqueConfirmationPrefix(DEFAULT_UNIQUE_CONFIRMATION_PREFIX)
            .venueClass(DEFAULT_VENUE_CLASS)
            .venueGroupId(DEFAULT_VENUE_GROUP_ID)
            .venueGroupName(DEFAULT_VENUE_GROUP_NAME)
            .venueUrlKey(DEFAULT_VENUE_URL_KEY)
            .website(DEFAULT_WEBSITE)
            .whiteLogo(DEFAULT_WHITE_LOGO);
        return venue;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Venue createUpdatedEntity() {
        Venue venue = new Venue()
            .address(UPDATED_ADDRESS)
            .blackLogo(UPDATED_BLACK_LOGO)
            .country(UPDATED_COUNTRY)
            .crossStreet(UPDATED_CROSS_STREET)
            .currencyCode(UPDATED_CURRENCY_CODE)
            .externalVenueId(UPDATED_EXTERNAL_VENUE_ID)
            .fullDiningBackend(UPDATED_FULL_DINING_BACKEND)
            .gridEnabled(UPDATED_GRID_ENABLED)
            .venueId(UPDATED_VENUE_ID)
            .internalName(UPDATED_INTERNAL_NAME)
            .membershipEnabled(UPDATED_MEMBERSHIP_ENABLED)
            .name(UPDATED_NAME)
            .neighborhood(UPDATED_NEIGHBORHOOD)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .policy(UPDATED_POLICY)
            .postalCode(UPDATED_POSTAL_CODE)
            .primaryColor(UPDATED_PRIMARY_COLOR)
            .secondaryColor(UPDATED_SECONDARY_COLOR)
            .state(UPDATED_STATE)
            .uniqueConfirmationPrefix(UPDATED_UNIQUE_CONFIRMATION_PREFIX)
            .venueClass(UPDATED_VENUE_CLASS)
            .venueGroupId(UPDATED_VENUE_GROUP_ID)
            .venueGroupName(UPDATED_VENUE_GROUP_NAME)
            .venueUrlKey(UPDATED_VENUE_URL_KEY)
            .website(UPDATED_WEBSITE)
            .whiteLogo(UPDATED_WHITE_LOGO);
        return venue;
    }

    @BeforeEach
    public void initTest() {
        venue = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedVenue != null) {
            venueRepository.delete(insertedVenue);
            insertedVenue = null;
        }
    }

    @Test
    void createVenue() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Venue
        var returnedVenue = om.readValue(
            restVenueMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(venue)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Venue.class
        );

        // Validate the Venue in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertVenueUpdatableFieldsEquals(returnedVenue, getPersistedVenue(returnedVenue));

        insertedVenue = returnedVenue;
    }

    @Test
    void createVenueWithExistingId() throws Exception {
        // Create the Venue with an existing ID
        venue.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restVenueMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(venue)))
            .andExpect(status().isBadRequest());

        // Validate the Venue in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void getAllVenues() throws Exception {
        // Initialize the database
        insertedVenue = venueRepository.save(venue);

        // Get all the venueList
        restVenueMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(venue.getId())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].blackLogo").value(hasItem(DEFAULT_BLACK_LOGO)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].crossStreet").value(hasItem(DEFAULT_CROSS_STREET)))
            .andExpect(jsonPath("$.[*].currencyCode").value(hasItem(DEFAULT_CURRENCY_CODE)))
            .andExpect(jsonPath("$.[*].externalVenueId").value(hasItem(DEFAULT_EXTERNAL_VENUE_ID)))
            .andExpect(jsonPath("$.[*].fullDiningBackend").value(hasItem(DEFAULT_FULL_DINING_BACKEND.booleanValue())))
            .andExpect(jsonPath("$.[*].gridEnabled").value(hasItem(DEFAULT_GRID_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].venueId").value(hasItem(DEFAULT_VENUE_ID)))
            .andExpect(jsonPath("$.[*].internalName").value(hasItem(DEFAULT_INTERNAL_NAME)))
            .andExpect(jsonPath("$.[*].membershipEnabled").value(hasItem(DEFAULT_MEMBERSHIP_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].neighborhood").value(hasItem(DEFAULT_NEIGHBORHOOD)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].policy").value(hasItem(DEFAULT_POLICY)))
            .andExpect(jsonPath("$.[*].postalCode").value(hasItem(DEFAULT_POSTAL_CODE)))
            .andExpect(jsonPath("$.[*].primaryColor").value(hasItem(DEFAULT_PRIMARY_COLOR)))
            .andExpect(jsonPath("$.[*].secondaryColor").value(hasItem(DEFAULT_SECONDARY_COLOR)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].uniqueConfirmationPrefix").value(hasItem(DEFAULT_UNIQUE_CONFIRMATION_PREFIX)))
            .andExpect(jsonPath("$.[*].venueClass").value(hasItem(DEFAULT_VENUE_CLASS)))
            .andExpect(jsonPath("$.[*].venueGroupId").value(hasItem(DEFAULT_VENUE_GROUP_ID)))
            .andExpect(jsonPath("$.[*].venueGroupName").value(hasItem(DEFAULT_VENUE_GROUP_NAME)))
            .andExpect(jsonPath("$.[*].venueUrlKey").value(hasItem(DEFAULT_VENUE_URL_KEY)))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE)))
            .andExpect(jsonPath("$.[*].whiteLogo").value(hasItem(DEFAULT_WHITE_LOGO)));
    }

    @Test
    void getVenue() throws Exception {
        // Initialize the database
        insertedVenue = venueRepository.save(venue);

        // Get the venue
        restVenueMockMvc
            .perform(get(ENTITY_API_URL_ID, venue.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(venue.getId()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.blackLogo").value(DEFAULT_BLACK_LOGO))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.crossStreet").value(DEFAULT_CROSS_STREET))
            .andExpect(jsonPath("$.currencyCode").value(DEFAULT_CURRENCY_CODE))
            .andExpect(jsonPath("$.externalVenueId").value(DEFAULT_EXTERNAL_VENUE_ID))
            .andExpect(jsonPath("$.fullDiningBackend").value(DEFAULT_FULL_DINING_BACKEND.booleanValue()))
            .andExpect(jsonPath("$.gridEnabled").value(DEFAULT_GRID_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.venueId").value(DEFAULT_VENUE_ID))
            .andExpect(jsonPath("$.internalName").value(DEFAULT_INTERNAL_NAME))
            .andExpect(jsonPath("$.membershipEnabled").value(DEFAULT_MEMBERSHIP_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.neighborhood").value(DEFAULT_NEIGHBORHOOD))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.policy").value(DEFAULT_POLICY))
            .andExpect(jsonPath("$.postalCode").value(DEFAULT_POSTAL_CODE))
            .andExpect(jsonPath("$.primaryColor").value(DEFAULT_PRIMARY_COLOR))
            .andExpect(jsonPath("$.secondaryColor").value(DEFAULT_SECONDARY_COLOR))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE))
            .andExpect(jsonPath("$.uniqueConfirmationPrefix").value(DEFAULT_UNIQUE_CONFIRMATION_PREFIX))
            .andExpect(jsonPath("$.venueClass").value(DEFAULT_VENUE_CLASS))
            .andExpect(jsonPath("$.venueGroupId").value(DEFAULT_VENUE_GROUP_ID))
            .andExpect(jsonPath("$.venueGroupName").value(DEFAULT_VENUE_GROUP_NAME))
            .andExpect(jsonPath("$.venueUrlKey").value(DEFAULT_VENUE_URL_KEY))
            .andExpect(jsonPath("$.website").value(DEFAULT_WEBSITE))
            .andExpect(jsonPath("$.whiteLogo").value(DEFAULT_WHITE_LOGO));
    }

    @Test
    void getNonExistingVenue() throws Exception {
        // Get the venue
        restVenueMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingVenue() throws Exception {
        // Initialize the database
        insertedVenue = venueRepository.save(venue);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the venue
        Venue updatedVenue = venueRepository.findById(venue.getId()).orElseThrow();
        updatedVenue
            .address(UPDATED_ADDRESS)
            .blackLogo(UPDATED_BLACK_LOGO)
            .country(UPDATED_COUNTRY)
            .crossStreet(UPDATED_CROSS_STREET)
            .currencyCode(UPDATED_CURRENCY_CODE)
            .externalVenueId(UPDATED_EXTERNAL_VENUE_ID)
            .fullDiningBackend(UPDATED_FULL_DINING_BACKEND)
            .gridEnabled(UPDATED_GRID_ENABLED)
            .venueId(UPDATED_VENUE_ID)
            .internalName(UPDATED_INTERNAL_NAME)
            .membershipEnabled(UPDATED_MEMBERSHIP_ENABLED)
            .name(UPDATED_NAME)
            .neighborhood(UPDATED_NEIGHBORHOOD)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .policy(UPDATED_POLICY)
            .postalCode(UPDATED_POSTAL_CODE)
            .primaryColor(UPDATED_PRIMARY_COLOR)
            .secondaryColor(UPDATED_SECONDARY_COLOR)
            .state(UPDATED_STATE)
            .uniqueConfirmationPrefix(UPDATED_UNIQUE_CONFIRMATION_PREFIX)
            .venueClass(UPDATED_VENUE_CLASS)
            .venueGroupId(UPDATED_VENUE_GROUP_ID)
            .venueGroupName(UPDATED_VENUE_GROUP_NAME)
            .venueUrlKey(UPDATED_VENUE_URL_KEY)
            .website(UPDATED_WEBSITE)
            .whiteLogo(UPDATED_WHITE_LOGO);

        restVenueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedVenue.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedVenue))
            )
            .andExpect(status().isOk());

        // Validate the Venue in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedVenueToMatchAllProperties(updatedVenue);
    }

    @Test
    void putNonExistingVenue() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        venue.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVenueMockMvc
            .perform(put(ENTITY_API_URL_ID, venue.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(venue)))
            .andExpect(status().isBadRequest());

        // Validate the Venue in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchVenue() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        venue.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVenueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(venue))
            )
            .andExpect(status().isBadRequest());

        // Validate the Venue in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamVenue() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        venue.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVenueMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(venue)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Venue in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateVenueWithPatch() throws Exception {
        // Initialize the database
        insertedVenue = venueRepository.save(venue);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the venue using partial update
        Venue partialUpdatedVenue = new Venue();
        partialUpdatedVenue.setId(venue.getId());

        partialUpdatedVenue
            .blackLogo(UPDATED_BLACK_LOGO)
            .currencyCode(UPDATED_CURRENCY_CODE)
            .externalVenueId(UPDATED_EXTERNAL_VENUE_ID)
            .fullDiningBackend(UPDATED_FULL_DINING_BACKEND)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .policy(UPDATED_POLICY)
            .postalCode(UPDATED_POSTAL_CODE)
            .primaryColor(UPDATED_PRIMARY_COLOR)
            .venueGroupId(UPDATED_VENUE_GROUP_ID)
            .venueGroupName(UPDATED_VENUE_GROUP_NAME)
            .website(UPDATED_WEBSITE)
            .whiteLogo(UPDATED_WHITE_LOGO);

        restVenueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVenue.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedVenue))
            )
            .andExpect(status().isOk());

        // Validate the Venue in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertVenueUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedVenue, venue), getPersistedVenue(venue));
    }

    @Test
    void fullUpdateVenueWithPatch() throws Exception {
        // Initialize the database
        insertedVenue = venueRepository.save(venue);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the venue using partial update
        Venue partialUpdatedVenue = new Venue();
        partialUpdatedVenue.setId(venue.getId());

        partialUpdatedVenue
            .address(UPDATED_ADDRESS)
            .blackLogo(UPDATED_BLACK_LOGO)
            .country(UPDATED_COUNTRY)
            .crossStreet(UPDATED_CROSS_STREET)
            .currencyCode(UPDATED_CURRENCY_CODE)
            .externalVenueId(UPDATED_EXTERNAL_VENUE_ID)
            .fullDiningBackend(UPDATED_FULL_DINING_BACKEND)
            .gridEnabled(UPDATED_GRID_ENABLED)
            .venueId(UPDATED_VENUE_ID)
            .internalName(UPDATED_INTERNAL_NAME)
            .membershipEnabled(UPDATED_MEMBERSHIP_ENABLED)
            .name(UPDATED_NAME)
            .neighborhood(UPDATED_NEIGHBORHOOD)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .policy(UPDATED_POLICY)
            .postalCode(UPDATED_POSTAL_CODE)
            .primaryColor(UPDATED_PRIMARY_COLOR)
            .secondaryColor(UPDATED_SECONDARY_COLOR)
            .state(UPDATED_STATE)
            .uniqueConfirmationPrefix(UPDATED_UNIQUE_CONFIRMATION_PREFIX)
            .venueClass(UPDATED_VENUE_CLASS)
            .venueGroupId(UPDATED_VENUE_GROUP_ID)
            .venueGroupName(UPDATED_VENUE_GROUP_NAME)
            .venueUrlKey(UPDATED_VENUE_URL_KEY)
            .website(UPDATED_WEBSITE)
            .whiteLogo(UPDATED_WHITE_LOGO);

        restVenueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVenue.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedVenue))
            )
            .andExpect(status().isOk());

        // Validate the Venue in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertVenueUpdatableFieldsEquals(partialUpdatedVenue, getPersistedVenue(partialUpdatedVenue));
    }

    @Test
    void patchNonExistingVenue() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        venue.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVenueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, venue.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(venue))
            )
            .andExpect(status().isBadRequest());

        // Validate the Venue in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchVenue() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        venue.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVenueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(venue))
            )
            .andExpect(status().isBadRequest());

        // Validate the Venue in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamVenue() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        venue.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVenueMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(venue)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Venue in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteVenue() throws Exception {
        // Initialize the database
        insertedVenue = venueRepository.save(venue);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the venue
        restVenueMockMvc
            .perform(delete(ENTITY_API_URL_ID, venue.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return venueRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected Venue getPersistedVenue(Venue venue) {
        return venueRepository.findById(venue.getId()).orElseThrow();
    }

    protected void assertPersistedVenueToMatchAllProperties(Venue expectedVenue) {
        assertVenueAllPropertiesEquals(expectedVenue, getPersistedVenue(expectedVenue));
    }

    protected void assertPersistedVenueToMatchUpdatableProperties(Venue expectedVenue) {
        assertVenueAllUpdatablePropertiesEquals(expectedVenue, getPersistedVenue(expectedVenue));
    }
}
