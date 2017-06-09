package com.example.igor.networks.model;

import io.realm.RealmObject;

/**
 * @author Igor Hnes on 6/9/17.
 */

public class User extends RealmObject {

    private String nicName;
    private String email;
    private String password;
    private Long money;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
