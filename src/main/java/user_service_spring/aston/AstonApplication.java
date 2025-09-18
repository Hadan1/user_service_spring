package user_service_spring.aston;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AstonApplication {

	public static void main(String[] args) {
		SpringApplication.run(AstonApplication.class, args);
	}
}
