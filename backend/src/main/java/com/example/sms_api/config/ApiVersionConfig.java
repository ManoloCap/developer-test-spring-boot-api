package com.example.sms_api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "api.version")
public class ApiVersionConfig {
    
    private String defaultVersion;
    private String[] supported;
    private String latest;
    
    // Getters and Setters
    public String getDefaultVersion() {
        return defaultVersion;
    }
    
    public void setDefault(String defaultVersion) {
        this.defaultVersion = defaultVersion;
    }
    
    public String[] getSupported() {
        return supported;
    }
    
    public void setSupported(String[] supported) {
        this.supported = supported;
    }
    
    public String getLatest() {
        return latest;
    }
    
    public void setLatest(String latest) {
        this.latest = latest;
    }
} 