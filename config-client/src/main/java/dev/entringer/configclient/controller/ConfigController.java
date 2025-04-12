package dev.entringer.configclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.entringer.configclient.model.ApplicationConfig;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/config")
@RefreshScope
public class ConfigController {

    @Autowired
    private ApplicationConfig applicationConfig;
    
    @Autowired
    private Environment environment;
    
    @Value("${spring.application.name:config-client}")
    private String applicationName;
    
    @Value("${server.port:8080}")
    private String serverPort;

    @GetMapping
    public ResponseEntity<ApplicationConfig> getConfig() {
        return ResponseEntity.ok(applicationConfig);
    }
    
    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getConfigInfo() {
        Map<String, Object> response = new HashMap<>();
        response.put("applicationName", applicationName);
        response.put("serverPort", serverPort);
        response.put("activeProfiles", Arrays.asList(environment.getActiveProfiles()));
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/database")
    public ResponseEntity<ApplicationConfig.DatabaseConfig> getDatabaseConfig() {
        return ResponseEntity.ok(applicationConfig.getDatabase());
    }
    
    @GetMapping("/security")
    public ResponseEntity<ApplicationConfig.SecurityConfig> getSecurityConfig() {
        return ResponseEntity.ok(applicationConfig.getSecurity());
    }
    
    @GetMapping("/cache")
    public ResponseEntity<ApplicationConfig.CacheConfig> getCacheConfig() {
        return ResponseEntity.ok(applicationConfig.getCache());
    }
    
    @GetMapping("/integration")
    public ResponseEntity<ApplicationConfig.IntegrationConfig> getIntegrationConfig() {
        return ResponseEntity.ok(applicationConfig.getIntegration());
    }
}