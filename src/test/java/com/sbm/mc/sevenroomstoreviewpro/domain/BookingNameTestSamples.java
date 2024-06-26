package com.sbm.mc.sevenroomstoreviewpro.domain;

import java.util.UUID;

public class BookingNameTestSamples {

    public static BookingName getBookingNameSample1() {
        return new BookingName().id("id1").name("name1");
    }

    public static BookingName getBookingNameSample2() {
        return new BookingName().id("id2").name("name2");
    }

    public static BookingName getBookingNameRandomSampleGenerator() {
        return new BookingName().id(UUID.randomUUID().toString()).name(UUID.randomUUID().toString());
    }
}
