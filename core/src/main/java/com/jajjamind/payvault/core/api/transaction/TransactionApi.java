package com.jajjamind.payvault.core.api.transaction;

import com.jajjamind.payvault.core.api.transaction.models.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author akena
 * 30/01/2021
 * 03:55
 **/
@RequestMapping("/transaction")
@RestController
public class TransactionApi {

    @PostMapping("/airtime")
    public AirtimeTransaction buyAirtime(AirtimeTransaction airtimeTransaction){
        return null;
    }


    @PostMapping("/utilities")
    public UtilitiesTransaction payForUtilities(UtilitiesTransaction airtimeTransaction){
        return null;
    }

    @PostMapping("/reverse")
    public Transaction reverseTransaction(Transaction transaction){
        return null;
    }


    @PostMapping("/mobilemoney/cashout")
    public MobileMoneyCashoutTransaction mobileMoneyCashOut(MobileMoneyCashoutTransaction transaction){
        return null;
    }


    @PostMapping("/mobilemoney/cashin")
    public MobileMoneyCashInTransaction mobileMoneyCashIn(MobileMoneyCashInTransaction transaction){
        return null;
    }

    @PostMapping("/data")
    public DataTransaction buyMobileData(DataTransaction transaction){
        return null;
    }
}
