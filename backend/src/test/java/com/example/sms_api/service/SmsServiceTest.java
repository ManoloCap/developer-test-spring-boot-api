package com.example.sms_api.service;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SmsServiceTest {

    private final SmsService smsService = new SmsService();

    @Test
    void shouldReturnSinglePartForShortMessage() {
        String message = "Hello World!";
        List<String> result = smsService.splitSms(message);
        assertEquals(1, result.size());
        assertEquals("Hello World!", result.get(0));
    }

    @Test
    void shouldSplitLongMessageIntoMultipleParts() {
        String longMessage = "A".repeat(300);
        List<String> result = smsService.splitSms(longMessage);
        assertTrue(result.size() > 1);
        assertTrue(result.get(0).length() <= 160);
    }
}
