package com.example.igor.networks.service;

import org.springframework.mail.SimpleMailMessage;

/**
 * @author Igor Hnes on 06.06.17.
 */
public interface EmailService {

    /**
     * Method just send a message.
     *
     * @param message contains text, subject, recipient or recipients.
     */
    void sendMessage(SimpleMailMessage message);

}
