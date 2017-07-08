package com.example.igor.networks.service.impl;

import android.support.design.widget.Snackbar;

import com.example.igor.networks.service.EtherTransaction;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.infura.InfuraHttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author Igor Hnes on 7/3/17.
 */

public class EtherTransactionImpl implements EtherTransaction {

    /**
     * @param ether is rate.
     */
    @Override
    public String send(String ether, String privateKey, String address, String token) {

        Web3j web3 = Web3jFactory.build(new InfuraHttpService("https://mainnet.infura.io/" + token));
        BigInteger key = new BigInteger(privateKey);

        ECKeyPair ecKeyPair = ECKeyPair.create(key.toByteArray());
        Credentials credentials = Credentials.create(ecKeyPair);
        BigDecimal value = Convert.toWei(ether, Convert.Unit.ETHER);
        try {
            TransactionReceipt transactionReceipt = Transfer.sendFundsAsync(
                    web3, credentials, address, value, Convert.Unit.WEI).get();
//        System.out.println(transactionReceipt.getTransactionHash());
        } catch (Exception e) {
            return "Something went wrong";
        }
        return "Accepted " + ether + " ฿";
    }
}
