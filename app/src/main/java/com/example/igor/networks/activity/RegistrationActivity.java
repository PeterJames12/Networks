package com.example.igor.networks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.igor.networks.R;
import com.example.igor.networks.model.Helper;
import com.example.igor.networks.model.Player;
import com.example.igor.networks.model.RemoteUser;
import com.example.igor.networks.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.realm.Realm;

/**
 * @author Igor Hnes on 06.06.17.
 */
public class RegistrationActivity extends AppCompatActivity {

    private Realm realm;
    private Button btnConfirmRegistration;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_layout);
        btnConfirmRegistration = (Button) findViewById(R.id.btbConfirmRegistration);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("user");
        Realm.init(this);
    }

    public void confirm(View view) {

        TextView email = (TextView) findViewById(R.id.txtUserEmail);
        TextView password = (TextView) findViewById(R.id.txtUserPassword);
        TextView nicName = (TextView) findViewById(R.id.txtUserNicName);


        User user = new User();
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());
        user.setNicName(nicName.getText().toString());
        forRealm(user);

        forFirebase(user);

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.putExtra("nicName", user.getNicName());
        intent.putExtra("password", user.getPassword());
        startActivity(intent);
    }

    private void forFirebase(User user) {
        final RemoteUser remoteUser = new RemoteUser();
        remoteUser.setNicName(user.getNicName());
        remoteUser.setMoney(100L);
        databaseReference.push().setValue(remoteUser);
    }

    private void forRealm(User user) {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.delete(User.class);
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
