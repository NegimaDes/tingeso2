package negima.provserv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProvservApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProvservApplication.class, args);
	}

}
