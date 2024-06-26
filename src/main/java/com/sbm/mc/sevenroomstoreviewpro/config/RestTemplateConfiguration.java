package com.sbm.mc.sevenroomstoreviewpro.config;

import java.nio.charset.StandardCharsets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        // Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress("10.62.1.10", 3128));
        // SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        // requestFactory.setProxy(proxy);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }
}
