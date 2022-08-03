package com.techsophy.tsf.wrapperservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.CURRENT_PROJECT;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.MULTITENANCY_PROJECT;

@SpringBootApplication
@RefreshScope
@ComponentScan({CURRENT_PROJECT,MULTITENANCY_PROJECT})
public class TechsophyPlatformUXApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(TechsophyPlatformUXApplication.class, args);
	}
}
