package com.sbm.mc.sevenroomstoreviewpro.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class ResPosTicketTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static ResPosTicket getResPosTicketSample1() {
        return new ResPosTicket()
            .id("id1")
            .status("status1")
            .code(1)
            .tableNo("tableNo1")
            .businessId(1)
            .ticketId(1)
            .localPosticketId("localPosticketId1")
            .employeeName("employeeName1")
            .startTime("startTime1")
            .endtime("endtime1");
    }

    public static ResPosTicket getResPosTicketSample2() {
        return new ResPosTicket()
            .id("id2")
            .status("status2")
            .code(2)
            .tableNo("tableNo2")
            .businessId(2)
            .ticketId(2)
            .localPosticketId("localPosticketId2")
            .employeeName("employeeName2")
            .startTime("startTime2")
            .endtime("endtime2");
    }

    public static ResPosTicket getResPosTicketRandomSampleGenerator() {
        return new ResPosTicket()
            .id(UUID.randomUUID().toString())
            .status(UUID.randomUUID().toString())
            .code(intCount.incrementAndGet())
            .tableNo(UUID.randomUUID().toString())
            .businessId(intCount.incrementAndGet())
            .ticketId(intCount.incrementAndGet())
            .localPosticketId(UUID.randomUUID().toString())
            .employeeName(UUID.randomUUID().toString())
            .startTime(UUID.randomUUID().toString())
            .endtime(UUID.randomUUID().toString());
    }
}
