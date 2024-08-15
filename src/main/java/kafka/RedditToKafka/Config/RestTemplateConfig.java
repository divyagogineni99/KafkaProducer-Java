package kafka.RedditToKafka.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    //restTemplate: It is a Spring framework class used for making HTTP requests.
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

