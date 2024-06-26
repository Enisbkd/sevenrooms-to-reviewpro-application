package com.sbm.mc.sevenroomstoreviewpro.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class BookingNameAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertBookingNameAllPropertiesEquals(BookingName expected, BookingName actual) {
        assertBookingNameAutoGeneratedPropertiesEquals(expected, actual);
        assertBookingNameAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertBookingNameAllUpdatablePropertiesEquals(BookingName expected, BookingName actual) {
        assertBookingNameUpdatableFieldsEquals(expected, actual);
        assertBookingNameUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertBookingNameAutoGeneratedPropertiesEquals(BookingName expected, BookingName actual) {
        assertThat(expected)
            .as("Verify BookingName auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertBookingNameUpdatableFieldsEquals(BookingName expected, BookingName actual) {
        assertThat(expected)
            .as("Verify BookingName relevant properties")
            .satisfies(e -> assertThat(e.getName()).as("check name").isEqualTo(actual.getName()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertBookingNameUpdatableRelationshipsEquals(BookingName expected, BookingName actual) {
        assertThat(expected)
            .as("Verify BookingName relationships")
            .satisfies(e -> assertThat(e.getClientVenueStats()).as("check clientVenueStats").isEqualTo(actual.getClientVenueStats()));
    }
}