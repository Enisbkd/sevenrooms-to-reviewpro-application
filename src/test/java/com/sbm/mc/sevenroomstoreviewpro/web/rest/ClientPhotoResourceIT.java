package com.sbm.mc.sevenroomstoreviewpro.web.rest;

import static com.sbm.mc.sevenroomstoreviewpro.domain.ClientPhotoAsserts.*;
import static com.sbm.mc.sevenroomstoreviewpro.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbm.mc.sevenroomstoreviewpro.IntegrationTest;
import com.sbm.mc.sevenroomstoreviewpro.domain.ClientPhoto;
import com.sbm.mc.sevenroomstoreviewpro.repository.ClientPhotoRepository;
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
 * Integration tests for the {@link ClientPhotoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ClientPhotoResourceIT {

    private static final String DEFAULT_LARGE = "AAAAAAAAAA";
    private static final String UPDATED_LARGE = "BBBBBBBBBB";

    private static final Integer DEFAULT_LARGE_HEIGHT = 1;
    private static final Integer UPDATED_LARGE_HEIGHT = 2;

    private static final Integer DEFAULT_LARGE_WIDTH = 1;
    private static final Integer UPDATED_LARGE_WIDTH = 2;

    private static final String DEFAULT_MEDIUM = "AAAAAAAAAA";
    private static final String UPDATED_MEDIUM = "BBBBBBBBBB";

    private static final Integer DEFAULT_MEDIUM_HEIGHT = 1;
    private static final Integer UPDATED_MEDIUM_HEIGHT = 2;

    private static final Integer DEFAULT_MEDIUM_WIDTH = 1;
    private static final Integer UPDATED_MEDIUM_WIDTH = 2;

    private static final String DEFAULT_SMALL = "AAAAAAAAAA";
    private static final String UPDATED_SMALL = "BBBBBBBBBB";

    private static final Integer DEFAULT_SMALL_HEIGHT = 1;
    private static final Integer UPDATED_SMALL_HEIGHT = 2;

    private static final Integer DEFAULT_SMALL_WIDTH = 1;
    private static final Integer UPDATED_SMALL_WIDTH = 2;

    private static final String DEFAULT_RAW = "AAAAAAAAAA";
    private static final String UPDATED_RAW = "BBBBBBBBBB";

    private static final Integer DEFAULT_CROPX = 1;
    private static final Integer UPDATED_CROPX = 2;

    private static final Integer DEFAULT_CROPY = 1;
    private static final Integer UPDATED_CROPY = 2;

    private static final Double DEFAULT_CROP_HEIGHT = 1D;
    private static final Double UPDATED_CROP_HEIGHT = 2D;

    private static final Double DEFAULT_CROP_WIDTH = 1D;
    private static final Double UPDATED_CROP_WIDTH = 2D;

    private static final String ENTITY_API_URL = "/api/client-photos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ClientPhotoRepository clientPhotoRepository;

    @Autowired
    private MockMvc restClientPhotoMockMvc;

    private ClientPhoto clientPhoto;

    private ClientPhoto insertedClientPhoto;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClientPhoto createEntity() {
        ClientPhoto clientPhoto = new ClientPhoto()
            .large(DEFAULT_LARGE)
            .largeHeight(DEFAULT_LARGE_HEIGHT)
            .largeWidth(DEFAULT_LARGE_WIDTH)
            .medium(DEFAULT_MEDIUM)
            .mediumHeight(DEFAULT_MEDIUM_HEIGHT)
            .mediumWidth(DEFAULT_MEDIUM_WIDTH)
            .small(DEFAULT_SMALL)
            .smallHeight(DEFAULT_SMALL_HEIGHT)
            .smallWidth(DEFAULT_SMALL_WIDTH)
            .raw(DEFAULT_RAW)
            .cropx(DEFAULT_CROPX)
            .cropy(DEFAULT_CROPY)
            .cropHeight(DEFAULT_CROP_HEIGHT)
            .cropWidth(DEFAULT_CROP_WIDTH);
        return clientPhoto;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClientPhoto createUpdatedEntity() {
        ClientPhoto clientPhoto = new ClientPhoto()
            .large(UPDATED_LARGE)
            .largeHeight(UPDATED_LARGE_HEIGHT)
            .largeWidth(UPDATED_LARGE_WIDTH)
            .medium(UPDATED_MEDIUM)
            .mediumHeight(UPDATED_MEDIUM_HEIGHT)
            .mediumWidth(UPDATED_MEDIUM_WIDTH)
            .small(UPDATED_SMALL)
            .smallHeight(UPDATED_SMALL_HEIGHT)
            .smallWidth(UPDATED_SMALL_WIDTH)
            .raw(UPDATED_RAW)
            .cropx(UPDATED_CROPX)
            .cropy(UPDATED_CROPY)
            .cropHeight(UPDATED_CROP_HEIGHT)
            .cropWidth(UPDATED_CROP_WIDTH);
        return clientPhoto;
    }

    @BeforeEach
    public void initTest() {
        clientPhoto = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedClientPhoto != null) {
            clientPhotoRepository.delete(insertedClientPhoto);
            insertedClientPhoto = null;
        }
    }

    @Test
    void createClientPhoto() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ClientPhoto
        var returnedClientPhoto = om.readValue(
            restClientPhotoMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(clientPhoto)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ClientPhoto.class
        );

        // Validate the ClientPhoto in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertClientPhotoUpdatableFieldsEquals(returnedClientPhoto, getPersistedClientPhoto(returnedClientPhoto));

        insertedClientPhoto = returnedClientPhoto;
    }

    @Test
    void createClientPhotoWithExistingId() throws Exception {
        // Create the ClientPhoto with an existing ID
        clientPhoto.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restClientPhotoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(clientPhoto)))
            .andExpect(status().isBadRequest());

        // Validate the ClientPhoto in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void getAllClientPhotos() throws Exception {
        // Initialize the database
        insertedClientPhoto = clientPhotoRepository.save(clientPhoto);

        // Get all the clientPhotoList
        restClientPhotoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(clientPhoto.getId())))
            .andExpect(jsonPath("$.[*].large").value(hasItem(DEFAULT_LARGE)))
            .andExpect(jsonPath("$.[*].largeHeight").value(hasItem(DEFAULT_LARGE_HEIGHT)))
            .andExpect(jsonPath("$.[*].largeWidth").value(hasItem(DEFAULT_LARGE_WIDTH)))
            .andExpect(jsonPath("$.[*].medium").value(hasItem(DEFAULT_MEDIUM)))
            .andExpect(jsonPath("$.[*].mediumHeight").value(hasItem(DEFAULT_MEDIUM_HEIGHT)))
            .andExpect(jsonPath("$.[*].mediumWidth").value(hasItem(DEFAULT_MEDIUM_WIDTH)))
            .andExpect(jsonPath("$.[*].small").value(hasItem(DEFAULT_SMALL)))
            .andExpect(jsonPath("$.[*].smallHeight").value(hasItem(DEFAULT_SMALL_HEIGHT)))
            .andExpect(jsonPath("$.[*].smallWidth").value(hasItem(DEFAULT_SMALL_WIDTH)))
            .andExpect(jsonPath("$.[*].raw").value(hasItem(DEFAULT_RAW)))
            .andExpect(jsonPath("$.[*].cropx").value(hasItem(DEFAULT_CROPX)))
            .andExpect(jsonPath("$.[*].cropy").value(hasItem(DEFAULT_CROPY)))
            .andExpect(jsonPath("$.[*].cropHeight").value(hasItem(DEFAULT_CROP_HEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].cropWidth").value(hasItem(DEFAULT_CROP_WIDTH.doubleValue())));
    }

    @Test
    void getClientPhoto() throws Exception {
        // Initialize the database
        insertedClientPhoto = clientPhotoRepository.save(clientPhoto);

        // Get the clientPhoto
        restClientPhotoMockMvc
            .perform(get(ENTITY_API_URL_ID, clientPhoto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(clientPhoto.getId()))
            .andExpect(jsonPath("$.large").value(DEFAULT_LARGE))
            .andExpect(jsonPath("$.largeHeight").value(DEFAULT_LARGE_HEIGHT))
            .andExpect(jsonPath("$.largeWidth").value(DEFAULT_LARGE_WIDTH))
            .andExpect(jsonPath("$.medium").value(DEFAULT_MEDIUM))
            .andExpect(jsonPath("$.mediumHeight").value(DEFAULT_MEDIUM_HEIGHT))
            .andExpect(jsonPath("$.mediumWidth").value(DEFAULT_MEDIUM_WIDTH))
            .andExpect(jsonPath("$.small").value(DEFAULT_SMALL))
            .andExpect(jsonPath("$.smallHeight").value(DEFAULT_SMALL_HEIGHT))
            .andExpect(jsonPath("$.smallWidth").value(DEFAULT_SMALL_WIDTH))
            .andExpect(jsonPath("$.raw").value(DEFAULT_RAW))
            .andExpect(jsonPath("$.cropx").value(DEFAULT_CROPX))
            .andExpect(jsonPath("$.cropy").value(DEFAULT_CROPY))
            .andExpect(jsonPath("$.cropHeight").value(DEFAULT_CROP_HEIGHT.doubleValue()))
            .andExpect(jsonPath("$.cropWidth").value(DEFAULT_CROP_WIDTH.doubleValue()));
    }

    @Test
    void getNonExistingClientPhoto() throws Exception {
        // Get the clientPhoto
        restClientPhotoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingClientPhoto() throws Exception {
        // Initialize the database
        insertedClientPhoto = clientPhotoRepository.save(clientPhoto);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the clientPhoto
        ClientPhoto updatedClientPhoto = clientPhotoRepository.findById(clientPhoto.getId()).orElseThrow();
        updatedClientPhoto
            .large(UPDATED_LARGE)
            .largeHeight(UPDATED_LARGE_HEIGHT)
            .largeWidth(UPDATED_LARGE_WIDTH)
            .medium(UPDATED_MEDIUM)
            .mediumHeight(UPDATED_MEDIUM_HEIGHT)
            .mediumWidth(UPDATED_MEDIUM_WIDTH)
            .small(UPDATED_SMALL)
            .smallHeight(UPDATED_SMALL_HEIGHT)
            .smallWidth(UPDATED_SMALL_WIDTH)
            .raw(UPDATED_RAW)
            .cropx(UPDATED_CROPX)
            .cropy(UPDATED_CROPY)
            .cropHeight(UPDATED_CROP_HEIGHT)
            .cropWidth(UPDATED_CROP_WIDTH);

        restClientPhotoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedClientPhoto.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedClientPhoto))
            )
            .andExpect(status().isOk());

        // Validate the ClientPhoto in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedClientPhotoToMatchAllProperties(updatedClientPhoto);
    }

    @Test
    void putNonExistingClientPhoto() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        clientPhoto.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClientPhotoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, clientPhoto.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(clientPhoto))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientPhoto in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchClientPhoto() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        clientPhoto.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientPhotoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(clientPhoto))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientPhoto in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamClientPhoto() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        clientPhoto.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientPhotoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(clientPhoto)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClientPhoto in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateClientPhotoWithPatch() throws Exception {
        // Initialize the database
        insertedClientPhoto = clientPhotoRepository.save(clientPhoto);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the clientPhoto using partial update
        ClientPhoto partialUpdatedClientPhoto = new ClientPhoto();
        partialUpdatedClientPhoto.setId(clientPhoto.getId());

        partialUpdatedClientPhoto
            .largeHeight(UPDATED_LARGE_HEIGHT)
            .medium(UPDATED_MEDIUM)
            .mediumHeight(UPDATED_MEDIUM_HEIGHT)
            .smallWidth(UPDATED_SMALL_WIDTH)
            .cropy(UPDATED_CROPY)
            .cropHeight(UPDATED_CROP_HEIGHT)
            .cropWidth(UPDATED_CROP_WIDTH);

        restClientPhotoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClientPhoto.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedClientPhoto))
            )
            .andExpect(status().isOk());

        // Validate the ClientPhoto in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertClientPhotoUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedClientPhoto, clientPhoto),
            getPersistedClientPhoto(clientPhoto)
        );
    }

    @Test
    void fullUpdateClientPhotoWithPatch() throws Exception {
        // Initialize the database
        insertedClientPhoto = clientPhotoRepository.save(clientPhoto);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the clientPhoto using partial update
        ClientPhoto partialUpdatedClientPhoto = new ClientPhoto();
        partialUpdatedClientPhoto.setId(clientPhoto.getId());

        partialUpdatedClientPhoto
            .large(UPDATED_LARGE)
            .largeHeight(UPDATED_LARGE_HEIGHT)
            .largeWidth(UPDATED_LARGE_WIDTH)
            .medium(UPDATED_MEDIUM)
            .mediumHeight(UPDATED_MEDIUM_HEIGHT)
            .mediumWidth(UPDATED_MEDIUM_WIDTH)
            .small(UPDATED_SMALL)
            .smallHeight(UPDATED_SMALL_HEIGHT)
            .smallWidth(UPDATED_SMALL_WIDTH)
            .raw(UPDATED_RAW)
            .cropx(UPDATED_CROPX)
            .cropy(UPDATED_CROPY)
            .cropHeight(UPDATED_CROP_HEIGHT)
            .cropWidth(UPDATED_CROP_WIDTH);

        restClientPhotoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClientPhoto.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedClientPhoto))
            )
            .andExpect(status().isOk());

        // Validate the ClientPhoto in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertClientPhotoUpdatableFieldsEquals(partialUpdatedClientPhoto, getPersistedClientPhoto(partialUpdatedClientPhoto));
    }

    @Test
    void patchNonExistingClientPhoto() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        clientPhoto.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClientPhotoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, clientPhoto.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(clientPhoto))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientPhoto in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchClientPhoto() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        clientPhoto.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientPhotoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(clientPhoto))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientPhoto in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamClientPhoto() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        clientPhoto.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientPhotoMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(clientPhoto)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClientPhoto in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteClientPhoto() throws Exception {
        // Initialize the database
        insertedClientPhoto = clientPhotoRepository.save(clientPhoto);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the clientPhoto
        restClientPhotoMockMvc
            .perform(delete(ENTITY_API_URL_ID, clientPhoto.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return clientPhotoRepository.count();
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

    protected ClientPhoto getPersistedClientPhoto(ClientPhoto clientPhoto) {
        return clientPhotoRepository.findById(clientPhoto.getId()).orElseThrow();
    }

    protected void assertPersistedClientPhotoToMatchAllProperties(ClientPhoto expectedClientPhoto) {
        assertClientPhotoAllPropertiesEquals(expectedClientPhoto, getPersistedClientPhoto(expectedClientPhoto));
    }

    protected void assertPersistedClientPhotoToMatchUpdatableProperties(ClientPhoto expectedClientPhoto) {
        assertClientPhotoAllUpdatablePropertiesEquals(expectedClientPhoto, getPersistedClientPhoto(expectedClientPhoto));
    }
}
