package com.demo.demo_sns.Config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.pinpoint.PinpointClient;

@Configuration
@Slf4j
public class pinPointConfiguration
{
    @Value("${cloud.aws.credentials.access-key}")
    private  String accessKey;
    @Value("${cloud.aws.credentials.secret-key}")
    private  String secretKey;
    @Value("${cloud.aws.region.static}")
    private  String region;
  @Bean
    public PinpointClient pinpointClient()
    {
        PinpointClient pinpointClient=PinpointClient.builder()
                .region(Region.EU_WEST_1).credentialsProvider(()-> AwsBasicCredentials.create(accessKey,secretKey))
                .build();
        System.out.println("Pinpoint Client Configuration:  "+pinpointClient);
        return pinpointClient;

    }
}
