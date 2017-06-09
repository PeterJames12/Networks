package com.example.igor.networks.model;

/**
 * @author Igor Hnes on 6/8/17.
 */

public class Helper {

    private int id;
    private int money;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Helper() {
    }

//    public Helper(int id, int money) {
//        this.id = id;
//        this.money = money;
//    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
