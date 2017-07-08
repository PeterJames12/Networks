package com.example.igor.networks.service;

/**
 * @author Igor Hnes on 7/3/17.
 */

public interface EtherTransaction {

    /**
     * @param ether is rate.
     * @return message result.
     */
    String send(String ether, String privateKey, String address, String token);
}
