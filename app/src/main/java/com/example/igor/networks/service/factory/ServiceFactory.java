package com.example.igor.networks.service.factory;

import com.example.igor.networks.service.CantripWalletService;
import com.example.igor.networks.service.EtherTransaction;
import com.example.igor.networks.service.ParticipantsService;
import com.example.igor.networks.service.WinnerService;
import com.example.igor.networks.service.impl.CantripWalletServiceImpl;
import com.example.igor.networks.service.impl.EtherTransactionImpl;
import com.example.igor.networks.service.impl.ParticipantsServiceImpl;
import com.example.igor.networks.service.impl.WinnersServiceImpl;

/**
 * @author Igor Hnes on 6/13/17.
 */
public class ServiceFactory {

    /**
     * @return instance of {@link ParticipantsService}
     */
    public static ParticipantsService getParticipantsService() {
        return new ParticipantsServiceImpl();
    }

    /**
     * @return instance of {@link WinnerService}
     */
    public static WinnerService getWinnerService() {
        return new WinnersServiceImpl();
    }

    /**
     * @return instance of {@link CantripWalletService}.
     */
    public static CantripWalletService getCantripWalletService() {
        return new CantripWalletServiceImpl();
    }

    /**
     * @return instance of {@link EtherTransaction}.
     */
    public static EtherTransaction getEtherTransaction() {
        return new EtherTransactionImpl();
    }
}
