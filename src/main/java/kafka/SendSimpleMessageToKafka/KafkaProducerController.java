//package kafka.SendSimpleMessageToKafka;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class KafkaProducerController {
//
//    @Autowired
//    private KafkaProducerService producerService;
//
//    @GetMapping("/send")
//    public String sendMessageToKafka(@RequestParam("message") String message) {
//        producerService.sendMessage(message);
//        return "Message sent to Kafka topic";
//    }
//}
//
