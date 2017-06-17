package com.example.igor.networks.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.igor.networks.R;
import com.example.igor.networks.model.Participant;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.LinkedList;
import java.util.List;

import io.realm.Realm;

/**
 * @author Igor Hnes on 06.06.17.
 */

public class RecentRatesFragment extends Fragment {

    private DatabaseReference databaseReference;
    List<String> list = new LinkedList<>();

    public RecentRatesFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.resent_rates, container, false);
        list.add("Loading...");
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("participants");
        Realm.init(view.getContext());
        attachDatabaseReadListener(view);
        return view;
    }

    private void attachDatabaseReadListener(View view) {

        final ListView listView = (ListView) view.findViewById(R.id.listEvent);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(),
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                list.remove("Loading...");
                Participant participant = dataSnapshot.getValue(Participant.class);
                list.add(participant.getDataOfAdded()
                        + " "
                        + participant.getNicName()
                        + " "
                        + String.valueOf(participant.getUserMoney()));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Participant participant = dataSnapshot.getValue(Participant.class);
                list.remove(participant.getDataOfAdded()
                        + " "
                        + participant.getNicName()
                        + " "
                        + String.valueOf(participant.getUserMoney()));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
