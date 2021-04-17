package com.danylko.yourburger.mail;

import com.danylko.yourburger.config.EmailProperties;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {


    private EmailProperties emailProperties;
    private JavaMailSender emailSender;
    private SpringTemplateEngine thymeleafTemplateEngine;

    public EmailServiceImpl(EmailProperties emailProperties,
                            JavaMailSender emailSender,
                            SpringTemplateEngine thymeleafTemplateEngine) {
        this.emailProperties = emailProperties;
        this.emailSender = emailSender;
        this.thymeleafTemplateEngine = thymeleafTemplateEngine;
    }

    @Override
    public void sendSimpleMessage(String to, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailProperties.getFrom());
            message.setTo(to);
            message.setSubject(emailProperties.getSubject());
            message.setText(text);

            emailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessageWithAttachment(String to,
                                          String text,
                                          String pathToAttachment) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(emailProperties.getFrom());
            helper.setTo(to);
            helper.setSubject(emailProperties.getSubject());
            helper.setText(text);

            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment("Invoice", file);

            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessageUsingThymeleafTemplate(String to,
                                                  String htmlTemplate,
                                                  Map<String, Object> templateModel) throws MessagingException {
        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(templateModel);

        String htmlBody = thymeleafTemplateEngine.process(htmlTemplate, thymeleafContext);

        sendHtmlMessage(to, emailProperties.getSubject(), htmlBody);

    }

    private void sendHtmlMessage(String to, String subject, String htmlBody) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(emailProperties.getFrom());
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        emailSender.send(message);
    }
}
