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
import com.example.igor.networks.model.User;
import com.mikepenz.materialdrawer.util.KeyboardUtil;

import io.realm.Realm;
import io.realm.RealmConfiguration;

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

        String nicName = getIntent().getStringExtra("nicName");
        String password = getIntent().getStringExtra("password");

        TextView txtNicName = (TextView) findViewById(R.id.txtNicName);
        TextView passwordText = (TextView) findViewById(R.id.txtPassword);

        txtNicName.setText(nicName);
        passwordText.setText(password);
    }

    public void login(View view) {

        TextView txtNicName = (TextView) findViewById(R.id.txtNicName);
        TextView passwordText = (TextView) findViewById(R.id.txtPassword);

        realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        User user = realm.where(User.class).equalTo("nicName", txtNicName.getText().toString()).findFirst();
        realm.commitTransaction();

        if (txtNicName.getText().toString().equals("") || passwordText.getText().toString().equals("")) {
            KeyboardUtil.hideKeyboard(this);
            Snackbar.make(view, "User not found 2", Snackbar.LENGTH_LONG).show();
            return;
        }

        if (user == null) {
            KeyboardUtil.hideKeyboard(this);
            Snackbar.make(view, "User not found 2", Snackbar.LENGTH_LONG).show();
            return;
        }

        if (txtNicName.getText().toString().equals(user.getNicName())
                && passwordText.getText().toString().equals(user.getPassword())) {
            KeyboardUtil.hideKeyboard(this);
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
//        realm.delete(User.class);
//        realm.createObject(User.class);
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
