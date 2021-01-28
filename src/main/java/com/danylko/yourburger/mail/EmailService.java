package com.danylko.yourburger.mail;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

public interface EmailService {

    void sendSimpleMessage(String to,
                           String text);
    void sendMessageWithAttachment(String to,
                                   String text,
                                   String pathToAttachment);
    void sendMessageUsingThymeleafTemplate(String to,
                                           String htmlTemplate,
                                           Map<String, Object> templateModel)
            throws IOException, MessagingException;
}
