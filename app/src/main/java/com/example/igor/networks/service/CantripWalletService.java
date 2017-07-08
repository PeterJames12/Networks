package com.example.igor.networks.service;

/**
 * @author Igor Hnes on 6/13/17.
 */
public interface CantripWalletService {

    /**
     * @param money is going to cantrip wallet.
     * @return message result.
     */
    String putMoney(String money, String privateKey, String address, String token);
}
