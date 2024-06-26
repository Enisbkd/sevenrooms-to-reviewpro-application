package com.sbm.mc.sevenroomstoreviewpro.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientVenueStatsTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static ClientVenueStats getClientVenueStatsSample1() {
        return new ClientVenueStats()
            .id("id1")
            .lastVisitDate("lastVisitDate1")
            .totalCancellations(1)
            .totalCovers(1)
            .avgRating(1)
            .totalNoShows(1)
            .numRatings(1)
            .totalVisits(1)
            .lastOrderDate("lastOrderDate1")
            .venueId("venueId1")
            .venueMarketingOptints("venueMarketingOptints1");
    }

    public static ClientVenueStats getClientVenueStatsSample2() {
        return new ClientVenueStats()
            .id("id2")
            .lastVisitDate("lastVisitDate2")
            .totalCancellations(2)
            .totalCovers(2)
            .avgRating(2)
            .totalNoShows(2)
            .numRatings(2)
            .totalVisits(2)
            .lastOrderDate("lastOrderDate2")
            .venueId("venueId2")
            .venueMarketingOptints("venueMarketingOptints2");
    }

    public static ClientVenueStats getClientVenueStatsRandomSampleGenerator() {
        return new ClientVenueStats()
            .id(UUID.randomUUID().toString())
            .lastVisitDate(UUID.randomUUID().toString())
            .totalCancellations(intCount.incrementAndGet())
            .totalCovers(intCount.incrementAndGet())
            .avgRating(intCount.incrementAndGet())
            .totalNoShows(intCount.incrementAndGet())
            .numRatings(intCount.incrementAndGet())
            .totalVisits(intCount.incrementAndGet())
            .lastOrderDate(UUID.randomUUID().toString())
            .venueId(UUID.randomUUID().toString())
            .venueMarketingOptints(UUID.randomUUID().toString());
    }
}
