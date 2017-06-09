package com.example.igor.networks.model;

/**
 * @author Igor Hnes on 6/9/17.
 */

public class Fond {

    private Long money;
    private String nicName;

    public String getNicName() {
        return nicName;
    }

    public void setNicName(String nicName) {
        this.nicName = nicName;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }
}
