package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataService
{
    private static final Logger logger = LoggerFactory.getLogger(DataService.class);

    // https://www.baeldung.com/spring-boot-evict-cache

    @CachePut(value = "media", key="#key")
    public String updateMedia(String key)
    {
        retrieveDataFromService(key);
        return String.format("%s -> %s", key, UUID.randomUUID().toString());
    }

    @CacheEvict(value = "media", key = "#key")
    public void evictMedia(String key)
    {
        // Nothing
    }

    @Cacheable("media")
    public String getMediaById(String key)
    {
        retrieveDataFromService(key);
        return String.format("%s -> %s", key, UUID.randomUUID().toString());
    }

    private void retrieveDataFromService(String key)
    {
        // TODO: Would lookup data here expensively - simulate with a 'pause'
        logger.info("    DS Query for key:"+key);
        try
        {
            Thread.sleep(4000);
        } catch (InterruptedException ignored)
        {
        }
    }
}
