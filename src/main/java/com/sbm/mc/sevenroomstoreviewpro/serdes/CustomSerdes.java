package com.sbm.mc.sevenroomstoreviewpro.serdes;

import com.sbm.mc.sevenroomstoreviewpro.domain.ClientPayload;
import com.sbm.mc.sevenroomstoreviewpro.domain.ReservationPayload;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public final class CustomSerdes {

    private CustomSerdes() {}

    public static Serde<ClientPayload> ClientPayload() {
        JacksonSerializer<ClientPayload> serializer = new JacksonSerializer<>();
        ClientDeserializer<ClientPayload> deserializer = new ClientDeserializer<>(ClientPayload.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }

    public static Serde<ReservationPayload> ReservationPayload() {
        JacksonSerializer<ReservationPayload> serializer = new JacksonSerializer<>();
        ReservationDeserializer<ReservationPayload> deserializer = new ReservationDeserializer<>(ReservationPayload.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }
}
