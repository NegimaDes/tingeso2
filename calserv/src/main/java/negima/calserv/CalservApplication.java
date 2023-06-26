package negima.calserv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CalservApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalservApplication.class, args);
	}

}
