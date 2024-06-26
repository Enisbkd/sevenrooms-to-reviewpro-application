package com.sbm.mc.sevenroomstoreviewpro.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientPhotoTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static ClientPhoto getClientPhotoSample1() {
        return new ClientPhoto()
            .id("id1")
            .large("large1")
            .largeHeight(1)
            .largeWidth(1)
            .medium("medium1")
            .mediumHeight(1)
            .mediumWidth(1)
            .small("small1")
            .smallHeight(1)
            .smallWidth(1)
            .raw("raw1")
            .cropx(1)
            .cropy(1);
    }

    public static ClientPhoto getClientPhotoSample2() {
        return new ClientPhoto()
            .id("id2")
            .large("large2")
            .largeHeight(2)
            .largeWidth(2)
            .medium("medium2")
            .mediumHeight(2)
            .mediumWidth(2)
            .small("small2")
            .smallHeight(2)
            .smallWidth(2)
            .raw("raw2")
            .cropx(2)
            .cropy(2);
    }

    public static ClientPhoto getClientPhotoRandomSampleGenerator() {
        return new ClientPhoto()
            .id(UUID.randomUUID().toString())
            .large(UUID.randomUUID().toString())
            .largeHeight(intCount.incrementAndGet())
            .largeWidth(intCount.incrementAndGet())
            .medium(UUID.randomUUID().toString())
            .mediumHeight(intCount.incrementAndGet())
            .mediumWidth(intCount.incrementAndGet())
            .small(UUID.randomUUID().toString())
            .smallHeight(intCount.incrementAndGet())
            .smallWidth(intCount.incrementAndGet())
            .raw(UUID.randomUUID().toString())
            .cropx(intCount.incrementAndGet())
            .cropy(intCount.incrementAndGet());
    }
}
