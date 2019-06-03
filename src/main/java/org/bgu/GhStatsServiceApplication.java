package org.bgu;

import org.bgu.config.annotation.TheAppStarter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@TheAppStarter
@EnableDiscoveryClient
@SpringBootApplication
public class GhStatsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GhStatsServiceApplication.class, args);
	}

}
