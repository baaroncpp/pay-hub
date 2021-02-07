package com.jajjamind.payvault.core.service.account;

import com.jajjamind.payvault.core.api.account.models.CashFlow;
import com.jajjamind.payvault.core.api.users.models.Approval;

/**
 * @author akena
 * 04/02/2021
 * 03:55
 **/
public interface CashFlowService {

    void initiateToBusinessUserTransfer(CashFlow cashFlowRequest);
    CashFlow approveToBusinessUserTransfer(Approval approval);

    void initiateToServiceProviderTransfer(CashFlow cashFlowRequest);
    CashFlow approveToServiceProviderRequest(Approval approval);

    void initiateStockWithdraw(CashFlow cashFlow);
    CashFlow approveStockWithdraw(Approval approval);

    void initiateFromBusinessUserTransfer(CashFlow cashFlow);
    CashFlow approveFromBusinessUserTransfer(Approval approval);

}
