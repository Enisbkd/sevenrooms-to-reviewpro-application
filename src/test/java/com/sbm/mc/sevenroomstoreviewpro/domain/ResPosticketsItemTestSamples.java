package com.sbm.mc.sevenroomstoreviewpro.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class ResPosticketsItemTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static ResPosticketsItem getResPosticketsItemSample1() {
        return new ResPosticketsItem().id("id1").name("name1").quantity(1);
    }

    public static ResPosticketsItem getResPosticketsItemSample2() {
        return new ResPosticketsItem().id("id2").name("name2").quantity(2);
    }

    public static ResPosticketsItem getResPosticketsItemRandomSampleGenerator() {
        return new ResPosticketsItem()
            .id(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .quantity(intCount.incrementAndGet());
    }
}
