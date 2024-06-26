package com.sbm.mc.sevenroomstoreviewpro.domain;

import java.util.UUID;

public class VenueTestSamples {

    public static Venue getVenueSample1() {
        return new Venue()
            .id("id1")
            .address("address1")
            .blackLogo("blackLogo1")
            .country("country1")
            .crossStreet("crossStreet1")
            .currencyCode("currencyCode1")
            .externalVenueId("externalVenueId1")
            .venueId("venueId1")
            .internalName("internalName1")
            .name("name1")
            .neighborhood("neighborhood1")
            .phoneNumber("phoneNumber1")
            .policy("policy1")
            .postalCode("postalCode1")
            .primaryColor("primaryColor1")
            .secondaryColor("secondaryColor1")
            .state("state1")
            .uniqueConfirmationPrefix("uniqueConfirmationPrefix1")
            .venueClass("venueClass1")
            .venueGroupId("venueGroupId1")
            .venueGroupName("venueGroupName1")
            .venueUrlKey("venueUrlKey1")
            .website("website1")
            .whiteLogo("whiteLogo1");
    }

    public static Venue getVenueSample2() {
        return new Venue()
            .id("id2")
            .address("address2")
            .blackLogo("blackLogo2")
            .country("country2")
            .crossStreet("crossStreet2")
            .currencyCode("currencyCode2")
            .externalVenueId("externalVenueId2")
            .venueId("venueId2")
            .internalName("internalName2")
            .name("name2")
            .neighborhood("neighborhood2")
            .phoneNumber("phoneNumber2")
            .policy("policy2")
            .postalCode("postalCode2")
            .primaryColor("primaryColor2")
            .secondaryColor("secondaryColor2")
            .state("state2")
            .uniqueConfirmationPrefix("uniqueConfirmationPrefix2")
            .venueClass("venueClass2")
            .venueGroupId("venueGroupId2")
            .venueGroupName("venueGroupName2")
            .venueUrlKey("venueUrlKey2")
            .website("website2")
            .whiteLogo("whiteLogo2");
    }

    public static Venue getVenueRandomSampleGenerator() {
        return new Venue()
            .id(UUID.randomUUID().toString())
            .address(UUID.randomUUID().toString())
            .blackLogo(UUID.randomUUID().toString())
            .country(UUID.randomUUID().toString())
            .crossStreet(UUID.randomUUID().toString())
            .currencyCode(UUID.randomUUID().toString())
            .externalVenueId(UUID.randomUUID().toString())
            .venueId(UUID.randomUUID().toString())
            .internalName(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .neighborhood(UUID.randomUUID().toString())
            .phoneNumber(UUID.randomUUID().toString())
            .policy(UUID.randomUUID().toString())
            .postalCode(UUID.randomUUID().toString())
            .primaryColor(UUID.randomUUID().toString())
            .secondaryColor(UUID.randomUUID().toString())
            .state(UUID.randomUUID().toString())
            .uniqueConfirmationPrefix(UUID.randomUUID().toString())
            .venueClass(UUID.randomUUID().toString())
            .venueGroupId(UUID.randomUUID().toString())
            .venueGroupName(UUID.randomUUID().toString())
            .venueUrlKey(UUID.randomUUID().toString())
            .website(UUID.randomUUID().toString())
            .whiteLogo(UUID.randomUUID().toString());
    }
}
