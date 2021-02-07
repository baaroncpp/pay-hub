package com.jajjamind.payvault.core.service.utilities;

import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.api.constants.ErrorMessageConstants;
import com.jajjamind.payvault.core.jpa.models.account.TAccount;
import com.jajjamind.payvault.core.jpa.models.enums.AccountStatusEnum;
import com.jajjamind.payvault.core.jpa.models.enums.AccountTypeEnum;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @author akena
 * 03/02/2021
 * 11:12
 **/
public class AccountUtilities {

    private AccountUtilities(){

    }

    public static void checkThatAccountCanBeAssigned(TAccount acc){

        Validate.isTrue(!acc.getAssigned(), ErrorMessageConstants.ACCOUNT_ALREADY_ASSIGNED,acc.getId());
        Validate.isTrue(!acc.getAccountStatus().equals(AccountStatusEnum.CLOSED),ErrorMessageConstants.ACCOUNT_IS_CLOSED);
        Validate.isTrue(acc.getAccountStatus().equals(AccountStatusEnum.NOT_ACTIVE),ErrorMessageConstants.ACCOUNT_ALREADY_ASSIGNED,acc.getId());
        Validate.isTrue(acc.getAvailableBalance().compareTo(BigDecimal.ZERO) > 0,ErrorMessageConstants.ACCOUNT_BALANCE_MUST_BE_ZERO);

    }

    public static  void checkThatAccountCanBeAssignedAsMain(TAccount acc){
        AccountUtilities.checkThatAccountCanBeAssigned(acc);
        Validate.isTrue(acc.getAccountType().equals(AccountTypeEnum.MAIN),"Only a main account can be assigned to a bank");

    }

    public static void checkThatAccountCanBeUnAssigned(TAccount account){
        Validate.isTrue(account.getAccountStatus().equals(AccountStatusEnum.ACTIVE),"Account is already not assigned");
        Validate.isTrue(account.getAvailableBalance().compareTo(BigDecimal.ZERO) > 0, ErrorMessageConstants.ACCOUNT_BALANCE_MUST_BE_ZERO );
    }

    public static void checkThatAccountCanTransact(TAccount account,AccountTypeEnum... accountTypeEnum){
        Validate.isTrue(Arrays.stream(accountTypeEnum).anyMatch(type -> type.equals(account.getAccountType())),"Account mapped is the desired account type");
        Validate.isTrue(account.getAccountStatus().equals(AccountStatusEnum.ACTIVE),ErrorMessageConstants.ACCOUNT_IS_CLOSED);
        Validate.isTrue(account.getAssigned(),ErrorMessageConstants.ACCOUNT_NOT_ASSIGNED_TO_ANY_ENTITY,account.getId());
    }

    public static void checkThatTransactionWontResultInNegativeBalance(TAccount fromAccount,BigDecimal amountToTransact){
        final BigDecimal fromAccountBalance = fromAccount.getAvailableBalance();
        final BigDecimal newFromAccountBalance = fromAccountBalance.subtract(amountToTransact);
        Validate.isTrue(newFromAccountBalance.compareTo(BigDecimal.ZERO)>= 0,ErrorMessageConstants.INSUFFICIENT_FUNDS_ON_ACCOUNT);
    }
}
