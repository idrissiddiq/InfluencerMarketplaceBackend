package com.InfluencerMarketpalce.serverside;

import com.InfluencerMarketpalce.serverside.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import com.InfluencerMarketpalce.serverside.property.FileStorageProperties;

@SpringBootApplication
public class ServersideApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServersideApplication.class, args);
		Logger logger= LoggerFactory.getLogger(ServersideApplication.class);
		logger.info("Backend sudah berjalan!");
	}

}
