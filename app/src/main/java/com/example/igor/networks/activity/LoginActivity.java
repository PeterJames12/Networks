package com.example.igor.networks.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.igor.networks.R;
import com.example.igor.networks.model.Player;
import com.mikepenz.materialdrawer.util.KeyboardUtil;

import io.realm.Realm;

/**
 * @author Igor Hnes on 06.06.17.
 */
public class LoginActivity extends AppCompatActivity {

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        Realm.init(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String email = getIntent().getStringExtra("login");
        String password = getIntent().getStringExtra("password");

        TextView emailText = (TextView) findViewById(R.id.txtEmail);
        TextView passwordText = (TextView) findViewById(R.id.txtPassword);

        emailText.setText(email);
        passwordText.setText(password);
    }

    public void login(View view) {

        TextView emailText = (TextView) findViewById(R.id.txtEmail);
        TextView passwordText = (TextView) findViewById(R.id.txtPassword);

        realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        Player player = realm.where(Player.class).equalTo("email", emailText.getText().toString()).findFirst();
        realm.commitTransaction();

        if (emailText.getText().toString().equals("") || passwordText.getText().toString().equals("")) {
            KeyboardUtil.hideKeyboard(this);
            Snackbar.make(view, "User not found 2", Snackbar.LENGTH_LONG).show();
            return;
        }

        if (player == null) {
            KeyboardUtil.hideKeyboard(this);
            Snackbar.make(view, "User not found 2", Snackbar.LENGTH_LONG).show();
            return;
        }

        if (emailText.getText().toString().equals(player.getEmail())
                && passwordText.getText().toString().equals(player.getPassword())) {
            final Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else {
            KeyboardUtil.hideKeyboard(this);
            Snackbar.make(view, "User not found", Snackbar.LENGTH_LONG).show();
        }
    }

    public void registration(View view) {

//        RealmConfiguration config = new RealmConfiguration
//                .Builder()
//                .deleteRealmIfMigrationNeeded()
//                .build();
//
//        Realm.setDefaultConfiguration(config);
//
//        realm = Realm.getDefaultInstance();

//        realm.beginTransaction();
//        realm.delete(Player.class);
//        realm.createObject(Player.class);
//        realm.commitTransaction();

        final Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm = Realm.getDefaultInstance();
        realm.close();
    }
}
