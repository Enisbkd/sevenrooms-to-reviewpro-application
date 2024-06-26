package com.sbm.mc.sevenroomstoreviewpro.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class ResPosticketsItemAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertResPosticketsItemAllPropertiesEquals(ResPosticketsItem expected, ResPosticketsItem actual) {
        assertResPosticketsItemAutoGeneratedPropertiesEquals(expected, actual);
        assertResPosticketsItemAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertResPosticketsItemAllUpdatablePropertiesEquals(ResPosticketsItem expected, ResPosticketsItem actual) {
        assertResPosticketsItemUpdatableFieldsEquals(expected, actual);
        assertResPosticketsItemUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertResPosticketsItemAutoGeneratedPropertiesEquals(ResPosticketsItem expected, ResPosticketsItem actual) {
        assertThat(expected)
            .as("Verify ResPosticketsItem auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertResPosticketsItemUpdatableFieldsEquals(ResPosticketsItem expected, ResPosticketsItem actual) {
        assertThat(expected)
            .as("Verify ResPosticketsItem relevant properties")
            .satisfies(e -> assertThat(e.getPrice()).as("check price").isEqualTo(actual.getPrice()))
            .satisfies(e -> assertThat(e.getName()).as("check name").isEqualTo(actual.getName()))
            .satisfies(e -> assertThat(e.getQuantity()).as("check quantity").isEqualTo(actual.getQuantity()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertResPosticketsItemUpdatableRelationshipsEquals(ResPosticketsItem expected, ResPosticketsItem actual) {
        assertThat(expected)
            .as("Verify ResPosticketsItem relationships")
            .satisfies(e -> assertThat(e.getResPosTicket()).as("check resPosTicket").isEqualTo(actual.getResPosTicket()));
    }
}