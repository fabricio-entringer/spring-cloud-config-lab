package dev.entringer.configclient.model;

import lombok.Data;

@Data
public class ApplicationConfigDTO {
    private String name;
    private String description;
    private String environment;
    private DatabaseConfigDTO database;
    private SecurityConfigDTO security;
    private CacheConfigDTO cache;
    private IntegrationConfigDTO integration;
    
    @Data
    public static class DatabaseConfigDTO {
        private String url;
        private String username;
        private String password;
        private int maxPoolSize;
        private int minIdle;
        private long connectionTimeout;
        private boolean showSql;
    }
    
    @Data
    public static class SecurityConfigDTO {
        private String tokenSecret;
        private long tokenExpirationMs;
        private String allowedOrigins;
        private boolean enableCsrf;
    }
    
    @Data
    public static class CacheConfigDTO {
        private boolean enabled;
        private long timeToLiveSeconds;
        private int maxSize;
    }
    
    @Data
    public static class IntegrationConfigDTO {
        private String apiUrl;
        private long timeout;
        private int maxRetries;
        private boolean enabled;
    }
    
    public static ApplicationConfigDTO from(ApplicationConfig config) {
        if (config == null) {
            return null;
        }
        
        ApplicationConfigDTO dto = new ApplicationConfigDTO();
        dto.setName(config.getName());
        dto.setDescription(config.getDescription());
        dto.setEnvironment(config.getEnvironment());
        
        // Map database config
        if (config.getDatabase() != null) {
            DatabaseConfigDTO dbDto = new DatabaseConfigDTO();
            dbDto.setUrl(config.getDatabase().getUrl());
            dbDto.setUsername(config.getDatabase().getUsername());
            dbDto.setPassword(config.getDatabase().getPassword());
            dbDto.setMaxPoolSize(config.getDatabase().getMaxPoolSize());
            dbDto.setMinIdle(config.getDatabase().getMinIdle());
            dbDto.setConnectionTimeout(config.getDatabase().getConnectionTimeout());
            dbDto.setShowSql(config.getDatabase().isShowSql());
            dto.setDatabase(dbDto);
        }
        
        // Map security config
        if (config.getSecurity() != null) {
            SecurityConfigDTO secDto = new SecurityConfigDTO();
            secDto.setTokenSecret(config.getSecurity().getTokenSecret());
            secDto.setTokenExpirationMs(config.getSecurity().getTokenExpirationMs());
            secDto.setAllowedOrigins(config.getSecurity().getAllowedOrigins());
            secDto.setEnableCsrf(config.getSecurity().isEnableCsrf());
            dto.setSecurity(secDto);
        }
        
        // Map cache config
        if (config.getCache() != null) {
            CacheConfigDTO cacheDto = new CacheConfigDTO();
            cacheDto.setEnabled(config.getCache().isEnabled());
            cacheDto.setTimeToLiveSeconds(config.getCache().getTimeToLiveSeconds());
            cacheDto.setMaxSize(config.getCache().getMaxSize());
            dto.setCache(cacheDto);
        }
        
        // Map integration config
        if (config.getIntegration() != null) {
            IntegrationConfigDTO intDto = new IntegrationConfigDTO();
            intDto.setApiUrl(config.getIntegration().getApiUrl());
            intDto.setTimeout(config.getIntegration().getTimeout());
            intDto.setMaxRetries(config.getIntegration().getMaxRetries());
            intDto.setEnabled(config.getIntegration().isEnabled());
            dto.setIntegration(intDto);
        }
        
        return dto;
    }
}