package com.example.igor.networks.fragments;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.igor.networks.R;
import com.example.igor.networks.model.Winner;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author Igor Hnes on 6/10/17.
 */
public class WinnersFragment extends Fragment {


    private DatabaseReference databaseReference;
    List<String> list = new LinkedList<>();
    private NotificationManager notificationManager;

    public WinnersFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.winners_layout, null);
        notificationManager = (NotificationManager) view.getContext()
                .getSystemService(Context.NOTIFICATION_SERVICE);
        list.add("Loading...");
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("winners");

        attachDatabaseReadListener(view);
        return view;
    }

    /**
     * Notification about new winners.
     *
     * @param nicName winner
     * @param money   how mush he or she wins.
     */
    private void newWinner(String nicName, Long money) {

        final Random random = new Random();
        int NOTIFICATION_ID = random.nextInt(100000);

        Notification.Builder builder = new Notification.Builder(getContext());

        final Intent intent = new Intent(getContext(), CantripFragment.class);
        final PendingIntent pendingIntent = PendingIntent
                .getActivity(getContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.btc)
                .setLargeIcon(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.favicon))
                .setTicker("Yes, Winners")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle("Winners")
                .setContentText(nicName + " wins " + money + " ฿");

        final Notification notification = builder.build();
        notification.defaults = Notification.DEFAULT_ALL;

        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    /**
     * Listener winners.
     */
    private void attachDatabaseReadListener(View view) {

        ListView listView = (ListView) view.findViewById(R.id.listWinners);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(),
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                list.remove("Loading...");
                Winner winner = dataSnapshot.getValue(Winner.class);
                list.add(winner.getKind()
                        + ": "
                        + winner.getDate()
                        + " "
                        + winner.getWinner()
                        + " wins "
                        + String.valueOf(winner.getMoney()
                        + " ฿"));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Winner winner = dataSnapshot.getValue(Winner.class);
                list.remove(winner.getKind()
                        + ": "
                        + winner.getDate()
                        + " "
                        + winner.getWinner()
                        + " wins "
                        + String.valueOf(winner.getMoney()
                        + " ฿"));
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
