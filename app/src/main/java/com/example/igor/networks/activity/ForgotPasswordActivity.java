package com.example.igor.networks.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.igor.networks.R;

/**
 * @author Igor Hnes on 06.06.17.
 */
public class ForgotPasswordActivity extends AppCompatActivity {

    private Button btnForgotPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_layout);

        btnForgotPassword = (Button) findViewById(R.id.btnForgotPassword);
    }

    public void recoverPassword(View view) {
        Snackbar.make(view, "Sent", Snackbar.LENGTH_LONG).show();
    }
}
