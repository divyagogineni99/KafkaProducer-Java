package kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication // Enables autoconfiguration(@EnableAutoConfiguration), component scanning(@ComponentScan, additional configuration(@Configuration)
//The @EnableAutoConfiguration annotation enables Spring Boot to auto-configure the application context. Therefore, it automatically creates and registers beans based on both the included jar files in the classpath and the beans defined by us.
@EnableScheduling //This annotation should be added to one of your configuration classes to enable Spring's scheduled task execution capability.
public class KafkaproducerApplication {
	public static void main(String[] args) {
		SpringApplication.run(KafkaproducerApplication.class, args);

	}

}
