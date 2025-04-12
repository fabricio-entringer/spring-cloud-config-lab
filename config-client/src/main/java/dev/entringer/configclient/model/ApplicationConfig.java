package dev.entringer.configclient.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "application")
@RefreshScope
@Data
public class ApplicationConfig {
    private String name;
    private String description;
    private String environment;
    private DatabaseConfig database = new DatabaseConfig();
    private SecurityConfig security = new SecurityConfig();
    private CacheConfig cache = new CacheConfig();
    private IntegrationConfig integration = new IntegrationConfig();
    
    @Data
    public static class DatabaseConfig {
        private String url;
        private String username;
        private String password;
        private int maxPoolSize;
        private int minIdle;
        private long connectionTimeout;
        private boolean showSql;
    }
    
    @Data
    public static class SecurityConfig {
        private String tokenSecret;
        private long tokenExpirationMs;
        private String allowedOrigins;
        private boolean enableCsrf;
    }
    
    @Data
    public static class CacheConfig {
        private boolean enabled;
        private long timeToLiveSeconds;
        private int maxSize;
    }
    
    @Data
    public static class IntegrationConfig {
        private String apiUrl;
        private long timeout;
        private int maxRetries;
        private boolean enabled;
    }
}