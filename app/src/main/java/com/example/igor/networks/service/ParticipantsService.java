package com.example.igor.networks.service;

/**
 * @author Igor Hnes on 6/12/17.
 */
public interface ParticipantsService {

    /**
     *  @param userKey is unique user key, using this key
     *                method send him to participants child in database.
     * @param dataOfAdded is date when user pushed the money.
     * @param money means how much user pushed the money.
     * @param userName actually is user nic name in system.
     */
    void addUserToParticipantsChild(String userKey, String dataOfAdded, String money, String userName);
}
