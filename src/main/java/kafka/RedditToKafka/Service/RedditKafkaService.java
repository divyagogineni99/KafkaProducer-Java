// Package declaration
package kafka.RedditToKafka.Service;

// Necessary imports
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// Mark this class as a service
@Service
//The @Service annotation holds the business logic of the application.
//The @Service annotation is a specialization of the @Component annotation,
//which allows Spring to automatically detect it through classpath scanning and register it as a Spring bean.
public class RedditKafkaService {

    // Logger for logging messages
    private static final Logger logger = LoggerFactory.getLogger(RedditKafkaService.class);

    // Inject RestTemplate and KafkaTemplate dependencies
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    // Constants
    private static final String TOPIC = "reddit-posts";
    private static final String REDDIT_API_URL = "https://www.reddit.com/r/all/hot.json?limit=10";


    // Method to fetch data from Reddit and send to Kafka
    @Scheduled(fixedRate = 6000) // Schedule to run every 1 minute (60000 ms)
    // few other are :- fixedDelay = 5000, initialDelay = 1000
    //fixed delay of 5000 milliseconds (5 seconds) after the previous execution completes.
    //The first execution will start after an initial delay of 1000 milliseconds (1 second).
    public void fetchAndSendPosts() {

        // Log the start of the method
        logger.info("Starting to fetch posts from Reddit and send to Kafka");

        // Fetch data from Reddit

        // 1) restTemplate: It is a Spring framework class used for making HTTP requests.
        // In this case, it is used to send an HTTP GET request to an API and retrieve data.

        // 2)getForObject:
        //    Method of RestTemplate: This method is used to send an HTTP GET request to a specified URL and convert the response into a Java object.
        //    Parameters:
        //        URL: The URL to which the GET request is sent.
        //        Response Type: The class type into which the response should be converted.

        RedditResponse response = restTemplate.getForObject(REDDIT_API_URL, RedditResponse.class);

        // Check if the response is not null and has data
        if (response != null && response.getData() != null) {

            // Log the number of posts fetched
            logger.info("Fetched {} posts from Reddit", response.getData().getChildren().size());

            // Iterate through each post
            response.getData().getChildren().forEach(child -> {
                // Get the title and URL of the post
                String title = child.getData().getTitle();
                String url = child.getData().getUrl();

                // Log the title and URL of the post
                logger.info("Sending post to Kafka: {} - {}", title, url);

                // Send the title and URL to Kafka
                //KafkaTemplate is a class provided by Spring Kafka that simplifies the process of sending messages to Apache Kafka topics.
                //It serves as a high-level abstraction for Kafka producers, making it easier to interact with Kafka in a Spring application.
                kafkaTemplate.send(TOPIC, title + " - " + url);
            });
        }else {
            // Log if no posts were fetched
            logger.warn("No posts fetched from Reddit or response is null");
        }

        // Log the end of the method
        logger.info("Finished fetching posts from Reddit and sending to Kafka");

    }
}

//Service Class: Manages the business logic for fetching Reddit posts and sending them to Kafka.
//Dependencies: RestTemplate for making HTTP requests, KafkaTemplate for sending messages to Kafka.
//Constants: Defines the Kafka topic and Reddit API URL.
//Method: Fetches the latest hot posts from Reddit and sends their titles and URLs to Kafka.