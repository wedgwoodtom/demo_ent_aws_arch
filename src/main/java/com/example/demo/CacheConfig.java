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

// CacheConfig that, based on the profile configured in application.properties, will configure the
//  actual Elasticache in AWS or a local cache.  This is necessary as only EC2 instances can connect
//  to Elasticache which prevents our local dev boxes from doing so without considerable manual setup
//  of a proxy/etc - this seems more maintainable.  As a result, elasticache will only be used when
//  deployed to AWS.
//
// See https://github.com/spring-cloud-samples/aws-refapp/blob/master/src/main/java/org/springframework/cloud/aws/sample/ReferenceApplication.java
//   or can use this: https://github.com/sixhours-team/memcached-spring-boot
//
@Configuration
public class CacheConfig
{
    // Local DEV cache config (no Elasticache)
    @Configuration
    @EnableCaching
    @Profile("local")
    protected static class LocalCacheConfiguration {
        @Bean
        public CacheManager createSimpleCacheManager() {
            SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
            List<Cache> caches = new ArrayList<>(1);
            caches.add(new ConcurrentMapCache("media"));
            simpleCacheManager.setCaches(caches);

            return simpleCacheManager;
        }
    }

    // AWS cache config with Elasticache - autoconfigures the client and looks up the cache by name (that's awesome)
    @Configuration
    @EnableElastiCache({
        @CacheClusterConfig(name = "media", expiration = 30)
    })
    @Profile("!local")
    protected static class ElastiCacheConfiguration {
        // No need to do this manually - The built-in manage does it already
//        @Bean
//        public CacheManager createSimpleCacheManager()
//        {
//            SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
//            List<Cache> caches = new ArrayList<>(1);
//
//            MemcachedCacheFactory factory = new MemcachedCacheFactory();
//            try
//            {
//                SimpleSpringMemcached memCache = factory.createCache("media", "media.sodexk.cfg.usw2.cache.amazonaws.com", 11211);
//                caches.add(memCache);
//                simpleCacheManager.setCaches(caches);
//            }
//            catch (Exception e)
//            {
//                throw new RuntimeException(e);
//            }
//
//            return simpleCacheManager;
//        }
    }


}
