package com.sbm.mc.sevenroomstoreviewpro.domain;

import java.util.UUID;

public class ResTableTestSamples {

    public static ResTable getResTableSample1() {
        return new ResTable().id("id1").tableNumber("tableNumber1");
    }

    public static ResTable getResTableSample2() {
        return new ResTable().id("id2").tableNumber("tableNumber2");
    }

    public static ResTable getResTableRandomSampleGenerator() {
        return new ResTable().id(UUID.randomUUID().toString()).tableNumber(UUID.randomUUID().toString());
    }
}
