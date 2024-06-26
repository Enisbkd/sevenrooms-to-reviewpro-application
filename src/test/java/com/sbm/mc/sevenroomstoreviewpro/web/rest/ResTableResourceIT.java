package com.sbm.mc.sevenroomstoreviewpro.web.rest;

import static com.sbm.mc.sevenroomstoreviewpro.domain.ResTableAsserts.*;
import static com.sbm.mc.sevenroomstoreviewpro.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbm.mc.sevenroomstoreviewpro.IntegrationTest;
import com.sbm.mc.sevenroomstoreviewpro.domain.ResTable;
import com.sbm.mc.sevenroomstoreviewpro.repository.ResTableRepository;
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
 * Integration tests for the {@link ResTableResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ResTableResourceIT {

    private static final String DEFAULT_TABLE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_TABLE_NUMBER = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/res-tables";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ResTableRepository resTableRepository;

    @Autowired
    private MockMvc restResTableMockMvc;

    private ResTable resTable;

    private ResTable insertedResTable;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResTable createEntity() {
        ResTable resTable = new ResTable().tableNumber(DEFAULT_TABLE_NUMBER);
        return resTable;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResTable createUpdatedEntity() {
        ResTable resTable = new ResTable().tableNumber(UPDATED_TABLE_NUMBER);
        return resTable;
    }

    @BeforeEach
    public void initTest() {
        resTable = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedResTable != null) {
            resTableRepository.delete(insertedResTable);
            insertedResTable = null;
        }
    }

    @Test
    void createResTable() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ResTable
        var returnedResTable = om.readValue(
            restResTableMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(resTable)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ResTable.class
        );

        // Validate the ResTable in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertResTableUpdatableFieldsEquals(returnedResTable, getPersistedResTable(returnedResTable));

        insertedResTable = returnedResTable;
    }

    @Test
    void createResTableWithExistingId() throws Exception {
        // Create the ResTable with an existing ID
        resTable.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restResTableMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(resTable)))
            .andExpect(status().isBadRequest());

        // Validate the ResTable in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void getAllResTables() throws Exception {
        // Initialize the database
        insertedResTable = resTableRepository.save(resTable);

        // Get all the resTableList
        restResTableMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resTable.getId())))
            .andExpect(jsonPath("$.[*].tableNumber").value(hasItem(DEFAULT_TABLE_NUMBER)));
    }

    @Test
    void getResTable() throws Exception {
        // Initialize the database
        insertedResTable = resTableRepository.save(resTable);

        // Get the resTable
        restResTableMockMvc
            .perform(get(ENTITY_API_URL_ID, resTable.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(resTable.getId()))
            .andExpect(jsonPath("$.tableNumber").value(DEFAULT_TABLE_NUMBER));
    }

    @Test
    void getNonExistingResTable() throws Exception {
        // Get the resTable
        restResTableMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingResTable() throws Exception {
        // Initialize the database
        insertedResTable = resTableRepository.save(resTable);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the resTable
        ResTable updatedResTable = resTableRepository.findById(resTable.getId()).orElseThrow();
        updatedResTable.tableNumber(UPDATED_TABLE_NUMBER);

        restResTableMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedResTable.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedResTable))
            )
            .andExpect(status().isOk());

        // Validate the ResTable in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedResTableToMatchAllProperties(updatedResTable);
    }

    @Test
    void putNonExistingResTable() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resTable.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResTableMockMvc
            .perform(
                put(ENTITY_API_URL_ID, resTable.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(resTable))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResTable in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchResTable() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resTable.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResTableMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(resTable))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResTable in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamResTable() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resTable.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResTableMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(resTable)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ResTable in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateResTableWithPatch() throws Exception {
        // Initialize the database
        insertedResTable = resTableRepository.save(resTable);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the resTable using partial update
        ResTable partialUpdatedResTable = new ResTable();
        partialUpdatedResTable.setId(resTable.getId());

        partialUpdatedResTable.tableNumber(UPDATED_TABLE_NUMBER);

        restResTableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResTable.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedResTable))
            )
            .andExpect(status().isOk());

        // Validate the ResTable in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertResTableUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedResTable, resTable), getPersistedResTable(resTable));
    }

    @Test
    void fullUpdateResTableWithPatch() throws Exception {
        // Initialize the database
        insertedResTable = resTableRepository.save(resTable);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the resTable using partial update
        ResTable partialUpdatedResTable = new ResTable();
        partialUpdatedResTable.setId(resTable.getId());

        partialUpdatedResTable.tableNumber(UPDATED_TABLE_NUMBER);

        restResTableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResTable.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedResTable))
            )
            .andExpect(status().isOk());

        // Validate the ResTable in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertResTableUpdatableFieldsEquals(partialUpdatedResTable, getPersistedResTable(partialUpdatedResTable));
    }

    @Test
    void patchNonExistingResTable() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resTable.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResTableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, resTable.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(resTable))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResTable in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchResTable() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resTable.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResTableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(resTable))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResTable in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamResTable() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resTable.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResTableMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(resTable)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ResTable in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteResTable() throws Exception {
        // Initialize the database
        insertedResTable = resTableRepository.save(resTable);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the resTable
        restResTableMockMvc
            .perform(delete(ENTITY_API_URL_ID, resTable.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return resTableRepository.count();
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

    protected ResTable getPersistedResTable(ResTable resTable) {
        return resTableRepository.findById(resTable.getId()).orElseThrow();
    }

    protected void assertPersistedResTableToMatchAllProperties(ResTable expectedResTable) {
        assertResTableAllPropertiesEquals(expectedResTable, getPersistedResTable(expectedResTable));
    }

    protected void assertPersistedResTableToMatchUpdatableProperties(ResTable expectedResTable) {
        assertResTableAllUpdatablePropertiesEquals(expectedResTable, getPersistedResTable(expectedResTable));
    }
}
