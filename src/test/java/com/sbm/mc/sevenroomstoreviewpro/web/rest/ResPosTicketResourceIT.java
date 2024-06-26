package com.sbm.mc.sevenroomstoreviewpro.web.rest;

import static com.sbm.mc.sevenroomstoreviewpro.domain.ResPosTicketAsserts.*;
import static com.sbm.mc.sevenroomstoreviewpro.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbm.mc.sevenroomstoreviewpro.IntegrationTest;
import com.sbm.mc.sevenroomstoreviewpro.domain.ResPosTicket;
import com.sbm.mc.sevenroomstoreviewpro.repository.ResPosTicketRepository;
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
 * Integration tests for the {@link ResPosTicketResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ResPosTicketResourceIT {

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Double DEFAULT_ADMIN_FEE = 1D;
    private static final Double UPDATED_ADMIN_FEE = 2D;

    private static final Integer DEFAULT_CODE = 1;
    private static final Integer UPDATED_CODE = 2;

    private static final String DEFAULT_TABLE_NO = "AAAAAAAAAA";
    private static final String UPDATED_TABLE_NO = "BBBBBBBBBB";

    private static final Double DEFAULT_TAX = 1D;
    private static final Double UPDATED_TAX = 2D;

    private static final Integer DEFAULT_BUSINESS_ID = 1;
    private static final Integer UPDATED_BUSINESS_ID = 2;

    private static final Integer DEFAULT_TICKET_ID = 1;
    private static final Integer UPDATED_TICKET_ID = 2;

    private static final String DEFAULT_LOCAL_POSTICKET_ID = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_POSTICKET_ID = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYEE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_NAME = "BBBBBBBBBB";

    private static final Double DEFAULT_TOTAL = 1D;
    private static final Double UPDATED_TOTAL = 2D;

    private static final Double DEFAULT_SUBTOTAL = 1D;
    private static final Double UPDATED_SUBTOTAL = 2D;

    private static final String DEFAULT_START_TIME = "AAAAAAAAAA";
    private static final String UPDATED_START_TIME = "BBBBBBBBBB";

    private static final Double DEFAULT_SERVICE_CHARGE = 1D;
    private static final Double UPDATED_SERVICE_CHARGE = 2D;

    private static final String DEFAULT_ENDTIME = "AAAAAAAAAA";
    private static final String UPDATED_ENDTIME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/res-pos-tickets";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ResPosTicketRepository resPosTicketRepository;

    @Autowired
    private MockMvc restResPosTicketMockMvc;

    private ResPosTicket resPosTicket;

    private ResPosTicket insertedResPosTicket;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResPosTicket createEntity() {
        ResPosTicket resPosTicket = new ResPosTicket()
            .status(DEFAULT_STATUS)
            .adminFee(DEFAULT_ADMIN_FEE)
            .code(DEFAULT_CODE)
            .tableNo(DEFAULT_TABLE_NO)
            .tax(DEFAULT_TAX)
            .businessId(DEFAULT_BUSINESS_ID)
            .ticketId(DEFAULT_TICKET_ID)
            .localPosticketId(DEFAULT_LOCAL_POSTICKET_ID)
            .employeeName(DEFAULT_EMPLOYEE_NAME)
            .total(DEFAULT_TOTAL)
            .subtotal(DEFAULT_SUBTOTAL)
            .startTime(DEFAULT_START_TIME)
            .serviceCharge(DEFAULT_SERVICE_CHARGE)
            .endtime(DEFAULT_ENDTIME);
        return resPosTicket;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResPosTicket createUpdatedEntity() {
        ResPosTicket resPosTicket = new ResPosTicket()
            .status(UPDATED_STATUS)
            .adminFee(UPDATED_ADMIN_FEE)
            .code(UPDATED_CODE)
            .tableNo(UPDATED_TABLE_NO)
            .tax(UPDATED_TAX)
            .businessId(UPDATED_BUSINESS_ID)
            .ticketId(UPDATED_TICKET_ID)
            .localPosticketId(UPDATED_LOCAL_POSTICKET_ID)
            .employeeName(UPDATED_EMPLOYEE_NAME)
            .total(UPDATED_TOTAL)
            .subtotal(UPDATED_SUBTOTAL)
            .startTime(UPDATED_START_TIME)
            .serviceCharge(UPDATED_SERVICE_CHARGE)
            .endtime(UPDATED_ENDTIME);
        return resPosTicket;
    }

    @BeforeEach
    public void initTest() {
        resPosTicket = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedResPosTicket != null) {
            resPosTicketRepository.delete(insertedResPosTicket);
            insertedResPosTicket = null;
        }
    }

    @Test
    void createResPosTicket() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ResPosTicket
        var returnedResPosTicket = om.readValue(
            restResPosTicketMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(resPosTicket)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ResPosTicket.class
        );

        // Validate the ResPosTicket in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertResPosTicketUpdatableFieldsEquals(returnedResPosTicket, getPersistedResPosTicket(returnedResPosTicket));

        insertedResPosTicket = returnedResPosTicket;
    }

    @Test
    void createResPosTicketWithExistingId() throws Exception {
        // Create the ResPosTicket with an existing ID
        resPosTicket.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restResPosTicketMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(resPosTicket)))
            .andExpect(status().isBadRequest());

        // Validate the ResPosTicket in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void getAllResPosTickets() throws Exception {
        // Initialize the database
        insertedResPosTicket = resPosTicketRepository.save(resPosTicket);

        // Get all the resPosTicketList
        restResPosTicketMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resPosTicket.getId())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].adminFee").value(hasItem(DEFAULT_ADMIN_FEE.doubleValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].tableNo").value(hasItem(DEFAULT_TABLE_NO)))
            .andExpect(jsonPath("$.[*].tax").value(hasItem(DEFAULT_TAX.doubleValue())))
            .andExpect(jsonPath("$.[*].businessId").value(hasItem(DEFAULT_BUSINESS_ID)))
            .andExpect(jsonPath("$.[*].ticketId").value(hasItem(DEFAULT_TICKET_ID)))
            .andExpect(jsonPath("$.[*].localPosticketId").value(hasItem(DEFAULT_LOCAL_POSTICKET_ID)))
            .andExpect(jsonPath("$.[*].employeeName").value(hasItem(DEFAULT_EMPLOYEE_NAME)))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].subtotal").value(hasItem(DEFAULT_SUBTOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].startTime").value(hasItem(DEFAULT_START_TIME)))
            .andExpect(jsonPath("$.[*].serviceCharge").value(hasItem(DEFAULT_SERVICE_CHARGE.doubleValue())))
            .andExpect(jsonPath("$.[*].endtime").value(hasItem(DEFAULT_ENDTIME)));
    }

    @Test
    void getResPosTicket() throws Exception {
        // Initialize the database
        insertedResPosTicket = resPosTicketRepository.save(resPosTicket);

        // Get the resPosTicket
        restResPosTicketMockMvc
            .perform(get(ENTITY_API_URL_ID, resPosTicket.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(resPosTicket.getId()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.adminFee").value(DEFAULT_ADMIN_FEE.doubleValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.tableNo").value(DEFAULT_TABLE_NO))
            .andExpect(jsonPath("$.tax").value(DEFAULT_TAX.doubleValue()))
            .andExpect(jsonPath("$.businessId").value(DEFAULT_BUSINESS_ID))
            .andExpect(jsonPath("$.ticketId").value(DEFAULT_TICKET_ID))
            .andExpect(jsonPath("$.localPosticketId").value(DEFAULT_LOCAL_POSTICKET_ID))
            .andExpect(jsonPath("$.employeeName").value(DEFAULT_EMPLOYEE_NAME))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.subtotal").value(DEFAULT_SUBTOTAL.doubleValue()))
            .andExpect(jsonPath("$.startTime").value(DEFAULT_START_TIME))
            .andExpect(jsonPath("$.serviceCharge").value(DEFAULT_SERVICE_CHARGE.doubleValue()))
            .andExpect(jsonPath("$.endtime").value(DEFAULT_ENDTIME));
    }

    @Test
    void getNonExistingResPosTicket() throws Exception {
        // Get the resPosTicket
        restResPosTicketMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingResPosTicket() throws Exception {
        // Initialize the database
        insertedResPosTicket = resPosTicketRepository.save(resPosTicket);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the resPosTicket
        ResPosTicket updatedResPosTicket = resPosTicketRepository.findById(resPosTicket.getId()).orElseThrow();
        updatedResPosTicket
            .status(UPDATED_STATUS)
            .adminFee(UPDATED_ADMIN_FEE)
            .code(UPDATED_CODE)
            .tableNo(UPDATED_TABLE_NO)
            .tax(UPDATED_TAX)
            .businessId(UPDATED_BUSINESS_ID)
            .ticketId(UPDATED_TICKET_ID)
            .localPosticketId(UPDATED_LOCAL_POSTICKET_ID)
            .employeeName(UPDATED_EMPLOYEE_NAME)
            .total(UPDATED_TOTAL)
            .subtotal(UPDATED_SUBTOTAL)
            .startTime(UPDATED_START_TIME)
            .serviceCharge(UPDATED_SERVICE_CHARGE)
            .endtime(UPDATED_ENDTIME);

        restResPosTicketMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedResPosTicket.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedResPosTicket))
            )
            .andExpect(status().isOk());

        // Validate the ResPosTicket in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedResPosTicketToMatchAllProperties(updatedResPosTicket);
    }

    @Test
    void putNonExistingResPosTicket() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resPosTicket.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResPosTicketMockMvc
            .perform(
                put(ENTITY_API_URL_ID, resPosTicket.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(resPosTicket))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResPosTicket in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchResPosTicket() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resPosTicket.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResPosTicketMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(resPosTicket))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResPosTicket in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamResPosTicket() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resPosTicket.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResPosTicketMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(resPosTicket)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ResPosTicket in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateResPosTicketWithPatch() throws Exception {
        // Initialize the database
        insertedResPosTicket = resPosTicketRepository.save(resPosTicket);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the resPosTicket using partial update
        ResPosTicket partialUpdatedResPosTicket = new ResPosTicket();
        partialUpdatedResPosTicket.setId(resPosTicket.getId());

        partialUpdatedResPosTicket
            .status(UPDATED_STATUS)
            .businessId(UPDATED_BUSINESS_ID)
            .startTime(UPDATED_START_TIME)
            .serviceCharge(UPDATED_SERVICE_CHARGE);

        restResPosTicketMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResPosTicket.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedResPosTicket))
            )
            .andExpect(status().isOk());

        // Validate the ResPosTicket in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertResPosTicketUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedResPosTicket, resPosTicket),
            getPersistedResPosTicket(resPosTicket)
        );
    }

    @Test
    void fullUpdateResPosTicketWithPatch() throws Exception {
        // Initialize the database
        insertedResPosTicket = resPosTicketRepository.save(resPosTicket);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the resPosTicket using partial update
        ResPosTicket partialUpdatedResPosTicket = new ResPosTicket();
        partialUpdatedResPosTicket.setId(resPosTicket.getId());

        partialUpdatedResPosTicket
            .status(UPDATED_STATUS)
            .adminFee(UPDATED_ADMIN_FEE)
            .code(UPDATED_CODE)
            .tableNo(UPDATED_TABLE_NO)
            .tax(UPDATED_TAX)
            .businessId(UPDATED_BUSINESS_ID)
            .ticketId(UPDATED_TICKET_ID)
            .localPosticketId(UPDATED_LOCAL_POSTICKET_ID)
            .employeeName(UPDATED_EMPLOYEE_NAME)
            .total(UPDATED_TOTAL)
            .subtotal(UPDATED_SUBTOTAL)
            .startTime(UPDATED_START_TIME)
            .serviceCharge(UPDATED_SERVICE_CHARGE)
            .endtime(UPDATED_ENDTIME);

        restResPosTicketMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResPosTicket.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedResPosTicket))
            )
            .andExpect(status().isOk());

        // Validate the ResPosTicket in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertResPosTicketUpdatableFieldsEquals(partialUpdatedResPosTicket, getPersistedResPosTicket(partialUpdatedResPosTicket));
    }

    @Test
    void patchNonExistingResPosTicket() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resPosTicket.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResPosTicketMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, resPosTicket.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(resPosTicket))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResPosTicket in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchResPosTicket() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resPosTicket.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResPosTicketMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(resPosTicket))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResPosTicket in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamResPosTicket() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resPosTicket.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResPosTicketMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(resPosTicket)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ResPosTicket in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteResPosTicket() throws Exception {
        // Initialize the database
        insertedResPosTicket = resPosTicketRepository.save(resPosTicket);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the resPosTicket
        restResPosTicketMockMvc
            .perform(delete(ENTITY_API_URL_ID, resPosTicket.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return resPosTicketRepository.count();
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

    protected ResPosTicket getPersistedResPosTicket(ResPosTicket resPosTicket) {
        return resPosTicketRepository.findById(resPosTicket.getId()).orElseThrow();
    }

    protected void assertPersistedResPosTicketToMatchAllProperties(ResPosTicket expectedResPosTicket) {
        assertResPosTicketAllPropertiesEquals(expectedResPosTicket, getPersistedResPosTicket(expectedResPosTicket));
    }

    protected void assertPersistedResPosTicketToMatchUpdatableProperties(ResPosTicket expectedResPosTicket) {
        assertResPosTicketAllUpdatablePropertiesEquals(expectedResPosTicket, getPersistedResPosTicket(expectedResPosTicket));
    }
}
