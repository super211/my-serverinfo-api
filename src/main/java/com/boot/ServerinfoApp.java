package com.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties
public class ServerinfoApp extends SpringBootServletInitializer
{
	private static final Logger log = LoggerFactory.getLogger(ServerinfoApp.class);
    public static void main( String[] args )
    {
    	log.info("Start Application Context REST");
		ConfigurableApplicationContext context = SpringApplication.run(ServerinfoApp.class, args);
		log.info("Started context "+ context.getApplicationName());
    }
    
}
