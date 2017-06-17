package com.example.igor.networks.service.impl;

import com.example.igor.networks.model.Participant;
import com.example.igor.networks.service.ParticipantsService;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * @author Igor Hnes on 6/12/17.
 */
public class ParticipantsServiceImpl implements ParticipantsService {

    private DatabaseReference databaseReference;

    /**
     * {@inheritDoc}.
     */
    @Override
    public void addUserToParticipantsChild(String userKey, String dataOfAdded, Long money, String userName) {
        getReference();
        final Participant participant = new Participant();
        participant.setUserKey(userKey);
        participant.setDataOfAdded(dataOfAdded);
        participant.setUserMoney(money);
        participant.setNicName(userName);
        databaseReference.push().setValue(participant);
    }

    /**
     * Set up the database.
     */
    private void getReference() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("participants");
    }
}
