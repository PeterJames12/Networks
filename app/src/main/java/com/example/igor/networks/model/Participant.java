package com.example.igor.networks.model;

/**
 * @author Igor Hnes on 6/12/17.
 */
public class Participant {

    private String nicName;
    private String userKey;
    private String dataOfAdded;
    private Long userMoney;

    public String getNicName() {
        return nicName;
    }

    public void setNicName(String nicName) {
        this.nicName = nicName;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public Long getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(Long userMoney) {
        this.userMoney = userMoney;
    }

    public String getDataOfAdded() {
        return dataOfAdded;
    }

    public void setDataOfAdded(String dataOfAdded) {
        this.dataOfAdded = dataOfAdded;
    }
}
