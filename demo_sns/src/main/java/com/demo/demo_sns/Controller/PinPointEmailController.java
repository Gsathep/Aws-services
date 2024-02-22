package com.demo.demo_sns.Controller;

import com.demo.demo_sns.Config.PinPointEmail;
import com.demo.demo_sns.entity.Pinpoint;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class PinPointEmailController
{
private   final PinPointEmail pinPointEmail;

    public PinPointEmailController(PinPointEmail pinPointEmail) {
        this.pinPointEmail = pinPointEmail;
    }
    @GetMapping("/test")
    public String Test()
    {
        return "Test Successfully";
    }
    @GetMapping("/send")
    public String sendEmail()
    {
        pinPointEmail.sendEmail("This is test mail","790f263fe89043da9566f8c39534c544","geetasathe1155@gmail.com","geetasathe1155@gmail.com");
        return "Email sent successfully!";
    }
    @PostMapping("/sendEmail")
    public String sendEmail(@RequestBody Pinpoint pinpoint) {

        String subject = pinpoint.getSubject();
        String apiKey = pinpoint.getApiKey();
        String fromEmail = pinpoint.getFromEmail();
        String toEmail = pinpoint.getToEmail();
        pinPointEmail.sendEmail(subject, apiKey, fromEmail, toEmail);
        return "Email sent successfully!";
    }

}
