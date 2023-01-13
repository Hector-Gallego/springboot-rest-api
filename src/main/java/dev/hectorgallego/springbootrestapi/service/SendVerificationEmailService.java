package dev.hectorgallego.springbootrestapi.service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import dev.hectorgallego.springbootrestapi.model.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class SendVerificationEmailService {


    @Value("${spring.mail.username}")
    private String fromAdress;

    private JavaMailSender javaMailSender;
    private TemplateEngine templateEngine;


    public SendVerificationEmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {

        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine; 

    }



    public void sendVerificationEmail(User user, String url) throws UnsupportedEncodingException, MessagingException{

        String toAdress = user.getEmail();
        String sendName = "codigo con cafe";
        String subject = "Confirmación de correo eléctronico";
        String verifyUrl = url + "/api/verification/" + user.getTokenVerificaton();
        
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        Context context = new Context();

        Map<String, Object> properties = new HashMap<>();
        properties.put("name", user.getFirstName());
        properties.put("verifyUrl", verifyUrl);

        context.setVariables(properties);

        String html = templateEngine.process("email.html", context);

        helper.setFrom(fromAdress, sendName);
        helper.setTo(toAdress);
        helper.setSubject(subject);
        helper.setText(html, true);

        javaMailSender.send(message);
        
        

    }
    


    
    
}
