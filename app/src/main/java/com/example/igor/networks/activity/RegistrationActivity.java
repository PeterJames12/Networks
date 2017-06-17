package com.example.igor.networks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.igor.networks.R;
import com.example.igor.networks.model.CurrentUser;
import com.example.igor.networks.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.UUID;

import io.realm.Realm;

/**
 * @author Igor Hnes on 06.06.17.
 */
public class RegistrationActivity extends AppCompatActivity {

    private Realm realm;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_layout);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("user");
        Realm.init(this);
    }

    public void confirm(View view) {
        TextView password = (TextView) findViewById(R.id.txtUserPassword);
        TextView nicName = (TextView) findViewById(R.id.txtUserNicName);
        CurrentUser user = new CurrentUser();
        user.setPassword(password.getText().toString());
        user.setNicName(nicName.getText().toString());
        forFirebase(user);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//        intent.putExtra("password", user.getPassword());
        startActivity(intent);
    }

    private void forFirebase(final CurrentUser user) {
        String encryptedChild = UUID.randomUUID().toString();
        user.setKey(encryptedChild);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("nicName", user.getNicName());
        hashMap.put("key", user.getKey());
        hashMap.put("password", user.getPassword());
        databaseReference.child(encryptedChild).updateChildren(hashMap);
        forRealm(user);
    }

    private void forRealm(CurrentUser user) {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insert(user);
        realm.commitTransaction();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm = Realm.getDefaultInstance();
        realm.close();
    }
}
