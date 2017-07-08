package com.example.igor.networks.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.igor.networks.R;
import com.example.igor.networks.currency.Currency;
import com.example.igor.networks.model.CurrentUser;
import com.example.igor.networks.model.LuckyEther;
import com.example.igor.networks.service.CantripWalletService;
import com.example.igor.networks.service.ParticipantsService;
import com.example.igor.networks.service.factory.ServiceFactory;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * @author Igor Hnes on 6/13/17.
 */
public class CantripFragment extends Fragment {

    private DatabaseReference databaseReferenceJackpot;
    private DatabaseReference databaseLuckyEther;
    private Long jackpotMoney = 0L;
    private TextView txtJackpot;
    private Spinner spinner;
    private LuckyEther luckyEther;

    public CantripFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cantrip_layout, null);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReferenceJackpot = firebaseDatabase.getReference().child("jackpot");
        databaseLuckyEther = firebaseDatabase.getReference().child("LuckyEther");
        listenerUtil();
        txtJackpot = (TextView) view.findViewById(R.id.txtJackpot);
        listenerJackpot();
        Button btnTakePartInCantrip = (Button) view.findViewById(R.id.btnTakePart);
        spinner = (Spinner) view.findViewById(R.id.spnTableGames);
        configurationTables();
        btnTakePartInCantrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMoney(v);
            }
        });
        Realm.init(getContext());
        return view;
    }

    private void configurationTables() {
        List<String> list = new ArrayList<>();
        list.add("Select games table");
        list.add("฿ Game table 1, rate is 1 ฿");
        list.add("฿ Game table 2, rate is 5 ฿");
        list.add("฿ Game table 3, rate is 10 ฿");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.tables_spinner, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    /**
     * Send money to
     */
    private void sendMoney(View v) {
        if (spinner.getSelectedItemPosition() == 0) {
            Snackbar.make(v, "Can't be empty", Snackbar.LENGTH_SHORT).show();
            return;
        }
        final String address = getAddress();
        final String money = sayHowMuch();
        final CurrentUser currentUser = getCurrentUser();
//        final CantripWalletService cantripWalletService = ServiceFactory.getCantripWalletService();
//        String result = cantripWalletService.putMoney(money, currentUser.getPrivateKey(),
//                address, luckyEther.getToken());
        final ParticipantsService participantsService = ServiceFactory.getParticipantsService();
        participantsService.addUserToParticipantsChild(currentUser.getKey(),
                LocalDate.now().toString(),
                money,
                currentUser.getNicName());
        Snackbar.make(v, currentUser.getPrivateKey(), Snackbar.LENGTH_SHORT).show();
    }

    private String getAddress() {
        switch (spinner.getSelectedItemPosition()) {
            case 1:
                return luckyEther.getFirstWallet();
            case 2:
                return luckyEther.getSecondWallet();
            case 3:
                return luckyEther.getThirdWallet();
            default:
                return null;
        }
    }

    private String sayHowMuch() {
        switch (spinner.getSelectedItemPosition()) {
            case 1:
                return Currency.rateFirst;
            case 2:
                return Currency.rateSecond;
            case 3:
                return Currency.rateThird;
            default:
                return null;
        }
    }

    /**
     * @return instance of {@link  CurrentUser}.
     */
    private CurrentUser getCurrentUser() {
        final Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        final CurrentUser currentUser = realm.where(CurrentUser.class).findFirst();
        realm.commitTransaction();
        return currentUser;
    }

    /**
     * Listening how much in jackpot wallet right now.
     */
    private void listenerJackpot() {
        databaseReferenceJackpot.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                jackpotMoney = dataSnapshot.getValue(Long.class);
                txtJackpot.setText(String.valueOf(jackpotMoney));
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                jackpotMoney = dataSnapshot.getValue(Long.class);
                txtJackpot.setText(String.valueOf(jackpotMoney));
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                jackpotMoney = dataSnapshot.getValue(Long.class);
                txtJackpot.setText(String.valueOf(jackpotMoney));
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void listenerUtil() {

        databaseLuckyEther.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                luckyEther = dataSnapshot.getValue(LuckyEther.class);
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
    }
}
