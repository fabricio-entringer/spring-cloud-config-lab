package dev.entringer.configclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class ConfigClientApplication {
    
    private static final Logger logger = LoggerFactory.getLogger(ConfigClientApplication.class);
    
    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }
    
    @EventListener(ApplicationStartedEvent.class)
    public void logActiveProfile() {
        String[] activeProfiles = environment.getActiveProfiles();
        String profileInfo = activeProfiles.length > 0 ? String.join(", ", activeProfiles) : "default";
        
        logger.info("==============================================");
        logger.info("Application is running with profile: {}", profileInfo);
        logger.info("==============================================");
    }
}