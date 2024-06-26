package com.sbm.mc.sevenroomstoreviewpro.domain;

import java.util.UUID;

public class ClientTagTestSamples {

    public static ClientTag getClientTagSample1() {
        return new ClientTag()
            .id("id1")
            .tag("tag1")
            .tagDisplay("tagDisplay1")
            .group("group1")
            .groupDisplay("groupDisplay1")
            .color("color1")
            .tagSearchQuery("tagSearchQuery1");
    }

    public static ClientTag getClientTagSample2() {
        return new ClientTag()
            .id("id2")
            .tag("tag2")
            .tagDisplay("tagDisplay2")
            .group("group2")
            .groupDisplay("groupDisplay2")
            .color("color2")
            .tagSearchQuery("tagSearchQuery2");
    }

    public static ClientTag getClientTagRandomSampleGenerator() {
        return new ClientTag()
            .id(UUID.randomUUID().toString())
            .tag(UUID.randomUUID().toString())
            .tagDisplay(UUID.randomUUID().toString())
            .group(UUID.randomUUID().toString())
            .groupDisplay(UUID.randomUUID().toString())
            .color(UUID.randomUUID().toString())
            .tagSearchQuery(UUID.randomUUID().toString());
    }
}
