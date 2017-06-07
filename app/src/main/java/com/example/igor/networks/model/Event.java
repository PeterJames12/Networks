package com.example.igor.networks.model;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.util.List;

import io.realm.RealmObject;

/**
 * @author Igor Hnes on 6/6/17.
 */

public class Event extends RealmObject {

    private String winner;
    private int total;
//    private LocalDate dateOfEvent;
//    private LocalTime timeOfEvent;

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
