package com.sbm.mc.sevenroomstoreviewpro.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sbm.mc.sevenroomstoreviewpro.domain.RvpGuest;
import com.sbm.mc.sevenroomstoreviewpro.service.ReviewproService;
import com.sbm.mc.sevenroomstoreviewpro.service.SevenroomsTokenService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReviewproServiceImpl implements ReviewproService {

    private final Logger log = LoggerFactory.getLogger(ReviewproServiceImpl.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    SevenroomsTokenService sevenroomsTokenService;

    @Value(value = "${reviewproApi.graviteeUrl}")
    private String graviteeUrl;

    @Value(value = "${reviewproApi.api-key}")
    private String graviteeApiKey;

    public String postGuestToReviewpro(RvpGuest guest) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", graviteeApiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String reviewproUrl = graviteeUrl + "v1/pms/guests/58887da09932021ab31c5540";

        List<JsonNode> requestBody = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNodeFactory factory = JsonNodeFactory.instance;

        ObjectNode body = factory.objectNode();
        body.put("pmsId", guest.getPmsId());
        body.put("firstName", guest.getFirstName());
        body.put("lastName", guest.getLastName());
        body.put("language", guest.getLanguage());
        body.put("checkin", Objects.toString(guest.getCheckin(), null));
        body.put("checkout", Objects.toString(guest.getCheckout(), null));
        body.put("email", guest.getEmail());
        //        body.put("Civilite",guest.getCivilite());
        requestBody.add(body);

        String requestBodyJson = null;
        try {
            requestBodyJson = objectMapper.writeValueAsString(requestBody);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpEntity<String> entity = new HttpEntity<>(requestBodyJson, headers);

        log.info("Sending Post request to: {}", reviewproUrl);
        try {
            ResponseEntity<String> response = restTemplate.exchange(reviewproUrl, HttpMethod.POST, entity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                log.info("Received response with status code: {}", response.getStatusCode().value());
                return response.getBody();
            } else {
                return "Error: " + response.getStatusCode().value();
            }
        } catch (Exception e) {
            log.error("Error occurred while making POST request to {}: {}", reviewproUrl, e.getMessage());
            return "Error occurred while making POST request to " + reviewproUrl + e.getMessage();
        }
    }
}
