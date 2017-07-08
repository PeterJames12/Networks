package com.example.igor.networks.model;

/**
 * @author Igor Hnes on 7/7/17.
 */

public class LuckyEther {

    private String token;
    private String firstWallet;
    private String secondWallet;
    private String thirdWallet;

    public LuckyEther() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFirstWallet() {
        return firstWallet;
    }

    public void setFirstWallet(String firstWallet) {
        this.firstWallet = firstWallet;
    }

    public String getSecondWallet() {
        return secondWallet;
    }

    public void setSecondWallet(String secondWallet) {
        this.secondWallet = secondWallet;
    }

    public String getThirdWallet() {
        return thirdWallet;
    }

    public void setThirdWallet(String thirdWallet) {
        this.thirdWallet = thirdWallet;
    }
}
