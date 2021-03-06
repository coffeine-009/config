package com.thecoffeine.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Configuration service for micro-services.
 *
 * @version 1.0
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class ConfigApplication {

    /**
     * Entry point.
     *
     * @param args  Application arguments.
     */
    public static void main( final String [] args ) {
        SpringApplication.run( ConfigApplication.class, args );
    }
}
