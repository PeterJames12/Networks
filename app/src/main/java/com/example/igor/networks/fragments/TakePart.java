package com.example.igor.networks.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.igor.networks.R;
import com.example.igor.networks.activity.MainActivity;
import com.example.igor.networks.model.Fond;
import com.example.igor.networks.model.Helper;
import com.example.igor.networks.model.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mikepenz.materialdrawer.util.KeyboardUtil;

import io.realm.Realm;

/**
 * @author Igor Hnes on 6/8/17.
 */
public class TakePart extends Fragment {

    private TextView howMuch;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.take_part_layout, container, false);
        howMuch = (TextView) view.findViewById(R.id.txtHowMuch);
        Button takePart = (Button) view.findViewById(R.id.btnTakePart);
        Realm.init(getContext());
        takePart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePart(v);
            }
        });

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("participants");
        return view;
    }

    public void takePart(View view) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        User user = realm.where(User.class).findFirst();
        Fond fond = new Fond();
        fond.setMoney(Long.valueOf(howMuch.getText().toString()));
        user.setNicName(user.getNicName());
        realm.commitTransaction();
        KeyboardUtil.hideKeyboard(getActivity());
        databaseReference.push().setValue(fond);
        Snackbar.make(view, "take part with " + howMuch.getText().toString(), Snackbar.LENGTH_SHORT).show();
    }
}
