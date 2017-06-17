package com.example.igor.networks.service.impl;

import com.example.igor.networks.service.CantripWalletService;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * @author Igor Hnes on 6/13/17.
 */
public class CantripWalletServiceImpl implements CantripWalletService {

    private DatabaseReference databaseReference;

    /**
     * {@inheritDoc}.
     */
    @Override
    public void putMoney(Long money) {
        getReference();
        HashMap<String, Object> map = new HashMap<>();
        map.put("money", money);
        databaseReference.updateChildren(map);
    }

    /**
     * Set up the database.
     */
    private void getReference() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("cantripWallet");
    }
}
