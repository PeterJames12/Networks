package com.example.igor.networks.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.igor.networks.R;
import com.example.igor.networks.model.CurrentUser;

import io.realm.Realm;

/**
 * @author Igor Hnes on 6/14/17.
 */
public class SettingsFragment extends Fragment {

    private Realm realm;
    private EditText privateKey;

    public SettingsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_layout, null);
        Realm.init(getContext());
        privateKey = (EditText) view.findViewById(R.id.txt_private_key);
        Button btnConfigure = (Button) view.findViewById(R.id.btn_configure_private_key);
        btnConfigure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configurePrivateKey();
            }
        });
        return view;
    }


    private void configurePrivateKey() {
        CurrentUser currentUser = new CurrentUser();
        currentUser.setPrivateKey(privateKey.getText().toString());
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(currentUser);
        realm.commitTransaction();
        realm.close();
    }



}
