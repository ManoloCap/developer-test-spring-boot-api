package com.example.sms_api.service;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SmsServiceTest {

    private final SmsService smsService = new SmsService();

    /**
     * Test that a short message (under 160 characters) returns a single SMS part
     * and preserves the original message content exactly as provided.
     */
    @Test
    void shouldReturnSinglePartForShortMessage() {
        String message = "Hello World!";
        List<String> result = smsService.splitSms(message);
        assertEquals(1, result.size());
        assertEquals("Hello World!", result.get(0));
    }

    /**
     * Test that a long message (over 160 characters) gets split into multiple SMS parts
     * and each part respects the 160 character limit for standard SMS.
     */
    @Test
    void shouldSplitLongMessageIntoMultipleParts() {
        String longMessage = "A".repeat(300);
        List<String> result = smsService.splitSms(longMessage);
        assertTrue(result.size() > 1);
        assertTrue(result.get(0).length() <= 160);
    }
}
