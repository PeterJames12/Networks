package com.example.igor.networks.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.igor.networks.R;
import com.example.igor.networks.model.CurrentUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mikepenz.materialdrawer.util.KeyboardUtil;

import io.realm.Realm;

/**
 * @author Igor Hnes on 06.06.17.
 */
public class LoginActivity extends AppCompatActivity {

    private Realm realm;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        Realm.init(this);
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
//        realm.delete(CurrentUser.class);
//        realm.delete(User.class);
        CurrentUser user = realm.where(CurrentUser.class).findFirst();
        realm.commitTransaction();
        if (user == null) {
            registration(getCurrentFocus());
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String password = getIntent().getStringExtra("password");
        TextView passwordText = (TextView) findViewById(R.id.txtPassword);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("user");
        passwordText.setText(password);
    }

    public void login(View view) {
        TextView password = (TextView) findViewById(R.id.txtPassword);
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        CurrentUser user = realm.where(CurrentUser.class).equalTo("password", password.getText().toString()).findFirst();
        realm.commitTransaction();
        String notFound = "User not found";
        if (password.getText().toString().equals("") || password.getText().toString().equals("")) {
            KeyboardUtil.hideKeyboard(this);
            Snackbar.make(view, notFound, Snackbar.LENGTH_LONG).show();
            return;
        }

        if (user == null) {
            KeyboardUtil.hideKeyboard(this);
            Snackbar.make(view, notFound, Snackbar.LENGTH_LONG).show();
            return;
        }

        if (password.getText().toString().equals(user.getPassword())) {
            KeyboardUtil.hideKeyboard(this);
//            forCurrentUser(user);
            final Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else {
            KeyboardUtil.hideKeyboard(this);
            Snackbar.make(view, notFound, Snackbar.LENGTH_LONG).show();

        }
    }

    public void registration(View view) {

//        RealmConfiguration config = new RealmConfiguration
//                .Builder()
//                .deleteRealmIfMigrationNeeded()
//                .build();
//
//        Realm.setDefaultConfiguration(config);

//        realm = Realm.getDefaultInstance();
//        realm.beginTransaction();
//        realm.delete(User.class);
//        realm.delete(CurrentUser.class);
//        realm.createObject(User.class);
//        realm.createObject(CurrentUser.class);
//        realm.commitTransaction();

        final Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
        startActivity(intent);
    }

    private void forCurrentUser(CurrentUser user) {
        CurrentUser currentUser = new CurrentUser();

        currentUser.setNicName(user.getNicName());
        currentUser.setPassword(user.getPassword());
        currentUser.setKey(user.getKey());
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.delete(CurrentUser.class);
        realm.insert(currentUser);
        realm.commitTransaction();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm = Realm.getDefaultInstance();
        realm.close();
    }
}
