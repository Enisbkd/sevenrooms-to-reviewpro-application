package com.sbm.mc.sevenroomstoreviewpro.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class ResCustomFieldTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static ResCustomField getResCustomFieldSample1() {
        return new ResCustomField().id("id1").systemName("systemName1").displayOrder(1).name("name1").value("value1");
    }

    public static ResCustomField getResCustomFieldSample2() {
        return new ResCustomField().id("id2").systemName("systemName2").displayOrder(2).name("name2").value("value2");
    }

    public static ResCustomField getResCustomFieldRandomSampleGenerator() {
        return new ResCustomField()
            .id(UUID.randomUUID().toString())
            .systemName(UUID.randomUUID().toString())
            .displayOrder(intCount.incrementAndGet())
            .name(UUID.randomUUID().toString())
            .value(UUID.randomUUID().toString());
    }
}
