package com.example.igor.networks.service.impl;

import com.example.igor.networks.service.CantripWalletService;
import com.example.igor.networks.service.factory.ServiceFactory;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * @author Igor Hnes on 6/13/17.
 */
public class CantripWalletServiceImpl implements CantripWalletService {

    /**
     * {@inheritDoc}.
     */
    @Override
    public String putMoney(String money, String privateKey, String address, String token) {
        return ServiceFactory.getEtherTransaction().send(money, privateKey, address, token);
    }
}
