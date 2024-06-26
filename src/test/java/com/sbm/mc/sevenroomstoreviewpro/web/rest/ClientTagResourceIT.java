package com.sbm.mc.sevenroomstoreviewpro.web.rest;

import static com.sbm.mc.sevenroomstoreviewpro.domain.ClientTagAsserts.*;
import static com.sbm.mc.sevenroomstoreviewpro.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbm.mc.sevenroomstoreviewpro.IntegrationTest;
import com.sbm.mc.sevenroomstoreviewpro.domain.ClientTag;
import com.sbm.mc.sevenroomstoreviewpro.repository.ClientTagRepository;
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
 * Integration tests for the {@link ClientTagResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ClientTagResourceIT {

    private static final String DEFAULT_TAG = "AAAAAAAAAA";
    private static final String UPDATED_TAG = "BBBBBBBBBB";

    private static final String DEFAULT_TAG_DISPLAY = "AAAAAAAAAA";
    private static final String UPDATED_TAG_DISPLAY = "BBBBBBBBBB";

    private static final String DEFAULT_GROUP = "AAAAAAAAAA";
    private static final String UPDATED_GROUP = "BBBBBBBBBB";

    private static final String DEFAULT_GROUP_DISPLAY = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_DISPLAY = "BBBBBBBBBB";

    private static final String DEFAULT_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_COLOR = "BBBBBBBBBB";

    private static final String DEFAULT_TAG_SEARCH_QUERY = "AAAAAAAAAA";
    private static final String UPDATED_TAG_SEARCH_QUERY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/client-tags";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ClientTagRepository clientTagRepository;

    @Autowired
    private MockMvc restClientTagMockMvc;

    private ClientTag clientTag;

    private ClientTag insertedClientTag;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClientTag createEntity() {
        ClientTag clientTag = new ClientTag()
            .tag(DEFAULT_TAG)
            .tagDisplay(DEFAULT_TAG_DISPLAY)
            .group(DEFAULT_GROUP)
            .groupDisplay(DEFAULT_GROUP_DISPLAY)
            .color(DEFAULT_COLOR)
            .tagSearchQuery(DEFAULT_TAG_SEARCH_QUERY);
        return clientTag;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClientTag createUpdatedEntity() {
        ClientTag clientTag = new ClientTag()
            .tag(UPDATED_TAG)
            .tagDisplay(UPDATED_TAG_DISPLAY)
            .group(UPDATED_GROUP)
            .groupDisplay(UPDATED_GROUP_DISPLAY)
            .color(UPDATED_COLOR)
            .tagSearchQuery(UPDATED_TAG_SEARCH_QUERY);
        return clientTag;
    }

    @BeforeEach
    public void initTest() {
        clientTag = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedClientTag != null) {
            clientTagRepository.delete(insertedClientTag);
            insertedClientTag = null;
        }
    }

    @Test
    void createClientTag() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ClientTag
        var returnedClientTag = om.readValue(
            restClientTagMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(clientTag)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ClientTag.class
        );

        // Validate the ClientTag in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertClientTagUpdatableFieldsEquals(returnedClientTag, getPersistedClientTag(returnedClientTag));

        insertedClientTag = returnedClientTag;
    }

    @Test
    void createClientTagWithExistingId() throws Exception {
        // Create the ClientTag with an existing ID
        clientTag.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restClientTagMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(clientTag)))
            .andExpect(status().isBadRequest());

        // Validate the ClientTag in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void getAllClientTags() throws Exception {
        // Initialize the database
        insertedClientTag = clientTagRepository.save(clientTag);

        // Get all the clientTagList
        restClientTagMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(clientTag.getId())))
            .andExpect(jsonPath("$.[*].tag").value(hasItem(DEFAULT_TAG)))
            .andExpect(jsonPath("$.[*].tagDisplay").value(hasItem(DEFAULT_TAG_DISPLAY)))
            .andExpect(jsonPath("$.[*].group").value(hasItem(DEFAULT_GROUP)))
            .andExpect(jsonPath("$.[*].groupDisplay").value(hasItem(DEFAULT_GROUP_DISPLAY)))
            .andExpect(jsonPath("$.[*].color").value(hasItem(DEFAULT_COLOR)))
            .andExpect(jsonPath("$.[*].tagSearchQuery").value(hasItem(DEFAULT_TAG_SEARCH_QUERY)));
    }

    @Test
    void getClientTag() throws Exception {
        // Initialize the database
        insertedClientTag = clientTagRepository.save(clientTag);

        // Get the clientTag
        restClientTagMockMvc
            .perform(get(ENTITY_API_URL_ID, clientTag.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(clientTag.getId()))
            .andExpect(jsonPath("$.tag").value(DEFAULT_TAG))
            .andExpect(jsonPath("$.tagDisplay").value(DEFAULT_TAG_DISPLAY))
            .andExpect(jsonPath("$.group").value(DEFAULT_GROUP))
            .andExpect(jsonPath("$.groupDisplay").value(DEFAULT_GROUP_DISPLAY))
            .andExpect(jsonPath("$.color").value(DEFAULT_COLOR))
            .andExpect(jsonPath("$.tagSearchQuery").value(DEFAULT_TAG_SEARCH_QUERY));
    }

    @Test
    void getNonExistingClientTag() throws Exception {
        // Get the clientTag
        restClientTagMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingClientTag() throws Exception {
        // Initialize the database
        insertedClientTag = clientTagRepository.save(clientTag);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the clientTag
        ClientTag updatedClientTag = clientTagRepository.findById(clientTag.getId()).orElseThrow();
        updatedClientTag
            .tag(UPDATED_TAG)
            .tagDisplay(UPDATED_TAG_DISPLAY)
            .group(UPDATED_GROUP)
            .groupDisplay(UPDATED_GROUP_DISPLAY)
            .color(UPDATED_COLOR)
            .tagSearchQuery(UPDATED_TAG_SEARCH_QUERY);

        restClientTagMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedClientTag.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedClientTag))
            )
            .andExpect(status().isOk());

        // Validate the ClientTag in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedClientTagToMatchAllProperties(updatedClientTag);
    }

    @Test
    void putNonExistingClientTag() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        clientTag.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClientTagMockMvc
            .perform(
                put(ENTITY_API_URL_ID, clientTag.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(clientTag))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientTag in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchClientTag() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        clientTag.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientTagMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(clientTag))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientTag in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamClientTag() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        clientTag.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientTagMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(clientTag)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClientTag in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateClientTagWithPatch() throws Exception {
        // Initialize the database
        insertedClientTag = clientTagRepository.save(clientTag);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the clientTag using partial update
        ClientTag partialUpdatedClientTag = new ClientTag();
        partialUpdatedClientTag.setId(clientTag.getId());

        partialUpdatedClientTag
            .tagDisplay(UPDATED_TAG_DISPLAY)
            .group(UPDATED_GROUP)
            .groupDisplay(UPDATED_GROUP_DISPLAY)
            .color(UPDATED_COLOR);

        restClientTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClientTag.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedClientTag))
            )
            .andExpect(status().isOk());

        // Validate the ClientTag in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertClientTagUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedClientTag, clientTag),
            getPersistedClientTag(clientTag)
        );
    }

    @Test
    void fullUpdateClientTagWithPatch() throws Exception {
        // Initialize the database
        insertedClientTag = clientTagRepository.save(clientTag);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the clientTag using partial update
        ClientTag partialUpdatedClientTag = new ClientTag();
        partialUpdatedClientTag.setId(clientTag.getId());

        partialUpdatedClientTag
            .tag(UPDATED_TAG)
            .tagDisplay(UPDATED_TAG_DISPLAY)
            .group(UPDATED_GROUP)
            .groupDisplay(UPDATED_GROUP_DISPLAY)
            .color(UPDATED_COLOR)
            .tagSearchQuery(UPDATED_TAG_SEARCH_QUERY);

        restClientTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClientTag.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedClientTag))
            )
            .andExpect(status().isOk());

        // Validate the ClientTag in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertClientTagUpdatableFieldsEquals(partialUpdatedClientTag, getPersistedClientTag(partialUpdatedClientTag));
    }

    @Test
    void patchNonExistingClientTag() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        clientTag.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClientTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, clientTag.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(clientTag))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientTag in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchClientTag() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        clientTag.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientTagMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(clientTag))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientTag in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamClientTag() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        clientTag.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientTagMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(clientTag)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClientTag in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteClientTag() throws Exception {
        // Initialize the database
        insertedClientTag = clientTagRepository.save(clientTag);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the clientTag
        restClientTagMockMvc
            .perform(delete(ENTITY_API_URL_ID, clientTag.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return clientTagRepository.count();
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

    protected ClientTag getPersistedClientTag(ClientTag clientTag) {
        return clientTagRepository.findById(clientTag.getId()).orElseThrow();
    }

    protected void assertPersistedClientTagToMatchAllProperties(ClientTag expectedClientTag) {
        assertClientTagAllPropertiesEquals(expectedClientTag, getPersistedClientTag(expectedClientTag));
    }

    protected void assertPersistedClientTagToMatchUpdatableProperties(ClientTag expectedClientTag) {
        assertClientTagAllUpdatablePropertiesEquals(expectedClientTag, getPersistedClientTag(expectedClientTag));
    }
}
