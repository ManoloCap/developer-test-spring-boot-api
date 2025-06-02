package com.example.sms_api.controller;

import com.example.sms_api.model.SmsRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SmsControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Test that a valid SMS request with proper message and phone number
     * returns HTTP 200 OK and produces an array of SMS parts.
     */
    @Test
    void shouldSendSmsSuccessfully() {
        SmsRequest request = new SmsRequest("Hello World!", "+1234567890");
        
        ResponseEntity<String[]> response = restTemplate.postForEntity(
            "/api/sms/send", 
            request, 
            String[].class
        );
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length >= 1);
    }

    /**
     * Test that an invalid SMS request with empty message triggers validation
     * and returns HTTP 400 Bad Request due to validation constraints.
     */
    @Test
    void shouldReturnBadRequestForInvalidData() {
        SmsRequest request = new SmsRequest("", "+1234567890");
        
        ResponseEntity<String> response = restTemplate.postForEntity(
            "/api/sms/send", 
            request, 
            String.class
        );
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
} 