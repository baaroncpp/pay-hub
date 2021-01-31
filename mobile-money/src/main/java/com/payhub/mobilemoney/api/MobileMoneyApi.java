package com.payhub.mobilemoney.api;

import com.payhub.mobilemoney.airtel.service.AirtelTransactionService;
import com.payhub.mobilemoney.mtn.services.MtnTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MobileMoneyApi {

    @Autowired
    private AirtelTransactionService airtelTransactionService;

    @Autowired
    private MtnTransactionService mtnTransactionService;

    public AirtelTransactionService getAirtelTransactionService(){
        return airtelTransactionService;
    }

    public MtnTransactionService getMtnTransactionService(){
        return mtnTransactionService;
    }

}
