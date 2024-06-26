package com.sbm.mc.sevenroomstoreviewpro.serdes;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.streams.errors.DeserializationExceptionHandler;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendToDeadLetterQueueExceptionHandler implements DeserializationExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(SendToDeadLetterQueueExceptionHandler.class);

    KafkaProducer dlqProducer;

    @Override
    public DeserializationHandlerResponse handle(
        final ProcessorContext context,
        final ConsumerRecord<byte[], byte[]> record,
        final Exception exception
    ) {
        log.warn(
            "Exception caught during Deserialization, sending to the dead queue topic; " +
            "taskId: {}, topic: {}, partition: {}, offset: {}",
            context.taskId(),
            record.topic(),
            record.partition(),
            record.offset(),
            exception
        );

        String decodedKey = "";
        String decodedValue = "";
        if (record.key() != null) decodedKey = new String(record.key(), StandardCharsets.UTF_8);
        if (record.value() != null) decodedValue = new String(record.value(), StandardCharsets.UTF_8);
        dlqProducer.send(new ProducerRecord<>(record.topic() + "-deadletters", decodedKey, decodedValue));

        return DeserializationHandlerResponse.CONTINUE;
    }

    @Override
    public void configure(final Map<String, ?> configs) {
        dlqProducer = new KafkaProducer(configs);
    }
}
