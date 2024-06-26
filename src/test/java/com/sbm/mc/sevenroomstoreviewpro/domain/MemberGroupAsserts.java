package com.sbm.mc.sevenroomstoreviewpro.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberGroupAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertMemberGroupAllPropertiesEquals(MemberGroup expected, MemberGroup actual) {
        assertMemberGroupAutoGeneratedPropertiesEquals(expected, actual);
        assertMemberGroupAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertMemberGroupAllUpdatablePropertiesEquals(MemberGroup expected, MemberGroup actual) {
        assertMemberGroupUpdatableFieldsEquals(expected, actual);
        assertMemberGroupUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertMemberGroupAutoGeneratedPropertiesEquals(MemberGroup expected, MemberGroup actual) {
        assertThat(expected)
            .as("Verify MemberGroup auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertMemberGroupUpdatableFieldsEquals(MemberGroup expected, MemberGroup actual) {}

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertMemberGroupUpdatableRelationshipsEquals(MemberGroup expected, MemberGroup actual) {
        assertThat(expected)
            .as("Verify MemberGroup relationships")
            .satisfies(e -> assertThat(e.getClient()).as("check client").isEqualTo(actual.getClient()));
    }
}
