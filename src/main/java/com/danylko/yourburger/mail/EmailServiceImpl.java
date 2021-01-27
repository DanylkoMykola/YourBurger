package com.danylko.yourburger.mail;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

public class EmailServiceImpl implements EmailService {

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {

    }

    @Override
    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {

    }

    @Override
    public void sendMessageUsingThymeleafTemplate(String to, String subject, Map<String, Object> templateModel) throws IOException, MessagingException {

    }
}
