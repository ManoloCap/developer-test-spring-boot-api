package com.example.sms_api.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SmsService {
    
    private static final int SMS_MAX_LENGTH = 160;
    private static final int SMS_UNICODE_MAX_LENGTH = 70;
    
    // V1.0.0 - Basic SMS splitting
    public List<String> splitSms(String message) {
        
        System.out.println("API Version: 1.0.0");
        
        if (message == null || message.isEmpty()) {
            return new ArrayList<>();
        }
        
        List<String> parts = new ArrayList<>();
        int maxLength = containsUnicode(message) ? SMS_UNICODE_MAX_LENGTH : SMS_MAX_LENGTH;
        
        if (message.length() <= maxLength) {
            parts.add(message);
            return parts;
        }
        
        // Simple split without part numbering for v1.0.0
        int start = 0;
        while (start < message.length()) {
            int end = Math.min(start + maxLength, message.length());
            parts.add(message.substring(start, end));
            start = end;
        }
        
        return parts;
    }
    
    // V1.1.0 - Enhanced SMS splitting with part numbering
    public List<String> splitSmsWithParts(String message) {
        
        System.out.println("API Version: 1.1.0");
        
        if (message == null || message.isEmpty()) {
            return new ArrayList<>();
        }
        
        List<String> parts = new ArrayList<>();
        int maxLength = containsUnicode(message) ? SMS_UNICODE_MAX_LENGTH : SMS_MAX_LENGTH;
        
        if (message.length() <= maxLength) {
            parts.add(message);
            return parts;
        }
        
        // Split with part numbering using "... - Part X of Y" format (except last part)
        int start = 0;
        int partNumber = 1;
        String partSuffixWithDots = "... - Part %d of %d";
        String partSuffixLastPart = " - Part %d of %d";
        int suffixLength = String.format(partSuffixWithDots, 1, 99).length(); // Reserve space for suffix
        int totalParts = (int) Math.ceil((double) message.length() / (maxLength - suffixLength));
        
        while (start < message.length()) {
            boolean isLastPart = partNumber == totalParts;
            String currentPartInfo = String.format(
                isLastPart ? partSuffixLastPart : partSuffixWithDots, 
                partNumber, totalParts
            );
            int availableLength = maxLength - currentPartInfo.length();
            
            int end = Math.min(start + availableLength, message.length());
            
            // Try to break at word boundary
            if (end < message.length() && message.charAt(end) != ' ') {
                int lastSpace = message.lastIndexOf(' ', end);
                if (lastSpace > start) {
                    end = lastSpace;
                }
            }
            
            String part = message.substring(start, end) + currentPartInfo;
            parts.add(part);
            
            start = end;
            if (start < message.length() && message.charAt(start) == ' ') {
                start++; // Skip the space
            }
            partNumber++;
        }
        
        return parts;
    }
    
    private boolean containsUnicode(String message) {
        return message.chars().anyMatch(ch -> ch > 127);
    }
} 