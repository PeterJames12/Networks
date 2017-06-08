package com.example.igor.networks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.igor.networks.R;
import com.example.igor.networks.model.Player;

import io.realm.Realm;

/**
 * @author Igor Hnes on 06.06.17.
 */
public class RegistrationActivity extends AppCompatActivity {

    private Realm realm;
    private Button btnConfirmRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_layout);
        btnConfirmRegistration = (Button) findViewById(R.id.btbConfirmRegistration);
        Realm.init(this);
    }

    public void confirm(View view) {

        TextView emailText = (TextView) findViewById(R.id.txtUserEmail);
        TextView passwordText = (TextView) findViewById(R.id.txtUserPassword);

        Player player = new Player();
        player.setId(1);
        player.setEmail(emailText.getText().toString());
        player.setMoney(100);
        player.setPassword(passwordText.getText().toString());

        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insert(player);
        realm.commitTransaction();

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.putExtra("login", player.getEmail());
        intent.putExtra("password", player.getPassword());
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm = Realm.getDefaultInstance();
        realm.close();
    }
}
