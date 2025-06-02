package com.example.sms_api.controller;

import com.example.sms_api.config.ApiVersionConfig;
import com.example.sms_api.model.SmsRequest;
import com.example.sms_api.service.SmsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SmsController {

    private final SmsService smsService;
    private final ApiVersionConfig apiVersionConfig;

    public SmsController(SmsService smsService, ApiVersionConfig apiVersionConfig) {
        this.smsService = smsService;
        this.apiVersionConfig = apiVersionConfig;
    }


    //V1.0.0 - Basic SMS splitting
    //Simple message splitting without part numbering
    @PostMapping(value = "/api/sms/send", headers = "X-API-Version=1.0.0")
    public ResponseEntity<List<String>> sendSmsV1_0_0(@Valid @RequestBody SmsRequest smsRequest) {
        List<String> parts = smsService.splitSms(smsRequest.getMessage());
        parts.forEach(System.out::println); // Print to console as indicated in the test
        return ResponseEntity.ok(parts);
    }


    //V1.1.0 - Enhanced SMS splitting (minor change)
    //Added part numbering functionality
    @PostMapping(value = "/api/sms/send", headers = "X-API-Version=1.1.0")
    public ResponseEntity<List<String>> sendSmsV1_1_0(@Valid @RequestBody SmsRequest smsRequest) {
        List<String> parts = smsService.splitSmsWithParts(smsRequest.getMessage());
        parts.forEach(System.out::println);
        return ResponseEntity.ok(parts);
    }


    //Default version (when no header is provided) - uses configured default version
    @PostMapping("/api/sms/send")
    public ResponseEntity<List<String>> sendSmsDefault(@Valid @RequestBody SmsRequest smsRequest) {
        List<String> parts = smsService.splitSmsWithParts(smsRequest.getMessage());
        parts.forEach(System.out::println);
        return ResponseEntity.ok(parts);
    }


    //Health check endpoints for each version
    @GetMapping(value = "/api/sms/health", headers = "X-API-Version=1.0.0")
    public ResponseEntity<String> healthV1_0_0() {
        return ResponseEntity.ok("SMS API v1.0.0 is running - Basic splitting");
    }

    @GetMapping(value = "/api/sms/health", headers = "X-API-Version=1.1.0")
    public ResponseEntity<String> healthV1_1_0() {
        return ResponseEntity.ok("SMS API v1.1.0 is running - With part numbering");
    }

    @GetMapping("/api/sms/health")
    public ResponseEntity<String> healthDefault() {
        return ResponseEntity.ok("SMS API is running - Default version (" + apiVersionConfig.getDefaultVersion() + ")");
    }


    //API version information endpoint
    @GetMapping("/api/sms/versions")
    public ResponseEntity<ApiVersions> getVersions() {
        return ResponseEntity.ok(new ApiVersions(
            apiVersionConfig.getSupported(),
            apiVersionConfig.getDefaultVersion(),
            apiVersionConfig.getLatest()
        ));
    }

    // Response DTOs
    public static class ApiVersions {
        private String[] supportedVersions;
        private String currentVersion;
        private String latestVersion;
        private String[] deprecatedVersions = {};
        
        public ApiVersions(String[] supportedVersions, String currentVersion, String latestVersion) {
            this.supportedVersions = supportedVersions;
            this.currentVersion = currentVersion;
            this.latestVersion = latestVersion;
        }
        
        public String[] getSupportedVersions() { return supportedVersions; }
        public String getCurrentVersion() { return currentVersion; }
        public String getLatestVersion() { return latestVersion; }
        public String[] getDeprecatedVersions() { return deprecatedVersions; }
    }
}
