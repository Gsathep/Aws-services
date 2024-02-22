package com.demo.demo_sns.Config;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static com.amazonaws.regions.Regions.EU_WEST_1;

@Configuration
public class AmazonSNSConfiguration {
    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Primary
    @Bean
    public AmazonSNSClient amazonSNSClient()
    {
        BasicAWSCredentials basicAWSCredentials=new BasicAWSCredentials(accessKey,secretKey);
        return (AmazonSNSClient) AmazonSNSClientBuilder
                .standard()
                .withRegion(EU_WEST_1)
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .build();
    }


}