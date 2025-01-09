package com.example.project.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class CacheManagerConfiguration {
    @Bean
    public CacheManager getNoOpCacheManager() {
        return new NoOpCacheManager();
    }
}
