package com.sbm.mc.sevenroomstoreviewpro.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomFieldTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static CustomField getCustomFieldSample1() {
        return new CustomField().id("id1").systemName("systemName1").displayOrder(1).name("name1").value("value1");
    }

    public static CustomField getCustomFieldSample2() {
        return new CustomField().id("id2").systemName("systemName2").displayOrder(2).name("name2").value("value2");
    }

    public static CustomField getCustomFieldRandomSampleGenerator() {
        return new CustomField()
            .id(UUID.randomUUID().toString())
            .systemName(UUID.randomUUID().toString())
            .displayOrder(intCount.incrementAndGet())
            .name(UUID.randomUUID().toString())
            .value(UUID.randomUUID().toString());
    }
}
