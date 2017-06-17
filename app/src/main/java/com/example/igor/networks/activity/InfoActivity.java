package com.example.igor.networks.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.igor.networks.R;
import com.example.igor.networks.model.CurrentUser;

import io.realm.Realm;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Realm.init(this);
        final Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        final CurrentUser currentUser = realm.where(CurrentUser.class).findFirst();
        Toast.makeText(getApplicationContext(), currentUser.getNicName()
                + currentUser.getKey()
                + currentUser.getPassword(), Toast.LENGTH_LONG).show();
        realm.commitTransaction();
    }
}
