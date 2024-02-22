package com.demo.demo_sns.Controller;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SNSController {
    @Autowired
    private AmazonSNSClient amazonSNSClient;


    private static final String TOPIC_ARN = "arn:aws:sns:eu-west-1:985593072322:KidzCubicle";

    @GetMapping("/subscribe/{phoneNumber}")
    public String subscribeToSNSTopic(@PathVariable("phoneNumber") String phoneNumber) {
        SubscribeRequest Request = new SubscribeRequest(TOPIC_ARN, "SMS", phoneNumber);
        amazonSNSClient.subscribe(Request);
        return "phoneNumber subscription successful" + phoneNumber;

    }

    @GetMapping("/sendNotification")
    public String publishMessageToTopic() {
        PublishRequest publishRequest = new PublishRequest(TOPIC_ARN, "publishMessageToTopics", "Notification: Network connectivity issue");
        amazonSNSClient.publish(publishRequest);
        return "Notification send successfully !!";
    }

}
