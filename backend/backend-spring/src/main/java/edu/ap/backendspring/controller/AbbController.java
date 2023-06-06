package edu.ap.backendspring.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/abb")
public class AbbController {

        private final RestTemplate restTemplate;

        public AbbController(RestTemplate restTemplate) {
            this.restTemplate = restTemplate;
        }

        @GetMapping("")
        public String getAbbInformation() {
            String apiUrl = "http://projectvm27.p.bletchley.cloud:10079/";
            String response = restTemplate.getForObject(apiUrl, String.class);
            return response;
        }

    @GetMapping("/citizen/{nationalNumber}")
    public String getAbbCitizen(@PathVariable("nationalNumber") String nationalNumber) {
        String apiUrl = "http://projectvm27.p.bletchley.cloud:10079/citizen/" + nationalNumber;
        String response = restTemplate.getForObject(apiUrl, String.class);
        return response;
    }

    @GetMapping("/employee/{nationalNumber}")
    public String getAbbEmployee(@PathVariable("nationalNumber") String nationalNumber) {
        String apiUrl = "http://projectvm27.p.bletchley.cloud:10079/employee/" + nationalNumber;
        String response = restTemplate.getForObject(apiUrl, String.class);
        return response;
    }

    @GetMapping("/city/{postalCode}")
    public String getAbbCity(@PathVariable("postalCode") String postalCode) {
        String apiUrl = "http://projectvm27.p.bletchley.cloud:10079/city/" + postalCode;
        String response = restTemplate.getForObject(apiUrl, String.class);
        return response;
    }
    }
