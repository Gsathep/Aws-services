package com.demo.demo_sns.Config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.pinpoint.PinpointClient;
import software.amazon.awssdk.services.pinpoint.model.*;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class PinPointEmail
{
    @Value("${cloud.aws.credentials.access-key}")
    private  String accessKey;
    @Value("${cloud.aws.credentials.secret-key}")
    private  String secretKey;
    PinpointClient pinpointClient = PinpointClient.builder()
            .region(Region.EU_WEST_1)
            .credentialsProvider(()-> AwsBasicCredentials.create(accessKey,secretKey))
            .build();


    private static final String CHARSET = "UTF-8";
    private static final  String HTML_BODY="<h1>Amazon Pinpoint test </h1>"
            +"This email was send through the <a href='https://aws.amazon.com/pinpoint/'>"
            +"Amazon Pinpoint</a>Email API using the "
            +"<a href='https://aws.amazon.com/sdk-for-java/'>AWS SDK for Java 2.x</a>";
    public void sendEmail(String subject,String appId,String senderAddress,String toAddress)
    {
        try
        {
            Map<String, AddressConfiguration>addressConfigurationMap=new HashMap<>();
            AddressConfiguration addressConfiguration= AddressConfiguration.builder()
                    .channelType(ChannelType.EMAIL)
                    .build();

            addressConfigurationMap.put(toAddress, addressConfiguration);
            SimpleEmailPart emailPart = SimpleEmailPart.builder()
                    .data(HTML_BODY)
                    .charset(CHARSET)
                    .build();

            SimpleEmailPart subjectPart = SimpleEmailPart.builder()
                    .data(subject)
                    .charset(CHARSET)
                    .build();

            SimpleEmail simpleEmail=SimpleEmail.builder()
                    .htmlPart(emailPart)
                    .subject(subjectPart)
                    .build();

            EmailMessage emailMessage=EmailMessage.builder()
                    .body(HTML_BODY)
                    .fromAddress(senderAddress)
                    .simpleEmail(simpleEmail)
                    .build();


            DirectMessageConfiguration directMessageConfiguration=DirectMessageConfiguration.builder()
                    .emailMessage(emailMessage)
                    .build();


            MessageRequest messageRequest= MessageRequest.builder()
                    .addresses(addressConfigurationMap)
                    .messageConfiguration(directMessageConfiguration)
                    .build();


            SendMessagesRequest sendMessagesRequest=SendMessagesRequest.builder()
                    .applicationId(appId)
                 .messageRequest(messageRequest)
                 .build();
            pinpointClient.sendMessages(sendMessagesRequest);
            System.out.println("message is send ");
            System.out.println(sendMessagesRequest+"is not send any email");
        }
        catch (PinpointException e)
        {
            System.out.println(e.awsErrorDetails().errorMessage());
            //e.printStackTrace();
            throw new RuntimeException("Error sending email through pinpoint",e);
        }
    }
}
