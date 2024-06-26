package com.sbm.mc.sevenroomstoreviewpro.service;

import com.sbm.mc.sevenroomstoreviewpro.domain.ReservationPayload;
import com.sbm.mc.sevenroomstoreviewpro.domain.RvpGuest;
import com.sbm.mc.sevenroomstoreviewpro.serdes.CustomSerdes;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StreamsProcessor {

    private final Logger logger = LoggerFactory.getLogger(StreamsProcessor.class);

    private static final Serde<String> STRING_SERDE = Serdes.String();
    private static final Serde<ReservationPayload> RESERVATION_PAYLOAD_SERDE = CustomSerdes.ReservationPayload();

    private final ConstructGuestService constructGuestService;
    private final ReviewproService reviewproService;

    @Value(value = "${spring.kafka.topics.reservation-topic}")
    private String reservationTopic;

    public StreamsProcessor(ConstructGuestService constructGuestService, ReviewproService reviewproService) {
        this.constructGuestService = constructGuestService;
        this.reviewproService = reviewproService;
    }

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        KStream<String, ReservationPayload> reservationStream = streamsBuilder.stream(
            reservationTopic,
            Consumed.with(STRING_SERDE, RESERVATION_PAYLOAD_SERDE)
        );
        reservationStream.foreach((key, value) -> reservationsProcessor(value));
    }

    private void reservationsProcessor(ReservationPayload reservationPayload) {
        try {
            if (reservationPayload != null) {
                if (constructGuestService.validateReservation(reservationPayload)) {
                    RvpGuest rvpProfile = constructGuestService.constructGuest(reservationPayload);
                    reviewproService.postGuestToReviewpro(rvpProfile);
                }
            } else {
                logger.info("ReservationPayload Empty , Aborting ...");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e.getClass());
        }
    }
}
