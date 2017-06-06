package com.example.igor.networks.service.factory;

import com.example.igor.networks.service.EmailService;
import com.example.igor.networks.service.impl.EmailServiceImpl;

/**
 * @author Igor Hnes on 06.06.17.
 */
public class ServiceFactory {

    /**
     * @return instance of {@link EmailService}.
     */
    public static EmailService getEmailService() {
        return new EmailServiceImpl();
    }
}
