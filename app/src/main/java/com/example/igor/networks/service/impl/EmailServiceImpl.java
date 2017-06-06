package com.example.igor.networks.service.impl;

import com.example.igor.networks.config.EmailConfig;
import com.example.igor.networks.service.EmailService;

import org.springframework.mail.SimpleMailMessage;

/**
 * @author Igor Hnes on 06.06.17.
 */
public class EmailServiceImpl implements EmailService {

    /**
     * {@inheritDoc}.
     */
    @Override
    public void sendMessage(SimpleMailMessage message) {
        new EmailConfig().mailSender().send(message);
    }
}
