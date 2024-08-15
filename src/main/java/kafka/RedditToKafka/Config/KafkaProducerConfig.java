package kafka.RedditToKafka.Config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;


//Real-World Analogy (CONFIG FILE IN SPRING)
//  Imagine you are setting up a workshop. You need different tools (like a hammer, screwdriver, etc.) to do your work.
//    Configuration (@Configuration): Think of this as a list where you write down what tools you have.
//    Bean (@Bean): This is you saying, "I have a hammer, and it's this specific one." You put the hammer in a known place.

//  When you need a hammer, you don't create a new one each time.
// Instead, you just go to the place where you stored it.
// Similarly, Spring goes to the bean that you defined and uses it whenever it's needed.


//Configuration Class: Sets up the beans for Kafka producer and template.
//Property Value: Reads Kafka server address from the properties file.
//Producer Factory Bean: Configures the Kafka producer.
//Kafka Template Bean: Makes it easy to send messages to Kafka.

@Configuration
//Think of @Configuration as a marker that tells Spring, "Hey, this class is going to define some beans that you need to know about."
//        It makes this class a source of bean definitions.
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    // @Value :- Reads a value from the application's/system's properties file, specifically the Kafka server address.
    private String bootstrapServers;

    @Bean
    //@Bean is another marker. It tells Spring, "This method will return an object that you should manage as a bean."
    //When Spring sees @Bean, it knows that it needs to run the method and keep track of the object it returns.

    //The ProducerFactory class in Spring Kafka is responsible for creating and configuring Kafka producers
    //ProducerFactory creates instances of Kafka producers (KafkaProducer) based on the configuration provided.
    // It encapsulates the producer configurations and manages the lifecycle of producer instances.

    //To configure a ProducerFactory, you typically provide a map of configuration properties.
    // These properties include details like the Kafka server address, serializers for keys and values, and other producer-specific settings.
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        //DefaultKafkaProducerFactory: A common implementation that uses a map of configuration properties to create producers.
        return new DefaultKafkaProducerFactory<>(configProps);
        //Purpose: Creates and configures a Kafka producer.
        //ConfigProps Map: Holds settings for the producer:
        //
        //    BOOTSTRAP_SERVERS_CONFIG: Sets the address of the Kafka server.
        //    KEY_SERIALIZER_CLASS_CONFIG and VALUE_SERIALIZER_CLASS_CONFIG: Define how keys and values of messages will be converted to byte data before sending.
        //
        //Return: A new DefaultKafkaProducerFactory with the configuration.
    }

    @Bean
    //KafkaTemplate is a class provided by Spring Kafka that simplifies the process of sending messages to Apache Kafka topics.
    // It serves as a high-level abstraction for Kafka producers, making it easier to interact with Kafka in a Spring application.

    //To use KafkaTemplate, you typically need to configure a ProducerFactory bean that provides the settings for Kafka producers (e.g., bootstrap servers, serializers).
    // KafkaTemplate uses this factory to create Kafka producers under the hood.

    //KafkaTemplate is a Spring Kafka class used for sending messages to Kafka topics.
    //It abstracts the complexity of Kafka producer APIs.
    //It is configured using a ProducerFactory and can be used to send messages with various methods.
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}

