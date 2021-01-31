package com.jajjamind.payvault.core.api.account;

import com.jajjamind.payvault.core.api.Approve;
import com.jajjamind.payvault.core.api.account.models.CashFlow;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author akena
 * 30/01/2021
 * 03:47
 **/
@RestController
@RequestMapping("/cashflow")
public class CashFlowApi {

    @PostMapping(value = "/topup",produces = "application/json")
    public CashFlow moveMoneyToChildAccounts(CashFlow cashFlow){
        return null;
    }

    //Require two approvals
    @PostMapping(value = "/topup/approve")
    public Approve approveTopUp(Approve approve){
        return null;
    }

    @PostMapping(value = "/withdraw",produces = "application/json")
    public CashFlow moveMoneyFromChildAccounts(CashFlow cashFlow){
        return null;
    }

    //Require two approvals
    @PostMapping(value = "/withdraw/approve",produces = "application/json")
    public CashFlow approveMoveMoneyFromChildAccounts(CashFlow cashFlow){
        return null;
    }

}
