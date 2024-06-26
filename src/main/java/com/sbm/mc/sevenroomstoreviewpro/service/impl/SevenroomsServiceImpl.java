package com.sbm.mc.sevenroomstoreviewpro.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbm.mc.sevenroomstoreviewpro.domain.Client;
import com.sbm.mc.sevenroomstoreviewpro.service.SevenroomsService;
import com.sbm.mc.sevenroomstoreviewpro.service.SevenroomsTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SevenroomsServiceImpl implements SevenroomsService {

    private static final Logger log = LoggerFactory.getLogger(SevenroomsServiceImpl.class);

    @Autowired
    SevenroomsTokenService sevenroomsTokenService;

    @Autowired
    RestTemplate restTemplate;

    @Value(value = "${sevenroomsApi.graviteeUrl}")
    private String graviteeUrl;

    @Value(value = "${sevenroomsApi.api-key}")
    private String graviteeApiKey;

    public String getClientFromSevenRoomsApi(String client_id) {
        String url = graviteeUrl + "clients";

        String authToken = sevenroomsTokenService.generateToken();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-7rooms-Authorization", authToken);
        headers.set("X-Api-Key", graviteeApiKey);

        StringBuilder query = new StringBuilder();
        query.append("/").append(client_id);

        url += query.toString();

        HttpEntity<String> entity = new HttpEntity<>(headers);

        if (!authToken.isEmpty()) {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                log.info("Received response with status code: {}", response.getStatusCode().value());
                return response.getBody();
            } else {
                return "Error: " + response.getStatusCode().value();
            }
        } else {
            return "Error: Token empty , Request won't be sent.";
        }
    }

    public Client extractClientFields(String clientPayload) {
        ObjectMapper mapper = new ObjectMapper();
        Client client = new Client();
        try {
            JsonNode clientNode = mapper.readTree(clientPayload);
            String email = clientNode.path("data").path("email").asText();
            String language = clientNode.path("data").path("preferred_language_code").asText();
            String civilite = clientNode.path("data").path("salutation").asText();
            client.setEmail(email);
            client.setPreferredLanguageCode(language);
            client.setSalutation(civilite);
            return client;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
