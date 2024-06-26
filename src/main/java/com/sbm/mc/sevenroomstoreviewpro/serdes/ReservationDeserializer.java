package com.sbm.mc.sevenroomstoreviewpro.serdes;

/**
 * Copyright © 2017 Jeremy Custenborder (jcustenborder@gmail.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sbm.mc.sevenroomstoreviewpro.domain.ResTable;
import com.sbm.mc.sevenroomstoreviewpro.domain.Reservation;
import com.sbm.mc.sevenroomstoreviewpro.exceptions.BadEntityTypeException;
import com.sbm.mc.sevenroomstoreviewpro.exceptions.BadEventTypeException;
import java.io.IOException;
import java.util.*;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.streams.errors.StreamsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReservationDeserializer<ReservationPayload> implements Deserializer<ReservationPayload> {

    private static final Logger log = LoggerFactory.getLogger(ReservationDeserializer.class);
    private final ObjectMapper objectMapper;
    Class<ReservationPayload> cls;
    private JacksonDeserializerConfig config;

    List<String> eventTypes = Arrays.asList(new String[] { "created", "updated", "deleted" });

    public ReservationDeserializer() {
        this.objectMapper = new ObjectMapper();
    }

    public ReservationDeserializer(Class<ReservationPayload> cls) {
        this();
        this.cls = cls;
    }

    public static Map<String, String> nonDefaultSettings(ObjectMapper objectMapper) {
        return JacksonDeserializerConfig.nonDefaultSettings(objectMapper);
    }

    @Override
    public void configure(Map<String, ?> settings, boolean isKey) {
        this.config = new JacksonDeserializerConfig(settings);
        this.config.configure(this.objectMapper.findAndRegisterModules());
        if (null != this.cls) {
            log.trace("cls is already configured to {}", this.cls.getName());
        } else {
            this.cls = this.config.outputClass;
        }
    }

    @Override
    public ReservationPayload deserialize(String topic, byte[] bytes) {
        if (null == bytes) {
            return null;
        }
        try {
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.findAndRegisterModules();
            String entityType = String.valueOf(objectMapper.readTree(bytes).get("entity_type"));
            // Removing quotes because field is parsed with quotes.
            String eventType = String.valueOf(objectMapper.readTree(bytes).get("event_type")).replace("\"", "");
            if (entityType.contains("reservation")) {
                if (eventTypes.contains(eventType)) {
                    com.sbm.mc.sevenroomstoreviewpro.domain.ReservationPayload reservationPayload = objectMapper.readValue(
                        bytes,
                        com.sbm.mc.sevenroomstoreviewpro.domain.ReservationPayload.class
                    );
                    Reservation reservation = reservationPayload.getReservation();

                    JsonNode resEntity = objectMapper.readTree(bytes).get("entity");
                    reservation.setRealDateTimeOfSlot(resEntity.get("real_datetime_of_slot").toString().replace("\"", ""));

                    if (resEntity != null) {
                        userDeserializer(resEntity, reservation);
                        resTableDeserializer(resEntity, reservation);
                        reservationPayload.setReservation(reservation);
                    }
                    return (ReservationPayload) reservationPayload;
                } else throw new BadEventTypeException(
                    "Event type is not recognized , accepted values : " + eventTypes.toString() + " found :" + eventType
                );
            } else throw new BadEntityTypeException("Entity type is not Reservation , expected : Reservation , found :" + entityType);
        } catch (IOException | BadEntityTypeException | StreamsException | BadEventTypeException e) {
            throw new SerializationException(e);
        }
    }

    private static void userDeserializer(JsonNode resEntity, Reservation reservation) {
        JsonNode userNode = resEntity.get("user");
        if (userNode != null) {
            String userId = String.valueOf(userNode.get("id")).replace("\"", "");
            String userName = String.valueOf(userNode.get("name")).replace("\"", "");
            reservation.setUserId(userId);
            reservation.setUserName(userName);
        }
    }

    private static void resTableDeserializer(JsonNode resEntity, Reservation reservation) {
        JsonNode tableNumbersNode = resEntity.get("table_numbers");
        Set<ResTable> resTables = new HashSet<>();

        if (tableNumbersNode != null) {
            if (tableNumbersNode.isArray()) {
                for (JsonNode tableNumberNode : tableNumbersNode) {
                    resTables.add(new ResTable(tableNumberNode.toString(), reservation));
                }
            }
            reservation.setResTables(resTables);
        }
    }

    @Override
    public void close() {}
}
