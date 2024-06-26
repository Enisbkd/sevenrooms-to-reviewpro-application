package com.sbm.mc.sevenroomstoreviewpro.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class RvpGuestAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRvpGuestAllPropertiesEquals(RvpGuest expected, RvpGuest actual) {
        assertRvpGuestAutoGeneratedPropertiesEquals(expected, actual);
        assertRvpGuestAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRvpGuestAllUpdatablePropertiesEquals(RvpGuest expected, RvpGuest actual) {
        assertRvpGuestUpdatableFieldsEquals(expected, actual);
        assertRvpGuestUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRvpGuestAutoGeneratedPropertiesEquals(RvpGuest expected, RvpGuest actual) {
        assertThat(expected)
            .as("Verify RvpGuest auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRvpGuestUpdatableFieldsEquals(RvpGuest expected, RvpGuest actual) {
        assertThat(expected)
            .as("Verify RvpGuest relevant properties")
            .satisfies(e -> assertThat(e.getPmsId()).as("check pmsId").isEqualTo(actual.getPmsId()))
            .satisfies(e -> assertThat(e.getFirstName()).as("check firstName").isEqualTo(actual.getFirstName()))
            .satisfies(e -> assertThat(e.getLastName()).as("check lastName").isEqualTo(actual.getLastName()))
            .satisfies(e -> assertThat(e.getLanguage()).as("check language").isEqualTo(actual.getLanguage()))
            .satisfies(e -> assertThat(e.getCheckin()).as("check checkin").isEqualTo(actual.getCheckin()))
            .satisfies(e -> assertThat(e.getCheckout()).as("check checkout").isEqualTo(actual.getCheckout()))
            .satisfies(e -> assertThat(e.getEmail()).as("check email").isEqualTo(actual.getEmail()))
            .satisfies(e -> assertThat(e.getEmailAlt()).as("check emailAlt").isEqualTo(actual.getEmailAlt()))
            .satisfies(e -> assertThat(e.getSalutation()).as("check salutation").isEqualTo(actual.getSalutation()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRvpGuestUpdatableRelationshipsEquals(RvpGuest expected, RvpGuest actual) {}
}
