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
import com.example.igor.networks.model.Fond;
import com.example.igor.networks.model.Helper;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import io.realm.Realm;

/**
 * @author Igor Hnes on 6/8/17.
 */

public class MyEventFragment extends Fragment {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference databaseReference;
    private ChildEventListener childEventListener;
    private ValueEventListener eventListener;
    List<String> list = new LinkedList<>();
    List<Helper> helperList = new LinkedList<>();

    public MyEventFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_event_layout, container, false);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = mFirebaseDatabase.getReference().child("participants");
        Realm.init(view.getContext());
        attachDatabaseReadListener(view);
//        getAllListEvents();
//        getAllListEvents(view);
        return view;
    }
//
//    private void setAdapter(View view) {
//        ListView listView = (ListView) view.findViewById(R.id.listMyEvent);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(),
//                android.R.layout.simple_list_item_1, list);
//        listView.setAdapter(adapter);
//    }

    private List<String> getAllListEvents() {


        databaseReference.child(databaseReference.getKey());
        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("money", 17);

        databaseReference.updateChildren(hashMap);
        list.add(databaseReference.getKey());


//
//        ListView listView = (ListView) view.findViewById(R.id.listMyEvent);
//
//        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(),
//                android.R.layout.simple_list_item_1, list);
//        listView.setAdapter(adapter);
//
//        eventListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String value = dataSnapshot.getValue(String.class);
//                list.add(value);
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        };
//        databaseReference.addValueEventListener(eventListener);

//        RealmResults<Event> todaysEvents = getTodaysEvents();

//        for (Event todaysEvent : todaysEvents) {
//            list.add(todaysEvent.getWinner() + " " + todaysEvent.getTotal());
//        }
        return list;
    }

//    public RealmResults<Event> getTodaysEvents() {
//        Realm realm = Realm.getDefaultInstance();
//        return realm.where(Event.class).findAll();
//    }
//
//    public RealmResults<MyEvents> getMyEvents() {
//        Realm realm = Realm.getDefaultInstance();
//        return realm.where(MyEvents.class).findAll();
//    }

    private void attachDatabaseReadListener(View view) {

        ListView listView = (ListView) view.findViewById(R.id.listMyEvent);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(),
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Fond fond = dataSnapshot.getValue(Fond.class);
                list.add(String.valueOf(fond.getMoney()));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        if (childEventListener == null) {
//            childEventListener = new ChildEventListener() {
//                @Override
//                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                    String value = dataSnapshot.getValue(String.class);
//                    list.add(value);
//                    adapter.notifyDataSetChanged();
//                }
//
//                public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
//                public void onChildRemoved(DataSnapshot dataSnapshot) {}
//                public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
//                public void onCancelled(DatabaseError databaseError) {}
//            };
//        }
    }
}
