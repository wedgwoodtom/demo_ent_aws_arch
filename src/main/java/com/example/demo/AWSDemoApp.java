package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.cache.ElastiCacheAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;


//@SpringBootApplication
// Note: These non-standard-ish annotations are so that we can run locally with Elasticache
@EnableAutoConfiguration(exclude = ElastiCacheAutoConfiguration.class)
@Configuration
@ComponentScan
@EnableScheduling
public class AWSDemoApp
{
    public static void main(String[] args)
    {
        SpringApplication.run(AWSDemoApp.class, args);
    }
}
