package com.InfluencerMarketpalce.serverside;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import com.InfluencerMarketpalce.serverside.property.FileStorageProperties;

@SpringBootApplication
//@EnableConfigurationProperties({
//		FileStorageProperties.class
//})
public class ServersideApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServersideApplication.class, args);
                System.out.println("Backend sudah berjalan!");
	}

}
