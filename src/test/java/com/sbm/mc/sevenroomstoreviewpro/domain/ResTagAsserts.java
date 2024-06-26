package com.sbm.mc.sevenroomstoreviewpro.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class ResTagAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertResTagAllPropertiesEquals(ResTag expected, ResTag actual) {
        assertResTagAutoGeneratedPropertiesEquals(expected, actual);
        assertResTagAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertResTagAllUpdatablePropertiesEquals(ResTag expected, ResTag actual) {
        assertResTagUpdatableFieldsEquals(expected, actual);
        assertResTagUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertResTagAutoGeneratedPropertiesEquals(ResTag expected, ResTag actual) {
        assertThat(expected)
            .as("Verify ResTag auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertResTagUpdatableFieldsEquals(ResTag expected, ResTag actual) {
        assertThat(expected)
            .as("Verify ResTag relevant properties")
            .satisfies(e -> assertThat(e.getTag()).as("check tag").isEqualTo(actual.getTag()))
            .satisfies(e -> assertThat(e.getTagDisplay()).as("check tagDisplay").isEqualTo(actual.getTagDisplay()))
            .satisfies(e -> assertThat(e.getGroup()).as("check group").isEqualTo(actual.getGroup()))
            .satisfies(e -> assertThat(e.getGroupDisplay()).as("check groupDisplay").isEqualTo(actual.getGroupDisplay()))
            .satisfies(e -> assertThat(e.getColor()).as("check color").isEqualTo(actual.getColor()))
            .satisfies(e -> assertThat(e.getTagSearchQuery()).as("check tagSearchQuery").isEqualTo(actual.getTagSearchQuery()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertResTagUpdatableRelationshipsEquals(ResTag expected, ResTag actual) {
        assertThat(expected)
            .as("Verify ResTag relationships")
            .satisfies(e -> assertThat(e.getReservation()).as("check reservation").isEqualTo(actual.getReservation()));
    }
}