package com.sbm.mc.sevenroomstoreviewpro.config;

import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.*;

import com.sbm.mc.sevenroomstoreviewpro.serdes.SendToDeadLetterQueueExceptionHandler;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;

@Configuration
@EnableKafka
@EnableKafkaStreams
public class KafkaStreamsConfiguration {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${spring.kafka.properties.security.protocol}")
    private String protocolConfig;

    @Value(value = "${spring.kafka.properties.sasl.jaas.config}")
    private String saslJaasConfig;

    @Value(value = "${spring.kafka.properties.sasl.mechanism}")
    private String saslMechanism;

    @Value(value = "${spring.kafka.properties.application.id}")
    private String applicationId;

    @Value(value = "${spring.kafka.properties.ssl.sslTruststoreLocation}")
    private String trustStoreLocation;

    @Value(value = "${spring.kafka.properties.auto.offset.reset.config}")
    private String offsetReset;

    private String trustStorePassword = System.getProperty("javax.net.ssl.trustStorePassword");

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    org.springframework.kafka.config.KafkaStreamsConfiguration kStreamsConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(APPLICATION_ID_CONFIG, applicationId);

        if (bootstrapAddress != null) {
            props.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        }
        if (protocolConfig != null) {
            props.put(SECURITY_PROTOCOL_CONFIG, protocolConfig);
        }
        if (saslJaasConfig != null) {
            props.put(SaslConfigs.SASL_JAAS_CONFIG, saslJaasConfig);
        }
        if (saslMechanism != null) {
            props.put(SaslConfigs.SASL_MECHANISM, saslMechanism);
        }
        if (trustStoreLocation != null) {
            props.put("ssl.truststore.location", trustStoreLocation);
        }
        if (trustStorePassword != null) {
            props.put("ssl.truststore.password", trustStorePassword);
        }

        props.put(DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(DEFAULT_DESERIALIZATION_EXCEPTION_HANDLER_CLASS_CONFIG, SendToDeadLetterQueueExceptionHandler.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, offsetReset);

        return new org.springframework.kafka.config.KafkaStreamsConfiguration(props);
    }
}
