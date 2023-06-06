package edu.ap.backendspring.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AbbControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplate restTemplate;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://projectvm27.p.bletchley.cloud:10079";
    }

    @Test
    void testGetAbbInformation() {
        String expectedResponse = "hello world";

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(baseUrl, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    void testGetAbbCitizen() {
        String nationalNumber = "75.01.14-012.34";
        String expectedResponse = "{\"nationalNumber\":\"75.01.14-012.34\",\"firstName\":\"Jeroen\",\"lastName\":\"De Vos\",\"dateOfBirth\":\"1975-01-14\",\"placeOfBirth\":\"Schoten\",\"street\":\"Vierhoevenstraat\",\"number\":\"101\",\"postalCode\":2950,\"city\":\"Kapellen\"}";

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(baseUrl + "/citizen/" + nationalNumber, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    void testGetAbbEmployee() {
        String nationalNumber = "75.01.14-012.34";
        String expectedResponse = "{\"nationalNumber\":\"75.01.14-012.34\",\"currentOccupation\":\"Burgemeester\",\"currentFunctionTitle\":\"Burgemeester\",\"currentGrade\":\"Burgemeester\",\"currentSalary\":\"\",\"career\":[{\"functionTitle\":\"Burgemeester\",\"grade\":\"Burgemeester\",\"from\":\"2020-09-01\",\"until\":null,\"percentage\":100.0,\"statute\":\"mandataris\"},{\"functionTitle\":\"Schepen\",\"grade\":\"Schepen\",\"from\":\"2010-09-01\",\"until\":\"2020-08-31\",\"percentage\":100.0,\"statute\":\"mandataris\"},{\"functionTitle\":\"Gemeentesecretaris\",\"grade\":\"1.C\",\"from\":\"2005-09-01\",\"until\":\"2010-08-31\",\"percentage\":100.0,\"statute\":\"vastbenoemd\"},{\"functionTitle\":\"Medewerker\",\"grade\":\"1.A\",\"from\":\"2000-09-01\",\"until\":\"2005-08-01\",\"percentage\":100.0,\"statute\":\"vastbenoemd\"}]}";

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(baseUrl + "/employee/" + nationalNumber, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    void testGetAbbCity() {
        String postalCode = "2950";
        String expectedResponse = "{\"postalCode\":2950,\"name\":\"Kapellen\",\"citizens\":27400}";

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(baseUrl + "/city/" + postalCode, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }
}