package com.sbm.mc.sevenroomstoreviewpro.web.rest;

import static com.sbm.mc.sevenroomstoreviewpro.domain.RvpGuestAsserts.*;
import static com.sbm.mc.sevenroomstoreviewpro.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbm.mc.sevenroomstoreviewpro.IntegrationTest;
import com.sbm.mc.sevenroomstoreviewpro.domain.RvpGuest;
import com.sbm.mc.sevenroomstoreviewpro.repository.RvpGuestRepository;
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Integration tests for the {@link RvpGuestResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RvpGuestResourceIT {

    private static final String DEFAULT_PMS_ID = "AAAAAAAAAA";
    private static final String UPDATED_PMS_ID = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LANGUAGE = "AAAAAAAAAA";
    private static final String UPDATED_LANGUAGE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CHECKIN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CHECKIN = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_CHECKOUT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CHECKOUT = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_ALT = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_ALT = "BBBBBBBBBB";

    private static final String DEFAULT_SALUTATION = "AAAAAAAAAA";
    private static final String UPDATED_SALUTATION = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/rvp-guests";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private RvpGuestRepository rvpGuestRepository;

    @Autowired
    private MockMvc restRvpGuestMockMvc;

    private RvpGuest rvpGuest;

    private RvpGuest insertedRvpGuest;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RvpGuest createEntity() {
        RvpGuest rvpGuest = new RvpGuest()
            .pmsId(DEFAULT_PMS_ID)
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .language(DEFAULT_LANGUAGE)
            .checkin(DEFAULT_CHECKIN)
            .checkout(DEFAULT_CHECKOUT)
            .email(DEFAULT_EMAIL)
            .emailAlt(DEFAULT_EMAIL_ALT)
            .salutation(DEFAULT_SALUTATION);
        return rvpGuest;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RvpGuest createUpdatedEntity() {
        RvpGuest rvpGuest = new RvpGuest()
            .pmsId(UPDATED_PMS_ID)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .language(UPDATED_LANGUAGE)
            .checkin(UPDATED_CHECKIN)
            .checkout(UPDATED_CHECKOUT)
            .email(UPDATED_EMAIL)
            .emailAlt(UPDATED_EMAIL_ALT)
            .salutation(UPDATED_SALUTATION);
        return rvpGuest;
    }

    @BeforeEach
    public void initTest() {
        rvpGuest = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedRvpGuest != null) {
            rvpGuestRepository.delete(insertedRvpGuest);
            insertedRvpGuest = null;
        }
    }

    @Test
    void createRvpGuest() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the RvpGuest
        var returnedRvpGuest = om.readValue(
            restRvpGuestMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(rvpGuest)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            RvpGuest.class
        );

        // Validate the RvpGuest in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertRvpGuestUpdatableFieldsEquals(returnedRvpGuest, getPersistedRvpGuest(returnedRvpGuest));

        insertedRvpGuest = returnedRvpGuest;
    }

    @Test
    void createRvpGuestWithExistingId() throws Exception {
        // Create the RvpGuest with an existing ID
        rvpGuest.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRvpGuestMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(rvpGuest)))
            .andExpect(status().isBadRequest());

        // Validate the RvpGuest in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void getAllRvpGuests() throws Exception {
        // Initialize the database
        insertedRvpGuest = rvpGuestRepository.save(rvpGuest);

        // Get all the rvpGuestList
        restRvpGuestMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rvpGuest.getId())))
            .andExpect(jsonPath("$.[*].pmsId").value(hasItem(DEFAULT_PMS_ID)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].language").value(hasItem(DEFAULT_LANGUAGE)))
            .andExpect(jsonPath("$.[*].checkin").value(hasItem(DEFAULT_CHECKIN.toString())))
            .andExpect(jsonPath("$.[*].checkout").value(hasItem(DEFAULT_CHECKOUT.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].emailAlt").value(hasItem(DEFAULT_EMAIL_ALT)))
            .andExpect(jsonPath("$.[*].salutation").value(hasItem(DEFAULT_SALUTATION)));
    }

    @Test
    void getRvpGuest() throws Exception {
        // Initialize the database
        insertedRvpGuest = rvpGuestRepository.save(rvpGuest);

        // Get the rvpGuest
        restRvpGuestMockMvc
            .perform(get(ENTITY_API_URL_ID, rvpGuest.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(rvpGuest.getId()))
            .andExpect(jsonPath("$.pmsId").value(DEFAULT_PMS_ID))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.language").value(DEFAULT_LANGUAGE))
            .andExpect(jsonPath("$.checkin").value(DEFAULT_CHECKIN.toString()))
            .andExpect(jsonPath("$.checkout").value(DEFAULT_CHECKOUT.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.emailAlt").value(DEFAULT_EMAIL_ALT))
            .andExpect(jsonPath("$.salutation").value(DEFAULT_SALUTATION));
    }

    @Test
    void getNonExistingRvpGuest() throws Exception {
        // Get the rvpGuest
        restRvpGuestMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingRvpGuest() throws Exception {
        // Initialize the database
        insertedRvpGuest = rvpGuestRepository.save(rvpGuest);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the rvpGuest
        RvpGuest updatedRvpGuest = rvpGuestRepository.findById(rvpGuest.getId()).orElseThrow();
        updatedRvpGuest
            .pmsId(UPDATED_PMS_ID)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .language(UPDATED_LANGUAGE)
            .checkin(UPDATED_CHECKIN)
            .checkout(UPDATED_CHECKOUT)
            .email(UPDATED_EMAIL)
            .emailAlt(UPDATED_EMAIL_ALT)
            .salutation(UPDATED_SALUTATION);

        restRvpGuestMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRvpGuest.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedRvpGuest))
            )
            .andExpect(status().isOk());

        // Validate the RvpGuest in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedRvpGuestToMatchAllProperties(updatedRvpGuest);
    }

    @Test
    void putNonExistingRvpGuest() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rvpGuest.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRvpGuestMockMvc
            .perform(
                put(ENTITY_API_URL_ID, rvpGuest.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(rvpGuest))
            )
            .andExpect(status().isBadRequest());

        // Validate the RvpGuest in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchRvpGuest() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rvpGuest.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRvpGuestMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(rvpGuest))
            )
            .andExpect(status().isBadRequest());

        // Validate the RvpGuest in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamRvpGuest() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rvpGuest.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRvpGuestMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(rvpGuest)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RvpGuest in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateRvpGuestWithPatch() throws Exception {
        // Initialize the database
        insertedRvpGuest = rvpGuestRepository.save(rvpGuest);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the rvpGuest using partial update
        RvpGuest partialUpdatedRvpGuest = new RvpGuest();
        partialUpdatedRvpGuest.setId(rvpGuest.getId());

        partialUpdatedRvpGuest.pmsId(UPDATED_PMS_ID).firstName(UPDATED_FIRST_NAME).email(UPDATED_EMAIL).salutation(UPDATED_SALUTATION);

        restRvpGuestMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRvpGuest.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRvpGuest))
            )
            .andExpect(status().isOk());

        // Validate the RvpGuest in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRvpGuestUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedRvpGuest, rvpGuest), getPersistedRvpGuest(rvpGuest));
    }

    @Test
    void fullUpdateRvpGuestWithPatch() throws Exception {
        // Initialize the database
        insertedRvpGuest = rvpGuestRepository.save(rvpGuest);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the rvpGuest using partial update
        RvpGuest partialUpdatedRvpGuest = new RvpGuest();
        partialUpdatedRvpGuest.setId(rvpGuest.getId());

        partialUpdatedRvpGuest
            .pmsId(UPDATED_PMS_ID)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .language(UPDATED_LANGUAGE)
            .checkin(UPDATED_CHECKIN)
            .checkout(UPDATED_CHECKOUT)
            .email(UPDATED_EMAIL)
            .emailAlt(UPDATED_EMAIL_ALT)
            .salutation(UPDATED_SALUTATION);

        restRvpGuestMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRvpGuest.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRvpGuest))
            )
            .andExpect(status().isOk());

        // Validate the RvpGuest in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRvpGuestUpdatableFieldsEquals(partialUpdatedRvpGuest, getPersistedRvpGuest(partialUpdatedRvpGuest));
    }

    @Test
    void patchNonExistingRvpGuest() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rvpGuest.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRvpGuestMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, rvpGuest.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(rvpGuest))
            )
            .andExpect(status().isBadRequest());

        // Validate the RvpGuest in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchRvpGuest() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rvpGuest.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRvpGuestMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(rvpGuest))
            )
            .andExpect(status().isBadRequest());

        // Validate the RvpGuest in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamRvpGuest() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        rvpGuest.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRvpGuestMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(rvpGuest)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RvpGuest in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteRvpGuest() throws Exception {
        // Initialize the database
        insertedRvpGuest = rvpGuestRepository.save(rvpGuest);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the rvpGuest
        restRvpGuestMockMvc
            .perform(delete(ENTITY_API_URL_ID, rvpGuest.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return rvpGuestRepository.count();
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

    protected RvpGuest getPersistedRvpGuest(RvpGuest rvpGuest) {
        return rvpGuestRepository.findById(rvpGuest.getId()).orElseThrow();
    }

    protected void assertPersistedRvpGuestToMatchAllProperties(RvpGuest expectedRvpGuest) {
        assertRvpGuestAllPropertiesEquals(expectedRvpGuest, getPersistedRvpGuest(expectedRvpGuest));
    }

    protected void assertPersistedRvpGuestToMatchUpdatableProperties(RvpGuest expectedRvpGuest) {
        assertRvpGuestAllUpdatablePropertiesEquals(expectedRvpGuest, getPersistedRvpGuest(expectedRvpGuest));
    }
}
