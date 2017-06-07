package com.example.igor.networks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

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
}
