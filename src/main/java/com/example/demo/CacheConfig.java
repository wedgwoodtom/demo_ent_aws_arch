package com.example.demo;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.cloud.aws.cache.config.annotation.CacheClusterConfig;
import org.springframework.cloud.aws.cache.config.annotation.EnableElastiCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

@Configuration
//@EnableElastiCache({
//    @CacheClusterConfig(name = "media", expiration = 30)
//})
public class CacheConfig
{
    // TODO
    // See https://github.com/spring-cloud-samples/aws-refapp/blob/master/src/main/java/org/springframework/cloud/aws/sample/ReferenceApplication.java
    // For an example as to how to support local dev as well as EC2.

    @Configuration
    @EnableElastiCache({
        @CacheClusterConfig(name = "media", expiration = 30)
    })
    @Profile("!local")
    protected static class ElastiCacheConfiguration {
    }


//    @Bean
//    public CacheManager createSimpleCacheManager()
//    {
//        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
//        List<Cache> caches = new ArrayList<>(1);
//        caches.add(new ConcurrentMapCache("media"));
//        simpleCacheManager.setCaches(caches);
//
//        return simpleCacheManager;
//    }




}
