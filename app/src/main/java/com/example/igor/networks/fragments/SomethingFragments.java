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
import com.example.igor.networks.model.Event;

import java.util.LinkedList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * @author Igor Hnes on 06.06.17.
 */

public class SomethingFragments extends Fragment {

    public static final String TAG = "SomethingFragments";

    public SomethingFragments() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.something, container, false);
        ListView listView = (ListView)view.findViewById(R.id.listEvent);
        List<String> list = getAllListEvents();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(),
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        return view;
    }

    private List<String> getAllListEvents() {
        RealmResults<Event> todaysEvents = getTodaysEvents();
        List<String> list = new LinkedList<>();

        list.add("Something");

        for (Event todaysEvent : todaysEvents) {
            list.add(todaysEvent.getWinner() + " " + todaysEvent.getTotal());
        }
        return list;
    }

    public RealmResults<Event> getTodaysEvents() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Event.class).findAll();
    }
}
