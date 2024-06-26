package com.sbm.mc.sevenroomstoreviewpro.serdes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbm.mc.sevenroomstoreviewpro.domain.Venue;
import java.util.Map;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VenueDeserializer implements Deserializer<Venue> {

    private static final Logger log = LoggerFactory.getLogger(VenueDeserializer.class);

    public static Map<String, String> nonDefaultSettings(ObjectMapper objectMapper) {
        return JacksonDeserializerConfig.nonDefaultSettings(objectMapper);
    }

    @Override
    public void configure(Map<String, ?> settings, boolean isKey) {}

    @Override
    public Venue deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        Venue venue = null;
        try {
            venue = mapper.readValue(arg1, Venue.class);
        } catch (Exception e) {
            log.error("Error while deserializing Venue : ", e.getMessage());
        }
        return venue;
    }

    @Override
    public void close() {}
}
