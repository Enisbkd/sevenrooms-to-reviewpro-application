package com.sbm.mc.sevenroomstoreviewpro.web.rest;

import static com.sbm.mc.sevenroomstoreviewpro.domain.ClientVenueStatsAsserts.*;
import static com.sbm.mc.sevenroomstoreviewpro.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbm.mc.sevenroomstoreviewpro.IntegrationTest;
import com.sbm.mc.sevenroomstoreviewpro.domain.ClientVenueStats;
import com.sbm.mc.sevenroomstoreviewpro.repository.ClientVenueStatsRepository;
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
 * Integration tests for the {@link ClientVenueStatsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ClientVenueStatsResourceIT {

    private static final Double DEFAULT_TOTAL_SPEND_LOCALPER_COVER = 1D;
    private static final Double UPDATED_TOTAL_SPEND_LOCALPER_COVER = 2D;

    private static final String DEFAULT_LAST_VISIT_DATE = "AAAAAAAAAA";
    private static final String UPDATED_LAST_VISIT_DATE = "BBBBBBBBBB";

    private static final Integer DEFAULT_TOTAL_CANCELLATIONS = 1;
    private static final Integer UPDATED_TOTAL_CANCELLATIONS = 2;

    private static final Integer DEFAULT_TOTAL_COVERS = 1;
    private static final Integer UPDATED_TOTAL_COVERS = 2;

    private static final Integer DEFAULT_AVG_RATING = 1;
    private static final Integer UPDATED_AVG_RATING = 2;

    private static final Double DEFAULT_TOTAL_SPENDPER_COVER = 1D;
    private static final Double UPDATED_TOTAL_SPENDPER_COVER = 2D;

    private static final Double DEFAULT_TOTAL_SPEND = 1D;
    private static final Double UPDATED_TOTAL_SPEND = 2D;

    private static final Integer DEFAULT_TOTAL_NO_SHOWS = 1;
    private static final Integer UPDATED_TOTAL_NO_SHOWS = 2;

    private static final Integer DEFAULT_NUM_RATINGS = 1;
    private static final Integer UPDATED_NUM_RATINGS = 2;

    private static final Double DEFAULT_TOTAL_SPEND_PER_VISIT = 1D;
    private static final Double UPDATED_TOTAL_SPEND_PER_VISIT = 2D;

    private static final Double DEFAULT_TOTAL_SPEND_LOCAL = 1D;
    private static final Double UPDATED_TOTAL_SPEND_LOCAL = 2D;

    private static final Double DEFAULT_TOTAL_SPEND_LOCAL_PER_VISIT = 1D;
    private static final Double UPDATED_TOTAL_SPEND_LOCAL_PER_VISIT = 2D;

    private static final Integer DEFAULT_TOTAL_VISITS = 1;
    private static final Integer UPDATED_TOTAL_VISITS = 2;

    private static final Double DEFAULT_GROSS_TOTAL = 1D;
    private static final Double UPDATED_GROSS_TOTAL = 2D;

    private static final Double DEFAULT_TOTAL_ORDER_COUNT = 1D;
    private static final Double UPDATED_TOTAL_ORDER_COUNT = 2D;

    private static final Double DEFAULT_TOTAL_ORDER_CANCELLATIONS = 1D;
    private static final Double UPDATED_TOTAL_ORDER_CANCELLATIONS = 2D;

    private static final Double DEFAULT_TOTAL_ORDER_SPEND = 1D;
    private static final Double UPDATED_TOTAL_ORDER_SPEND = 2D;

    private static final Double DEFAULT_GROSS_ORDER_TOTAL = 1D;
    private static final Double UPDATED_GROSS_ORDER_TOTAL = 2D;

    private static final Double DEFAULT_TOTAL_ORDER_SPEND_LOCAL = 1D;
    private static final Double UPDATED_TOTAL_ORDER_SPEND_LOCAL = 2D;

    private static final String DEFAULT_LAST_ORDER_DATE = "AAAAAAAAAA";
    private static final String UPDATED_LAST_ORDER_DATE = "BBBBBBBBBB";

    private static final Double DEFAULT_TOTAL_SPENDPER_ORDER = 1D;
    private static final Double UPDATED_TOTAL_SPENDPER_ORDER = 2D;

    private static final Double DEFAULT_TOTAL_SPEND_LOCALPER_ORDER = 1D;
    private static final Double UPDATED_TOTAL_SPEND_LOCALPER_ORDER = 2D;

    private static final String DEFAULT_VENUE_ID = "AAAAAAAAAA";
    private static final String UPDATED_VENUE_ID = "BBBBBBBBBB";

    private static final Boolean DEFAULT_VENUE_MARKETING_OPTIN = false;
    private static final Boolean UPDATED_VENUE_MARKETING_OPTIN = true;

    private static final String DEFAULT_VENUE_MARKETING_OPTINTS = "AAAAAAAAAA";
    private static final String UPDATED_VENUE_MARKETING_OPTINTS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/client-venue-stats";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ClientVenueStatsRepository clientVenueStatsRepository;

    @Autowired
    private MockMvc restClientVenueStatsMockMvc;

    private ClientVenueStats clientVenueStats;

    private ClientVenueStats insertedClientVenueStats;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClientVenueStats createEntity() {
        ClientVenueStats clientVenueStats = new ClientVenueStats()
            .totalSpendLocalperCover(DEFAULT_TOTAL_SPEND_LOCALPER_COVER)
            .lastVisitDate(DEFAULT_LAST_VISIT_DATE)
            .totalCancellations(DEFAULT_TOTAL_CANCELLATIONS)
            .totalCovers(DEFAULT_TOTAL_COVERS)
            .avgRating(DEFAULT_AVG_RATING)
            .totalSpendperCover(DEFAULT_TOTAL_SPENDPER_COVER)
            .totalSpend(DEFAULT_TOTAL_SPEND)
            .totalNoShows(DEFAULT_TOTAL_NO_SHOWS)
            .numRatings(DEFAULT_NUM_RATINGS)
            .totalSpendPerVisit(DEFAULT_TOTAL_SPEND_PER_VISIT)
            .totalSpendLocal(DEFAULT_TOTAL_SPEND_LOCAL)
            .totalSpendLocalPerVisit(DEFAULT_TOTAL_SPEND_LOCAL_PER_VISIT)
            .totalVisits(DEFAULT_TOTAL_VISITS)
            .grossTotal(DEFAULT_GROSS_TOTAL)
            .totalOrderCount(DEFAULT_TOTAL_ORDER_COUNT)
            .totalOrderCancellations(DEFAULT_TOTAL_ORDER_CANCELLATIONS)
            .totalOrderSpend(DEFAULT_TOTAL_ORDER_SPEND)
            .grossOrderTotal(DEFAULT_GROSS_ORDER_TOTAL)
            .totalOrderSpendLocal(DEFAULT_TOTAL_ORDER_SPEND_LOCAL)
            .lastOrderDate(DEFAULT_LAST_ORDER_DATE)
            .totalSpendperOrder(DEFAULT_TOTAL_SPENDPER_ORDER)
            .totalSpendLocalperOrder(DEFAULT_TOTAL_SPEND_LOCALPER_ORDER)
            .venueId(DEFAULT_VENUE_ID)
            .venueMarketingOptin(DEFAULT_VENUE_MARKETING_OPTIN)
            .venueMarketingOptints(DEFAULT_VENUE_MARKETING_OPTINTS);
        return clientVenueStats;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClientVenueStats createUpdatedEntity() {
        ClientVenueStats clientVenueStats = new ClientVenueStats()
            .totalSpendLocalperCover(UPDATED_TOTAL_SPEND_LOCALPER_COVER)
            .lastVisitDate(UPDATED_LAST_VISIT_DATE)
            .totalCancellations(UPDATED_TOTAL_CANCELLATIONS)
            .totalCovers(UPDATED_TOTAL_COVERS)
            .avgRating(UPDATED_AVG_RATING)
            .totalSpendperCover(UPDATED_TOTAL_SPENDPER_COVER)
            .totalSpend(UPDATED_TOTAL_SPEND)
            .totalNoShows(UPDATED_TOTAL_NO_SHOWS)
            .numRatings(UPDATED_NUM_RATINGS)
            .totalSpendPerVisit(UPDATED_TOTAL_SPEND_PER_VISIT)
            .totalSpendLocal(UPDATED_TOTAL_SPEND_LOCAL)
            .totalSpendLocalPerVisit(UPDATED_TOTAL_SPEND_LOCAL_PER_VISIT)
            .totalVisits(UPDATED_TOTAL_VISITS)
            .grossTotal(UPDATED_GROSS_TOTAL)
            .totalOrderCount(UPDATED_TOTAL_ORDER_COUNT)
            .totalOrderCancellations(UPDATED_TOTAL_ORDER_CANCELLATIONS)
            .totalOrderSpend(UPDATED_TOTAL_ORDER_SPEND)
            .grossOrderTotal(UPDATED_GROSS_ORDER_TOTAL)
            .totalOrderSpendLocal(UPDATED_TOTAL_ORDER_SPEND_LOCAL)
            .lastOrderDate(UPDATED_LAST_ORDER_DATE)
            .totalSpendperOrder(UPDATED_TOTAL_SPENDPER_ORDER)
            .totalSpendLocalperOrder(UPDATED_TOTAL_SPEND_LOCALPER_ORDER)
            .venueId(UPDATED_VENUE_ID)
            .venueMarketingOptin(UPDATED_VENUE_MARKETING_OPTIN)
            .venueMarketingOptints(UPDATED_VENUE_MARKETING_OPTINTS);
        return clientVenueStats;
    }

    @BeforeEach
    public void initTest() {
        clientVenueStats = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedClientVenueStats != null) {
            clientVenueStatsRepository.delete(insertedClientVenueStats);
            insertedClientVenueStats = null;
        }
    }

    @Test
    void createClientVenueStats() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ClientVenueStats
        var returnedClientVenueStats = om.readValue(
            restClientVenueStatsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(clientVenueStats)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ClientVenueStats.class
        );

        // Validate the ClientVenueStats in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertClientVenueStatsUpdatableFieldsEquals(returnedClientVenueStats, getPersistedClientVenueStats(returnedClientVenueStats));

        insertedClientVenueStats = returnedClientVenueStats;
    }

    @Test
    void createClientVenueStatsWithExistingId() throws Exception {
        // Create the ClientVenueStats with an existing ID
        clientVenueStats.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restClientVenueStatsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(clientVenueStats)))
            .andExpect(status().isBadRequest());

        // Validate the ClientVenueStats in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void getAllClientVenueStats() throws Exception {
        // Initialize the database
        insertedClientVenueStats = clientVenueStatsRepository.save(clientVenueStats);

        // Get all the clientVenueStatsList
        restClientVenueStatsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(clientVenueStats.getId())))
            .andExpect(jsonPath("$.[*].totalSpendLocalperCover").value(hasItem(DEFAULT_TOTAL_SPEND_LOCALPER_COVER.doubleValue())))
            .andExpect(jsonPath("$.[*].lastVisitDate").value(hasItem(DEFAULT_LAST_VISIT_DATE)))
            .andExpect(jsonPath("$.[*].totalCancellations").value(hasItem(DEFAULT_TOTAL_CANCELLATIONS)))
            .andExpect(jsonPath("$.[*].totalCovers").value(hasItem(DEFAULT_TOTAL_COVERS)))
            .andExpect(jsonPath("$.[*].avgRating").value(hasItem(DEFAULT_AVG_RATING)))
            .andExpect(jsonPath("$.[*].totalSpendperCover").value(hasItem(DEFAULT_TOTAL_SPENDPER_COVER.doubleValue())))
            .andExpect(jsonPath("$.[*].totalSpend").value(hasItem(DEFAULT_TOTAL_SPEND.doubleValue())))
            .andExpect(jsonPath("$.[*].totalNoShows").value(hasItem(DEFAULT_TOTAL_NO_SHOWS)))
            .andExpect(jsonPath("$.[*].numRatings").value(hasItem(DEFAULT_NUM_RATINGS)))
            .andExpect(jsonPath("$.[*].totalSpendPerVisit").value(hasItem(DEFAULT_TOTAL_SPEND_PER_VISIT.doubleValue())))
            .andExpect(jsonPath("$.[*].totalSpendLocal").value(hasItem(DEFAULT_TOTAL_SPEND_LOCAL.doubleValue())))
            .andExpect(jsonPath("$.[*].totalSpendLocalPerVisit").value(hasItem(DEFAULT_TOTAL_SPEND_LOCAL_PER_VISIT.doubleValue())))
            .andExpect(jsonPath("$.[*].totalVisits").value(hasItem(DEFAULT_TOTAL_VISITS)))
            .andExpect(jsonPath("$.[*].grossTotal").value(hasItem(DEFAULT_GROSS_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].totalOrderCount").value(hasItem(DEFAULT_TOTAL_ORDER_COUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].totalOrderCancellations").value(hasItem(DEFAULT_TOTAL_ORDER_CANCELLATIONS.doubleValue())))
            .andExpect(jsonPath("$.[*].totalOrderSpend").value(hasItem(DEFAULT_TOTAL_ORDER_SPEND.doubleValue())))
            .andExpect(jsonPath("$.[*].grossOrderTotal").value(hasItem(DEFAULT_GROSS_ORDER_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].totalOrderSpendLocal").value(hasItem(DEFAULT_TOTAL_ORDER_SPEND_LOCAL.doubleValue())))
            .andExpect(jsonPath("$.[*].lastOrderDate").value(hasItem(DEFAULT_LAST_ORDER_DATE)))
            .andExpect(jsonPath("$.[*].totalSpendperOrder").value(hasItem(DEFAULT_TOTAL_SPENDPER_ORDER.doubleValue())))
            .andExpect(jsonPath("$.[*].totalSpendLocalperOrder").value(hasItem(DEFAULT_TOTAL_SPEND_LOCALPER_ORDER.doubleValue())))
            .andExpect(jsonPath("$.[*].venueId").value(hasItem(DEFAULT_VENUE_ID)))
            .andExpect(jsonPath("$.[*].venueMarketingOptin").value(hasItem(DEFAULT_VENUE_MARKETING_OPTIN.booleanValue())))
            .andExpect(jsonPath("$.[*].venueMarketingOptints").value(hasItem(DEFAULT_VENUE_MARKETING_OPTINTS)));
    }

    @Test
    void getClientVenueStats() throws Exception {
        // Initialize the database
        insertedClientVenueStats = clientVenueStatsRepository.save(clientVenueStats);

        // Get the clientVenueStats
        restClientVenueStatsMockMvc
            .perform(get(ENTITY_API_URL_ID, clientVenueStats.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(clientVenueStats.getId()))
            .andExpect(jsonPath("$.totalSpendLocalperCover").value(DEFAULT_TOTAL_SPEND_LOCALPER_COVER.doubleValue()))
            .andExpect(jsonPath("$.lastVisitDate").value(DEFAULT_LAST_VISIT_DATE))
            .andExpect(jsonPath("$.totalCancellations").value(DEFAULT_TOTAL_CANCELLATIONS))
            .andExpect(jsonPath("$.totalCovers").value(DEFAULT_TOTAL_COVERS))
            .andExpect(jsonPath("$.avgRating").value(DEFAULT_AVG_RATING))
            .andExpect(jsonPath("$.totalSpendperCover").value(DEFAULT_TOTAL_SPENDPER_COVER.doubleValue()))
            .andExpect(jsonPath("$.totalSpend").value(DEFAULT_TOTAL_SPEND.doubleValue()))
            .andExpect(jsonPath("$.totalNoShows").value(DEFAULT_TOTAL_NO_SHOWS))
            .andExpect(jsonPath("$.numRatings").value(DEFAULT_NUM_RATINGS))
            .andExpect(jsonPath("$.totalSpendPerVisit").value(DEFAULT_TOTAL_SPEND_PER_VISIT.doubleValue()))
            .andExpect(jsonPath("$.totalSpendLocal").value(DEFAULT_TOTAL_SPEND_LOCAL.doubleValue()))
            .andExpect(jsonPath("$.totalSpendLocalPerVisit").value(DEFAULT_TOTAL_SPEND_LOCAL_PER_VISIT.doubleValue()))
            .andExpect(jsonPath("$.totalVisits").value(DEFAULT_TOTAL_VISITS))
            .andExpect(jsonPath("$.grossTotal").value(DEFAULT_GROSS_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.totalOrderCount").value(DEFAULT_TOTAL_ORDER_COUNT.doubleValue()))
            .andExpect(jsonPath("$.totalOrderCancellations").value(DEFAULT_TOTAL_ORDER_CANCELLATIONS.doubleValue()))
            .andExpect(jsonPath("$.totalOrderSpend").value(DEFAULT_TOTAL_ORDER_SPEND.doubleValue()))
            .andExpect(jsonPath("$.grossOrderTotal").value(DEFAULT_GROSS_ORDER_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.totalOrderSpendLocal").value(DEFAULT_TOTAL_ORDER_SPEND_LOCAL.doubleValue()))
            .andExpect(jsonPath("$.lastOrderDate").value(DEFAULT_LAST_ORDER_DATE))
            .andExpect(jsonPath("$.totalSpendperOrder").value(DEFAULT_TOTAL_SPENDPER_ORDER.doubleValue()))
            .andExpect(jsonPath("$.totalSpendLocalperOrder").value(DEFAULT_TOTAL_SPEND_LOCALPER_ORDER.doubleValue()))
            .andExpect(jsonPath("$.venueId").value(DEFAULT_VENUE_ID))
            .andExpect(jsonPath("$.venueMarketingOptin").value(DEFAULT_VENUE_MARKETING_OPTIN.booleanValue()))
            .andExpect(jsonPath("$.venueMarketingOptints").value(DEFAULT_VENUE_MARKETING_OPTINTS));
    }

    @Test
    void getNonExistingClientVenueStats() throws Exception {
        // Get the clientVenueStats
        restClientVenueStatsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingClientVenueStats() throws Exception {
        // Initialize the database
        insertedClientVenueStats = clientVenueStatsRepository.save(clientVenueStats);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the clientVenueStats
        ClientVenueStats updatedClientVenueStats = clientVenueStatsRepository.findById(clientVenueStats.getId()).orElseThrow();
        updatedClientVenueStats
            .totalSpendLocalperCover(UPDATED_TOTAL_SPEND_LOCALPER_COVER)
            .lastVisitDate(UPDATED_LAST_VISIT_DATE)
            .totalCancellations(UPDATED_TOTAL_CANCELLATIONS)
            .totalCovers(UPDATED_TOTAL_COVERS)
            .avgRating(UPDATED_AVG_RATING)
            .totalSpendperCover(UPDATED_TOTAL_SPENDPER_COVER)
            .totalSpend(UPDATED_TOTAL_SPEND)
            .totalNoShows(UPDATED_TOTAL_NO_SHOWS)
            .numRatings(UPDATED_NUM_RATINGS)
            .totalSpendPerVisit(UPDATED_TOTAL_SPEND_PER_VISIT)
            .totalSpendLocal(UPDATED_TOTAL_SPEND_LOCAL)
            .totalSpendLocalPerVisit(UPDATED_TOTAL_SPEND_LOCAL_PER_VISIT)
            .totalVisits(UPDATED_TOTAL_VISITS)
            .grossTotal(UPDATED_GROSS_TOTAL)
            .totalOrderCount(UPDATED_TOTAL_ORDER_COUNT)
            .totalOrderCancellations(UPDATED_TOTAL_ORDER_CANCELLATIONS)
            .totalOrderSpend(UPDATED_TOTAL_ORDER_SPEND)
            .grossOrderTotal(UPDATED_GROSS_ORDER_TOTAL)
            .totalOrderSpendLocal(UPDATED_TOTAL_ORDER_SPEND_LOCAL)
            .lastOrderDate(UPDATED_LAST_ORDER_DATE)
            .totalSpendperOrder(UPDATED_TOTAL_SPENDPER_ORDER)
            .totalSpendLocalperOrder(UPDATED_TOTAL_SPEND_LOCALPER_ORDER)
            .venueId(UPDATED_VENUE_ID)
            .venueMarketingOptin(UPDATED_VENUE_MARKETING_OPTIN)
            .venueMarketingOptints(UPDATED_VENUE_MARKETING_OPTINTS);

        restClientVenueStatsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedClientVenueStats.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedClientVenueStats))
            )
            .andExpect(status().isOk());

        // Validate the ClientVenueStats in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedClientVenueStatsToMatchAllProperties(updatedClientVenueStats);
    }

    @Test
    void putNonExistingClientVenueStats() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        clientVenueStats.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClientVenueStatsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, clientVenueStats.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(clientVenueStats))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientVenueStats in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchClientVenueStats() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        clientVenueStats.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientVenueStatsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(clientVenueStats))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientVenueStats in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamClientVenueStats() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        clientVenueStats.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientVenueStatsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(clientVenueStats)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClientVenueStats in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateClientVenueStatsWithPatch() throws Exception {
        // Initialize the database
        insertedClientVenueStats = clientVenueStatsRepository.save(clientVenueStats);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the clientVenueStats using partial update
        ClientVenueStats partialUpdatedClientVenueStats = new ClientVenueStats();
        partialUpdatedClientVenueStats.setId(clientVenueStats.getId());

        partialUpdatedClientVenueStats
            .lastVisitDate(UPDATED_LAST_VISIT_DATE)
            .totalCovers(UPDATED_TOTAL_COVERS)
            .avgRating(UPDATED_AVG_RATING)
            .totalNoShows(UPDATED_TOTAL_NO_SHOWS)
            .numRatings(UPDATED_NUM_RATINGS)
            .totalSpendPerVisit(UPDATED_TOTAL_SPEND_PER_VISIT)
            .totalSpendLocalPerVisit(UPDATED_TOTAL_SPEND_LOCAL_PER_VISIT)
            .grossTotal(UPDATED_GROSS_TOTAL)
            .totalOrderCancellations(UPDATED_TOTAL_ORDER_CANCELLATIONS)
            .grossOrderTotal(UPDATED_GROSS_ORDER_TOTAL)
            .totalOrderSpendLocal(UPDATED_TOTAL_ORDER_SPEND_LOCAL)
            .lastOrderDate(UPDATED_LAST_ORDER_DATE)
            .totalSpendperOrder(UPDATED_TOTAL_SPENDPER_ORDER)
            .venueId(UPDATED_VENUE_ID)
            .venueMarketingOptin(UPDATED_VENUE_MARKETING_OPTIN);

        restClientVenueStatsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClientVenueStats.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedClientVenueStats))
            )
            .andExpect(status().isOk());

        // Validate the ClientVenueStats in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertClientVenueStatsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedClientVenueStats, clientVenueStats),
            getPersistedClientVenueStats(clientVenueStats)
        );
    }

    @Test
    void fullUpdateClientVenueStatsWithPatch() throws Exception {
        // Initialize the database
        insertedClientVenueStats = clientVenueStatsRepository.save(clientVenueStats);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the clientVenueStats using partial update
        ClientVenueStats partialUpdatedClientVenueStats = new ClientVenueStats();
        partialUpdatedClientVenueStats.setId(clientVenueStats.getId());

        partialUpdatedClientVenueStats
            .totalSpendLocalperCover(UPDATED_TOTAL_SPEND_LOCALPER_COVER)
            .lastVisitDate(UPDATED_LAST_VISIT_DATE)
            .totalCancellations(UPDATED_TOTAL_CANCELLATIONS)
            .totalCovers(UPDATED_TOTAL_COVERS)
            .avgRating(UPDATED_AVG_RATING)
            .totalSpendperCover(UPDATED_TOTAL_SPENDPER_COVER)
            .totalSpend(UPDATED_TOTAL_SPEND)
            .totalNoShows(UPDATED_TOTAL_NO_SHOWS)
            .numRatings(UPDATED_NUM_RATINGS)
            .totalSpendPerVisit(UPDATED_TOTAL_SPEND_PER_VISIT)
            .totalSpendLocal(UPDATED_TOTAL_SPEND_LOCAL)
            .totalSpendLocalPerVisit(UPDATED_TOTAL_SPEND_LOCAL_PER_VISIT)
            .totalVisits(UPDATED_TOTAL_VISITS)
            .grossTotal(UPDATED_GROSS_TOTAL)
            .totalOrderCount(UPDATED_TOTAL_ORDER_COUNT)
            .totalOrderCancellations(UPDATED_TOTAL_ORDER_CANCELLATIONS)
            .totalOrderSpend(UPDATED_TOTAL_ORDER_SPEND)
            .grossOrderTotal(UPDATED_GROSS_ORDER_TOTAL)
            .totalOrderSpendLocal(UPDATED_TOTAL_ORDER_SPEND_LOCAL)
            .lastOrderDate(UPDATED_LAST_ORDER_DATE)
            .totalSpendperOrder(UPDATED_TOTAL_SPENDPER_ORDER)
            .totalSpendLocalperOrder(UPDATED_TOTAL_SPEND_LOCALPER_ORDER)
            .venueId(UPDATED_VENUE_ID)
            .venueMarketingOptin(UPDATED_VENUE_MARKETING_OPTIN)
            .venueMarketingOptints(UPDATED_VENUE_MARKETING_OPTINTS);

        restClientVenueStatsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClientVenueStats.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedClientVenueStats))
            )
            .andExpect(status().isOk());

        // Validate the ClientVenueStats in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertClientVenueStatsUpdatableFieldsEquals(
            partialUpdatedClientVenueStats,
            getPersistedClientVenueStats(partialUpdatedClientVenueStats)
        );
    }

    @Test
    void patchNonExistingClientVenueStats() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        clientVenueStats.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClientVenueStatsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, clientVenueStats.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(clientVenueStats))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientVenueStats in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchClientVenueStats() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        clientVenueStats.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientVenueStatsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(clientVenueStats))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientVenueStats in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamClientVenueStats() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        clientVenueStats.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientVenueStatsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(clientVenueStats)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClientVenueStats in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteClientVenueStats() throws Exception {
        // Initialize the database
        insertedClientVenueStats = clientVenueStatsRepository.save(clientVenueStats);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the clientVenueStats
        restClientVenueStatsMockMvc
            .perform(delete(ENTITY_API_URL_ID, clientVenueStats.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return clientVenueStatsRepository.count();
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

    protected ClientVenueStats getPersistedClientVenueStats(ClientVenueStats clientVenueStats) {
        return clientVenueStatsRepository.findById(clientVenueStats.getId()).orElseThrow();
    }

    protected void assertPersistedClientVenueStatsToMatchAllProperties(ClientVenueStats expectedClientVenueStats) {
        assertClientVenueStatsAllPropertiesEquals(expectedClientVenueStats, getPersistedClientVenueStats(expectedClientVenueStats));
    }

    protected void assertPersistedClientVenueStatsToMatchUpdatableProperties(ClientVenueStats expectedClientVenueStats) {
        assertClientVenueStatsAllUpdatablePropertiesEquals(
            expectedClientVenueStats,
            getPersistedClientVenueStats(expectedClientVenueStats)
        );
    }
}
