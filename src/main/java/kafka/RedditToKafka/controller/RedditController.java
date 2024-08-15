package kafka.RedditToKafka.controller;

import kafka.RedditToKafka.Service.RedditKafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController is a special label (annotation) in Spring that you put on a Java class to tell Spring:
// "This class is going to handle web requests and send back data directly."
//It combines two other labels (@Controller and @ResponseBody)
//When you use @RestController, Spring automatically converts your Java objects to JSON (or XML) to send back to the web browser or application.
@RestController

@RequestMapping("/api/reddit")
public class RedditController {

    private final RedditKafkaService redditKafkaService;

    // Constructor Injection
    @Autowired
    public RedditController(RedditKafkaService redditKafkaService) {
        this.redditKafkaService = redditKafkaService;
    }
    @GetMapping("/fetch")
    public String fetchAndSendPosts() {
        redditKafkaService.fetchAndSendPosts();
        return "Posts sent to Kafka successfully!";
    }
}

