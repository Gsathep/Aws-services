package com.demo.demo_sns.Controller;

import com.demo.demo_sns.Service.MailService;
import com.demo.demo_sns.entity.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

@RestController
public class MailController {
    @Autowired
    private MailService mailService;

    @GetMapping("/sendmail")
    public String sendMessage(@RequestParam(value = "fromEmail") String fromEmail,
                              @RequestParam(value = "toEmail") String toEmail,
                              @RequestParam(value = "subject") String subject,
                              @RequestParam(value = "body") String body) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromEmail);
        simpleMailMessage.setReplyTo(toEmail);
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);
        mailService.sendMessage(simpleMailMessage);
        return "mail send successfully";
    }

    @PostMapping("/sendEmail")
    public String sendMessage(@RequestBody EmailDetails emailDetails) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(emailDetails.getFromEmail());
        simpleMailMessage.setTo(emailDetails.getToEmail());
        simpleMailMessage.setSubject(emailDetails.getSubject());
        simpleMailMessage.setText(emailDetails.getBody());
        mailService.sendMessage(simpleMailMessage);

        return "Email sent successfully";
    }
}
