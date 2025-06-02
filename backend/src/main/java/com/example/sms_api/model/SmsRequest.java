package com.example.sms_api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SmsRequest {
    
    @NotBlank(message = "Message is required and cannot be empty")
    @Size(max = 2000, message = "Message cannot exceed 2000 characters")
    private String message;
    
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Phone number must be a valid international format (e.g., +1234567890)")
    private String phoneNumber;
    
    // Default constructor
    public SmsRequest() {}
    
    // Constructor with parameters
    public SmsRequest(String message, String phoneNumber) {
        this.message = message;
        this.phoneNumber = phoneNumber;
    }
    
    // Getters and Setters
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    @Override
    public String toString() {
        return "SmsRequest{" +
                "message='" + message + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
} 