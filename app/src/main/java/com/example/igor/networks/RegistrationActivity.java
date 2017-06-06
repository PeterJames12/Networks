package com.example.igor.networks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.igor.networks.service.factory.ServiceFactory;

import org.springframework.mail.SimpleMailMessage;

/**
 * @author Igor Hnes on 06.06.17.
 */
public class RegistrationActivity extends AppCompatActivity {

    private Button btnConfirmRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_layout);

        btnConfirmRegistration = (Button) findViewById(R.id.btbConfirmRegistration);
    }

    public void confirmRegistration(View view) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("Joyukr@ukr.net");
        message.setSubject("Networks");
        message.setText("Hello");

        ServiceFactory.getEmailService().sendMessage(message);
    }
}
