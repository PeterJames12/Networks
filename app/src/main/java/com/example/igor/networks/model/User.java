package com.example.igor.networks.model;

import io.realm.RealmObject;

/**
 * @author Igor Hnes on 6/6/17.
 */
public class User extends RealmObject {

    private String name;
    private int money;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
