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
import dev.entringer.configclient.model.ApplicationConfigDTO;

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
    public ResponseEntity<Map<String, Object>> getConfig() {
        Map<String, Object> response = new HashMap<>();
        
        // Add profile information at the top level
        String[] activeProfiles = environment.getActiveProfiles();
        String profileInfo = activeProfiles.length > 0 ? String.join(", ", activeProfiles) : "default";
        response.put("activeProfile", profileInfo);
        response.put("applicationName", applicationName);
        
        // Add the application configuration
        response.put("config", ApplicationConfigDTO.from(applicationConfig));
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getConfigInfo() {
        Map<String, Object> response = new HashMap<>();
        response.put("applicationName", applicationName);
        response.put("serverPort", serverPort);
        
        // Enhanced profile information
        String[] activeProfiles = environment.getActiveProfiles();
        String profileInfo = activeProfiles.length > 0 ? String.join(", ", activeProfiles) : "default";
        response.put("activeProfile", profileInfo);
        response.put("activeProfiles", Arrays.asList(activeProfiles));
        response.put("environmentDescription", applicationConfig.getEnvironment());
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/profile")
    public ResponseEntity<Map<String, Object>> getProfileInfo() {
        Map<String, Object> response = new HashMap<>();
        String[] activeProfiles = environment.getActiveProfiles();
        String profileInfo = activeProfiles.length > 0 ? String.join(", ", activeProfiles) : "default";
        
        response.put("activeProfile", profileInfo);
        response.put("environmentDescription", applicationConfig.getEnvironment());
        response.put("applicationName", applicationConfig.getName());
        response.put("applicationDescription", applicationConfig.getDescription());
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/database")
    public ResponseEntity<ApplicationConfigDTO.DatabaseConfigDTO> getDatabaseConfig() {
        ApplicationConfigDTO dto = ApplicationConfigDTO.from(applicationConfig);
        return ResponseEntity.ok(dto.getDatabase());
    }
    
    @GetMapping("/security")
    public ResponseEntity<ApplicationConfigDTO.SecurityConfigDTO> getSecurityConfig() {
        ApplicationConfigDTO dto = ApplicationConfigDTO.from(applicationConfig);
        return ResponseEntity.ok(dto.getSecurity());
    }
    
    @GetMapping("/cache")
    public ResponseEntity<ApplicationConfigDTO.CacheConfigDTO> getCacheConfig() {
        ApplicationConfigDTO dto = ApplicationConfigDTO.from(applicationConfig);
        return ResponseEntity.ok(dto.getCache());
    }
    
    @GetMapping("/integration")
    public ResponseEntity<ApplicationConfigDTO.IntegrationConfigDTO> getIntegrationConfig() {
        ApplicationConfigDTO dto = ApplicationConfigDTO.from(applicationConfig);
        return ResponseEntity.ok(dto.getIntegration());
    }
}