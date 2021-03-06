package com.example.igor.networks.model;

import io.realm.RealmObject;

/**
 * @author Igor Hnes on 6/9/17.
 */

public class User extends RealmObject {

    private String nicName;
    private String password;
    private Long money;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public String getNicName() {
        return nicName;
    }

    public void setNicName(String nicName) {
        this.nicName = nicName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
