package com.sbm.mc.sevenroomstoreviewpro;

import com.sbm.mc.sevenroomstoreviewpro.config.AsyncSyncConfiguration;
import com.sbm.mc.sevenroomstoreviewpro.config.EmbeddedMongo;
import com.sbm.mc.sevenroomstoreviewpro.config.JacksonConfiguration;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Base composite annotation for integration tests.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = { SevenRoomsToReviewProApplicationApp.class, JacksonConfiguration.class, AsyncSyncConfiguration.class })
@EmbeddedMongo
public @interface IntegrationTest {
}
