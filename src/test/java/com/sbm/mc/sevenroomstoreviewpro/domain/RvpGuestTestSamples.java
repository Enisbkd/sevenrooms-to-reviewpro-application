package com.sbm.mc.sevenroomstoreviewpro.domain;

import java.util.UUID;

public class RvpGuestTestSamples {

    public static RvpGuest getRvpGuestSample1() {
        return new RvpGuest()
            .id("id1")
            .pmsId("pmsId1")
            .firstName("firstName1")
            .lastName("lastName1")
            .language("language1")
            .email("email1")
            .emailAlt("emailAlt1")
            .salutation("salutation1");
    }

    public static RvpGuest getRvpGuestSample2() {
        return new RvpGuest()
            .id("id2")
            .pmsId("pmsId2")
            .firstName("firstName2")
            .lastName("lastName2")
            .language("language2")
            .email("email2")
            .emailAlt("emailAlt2")
            .salutation("salutation2");
    }

    public static RvpGuest getRvpGuestRandomSampleGenerator() {
        return new RvpGuest()
            .id(UUID.randomUUID().toString())
            .pmsId(UUID.randomUUID().toString())
            .firstName(UUID.randomUUID().toString())
            .lastName(UUID.randomUUID().toString())
            .language(UUID.randomUUID().toString())
            .email(UUID.randomUUID().toString())
            .emailAlt(UUID.randomUUID().toString())
            .salutation(UUID.randomUUID().toString());
    }
}
