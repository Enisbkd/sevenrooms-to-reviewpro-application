package com.sbm.mc.sevenroomstoreviewpro.web.rest;

import static com.sbm.mc.sevenroomstoreviewpro.domain.ReservationAsserts.*;
import static com.sbm.mc.sevenroomstoreviewpro.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbm.mc.sevenroomstoreviewpro.IntegrationTest;
import com.sbm.mc.sevenroomstoreviewpro.domain.Reservation;
import com.sbm.mc.sevenroomstoreviewpro.repository.ReservationRepository;
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
 * Integration tests for the {@link ReservationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ReservationResourceIT {

    private static final String DEFAULT_RESV_ID = "AAAAAAAAAA";
    private static final String UPDATED_RESV_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED = "AAAAAAAAAA";
    private static final String UPDATED_CREATED = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED = "BBBBBBBBBB";

    private static final String DEFAULT_DELETED = "AAAAAAAAAA";
    private static final String UPDATED_DELETED = "BBBBBBBBBB";

    private static final String DEFAULT_VENUE_GROUP_CLIENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_VENUE_GROUP_CLIENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_VENUE_GROUP_ID = "AAAAAAAAAA";
    private static final String UPDATED_VENUE_GROUP_ID = "BBBBBBBBBB";

    private static final String DEFAULT_VENUE_ID = "AAAAAAAAAA";
    private static final String UPDATED_VENUE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DATE = "AAAAAAAAAA";
    private static final String UPDATED_DATE = "BBBBBBBBBB";

    private static final Integer DEFAULT_DURATION = 1;
    private static final Integer UPDATED_DURATION = 2;

    private static final String DEFAULT_CHECK_NUMBERS = "AAAAAAAAAA";
    private static final String UPDATED_CHECK_NUMBERS = "BBBBBBBBBB";

    private static final String DEFAULT_SHIFT_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_SHIFT_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_SHIFT_PERSISTENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_SHIFT_PERSISTENT_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_MAX_GUESTS = 1;
    private static final Integer UPDATED_MAX_GUESTS = 2;

    private static final Integer DEFAULT_MFRATIO_MALE = 1;
    private static final Integer UPDATED_MFRATIO_MALE = 2;

    private static final Integer DEFAULT_MFRATIO_FEMALE = 1;
    private static final Integer UPDATED_MFRATIO_FEMALE = 2;

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_DISPLAY = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_DISPLAY = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_SIMPLE = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_SIMPLE = "BBBBBBBBBB";

    private static final String DEFAULT_ACCESS_PERSISTENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_ACCESS_PERSISTENT_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_ARRIVED_GUESTS = 1;
    private static final Integer UPDATED_ARRIVED_GUESTS = 2;

    private static final Boolean DEFAULT_ISVIP = false;
    private static final Boolean UPDATED_ISVIP = true;

    private static final String DEFAULT_BOOKEDBY = "AAAAAAAAAA";
    private static final String UPDATED_BOOKEDBY = "BBBBBBBBBB";

    private static final String DEFAULT_CLIENT_REFERENCE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT_REFERENCE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LASTNAME = "AAAAAAAAAA";
    private static final String UPDATED_LASTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_FIRSTNAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRSTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

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

    private static final String DEFAULT_LOYALTY_ID = "AAAAAAAAAA";
    private static final String UPDATED_LOYALTY_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_LOYALTY_RANK = 1;
    private static final Integer UPDATED_LOYALTY_RANK = 2;

    private static final String DEFAULT_LOYALTY_TIER = "AAAAAAAAAA";
    private static final String UPDATED_LOYALTY_TIER = "BBBBBBBBBB";

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    private static final String DEFAULT_ARRIVAL_TIME = "AAAAAAAAAA";
    private static final String UPDATED_ARRIVAL_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_SEATED_TIME = "AAAAAAAAAA";
    private static final String UPDATED_SEATED_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_LEFT_TIME = "AAAAAAAAAA";
    private static final String UPDATED_LEFT_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_CLIENT_REQUESTS = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT_REQUESTS = "BBBBBBBBBB";

    private static final Integer DEFAULT_COMPS = 1;
    private static final Integer UPDATED_COMPS = 2;

    private static final String DEFAULT_COMPS_PRICE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_COMPS_PRICE_TYPE = "BBBBBBBBBB";

    private static final Integer DEFAULT_COST_OPTION = 1;
    private static final Integer UPDATED_COST_OPTION = 2;

    private static final String DEFAULT_POLICY = "AAAAAAAAAA";
    private static final String UPDATED_POLICY = "BBBBBBBBBB";

    private static final Integer DEFAULT_MIN_PRICE = 1;
    private static final Integer UPDATED_MIN_PRICE = 2;

    private static final Double DEFAULT_PRE_PAYMENT = 1D;
    private static final Double UPDATED_PRE_PAYMENT = 2D;

    private static final Double DEFAULT_ONSITE_PAYMENT = 1D;
    private static final Double UPDATED_ONSITE_PAYMENT = 2D;

    private static final Integer DEFAULT_TOTAL_PAYMENT = 1;
    private static final Integer UPDATED_TOTAL_PAYMENT = 2;

    private static final String DEFAULT_PAID_BY = "AAAAAAAAAA";
    private static final String UPDATED_PAID_BY = "BBBBBBBBBB";

    private static final String DEFAULT_SERVED_BY = "AAAAAAAAAA";
    private static final String UPDATED_SERVED_BY = "BBBBBBBBBB";

    private static final Integer DEFAULT_RATING = 1;
    private static final Integer UPDATED_RATING = 2;

    private static final String DEFAULT_PROBLEMS = "AAAAAAAAAA";
    private static final String UPDATED_PROBLEMS = "BBBBBBBBBB";

    private static final String DEFAULT_AUTO_ASSIGNMENTS = "AAAAAAAAAA";
    private static final String UPDATED_AUTO_ASSIGNMENTS = "BBBBBBBBBB";

    private static final String DEFAULT_EXTERNAL_CLIENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_EXTERNAL_CLIENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_EXTERNAL_ID = "AAAAAAAAAA";
    private static final String UPDATED_EXTERNAL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_EXTERNAL_REFERENCE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_EXTERNAL_REFERENCE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_EXTERNAL_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_EXTERNAL_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFY_RESERVATION_LINK = "AAAAAAAAAA";
    private static final String UPDATED_MODIFY_RESERVATION_LINK = "BBBBBBBBBB";

    private static final String DEFAULT_REFERENCE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE_CODE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_RESERVATION_SMS_OPTIN = false;
    private static final Boolean UPDATED_RESERVATION_SMS_OPTIN = true;

    private static final String DEFAULT_RESERVATION_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_RESERVATION_TYPE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_SEND_REMINDER_EMAIL = false;
    private static final Boolean UPDATED_SEND_REMINDER_EMAIL = true;

    private static final Boolean DEFAULT_SENDREMINDER_SMS = false;
    private static final Boolean UPDATED_SENDREMINDER_SMS = true;

    private static final String DEFAULT_SOURCE_CLIENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_CLIENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/reservations";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private MockMvc restReservationMockMvc;

    private Reservation reservation;

    private Reservation insertedReservation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Reservation createEntity() {
        Reservation reservation = new Reservation()
            .resvId(DEFAULT_RESV_ID)
            .created(DEFAULT_CREATED)
            .updated(DEFAULT_UPDATED)
            .deleted(DEFAULT_DELETED)
            .venueGroupClientId(DEFAULT_VENUE_GROUP_CLIENT_ID)
            .venueGroupId(DEFAULT_VENUE_GROUP_ID)
            .venueId(DEFAULT_VENUE_ID)
            .date(DEFAULT_DATE)
            .duration(DEFAULT_DURATION)
            .checkNumbers(DEFAULT_CHECK_NUMBERS)
            .shiftCategory(DEFAULT_SHIFT_CATEGORY)
            .shiftPersistentId(DEFAULT_SHIFT_PERSISTENT_ID)
            .maxGuests(DEFAULT_MAX_GUESTS)
            .mfratioMale(DEFAULT_MFRATIO_MALE)
            .mfratioFemale(DEFAULT_MFRATIO_FEMALE)
            .status(DEFAULT_STATUS)
            .statusDisplay(DEFAULT_STATUS_DISPLAY)
            .statusSimple(DEFAULT_STATUS_SIMPLE)
            .accessPersistentId(DEFAULT_ACCESS_PERSISTENT_ID)
            .arrivedGuests(DEFAULT_ARRIVED_GUESTS)
            .isvip(DEFAULT_ISVIP)
            .bookedby(DEFAULT_BOOKEDBY)
            .clientReferenceCode(DEFAULT_CLIENT_REFERENCE_CODE)
            .lastname(DEFAULT_LASTNAME)
            .firstname(DEFAULT_FIRSTNAME)
            .email(DEFAULT_EMAIL)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .address(DEFAULT_ADDRESS)
            .address2(DEFAULT_ADDRESS_2)
            .city(DEFAULT_CITY)
            .postalCode(DEFAULT_POSTAL_CODE)
            .state(DEFAULT_STATE)
            .country(DEFAULT_COUNTRY)
            .loyaltyId(DEFAULT_LOYALTY_ID)
            .loyaltyRank(DEFAULT_LOYALTY_RANK)
            .loyaltyTier(DEFAULT_LOYALTY_TIER)
            .notes(DEFAULT_NOTES)
            .arrivalTime(DEFAULT_ARRIVAL_TIME)
            .seatedTime(DEFAULT_SEATED_TIME)
            .leftTime(DEFAULT_LEFT_TIME)
            .clientRequests(DEFAULT_CLIENT_REQUESTS)
            .comps(DEFAULT_COMPS)
            .compsPriceType(DEFAULT_COMPS_PRICE_TYPE)
            .costOption(DEFAULT_COST_OPTION)
            .policy(DEFAULT_POLICY)
            .minPrice(DEFAULT_MIN_PRICE)
            .prePayment(DEFAULT_PRE_PAYMENT)
            .onsitePayment(DEFAULT_ONSITE_PAYMENT)
            .totalPayment(DEFAULT_TOTAL_PAYMENT)
            .paidBy(DEFAULT_PAID_BY)
            .servedBy(DEFAULT_SERVED_BY)
            .rating(DEFAULT_RATING)
            .problems(DEFAULT_PROBLEMS)
            .autoAssignments(DEFAULT_AUTO_ASSIGNMENTS)
            .externalClientId(DEFAULT_EXTERNAL_CLIENT_ID)
            .externalId(DEFAULT_EXTERNAL_ID)
            .externalReferenceCode(DEFAULT_EXTERNAL_REFERENCE_CODE)
            .externalUserId(DEFAULT_EXTERNAL_USER_ID)
            .modifyReservationLink(DEFAULT_MODIFY_RESERVATION_LINK)
            .referenceCode(DEFAULT_REFERENCE_CODE)
            .reservationSmsOptin(DEFAULT_RESERVATION_SMS_OPTIN)
            .reservationType(DEFAULT_RESERVATION_TYPE)
            .sendReminderEmail(DEFAULT_SEND_REMINDER_EMAIL)
            .sendreminderSms(DEFAULT_SENDREMINDER_SMS)
            .sourceClientId(DEFAULT_SOURCE_CLIENT_ID)
            .userId(DEFAULT_USER_ID)
            .userName(DEFAULT_USER_NAME);
        return reservation;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Reservation createUpdatedEntity() {
        Reservation reservation = new Reservation()
            .resvId(UPDATED_RESV_ID)
            .created(UPDATED_CREATED)
            .updated(UPDATED_UPDATED)
            .deleted(UPDATED_DELETED)
            .venueGroupClientId(UPDATED_VENUE_GROUP_CLIENT_ID)
            .venueGroupId(UPDATED_VENUE_GROUP_ID)
            .venueId(UPDATED_VENUE_ID)
            .date(UPDATED_DATE)
            .duration(UPDATED_DURATION)
            .checkNumbers(UPDATED_CHECK_NUMBERS)
            .shiftCategory(UPDATED_SHIFT_CATEGORY)
            .shiftPersistentId(UPDATED_SHIFT_PERSISTENT_ID)
            .maxGuests(UPDATED_MAX_GUESTS)
            .mfratioMale(UPDATED_MFRATIO_MALE)
            .mfratioFemale(UPDATED_MFRATIO_FEMALE)
            .status(UPDATED_STATUS)
            .statusDisplay(UPDATED_STATUS_DISPLAY)
            .statusSimple(UPDATED_STATUS_SIMPLE)
            .accessPersistentId(UPDATED_ACCESS_PERSISTENT_ID)
            .arrivedGuests(UPDATED_ARRIVED_GUESTS)
            .isvip(UPDATED_ISVIP)
            .bookedby(UPDATED_BOOKEDBY)
            .clientReferenceCode(UPDATED_CLIENT_REFERENCE_CODE)
            .lastname(UPDATED_LASTNAME)
            .firstname(UPDATED_FIRSTNAME)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .address(UPDATED_ADDRESS)
            .address2(UPDATED_ADDRESS_2)
            .city(UPDATED_CITY)
            .postalCode(UPDATED_POSTAL_CODE)
            .state(UPDATED_STATE)
            .country(UPDATED_COUNTRY)
            .loyaltyId(UPDATED_LOYALTY_ID)
            .loyaltyRank(UPDATED_LOYALTY_RANK)
            .loyaltyTier(UPDATED_LOYALTY_TIER)
            .notes(UPDATED_NOTES)
            .arrivalTime(UPDATED_ARRIVAL_TIME)
            .seatedTime(UPDATED_SEATED_TIME)
            .leftTime(UPDATED_LEFT_TIME)
            .clientRequests(UPDATED_CLIENT_REQUESTS)
            .comps(UPDATED_COMPS)
            .compsPriceType(UPDATED_COMPS_PRICE_TYPE)
            .costOption(UPDATED_COST_OPTION)
            .policy(UPDATED_POLICY)
            .minPrice(UPDATED_MIN_PRICE)
            .prePayment(UPDATED_PRE_PAYMENT)
            .onsitePayment(UPDATED_ONSITE_PAYMENT)
            .totalPayment(UPDATED_TOTAL_PAYMENT)
            .paidBy(UPDATED_PAID_BY)
            .servedBy(UPDATED_SERVED_BY)
            .rating(UPDATED_RATING)
            .problems(UPDATED_PROBLEMS)
            .autoAssignments(UPDATED_AUTO_ASSIGNMENTS)
            .externalClientId(UPDATED_EXTERNAL_CLIENT_ID)
            .externalId(UPDATED_EXTERNAL_ID)
            .externalReferenceCode(UPDATED_EXTERNAL_REFERENCE_CODE)
            .externalUserId(UPDATED_EXTERNAL_USER_ID)
            .modifyReservationLink(UPDATED_MODIFY_RESERVATION_LINK)
            .referenceCode(UPDATED_REFERENCE_CODE)
            .reservationSmsOptin(UPDATED_RESERVATION_SMS_OPTIN)
            .reservationType(UPDATED_RESERVATION_TYPE)
            .sendReminderEmail(UPDATED_SEND_REMINDER_EMAIL)
            .sendreminderSms(UPDATED_SENDREMINDER_SMS)
            .sourceClientId(UPDATED_SOURCE_CLIENT_ID)
            .userId(UPDATED_USER_ID)
            .userName(UPDATED_USER_NAME);
        return reservation;
    }

    @BeforeEach
    public void initTest() {
        reservation = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedReservation != null) {
            reservationRepository.delete(insertedReservation);
            insertedReservation = null;
        }
    }

    @Test
    void createReservation() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Reservation
        var returnedReservation = om.readValue(
            restReservationMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(reservation)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Reservation.class
        );

        // Validate the Reservation in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertReservationUpdatableFieldsEquals(returnedReservation, getPersistedReservation(returnedReservation));

        insertedReservation = returnedReservation;
    }

    @Test
    void createReservationWithExistingId() throws Exception {
        // Create the Reservation with an existing ID
        reservation.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restReservationMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(reservation)))
            .andExpect(status().isBadRequest());

        // Validate the Reservation in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void getAllReservations() throws Exception {
        // Initialize the database
        insertedReservation = reservationRepository.save(reservation);

        // Get all the reservationList
        restReservationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reservation.getId())))
            .andExpect(jsonPath("$.[*].resvId").value(hasItem(DEFAULT_RESV_ID)))
            .andExpect(jsonPath("$.[*].created").value(hasItem(DEFAULT_CREATED)))
            .andExpect(jsonPath("$.[*].updated").value(hasItem(DEFAULT_UPDATED)))
            .andExpect(jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED)))
            .andExpect(jsonPath("$.[*].venueGroupClientId").value(hasItem(DEFAULT_VENUE_GROUP_CLIENT_ID)))
            .andExpect(jsonPath("$.[*].venueGroupId").value(hasItem(DEFAULT_VENUE_GROUP_ID)))
            .andExpect(jsonPath("$.[*].venueId").value(hasItem(DEFAULT_VENUE_ID)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE)))
            .andExpect(jsonPath("$.[*].duration").value(hasItem(DEFAULT_DURATION)))
            .andExpect(jsonPath("$.[*].checkNumbers").value(hasItem(DEFAULT_CHECK_NUMBERS)))
            .andExpect(jsonPath("$.[*].shiftCategory").value(hasItem(DEFAULT_SHIFT_CATEGORY)))
            .andExpect(jsonPath("$.[*].shiftPersistentId").value(hasItem(DEFAULT_SHIFT_PERSISTENT_ID)))
            .andExpect(jsonPath("$.[*].maxGuests").value(hasItem(DEFAULT_MAX_GUESTS)))
            .andExpect(jsonPath("$.[*].mfratioMale").value(hasItem(DEFAULT_MFRATIO_MALE)))
            .andExpect(jsonPath("$.[*].mfratioFemale").value(hasItem(DEFAULT_MFRATIO_FEMALE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].statusDisplay").value(hasItem(DEFAULT_STATUS_DISPLAY)))
            .andExpect(jsonPath("$.[*].statusSimple").value(hasItem(DEFAULT_STATUS_SIMPLE)))
            .andExpect(jsonPath("$.[*].accessPersistentId").value(hasItem(DEFAULT_ACCESS_PERSISTENT_ID)))
            .andExpect(jsonPath("$.[*].arrivedGuests").value(hasItem(DEFAULT_ARRIVED_GUESTS)))
            .andExpect(jsonPath("$.[*].isvip").value(hasItem(DEFAULT_ISVIP.booleanValue())))
            .andExpect(jsonPath("$.[*].bookedby").value(hasItem(DEFAULT_BOOKEDBY)))
            .andExpect(jsonPath("$.[*].clientReferenceCode").value(hasItem(DEFAULT_CLIENT_REFERENCE_CODE)))
            .andExpect(jsonPath("$.[*].lastname").value(hasItem(DEFAULT_LASTNAME)))
            .andExpect(jsonPath("$.[*].firstname").value(hasItem(DEFAULT_FIRSTNAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].address2").value(hasItem(DEFAULT_ADDRESS_2)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].postalCode").value(hasItem(DEFAULT_POSTAL_CODE)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].loyaltyId").value(hasItem(DEFAULT_LOYALTY_ID)))
            .andExpect(jsonPath("$.[*].loyaltyRank").value(hasItem(DEFAULT_LOYALTY_RANK)))
            .andExpect(jsonPath("$.[*].loyaltyTier").value(hasItem(DEFAULT_LOYALTY_TIER)))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES)))
            .andExpect(jsonPath("$.[*].arrivalTime").value(hasItem(DEFAULT_ARRIVAL_TIME)))
            .andExpect(jsonPath("$.[*].seatedTime").value(hasItem(DEFAULT_SEATED_TIME)))
            .andExpect(jsonPath("$.[*].leftTime").value(hasItem(DEFAULT_LEFT_TIME)))
            .andExpect(jsonPath("$.[*].clientRequests").value(hasItem(DEFAULT_CLIENT_REQUESTS)))
            .andExpect(jsonPath("$.[*].comps").value(hasItem(DEFAULT_COMPS)))
            .andExpect(jsonPath("$.[*].compsPriceType").value(hasItem(DEFAULT_COMPS_PRICE_TYPE)))
            .andExpect(jsonPath("$.[*].costOption").value(hasItem(DEFAULT_COST_OPTION)))
            .andExpect(jsonPath("$.[*].policy").value(hasItem(DEFAULT_POLICY)))
            .andExpect(jsonPath("$.[*].minPrice").value(hasItem(DEFAULT_MIN_PRICE)))
            .andExpect(jsonPath("$.[*].prePayment").value(hasItem(DEFAULT_PRE_PAYMENT.doubleValue())))
            .andExpect(jsonPath("$.[*].onsitePayment").value(hasItem(DEFAULT_ONSITE_PAYMENT.doubleValue())))
            .andExpect(jsonPath("$.[*].totalPayment").value(hasItem(DEFAULT_TOTAL_PAYMENT)))
            .andExpect(jsonPath("$.[*].paidBy").value(hasItem(DEFAULT_PAID_BY)))
            .andExpect(jsonPath("$.[*].servedBy").value(hasItem(DEFAULT_SERVED_BY)))
            .andExpect(jsonPath("$.[*].rating").value(hasItem(DEFAULT_RATING)))
            .andExpect(jsonPath("$.[*].problems").value(hasItem(DEFAULT_PROBLEMS)))
            .andExpect(jsonPath("$.[*].autoAssignments").value(hasItem(DEFAULT_AUTO_ASSIGNMENTS)))
            .andExpect(jsonPath("$.[*].externalClientId").value(hasItem(DEFAULT_EXTERNAL_CLIENT_ID)))
            .andExpect(jsonPath("$.[*].externalId").value(hasItem(DEFAULT_EXTERNAL_ID)))
            .andExpect(jsonPath("$.[*].externalReferenceCode").value(hasItem(DEFAULT_EXTERNAL_REFERENCE_CODE)))
            .andExpect(jsonPath("$.[*].externalUserId").value(hasItem(DEFAULT_EXTERNAL_USER_ID)))
            .andExpect(jsonPath("$.[*].modifyReservationLink").value(hasItem(DEFAULT_MODIFY_RESERVATION_LINK)))
            .andExpect(jsonPath("$.[*].referenceCode").value(hasItem(DEFAULT_REFERENCE_CODE)))
            .andExpect(jsonPath("$.[*].reservationSmsOptin").value(hasItem(DEFAULT_RESERVATION_SMS_OPTIN.booleanValue())))
            .andExpect(jsonPath("$.[*].reservationType").value(hasItem(DEFAULT_RESERVATION_TYPE)))
            .andExpect(jsonPath("$.[*].sendReminderEmail").value(hasItem(DEFAULT_SEND_REMINDER_EMAIL.booleanValue())))
            .andExpect(jsonPath("$.[*].sendreminderSms").value(hasItem(DEFAULT_SENDREMINDER_SMS.booleanValue())))
            .andExpect(jsonPath("$.[*].sourceClientId").value(hasItem(DEFAULT_SOURCE_CLIENT_ID)))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME)));
    }

    @Test
    void getReservation() throws Exception {
        // Initialize the database
        insertedReservation = reservationRepository.save(reservation);

        // Get the reservation
        restReservationMockMvc
            .perform(get(ENTITY_API_URL_ID, reservation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(reservation.getId()))
            .andExpect(jsonPath("$.resvId").value(DEFAULT_RESV_ID))
            .andExpect(jsonPath("$.created").value(DEFAULT_CREATED))
            .andExpect(jsonPath("$.updated").value(DEFAULT_UPDATED))
            .andExpect(jsonPath("$.deleted").value(DEFAULT_DELETED))
            .andExpect(jsonPath("$.venueGroupClientId").value(DEFAULT_VENUE_GROUP_CLIENT_ID))
            .andExpect(jsonPath("$.venueGroupId").value(DEFAULT_VENUE_GROUP_ID))
            .andExpect(jsonPath("$.venueId").value(DEFAULT_VENUE_ID))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE))
            .andExpect(jsonPath("$.duration").value(DEFAULT_DURATION))
            .andExpect(jsonPath("$.checkNumbers").value(DEFAULT_CHECK_NUMBERS))
            .andExpect(jsonPath("$.shiftCategory").value(DEFAULT_SHIFT_CATEGORY))
            .andExpect(jsonPath("$.shiftPersistentId").value(DEFAULT_SHIFT_PERSISTENT_ID))
            .andExpect(jsonPath("$.maxGuests").value(DEFAULT_MAX_GUESTS))
            .andExpect(jsonPath("$.mfratioMale").value(DEFAULT_MFRATIO_MALE))
            .andExpect(jsonPath("$.mfratioFemale").value(DEFAULT_MFRATIO_FEMALE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.statusDisplay").value(DEFAULT_STATUS_DISPLAY))
            .andExpect(jsonPath("$.statusSimple").value(DEFAULT_STATUS_SIMPLE))
            .andExpect(jsonPath("$.accessPersistentId").value(DEFAULT_ACCESS_PERSISTENT_ID))
            .andExpect(jsonPath("$.arrivedGuests").value(DEFAULT_ARRIVED_GUESTS))
            .andExpect(jsonPath("$.isvip").value(DEFAULT_ISVIP.booleanValue()))
            .andExpect(jsonPath("$.bookedby").value(DEFAULT_BOOKEDBY))
            .andExpect(jsonPath("$.clientReferenceCode").value(DEFAULT_CLIENT_REFERENCE_CODE))
            .andExpect(jsonPath("$.lastname").value(DEFAULT_LASTNAME))
            .andExpect(jsonPath("$.firstname").value(DEFAULT_FIRSTNAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.address2").value(DEFAULT_ADDRESS_2))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.postalCode").value(DEFAULT_POSTAL_CODE))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.loyaltyId").value(DEFAULT_LOYALTY_ID))
            .andExpect(jsonPath("$.loyaltyRank").value(DEFAULT_LOYALTY_RANK))
            .andExpect(jsonPath("$.loyaltyTier").value(DEFAULT_LOYALTY_TIER))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES))
            .andExpect(jsonPath("$.arrivalTime").value(DEFAULT_ARRIVAL_TIME))
            .andExpect(jsonPath("$.seatedTime").value(DEFAULT_SEATED_TIME))
            .andExpect(jsonPath("$.leftTime").value(DEFAULT_LEFT_TIME))
            .andExpect(jsonPath("$.clientRequests").value(DEFAULT_CLIENT_REQUESTS))
            .andExpect(jsonPath("$.comps").value(DEFAULT_COMPS))
            .andExpect(jsonPath("$.compsPriceType").value(DEFAULT_COMPS_PRICE_TYPE))
            .andExpect(jsonPath("$.costOption").value(DEFAULT_COST_OPTION))
            .andExpect(jsonPath("$.policy").value(DEFAULT_POLICY))
            .andExpect(jsonPath("$.minPrice").value(DEFAULT_MIN_PRICE))
            .andExpect(jsonPath("$.prePayment").value(DEFAULT_PRE_PAYMENT.doubleValue()))
            .andExpect(jsonPath("$.onsitePayment").value(DEFAULT_ONSITE_PAYMENT.doubleValue()))
            .andExpect(jsonPath("$.totalPayment").value(DEFAULT_TOTAL_PAYMENT))
            .andExpect(jsonPath("$.paidBy").value(DEFAULT_PAID_BY))
            .andExpect(jsonPath("$.servedBy").value(DEFAULT_SERVED_BY))
            .andExpect(jsonPath("$.rating").value(DEFAULT_RATING))
            .andExpect(jsonPath("$.problems").value(DEFAULT_PROBLEMS))
            .andExpect(jsonPath("$.autoAssignments").value(DEFAULT_AUTO_ASSIGNMENTS))
            .andExpect(jsonPath("$.externalClientId").value(DEFAULT_EXTERNAL_CLIENT_ID))
            .andExpect(jsonPath("$.externalId").value(DEFAULT_EXTERNAL_ID))
            .andExpect(jsonPath("$.externalReferenceCode").value(DEFAULT_EXTERNAL_REFERENCE_CODE))
            .andExpect(jsonPath("$.externalUserId").value(DEFAULT_EXTERNAL_USER_ID))
            .andExpect(jsonPath("$.modifyReservationLink").value(DEFAULT_MODIFY_RESERVATION_LINK))
            .andExpect(jsonPath("$.referenceCode").value(DEFAULT_REFERENCE_CODE))
            .andExpect(jsonPath("$.reservationSmsOptin").value(DEFAULT_RESERVATION_SMS_OPTIN.booleanValue()))
            .andExpect(jsonPath("$.reservationType").value(DEFAULT_RESERVATION_TYPE))
            .andExpect(jsonPath("$.sendReminderEmail").value(DEFAULT_SEND_REMINDER_EMAIL.booleanValue()))
            .andExpect(jsonPath("$.sendreminderSms").value(DEFAULT_SENDREMINDER_SMS.booleanValue()))
            .andExpect(jsonPath("$.sourceClientId").value(DEFAULT_SOURCE_CLIENT_ID))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME));
    }

    @Test
    void getNonExistingReservation() throws Exception {
        // Get the reservation
        restReservationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingReservation() throws Exception {
        // Initialize the database
        insertedReservation = reservationRepository.save(reservation);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the reservation
        Reservation updatedReservation = reservationRepository.findById(reservation.getId()).orElseThrow();
        updatedReservation
            .resvId(UPDATED_RESV_ID)
            .created(UPDATED_CREATED)
            .updated(UPDATED_UPDATED)
            .deleted(UPDATED_DELETED)
            .venueGroupClientId(UPDATED_VENUE_GROUP_CLIENT_ID)
            .venueGroupId(UPDATED_VENUE_GROUP_ID)
            .venueId(UPDATED_VENUE_ID)
            .date(UPDATED_DATE)
            .duration(UPDATED_DURATION)
            .checkNumbers(UPDATED_CHECK_NUMBERS)
            .shiftCategory(UPDATED_SHIFT_CATEGORY)
            .shiftPersistentId(UPDATED_SHIFT_PERSISTENT_ID)
            .maxGuests(UPDATED_MAX_GUESTS)
            .mfratioMale(UPDATED_MFRATIO_MALE)
            .mfratioFemale(UPDATED_MFRATIO_FEMALE)
            .status(UPDATED_STATUS)
            .statusDisplay(UPDATED_STATUS_DISPLAY)
            .statusSimple(UPDATED_STATUS_SIMPLE)
            .accessPersistentId(UPDATED_ACCESS_PERSISTENT_ID)
            .arrivedGuests(UPDATED_ARRIVED_GUESTS)
            .isvip(UPDATED_ISVIP)
            .bookedby(UPDATED_BOOKEDBY)
            .clientReferenceCode(UPDATED_CLIENT_REFERENCE_CODE)
            .lastname(UPDATED_LASTNAME)
            .firstname(UPDATED_FIRSTNAME)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .address(UPDATED_ADDRESS)
            .address2(UPDATED_ADDRESS_2)
            .city(UPDATED_CITY)
            .postalCode(UPDATED_POSTAL_CODE)
            .state(UPDATED_STATE)
            .country(UPDATED_COUNTRY)
            .loyaltyId(UPDATED_LOYALTY_ID)
            .loyaltyRank(UPDATED_LOYALTY_RANK)
            .loyaltyTier(UPDATED_LOYALTY_TIER)
            .notes(UPDATED_NOTES)
            .arrivalTime(UPDATED_ARRIVAL_TIME)
            .seatedTime(UPDATED_SEATED_TIME)
            .leftTime(UPDATED_LEFT_TIME)
            .clientRequests(UPDATED_CLIENT_REQUESTS)
            .comps(UPDATED_COMPS)
            .compsPriceType(UPDATED_COMPS_PRICE_TYPE)
            .costOption(UPDATED_COST_OPTION)
            .policy(UPDATED_POLICY)
            .minPrice(UPDATED_MIN_PRICE)
            .prePayment(UPDATED_PRE_PAYMENT)
            .onsitePayment(UPDATED_ONSITE_PAYMENT)
            .totalPayment(UPDATED_TOTAL_PAYMENT)
            .paidBy(UPDATED_PAID_BY)
            .servedBy(UPDATED_SERVED_BY)
            .rating(UPDATED_RATING)
            .problems(UPDATED_PROBLEMS)
            .autoAssignments(UPDATED_AUTO_ASSIGNMENTS)
            .externalClientId(UPDATED_EXTERNAL_CLIENT_ID)
            .externalId(UPDATED_EXTERNAL_ID)
            .externalReferenceCode(UPDATED_EXTERNAL_REFERENCE_CODE)
            .externalUserId(UPDATED_EXTERNAL_USER_ID)
            .modifyReservationLink(UPDATED_MODIFY_RESERVATION_LINK)
            .referenceCode(UPDATED_REFERENCE_CODE)
            .reservationSmsOptin(UPDATED_RESERVATION_SMS_OPTIN)
            .reservationType(UPDATED_RESERVATION_TYPE)
            .sendReminderEmail(UPDATED_SEND_REMINDER_EMAIL)
            .sendreminderSms(UPDATED_SENDREMINDER_SMS)
            .sourceClientId(UPDATED_SOURCE_CLIENT_ID)
            .userId(UPDATED_USER_ID)
            .userName(UPDATED_USER_NAME);

        restReservationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedReservation.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedReservation))
            )
            .andExpect(status().isOk());

        // Validate the Reservation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedReservationToMatchAllProperties(updatedReservation);
    }

    @Test
    void putNonExistingReservation() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        reservation.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReservationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, reservation.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(reservation))
            )
            .andExpect(status().isBadRequest());

        // Validate the Reservation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchReservation() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        reservation.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReservationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(reservation))
            )
            .andExpect(status().isBadRequest());

        // Validate the Reservation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamReservation() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        reservation.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReservationMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(reservation)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Reservation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateReservationWithPatch() throws Exception {
        // Initialize the database
        insertedReservation = reservationRepository.save(reservation);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the reservation using partial update
        Reservation partialUpdatedReservation = new Reservation();
        partialUpdatedReservation.setId(reservation.getId());

        partialUpdatedReservation
            .created(UPDATED_CREATED)
            .deleted(UPDATED_DELETED)
            .venueGroupId(UPDATED_VENUE_GROUP_ID)
            .duration(UPDATED_DURATION)
            .shiftCategory(UPDATED_SHIFT_CATEGORY)
            .shiftPersistentId(UPDATED_SHIFT_PERSISTENT_ID)
            .mfratioMale(UPDATED_MFRATIO_MALE)
            .status(UPDATED_STATUS)
            .statusSimple(UPDATED_STATUS_SIMPLE)
            .arrivedGuests(UPDATED_ARRIVED_GUESTS)
            .bookedby(UPDATED_BOOKEDBY)
            .city(UPDATED_CITY)
            .postalCode(UPDATED_POSTAL_CODE)
            .loyaltyId(UPDATED_LOYALTY_ID)
            .loyaltyTier(UPDATED_LOYALTY_TIER)
            .seatedTime(UPDATED_SEATED_TIME)
            .leftTime(UPDATED_LEFT_TIME)
            .clientRequests(UPDATED_CLIENT_REQUESTS)
            .comps(UPDATED_COMPS)
            .costOption(UPDATED_COST_OPTION)
            .prePayment(UPDATED_PRE_PAYMENT)
            .servedBy(UPDATED_SERVED_BY)
            .externalClientId(UPDATED_EXTERNAL_CLIENT_ID)
            .externalId(UPDATED_EXTERNAL_ID)
            .externalReferenceCode(UPDATED_EXTERNAL_REFERENCE_CODE)
            .reservationType(UPDATED_RESERVATION_TYPE)
            .sendReminderEmail(UPDATED_SEND_REMINDER_EMAIL)
            .sendreminderSms(UPDATED_SENDREMINDER_SMS)
            .userId(UPDATED_USER_ID)
            .userName(UPDATED_USER_NAME);

        restReservationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReservation.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedReservation))
            )
            .andExpect(status().isOk());

        // Validate the Reservation in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertReservationUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedReservation, reservation),
            getPersistedReservation(reservation)
        );
    }

    @Test
    void fullUpdateReservationWithPatch() throws Exception {
        // Initialize the database
        insertedReservation = reservationRepository.save(reservation);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the reservation using partial update
        Reservation partialUpdatedReservation = new Reservation();
        partialUpdatedReservation.setId(reservation.getId());

        partialUpdatedReservation
            .resvId(UPDATED_RESV_ID)
            .created(UPDATED_CREATED)
            .updated(UPDATED_UPDATED)
            .deleted(UPDATED_DELETED)
            .venueGroupClientId(UPDATED_VENUE_GROUP_CLIENT_ID)
            .venueGroupId(UPDATED_VENUE_GROUP_ID)
            .venueId(UPDATED_VENUE_ID)
            .date(UPDATED_DATE)
            .duration(UPDATED_DURATION)
            .checkNumbers(UPDATED_CHECK_NUMBERS)
            .shiftCategory(UPDATED_SHIFT_CATEGORY)
            .shiftPersistentId(UPDATED_SHIFT_PERSISTENT_ID)
            .maxGuests(UPDATED_MAX_GUESTS)
            .mfratioMale(UPDATED_MFRATIO_MALE)
            .mfratioFemale(UPDATED_MFRATIO_FEMALE)
            .status(UPDATED_STATUS)
            .statusDisplay(UPDATED_STATUS_DISPLAY)
            .statusSimple(UPDATED_STATUS_SIMPLE)
            .accessPersistentId(UPDATED_ACCESS_PERSISTENT_ID)
            .arrivedGuests(UPDATED_ARRIVED_GUESTS)
            .isvip(UPDATED_ISVIP)
            .bookedby(UPDATED_BOOKEDBY)
            .clientReferenceCode(UPDATED_CLIENT_REFERENCE_CODE)
            .lastname(UPDATED_LASTNAME)
            .firstname(UPDATED_FIRSTNAME)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .address(UPDATED_ADDRESS)
            .address2(UPDATED_ADDRESS_2)
            .city(UPDATED_CITY)
            .postalCode(UPDATED_POSTAL_CODE)
            .state(UPDATED_STATE)
            .country(UPDATED_COUNTRY)
            .loyaltyId(UPDATED_LOYALTY_ID)
            .loyaltyRank(UPDATED_LOYALTY_RANK)
            .loyaltyTier(UPDATED_LOYALTY_TIER)
            .notes(UPDATED_NOTES)
            .arrivalTime(UPDATED_ARRIVAL_TIME)
            .seatedTime(UPDATED_SEATED_TIME)
            .leftTime(UPDATED_LEFT_TIME)
            .clientRequests(UPDATED_CLIENT_REQUESTS)
            .comps(UPDATED_COMPS)
            .compsPriceType(UPDATED_COMPS_PRICE_TYPE)
            .costOption(UPDATED_COST_OPTION)
            .policy(UPDATED_POLICY)
            .minPrice(UPDATED_MIN_PRICE)
            .prePayment(UPDATED_PRE_PAYMENT)
            .onsitePayment(UPDATED_ONSITE_PAYMENT)
            .totalPayment(UPDATED_TOTAL_PAYMENT)
            .paidBy(UPDATED_PAID_BY)
            .servedBy(UPDATED_SERVED_BY)
            .rating(UPDATED_RATING)
            .problems(UPDATED_PROBLEMS)
            .autoAssignments(UPDATED_AUTO_ASSIGNMENTS)
            .externalClientId(UPDATED_EXTERNAL_CLIENT_ID)
            .externalId(UPDATED_EXTERNAL_ID)
            .externalReferenceCode(UPDATED_EXTERNAL_REFERENCE_CODE)
            .externalUserId(UPDATED_EXTERNAL_USER_ID)
            .modifyReservationLink(UPDATED_MODIFY_RESERVATION_LINK)
            .referenceCode(UPDATED_REFERENCE_CODE)
            .reservationSmsOptin(UPDATED_RESERVATION_SMS_OPTIN)
            .reservationType(UPDATED_RESERVATION_TYPE)
            .sendReminderEmail(UPDATED_SEND_REMINDER_EMAIL)
            .sendreminderSms(UPDATED_SENDREMINDER_SMS)
            .sourceClientId(UPDATED_SOURCE_CLIENT_ID)
            .userId(UPDATED_USER_ID)
            .userName(UPDATED_USER_NAME);

        restReservationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReservation.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedReservation))
            )
            .andExpect(status().isOk());

        // Validate the Reservation in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertReservationUpdatableFieldsEquals(partialUpdatedReservation, getPersistedReservation(partialUpdatedReservation));
    }

    @Test
    void patchNonExistingReservation() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        reservation.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReservationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, reservation.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(reservation))
            )
            .andExpect(status().isBadRequest());

        // Validate the Reservation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchReservation() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        reservation.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReservationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(reservation))
            )
            .andExpect(status().isBadRequest());

        // Validate the Reservation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamReservation() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        reservation.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReservationMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(reservation)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Reservation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteReservation() throws Exception {
        // Initialize the database
        insertedReservation = reservationRepository.save(reservation);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the reservation
        restReservationMockMvc
            .perform(delete(ENTITY_API_URL_ID, reservation.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return reservationRepository.count();
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

    protected Reservation getPersistedReservation(Reservation reservation) {
        return reservationRepository.findById(reservation.getId()).orElseThrow();
    }

    protected void assertPersistedReservationToMatchAllProperties(Reservation expectedReservation) {
        assertReservationAllPropertiesEquals(expectedReservation, getPersistedReservation(expectedReservation));
    }

    protected void assertPersistedReservationToMatchUpdatableProperties(Reservation expectedReservation) {
        assertReservationAllUpdatablePropertiesEquals(expectedReservation, getPersistedReservation(expectedReservation));
    }
}
