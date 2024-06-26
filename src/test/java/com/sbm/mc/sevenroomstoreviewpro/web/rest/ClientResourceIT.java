package com.sbm.mc.sevenroomstoreviewpro.web.rest;

import static com.sbm.mc.sevenroomstoreviewpro.domain.ClientAsserts.*;
import static com.sbm.mc.sevenroomstoreviewpro.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbm.mc.sevenroomstoreviewpro.IntegrationTest;
import com.sbm.mc.sevenroomstoreviewpro.domain.Client;
import com.sbm.mc.sevenroomstoreviewpro.repository.ClientRepository;
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
 * Integration tests for the {@link ClientResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ClientResourceIT {

    private static final String DEFAULT_CLIENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_DATE = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_DATE = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_DELETED_DATE = "AAAAAAAAAA";
    private static final String UPDATED_DELETED_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_LASTNAME = "AAAAAAAAAA";
    private static final String UPDATED_LASTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_FIRSTNAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRSTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_SALUTATION = "AAAAAAAAAA";
    private static final String UPDATED_SALUTATION = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final Integer DEFAULT_BIRTHDAY_DAY = 1;
    private static final Integer UPDATED_BIRTHDAY_DAY = 2;

    private static final Integer DEFAULT_BIRTHDAY_MONTH = 1;
    private static final Integer UPDATED_BIRTHDAY_MONTH = 2;

    private static final Integer DEFAULT_BIRTHDAY_ALT_MONTH = 1;
    private static final Integer UPDATED_BIRTHDAY_ALT_MONTH = 2;

    private static final Integer DEFAULT_ANNIVERSARY_DAY = 1;
    private static final Integer UPDATED_ANNIVERSARY_DAY = 2;

    private static final Integer DEFAULT_ANNIVERSARY_MONTH = 1;
    private static final Integer UPDATED_ANNIVERSARY_MONTH = 2;

    private static final String DEFAULT_COMPANY = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_ALT = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_ALT = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBERLOCALE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBERLOCALE = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBERALT = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBERALT = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBERALTLOCALE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBERALTLOCALE = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_POSTAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_POSTAL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_CONTACT_PRIVATE = false;
    private static final Boolean UPDATED_IS_CONTACT_PRIVATE = true;

    private static final Boolean DEFAULT_IS_ONETIME_GUEST = false;
    private static final Boolean UPDATED_IS_ONETIME_GUEST = true;

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_LOYALTY_ID = "AAAAAAAAAA";
    private static final String UPDATED_LOYALTY_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_LOYALTY_RANK = 1;
    private static final Integer UPDATED_LOYALTY_RANK = 2;

    private static final String DEFAULT_LOYALTY_TIER = "AAAAAAAAAA";
    private static final String UPDATED_LOYALTY_TIER = "BBBBBBBBBB";

    private static final Boolean DEFAULT_MARKETING_OPTIN = false;
    private static final Boolean UPDATED_MARKETING_OPTIN = true;

    private static final String DEFAULT_MARKETING_OPTINTS = "AAAAAAAAAA";
    private static final String UPDATED_MARKETING_OPTINTS = "BBBBBBBBBB";

    private static final String DEFAULT_MARKETING_OPT_OUTTS = "AAAAAAAAAA";
    private static final String UPDATED_MARKETING_OPT_OUTTS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_HAS_BILLING_PROFILE = false;
    private static final Boolean UPDATED_HAS_BILLING_PROFILE = true;

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    private static final String DEFAULT_PRIVATE_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_PRIVATE_NOTES = "BBBBBBBBBB";

    private static final String DEFAULT_TAGS = "AAAAAAAAAA";
    private static final String UPDATED_TAGS = "BBBBBBBBBB";

    private static final Double DEFAULT_TOTAL_VISITS = 1D;
    private static final Double UPDATED_TOTAL_VISITS = 2D;

    private static final Double DEFAULT_TOTAL_COVERS = 1D;
    private static final Double UPDATED_TOTAL_COVERS = 2D;

    private static final Double DEFAULT_TOTAL_CANCELLATIONS = 1D;
    private static final Double UPDATED_TOTAL_CANCELLATIONS = 2D;

    private static final Double DEFAULT_TOTAL_NO_SHOWS = 1D;
    private static final Double UPDATED_TOTAL_NO_SHOWS = 2D;

    private static final Double DEFAULT_TOTAL_SPEND = 1D;
    private static final Double UPDATED_TOTAL_SPEND = 2D;

    private static final Double DEFAULT_TOTAL_SPEND_PER_COVER = 1D;
    private static final Double UPDATED_TOTAL_SPEND_PER_COVER = 2D;

    private static final Double DEFAULT_TOTALSPEND_PER_VISIT = 1D;
    private static final Double UPDATED_TOTALSPEND_PER_VISIT = 2D;

    private static final Double DEFAULT_AVG_RATING = 1D;
    private static final Double UPDATED_AVG_RATING = 2D;

    private static final String DEFAULT_REFERENCE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_EXTERNAL_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_EXTERNAL_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_VENUE_GROUP_ID = "AAAAAAAAAA";
    private static final String UPDATED_VENUE_GROUP_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_BIRTHDAY_ALT_DAY = 1;
    private static final Integer UPDATED_BIRTHDAY_ALT_DAY = 2;

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_TOTAL_ORDER_COUNT = 1;
    private static final Integer UPDATED_TOTAL_ORDER_COUNT = 2;

    private static final String DEFAULT_PREFERRED_LANGUAGE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PREFERRED_LANGUAGE_CODE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/clients";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MockMvc restClientMockMvc;

    private Client client;

    private Client insertedClient;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Client createEntity() {
        Client client = new Client()
            .clientId(DEFAULT_CLIENT_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .updatedDate(DEFAULT_UPDATED_DATE)
            .deletedDate(DEFAULT_DELETED_DATE)
            .lastname(DEFAULT_LASTNAME)
            .firstname(DEFAULT_FIRSTNAME)
            .gender(DEFAULT_GENDER)
            .salutation(DEFAULT_SALUTATION)
            .title(DEFAULT_TITLE)
            .birthdayDay(DEFAULT_BIRTHDAY_DAY)
            .birthdayMonth(DEFAULT_BIRTHDAY_MONTH)
            .birthdayAltMonth(DEFAULT_BIRTHDAY_ALT_MONTH)
            .anniversaryDay(DEFAULT_ANNIVERSARY_DAY)
            .anniversaryMonth(DEFAULT_ANNIVERSARY_MONTH)
            .company(DEFAULT_COMPANY)
            .email(DEFAULT_EMAIL)
            .emailAlt(DEFAULT_EMAIL_ALT)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .phoneNumberlocale(DEFAULT_PHONE_NUMBERLOCALE)
            .phoneNumberalt(DEFAULT_PHONE_NUMBERALT)
            .phoneNumberaltlocale(DEFAULT_PHONE_NUMBERALTLOCALE)
            .address(DEFAULT_ADDRESS)
            .address2(DEFAULT_ADDRESS_2)
            .city(DEFAULT_CITY)
            .postalCode(DEFAULT_POSTAL_CODE)
            .state(DEFAULT_STATE)
            .country(DEFAULT_COUNTRY)
            .isContactPrivate(DEFAULT_IS_CONTACT_PRIVATE)
            .isOnetimeGuest(DEFAULT_IS_ONETIME_GUEST)
            .status(DEFAULT_STATUS)
            .loyaltyId(DEFAULT_LOYALTY_ID)
            .loyaltyRank(DEFAULT_LOYALTY_RANK)
            .loyaltyTier(DEFAULT_LOYALTY_TIER)
            .marketingOptin(DEFAULT_MARKETING_OPTIN)
            .marketingOptints(DEFAULT_MARKETING_OPTINTS)
            .marketingOptOutts(DEFAULT_MARKETING_OPT_OUTTS)
            .hasBillingProfile(DEFAULT_HAS_BILLING_PROFILE)
            .notes(DEFAULT_NOTES)
            .privateNotes(DEFAULT_PRIVATE_NOTES)
            .tags(DEFAULT_TAGS)
            .totalVisits(DEFAULT_TOTAL_VISITS)
            .totalCovers(DEFAULT_TOTAL_COVERS)
            .totalCancellations(DEFAULT_TOTAL_CANCELLATIONS)
            .totalNoShows(DEFAULT_TOTAL_NO_SHOWS)
            .totalSpend(DEFAULT_TOTAL_SPEND)
            .totalSpendPerCover(DEFAULT_TOTAL_SPEND_PER_COVER)
            .totalspendPerVisit(DEFAULT_TOTALSPEND_PER_VISIT)
            .avgRating(DEFAULT_AVG_RATING)
            .referenceCode(DEFAULT_REFERENCE_CODE)
            .externalUserId(DEFAULT_EXTERNAL_USER_ID)
            .venueGroupId(DEFAULT_VENUE_GROUP_ID)
            .birthdayAltDay(DEFAULT_BIRTHDAY_ALT_DAY)
            .userId(DEFAULT_USER_ID)
            .userName(DEFAULT_USER_NAME)
            .totalOrderCount(DEFAULT_TOTAL_ORDER_COUNT)
            .preferredLanguageCode(DEFAULT_PREFERRED_LANGUAGE_CODE);
        return client;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Client createUpdatedEntity() {
        Client client = new Client()
            .clientId(UPDATED_CLIENT_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .deletedDate(UPDATED_DELETED_DATE)
            .lastname(UPDATED_LASTNAME)
            .firstname(UPDATED_FIRSTNAME)
            .gender(UPDATED_GENDER)
            .salutation(UPDATED_SALUTATION)
            .title(UPDATED_TITLE)
            .birthdayDay(UPDATED_BIRTHDAY_DAY)
            .birthdayMonth(UPDATED_BIRTHDAY_MONTH)
            .birthdayAltMonth(UPDATED_BIRTHDAY_ALT_MONTH)
            .anniversaryDay(UPDATED_ANNIVERSARY_DAY)
            .anniversaryMonth(UPDATED_ANNIVERSARY_MONTH)
            .company(UPDATED_COMPANY)
            .email(UPDATED_EMAIL)
            .emailAlt(UPDATED_EMAIL_ALT)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .phoneNumberlocale(UPDATED_PHONE_NUMBERLOCALE)
            .phoneNumberalt(UPDATED_PHONE_NUMBERALT)
            .phoneNumberaltlocale(UPDATED_PHONE_NUMBERALTLOCALE)
            .address(UPDATED_ADDRESS)
            .address2(UPDATED_ADDRESS_2)
            .city(UPDATED_CITY)
            .postalCode(UPDATED_POSTAL_CODE)
            .state(UPDATED_STATE)
            .country(UPDATED_COUNTRY)
            .isContactPrivate(UPDATED_IS_CONTACT_PRIVATE)
            .isOnetimeGuest(UPDATED_IS_ONETIME_GUEST)
            .status(UPDATED_STATUS)
            .loyaltyId(UPDATED_LOYALTY_ID)
            .loyaltyRank(UPDATED_LOYALTY_RANK)
            .loyaltyTier(UPDATED_LOYALTY_TIER)
            .marketingOptin(UPDATED_MARKETING_OPTIN)
            .marketingOptints(UPDATED_MARKETING_OPTINTS)
            .marketingOptOutts(UPDATED_MARKETING_OPT_OUTTS)
            .hasBillingProfile(UPDATED_HAS_BILLING_PROFILE)
            .notes(UPDATED_NOTES)
            .privateNotes(UPDATED_PRIVATE_NOTES)
            .tags(UPDATED_TAGS)
            .totalVisits(UPDATED_TOTAL_VISITS)
            .totalCovers(UPDATED_TOTAL_COVERS)
            .totalCancellations(UPDATED_TOTAL_CANCELLATIONS)
            .totalNoShows(UPDATED_TOTAL_NO_SHOWS)
            .totalSpend(UPDATED_TOTAL_SPEND)
            .totalSpendPerCover(UPDATED_TOTAL_SPEND_PER_COVER)
            .totalspendPerVisit(UPDATED_TOTALSPEND_PER_VISIT)
            .avgRating(UPDATED_AVG_RATING)
            .referenceCode(UPDATED_REFERENCE_CODE)
            .externalUserId(UPDATED_EXTERNAL_USER_ID)
            .venueGroupId(UPDATED_VENUE_GROUP_ID)
            .birthdayAltDay(UPDATED_BIRTHDAY_ALT_DAY)
            .userId(UPDATED_USER_ID)
            .userName(UPDATED_USER_NAME)
            .totalOrderCount(UPDATED_TOTAL_ORDER_COUNT)
            .preferredLanguageCode(UPDATED_PREFERRED_LANGUAGE_CODE);
        return client;
    }

    @BeforeEach
    public void initTest() {
        client = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedClient != null) {
            clientRepository.delete(insertedClient);
            insertedClient = null;
        }
    }

    @Test
    void createClient() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Client
        var returnedClient = om.readValue(
            restClientMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(client)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Client.class
        );

        // Validate the Client in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertClientUpdatableFieldsEquals(returnedClient, getPersistedClient(returnedClient));

        insertedClient = returnedClient;
    }

    @Test
    void createClientWithExistingId() throws Exception {
        // Create the Client with an existing ID
        client.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restClientMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(client)))
            .andExpect(status().isBadRequest());

        // Validate the Client in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void getAllClients() throws Exception {
        // Initialize the database
        insertedClient = clientRepository.save(client);

        // Get all the clientList
        restClientMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(client.getId())))
            .andExpect(jsonPath("$.[*].clientId").value(hasItem(DEFAULT_CLIENT_ID)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE)))
            .andExpect(jsonPath("$.[*].deletedDate").value(hasItem(DEFAULT_DELETED_DATE)))
            .andExpect(jsonPath("$.[*].lastname").value(hasItem(DEFAULT_LASTNAME)))
            .andExpect(jsonPath("$.[*].firstname").value(hasItem(DEFAULT_FIRSTNAME)))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER)))
            .andExpect(jsonPath("$.[*].salutation").value(hasItem(DEFAULT_SALUTATION)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].birthdayDay").value(hasItem(DEFAULT_BIRTHDAY_DAY)))
            .andExpect(jsonPath("$.[*].birthdayMonth").value(hasItem(DEFAULT_BIRTHDAY_MONTH)))
            .andExpect(jsonPath("$.[*].birthdayAltMonth").value(hasItem(DEFAULT_BIRTHDAY_ALT_MONTH)))
            .andExpect(jsonPath("$.[*].anniversaryDay").value(hasItem(DEFAULT_ANNIVERSARY_DAY)))
            .andExpect(jsonPath("$.[*].anniversaryMonth").value(hasItem(DEFAULT_ANNIVERSARY_MONTH)))
            .andExpect(jsonPath("$.[*].company").value(hasItem(DEFAULT_COMPANY)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].emailAlt").value(hasItem(DEFAULT_EMAIL_ALT)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].phoneNumberlocale").value(hasItem(DEFAULT_PHONE_NUMBERLOCALE)))
            .andExpect(jsonPath("$.[*].phoneNumberalt").value(hasItem(DEFAULT_PHONE_NUMBERALT)))
            .andExpect(jsonPath("$.[*].phoneNumberaltlocale").value(hasItem(DEFAULT_PHONE_NUMBERALTLOCALE)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].address2").value(hasItem(DEFAULT_ADDRESS_2)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].postalCode").value(hasItem(DEFAULT_POSTAL_CODE)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].isContactPrivate").value(hasItem(DEFAULT_IS_CONTACT_PRIVATE.booleanValue())))
            .andExpect(jsonPath("$.[*].isOnetimeGuest").value(hasItem(DEFAULT_IS_ONETIME_GUEST.booleanValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].loyaltyId").value(hasItem(DEFAULT_LOYALTY_ID)))
            .andExpect(jsonPath("$.[*].loyaltyRank").value(hasItem(DEFAULT_LOYALTY_RANK)))
            .andExpect(jsonPath("$.[*].loyaltyTier").value(hasItem(DEFAULT_LOYALTY_TIER)))
            .andExpect(jsonPath("$.[*].marketingOptin").value(hasItem(DEFAULT_MARKETING_OPTIN.booleanValue())))
            .andExpect(jsonPath("$.[*].marketingOptints").value(hasItem(DEFAULT_MARKETING_OPTINTS)))
            .andExpect(jsonPath("$.[*].marketingOptOutts").value(hasItem(DEFAULT_MARKETING_OPT_OUTTS)))
            .andExpect(jsonPath("$.[*].hasBillingProfile").value(hasItem(DEFAULT_HAS_BILLING_PROFILE.booleanValue())))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES)))
            .andExpect(jsonPath("$.[*].privateNotes").value(hasItem(DEFAULT_PRIVATE_NOTES)))
            .andExpect(jsonPath("$.[*].tags").value(hasItem(DEFAULT_TAGS)))
            .andExpect(jsonPath("$.[*].totalVisits").value(hasItem(DEFAULT_TOTAL_VISITS.doubleValue())))
            .andExpect(jsonPath("$.[*].totalCovers").value(hasItem(DEFAULT_TOTAL_COVERS.doubleValue())))
            .andExpect(jsonPath("$.[*].totalCancellations").value(hasItem(DEFAULT_TOTAL_CANCELLATIONS.doubleValue())))
            .andExpect(jsonPath("$.[*].totalNoShows").value(hasItem(DEFAULT_TOTAL_NO_SHOWS.doubleValue())))
            .andExpect(jsonPath("$.[*].totalSpend").value(hasItem(DEFAULT_TOTAL_SPEND.doubleValue())))
            .andExpect(jsonPath("$.[*].totalSpendPerCover").value(hasItem(DEFAULT_TOTAL_SPEND_PER_COVER.doubleValue())))
            .andExpect(jsonPath("$.[*].totalspendPerVisit").value(hasItem(DEFAULT_TOTALSPEND_PER_VISIT.doubleValue())))
            .andExpect(jsonPath("$.[*].avgRating").value(hasItem(DEFAULT_AVG_RATING.doubleValue())))
            .andExpect(jsonPath("$.[*].referenceCode").value(hasItem(DEFAULT_REFERENCE_CODE)))
            .andExpect(jsonPath("$.[*].externalUserId").value(hasItem(DEFAULT_EXTERNAL_USER_ID)))
            .andExpect(jsonPath("$.[*].venueGroupId").value(hasItem(DEFAULT_VENUE_GROUP_ID)))
            .andExpect(jsonPath("$.[*].birthdayAltDay").value(hasItem(DEFAULT_BIRTHDAY_ALT_DAY)))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME)))
            .andExpect(jsonPath("$.[*].totalOrderCount").value(hasItem(DEFAULT_TOTAL_ORDER_COUNT)))
            .andExpect(jsonPath("$.[*].preferredLanguageCode").value(hasItem(DEFAULT_PREFERRED_LANGUAGE_CODE)));
    }

    @Test
    void getClient() throws Exception {
        // Initialize the database
        insertedClient = clientRepository.save(client);

        // Get the client
        restClientMockMvc
            .perform(get(ENTITY_API_URL_ID, client.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(client.getId()))
            .andExpect(jsonPath("$.clientId").value(DEFAULT_CLIENT_ID))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE))
            .andExpect(jsonPath("$.deletedDate").value(DEFAULT_DELETED_DATE))
            .andExpect(jsonPath("$.lastname").value(DEFAULT_LASTNAME))
            .andExpect(jsonPath("$.firstname").value(DEFAULT_FIRSTNAME))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER))
            .andExpect(jsonPath("$.salutation").value(DEFAULT_SALUTATION))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.birthdayDay").value(DEFAULT_BIRTHDAY_DAY))
            .andExpect(jsonPath("$.birthdayMonth").value(DEFAULT_BIRTHDAY_MONTH))
            .andExpect(jsonPath("$.birthdayAltMonth").value(DEFAULT_BIRTHDAY_ALT_MONTH))
            .andExpect(jsonPath("$.anniversaryDay").value(DEFAULT_ANNIVERSARY_DAY))
            .andExpect(jsonPath("$.anniversaryMonth").value(DEFAULT_ANNIVERSARY_MONTH))
            .andExpect(jsonPath("$.company").value(DEFAULT_COMPANY))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.emailAlt").value(DEFAULT_EMAIL_ALT))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.phoneNumberlocale").value(DEFAULT_PHONE_NUMBERLOCALE))
            .andExpect(jsonPath("$.phoneNumberalt").value(DEFAULT_PHONE_NUMBERALT))
            .andExpect(jsonPath("$.phoneNumberaltlocale").value(DEFAULT_PHONE_NUMBERALTLOCALE))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.address2").value(DEFAULT_ADDRESS_2))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.postalCode").value(DEFAULT_POSTAL_CODE))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.isContactPrivate").value(DEFAULT_IS_CONTACT_PRIVATE.booleanValue()))
            .andExpect(jsonPath("$.isOnetimeGuest").value(DEFAULT_IS_ONETIME_GUEST.booleanValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.loyaltyId").value(DEFAULT_LOYALTY_ID))
            .andExpect(jsonPath("$.loyaltyRank").value(DEFAULT_LOYALTY_RANK))
            .andExpect(jsonPath("$.loyaltyTier").value(DEFAULT_LOYALTY_TIER))
            .andExpect(jsonPath("$.marketingOptin").value(DEFAULT_MARKETING_OPTIN.booleanValue()))
            .andExpect(jsonPath("$.marketingOptints").value(DEFAULT_MARKETING_OPTINTS))
            .andExpect(jsonPath("$.marketingOptOutts").value(DEFAULT_MARKETING_OPT_OUTTS))
            .andExpect(jsonPath("$.hasBillingProfile").value(DEFAULT_HAS_BILLING_PROFILE.booleanValue()))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES))
            .andExpect(jsonPath("$.privateNotes").value(DEFAULT_PRIVATE_NOTES))
            .andExpect(jsonPath("$.tags").value(DEFAULT_TAGS))
            .andExpect(jsonPath("$.totalVisits").value(DEFAULT_TOTAL_VISITS.doubleValue()))
            .andExpect(jsonPath("$.totalCovers").value(DEFAULT_TOTAL_COVERS.doubleValue()))
            .andExpect(jsonPath("$.totalCancellations").value(DEFAULT_TOTAL_CANCELLATIONS.doubleValue()))
            .andExpect(jsonPath("$.totalNoShows").value(DEFAULT_TOTAL_NO_SHOWS.doubleValue()))
            .andExpect(jsonPath("$.totalSpend").value(DEFAULT_TOTAL_SPEND.doubleValue()))
            .andExpect(jsonPath("$.totalSpendPerCover").value(DEFAULT_TOTAL_SPEND_PER_COVER.doubleValue()))
            .andExpect(jsonPath("$.totalspendPerVisit").value(DEFAULT_TOTALSPEND_PER_VISIT.doubleValue()))
            .andExpect(jsonPath("$.avgRating").value(DEFAULT_AVG_RATING.doubleValue()))
            .andExpect(jsonPath("$.referenceCode").value(DEFAULT_REFERENCE_CODE))
            .andExpect(jsonPath("$.externalUserId").value(DEFAULT_EXTERNAL_USER_ID))
            .andExpect(jsonPath("$.venueGroupId").value(DEFAULT_VENUE_GROUP_ID))
            .andExpect(jsonPath("$.birthdayAltDay").value(DEFAULT_BIRTHDAY_ALT_DAY))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME))
            .andExpect(jsonPath("$.totalOrderCount").value(DEFAULT_TOTAL_ORDER_COUNT))
            .andExpect(jsonPath("$.preferredLanguageCode").value(DEFAULT_PREFERRED_LANGUAGE_CODE));
    }

    @Test
    void getNonExistingClient() throws Exception {
        // Get the client
        restClientMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingClient() throws Exception {
        // Initialize the database
        insertedClient = clientRepository.save(client);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the client
        Client updatedClient = clientRepository.findById(client.getId()).orElseThrow();
        updatedClient
            .clientId(UPDATED_CLIENT_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .deletedDate(UPDATED_DELETED_DATE)
            .lastname(UPDATED_LASTNAME)
            .firstname(UPDATED_FIRSTNAME)
            .gender(UPDATED_GENDER)
            .salutation(UPDATED_SALUTATION)
            .title(UPDATED_TITLE)
            .birthdayDay(UPDATED_BIRTHDAY_DAY)
            .birthdayMonth(UPDATED_BIRTHDAY_MONTH)
            .birthdayAltMonth(UPDATED_BIRTHDAY_ALT_MONTH)
            .anniversaryDay(UPDATED_ANNIVERSARY_DAY)
            .anniversaryMonth(UPDATED_ANNIVERSARY_MONTH)
            .company(UPDATED_COMPANY)
            .email(UPDATED_EMAIL)
            .emailAlt(UPDATED_EMAIL_ALT)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .phoneNumberlocale(UPDATED_PHONE_NUMBERLOCALE)
            .phoneNumberalt(UPDATED_PHONE_NUMBERALT)
            .phoneNumberaltlocale(UPDATED_PHONE_NUMBERALTLOCALE)
            .address(UPDATED_ADDRESS)
            .address2(UPDATED_ADDRESS_2)
            .city(UPDATED_CITY)
            .postalCode(UPDATED_POSTAL_CODE)
            .state(UPDATED_STATE)
            .country(UPDATED_COUNTRY)
            .isContactPrivate(UPDATED_IS_CONTACT_PRIVATE)
            .isOnetimeGuest(UPDATED_IS_ONETIME_GUEST)
            .status(UPDATED_STATUS)
            .loyaltyId(UPDATED_LOYALTY_ID)
            .loyaltyRank(UPDATED_LOYALTY_RANK)
            .loyaltyTier(UPDATED_LOYALTY_TIER)
            .marketingOptin(UPDATED_MARKETING_OPTIN)
            .marketingOptints(UPDATED_MARKETING_OPTINTS)
            .marketingOptOutts(UPDATED_MARKETING_OPT_OUTTS)
            .hasBillingProfile(UPDATED_HAS_BILLING_PROFILE)
            .notes(UPDATED_NOTES)
            .privateNotes(UPDATED_PRIVATE_NOTES)
            .tags(UPDATED_TAGS)
            .totalVisits(UPDATED_TOTAL_VISITS)
            .totalCovers(UPDATED_TOTAL_COVERS)
            .totalCancellations(UPDATED_TOTAL_CANCELLATIONS)
            .totalNoShows(UPDATED_TOTAL_NO_SHOWS)
            .totalSpend(UPDATED_TOTAL_SPEND)
            .totalSpendPerCover(UPDATED_TOTAL_SPEND_PER_COVER)
            .totalspendPerVisit(UPDATED_TOTALSPEND_PER_VISIT)
            .avgRating(UPDATED_AVG_RATING)
            .referenceCode(UPDATED_REFERENCE_CODE)
            .externalUserId(UPDATED_EXTERNAL_USER_ID)
            .venueGroupId(UPDATED_VENUE_GROUP_ID)
            .birthdayAltDay(UPDATED_BIRTHDAY_ALT_DAY)
            .userId(UPDATED_USER_ID)
            .userName(UPDATED_USER_NAME)
            .totalOrderCount(UPDATED_TOTAL_ORDER_COUNT)
            .preferredLanguageCode(UPDATED_PREFERRED_LANGUAGE_CODE);

        restClientMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedClient.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedClient))
            )
            .andExpect(status().isOk());

        // Validate the Client in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedClientToMatchAllProperties(updatedClient);
    }

    @Test
    void putNonExistingClient() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        client.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClientMockMvc
            .perform(put(ENTITY_API_URL_ID, client.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(client)))
            .andExpect(status().isBadRequest());

        // Validate the Client in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchClient() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        client.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(client))
            )
            .andExpect(status().isBadRequest());

        // Validate the Client in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamClient() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        client.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(client)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Client in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateClientWithPatch() throws Exception {
        // Initialize the database
        insertedClient = clientRepository.save(client);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the client using partial update
        Client partialUpdatedClient = new Client();
        partialUpdatedClient.setId(client.getId());

        partialUpdatedClient
            .clientId(UPDATED_CLIENT_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .firstname(UPDATED_FIRSTNAME)
            .salutation(UPDATED_SALUTATION)
            .birthdayMonth(UPDATED_BIRTHDAY_MONTH)
            .anniversaryDay(UPDATED_ANNIVERSARY_DAY)
            .anniversaryMonth(UPDATED_ANNIVERSARY_MONTH)
            .phoneNumberalt(UPDATED_PHONE_NUMBERALT)
            .address(UPDATED_ADDRESS)
            .address2(UPDATED_ADDRESS_2)
            .postalCode(UPDATED_POSTAL_CODE)
            .country(UPDATED_COUNTRY)
            .isContactPrivate(UPDATED_IS_CONTACT_PRIVATE)
            .isOnetimeGuest(UPDATED_IS_ONETIME_GUEST)
            .loyaltyTier(UPDATED_LOYALTY_TIER)
            .marketingOptints(UPDATED_MARKETING_OPTINTS)
            .notes(UPDATED_NOTES)
            .privateNotes(UPDATED_PRIVATE_NOTES)
            .tags(UPDATED_TAGS)
            .totalNoShows(UPDATED_TOTAL_NO_SHOWS)
            .totalspendPerVisit(UPDATED_TOTALSPEND_PER_VISIT)
            .avgRating(UPDATED_AVG_RATING)
            .referenceCode(UPDATED_REFERENCE_CODE)
            .userName(UPDATED_USER_NAME)
            .totalOrderCount(UPDATED_TOTAL_ORDER_COUNT);

        restClientMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClient.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedClient))
            )
            .andExpect(status().isOk());

        // Validate the Client in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertClientUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedClient, client), getPersistedClient(client));
    }

    @Test
    void fullUpdateClientWithPatch() throws Exception {
        // Initialize the database
        insertedClient = clientRepository.save(client);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the client using partial update
        Client partialUpdatedClient = new Client();
        partialUpdatedClient.setId(client.getId());

        partialUpdatedClient
            .clientId(UPDATED_CLIENT_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .updatedDate(UPDATED_UPDATED_DATE)
            .deletedDate(UPDATED_DELETED_DATE)
            .lastname(UPDATED_LASTNAME)
            .firstname(UPDATED_FIRSTNAME)
            .gender(UPDATED_GENDER)
            .salutation(UPDATED_SALUTATION)
            .title(UPDATED_TITLE)
            .birthdayDay(UPDATED_BIRTHDAY_DAY)
            .birthdayMonth(UPDATED_BIRTHDAY_MONTH)
            .birthdayAltMonth(UPDATED_BIRTHDAY_ALT_MONTH)
            .anniversaryDay(UPDATED_ANNIVERSARY_DAY)
            .anniversaryMonth(UPDATED_ANNIVERSARY_MONTH)
            .company(UPDATED_COMPANY)
            .email(UPDATED_EMAIL)
            .emailAlt(UPDATED_EMAIL_ALT)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .phoneNumberlocale(UPDATED_PHONE_NUMBERLOCALE)
            .phoneNumberalt(UPDATED_PHONE_NUMBERALT)
            .phoneNumberaltlocale(UPDATED_PHONE_NUMBERALTLOCALE)
            .address(UPDATED_ADDRESS)
            .address2(UPDATED_ADDRESS_2)
            .city(UPDATED_CITY)
            .postalCode(UPDATED_POSTAL_CODE)
            .state(UPDATED_STATE)
            .country(UPDATED_COUNTRY)
            .isContactPrivate(UPDATED_IS_CONTACT_PRIVATE)
            .isOnetimeGuest(UPDATED_IS_ONETIME_GUEST)
            .status(UPDATED_STATUS)
            .loyaltyId(UPDATED_LOYALTY_ID)
            .loyaltyRank(UPDATED_LOYALTY_RANK)
            .loyaltyTier(UPDATED_LOYALTY_TIER)
            .marketingOptin(UPDATED_MARKETING_OPTIN)
            .marketingOptints(UPDATED_MARKETING_OPTINTS)
            .marketingOptOutts(UPDATED_MARKETING_OPT_OUTTS)
            .hasBillingProfile(UPDATED_HAS_BILLING_PROFILE)
            .notes(UPDATED_NOTES)
            .privateNotes(UPDATED_PRIVATE_NOTES)
            .tags(UPDATED_TAGS)
            .totalVisits(UPDATED_TOTAL_VISITS)
            .totalCovers(UPDATED_TOTAL_COVERS)
            .totalCancellations(UPDATED_TOTAL_CANCELLATIONS)
            .totalNoShows(UPDATED_TOTAL_NO_SHOWS)
            .totalSpend(UPDATED_TOTAL_SPEND)
            .totalSpendPerCover(UPDATED_TOTAL_SPEND_PER_COVER)
            .totalspendPerVisit(UPDATED_TOTALSPEND_PER_VISIT)
            .avgRating(UPDATED_AVG_RATING)
            .referenceCode(UPDATED_REFERENCE_CODE)
            .externalUserId(UPDATED_EXTERNAL_USER_ID)
            .venueGroupId(UPDATED_VENUE_GROUP_ID)
            .birthdayAltDay(UPDATED_BIRTHDAY_ALT_DAY)
            .userId(UPDATED_USER_ID)
            .userName(UPDATED_USER_NAME)
            .totalOrderCount(UPDATED_TOTAL_ORDER_COUNT)
            .preferredLanguageCode(UPDATED_PREFERRED_LANGUAGE_CODE);

        restClientMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClient.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedClient))
            )
            .andExpect(status().isOk());

        // Validate the Client in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertClientUpdatableFieldsEquals(partialUpdatedClient, getPersistedClient(partialUpdatedClient));
    }

    @Test
    void patchNonExistingClient() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        client.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClientMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, client.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(client))
            )
            .andExpect(status().isBadRequest());

        // Validate the Client in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchClient() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        client.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(client))
            )
            .andExpect(status().isBadRequest());

        // Validate the Client in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamClient() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        client.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(client)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Client in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteClient() throws Exception {
        // Initialize the database
        insertedClient = clientRepository.save(client);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the client
        restClientMockMvc
            .perform(delete(ENTITY_API_URL_ID, client.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return clientRepository.count();
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

    protected Client getPersistedClient(Client client) {
        return clientRepository.findById(client.getId()).orElseThrow();
    }

    protected void assertPersistedClientToMatchAllProperties(Client expectedClient) {
        assertClientAllPropertiesEquals(expectedClient, getPersistedClient(expectedClient));
    }

    protected void assertPersistedClientToMatchUpdatableProperties(Client expectedClient) {
        assertClientAllUpdatablePropertiesEquals(expectedClient, getPersistedClient(expectedClient));
    }
}
