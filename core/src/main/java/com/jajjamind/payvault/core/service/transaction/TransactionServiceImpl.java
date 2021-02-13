package com.jajjamind.payvault.core.service.transaction;

import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.api.constants.ErrorMessageConstants;
import com.jajjamind.payvault.core.api.transaction.models.Transaction;
import com.jajjamind.payvault.core.api.transaction.models.TransactionResponse;
import com.jajjamind.payvault.core.jpa.models.account.TAccount;
import com.jajjamind.payvault.core.jpa.models.account.TAccountMapping;
import com.jajjamind.payvault.core.jpa.models.agent.TAgent;
import com.jajjamind.payvault.core.jpa.models.agent.TAgentProduct;
import com.jajjamind.payvault.core.jpa.models.enums.AccountStatusEnum;
import com.jajjamind.payvault.core.jpa.models.enums.AccountTypeEnum;
import com.jajjamind.payvault.core.jpa.models.product.TProduct;
import com.jajjamind.payvault.core.jpa.models.product.TProductCharge;
import com.jajjamind.payvault.core.jpa.models.product.TProductCommission;
import com.jajjamind.payvault.core.repository.account.AccountRepository;
import com.jajjamind.payvault.core.repository.agent.AgentProductRepository;
import com.jajjamind.payvault.core.repository.bank.AccountMappingRepository;
import com.jajjamind.payvault.core.repository.product.ProductCommissionRepository;
import com.jajjamind.payvault.core.repository.product.ProductRepository;
import com.jajjamind.payvault.core.service.utilities.AccountUtilities;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author akena
 * 08/02/2021
 * 01:17
 **/
@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public ProductCommissionRepository productCommissionRepository;

    @Autowired
    public AgentProductRepository agentProductRepository;

    @Autowired
    public AccountMappingRepository accountMappingRepository;

    @Autowired
    public AccountRepository accountRepository;

    @Override
    public TransactionResponse createTransaction(Transaction request) {

        Optional<TProduct> productOptional = productRepository.findProductByCode(request.getProductCode());
        Validate.isPresent(productOptional, ErrorMessageConstants.PRODUCT_REQUEST_FAILED);

        Optional<TAgentProduct> agentProduct = agentProductRepository.findByAgentIdAndProductId(productOptional.get().getId(),request.getAgentId());
        Validate.isPresent(agentProduct,ErrorMessageConstants.NO_PRODUCT_ACCESS);

        TAgent agent = agentProduct.get().getAgent();
        final TProduct product = resolveProductToUse(productOptional.get());



        return null;
    }

    @Transactional
    private void performAccountDeductions(Transaction request, String externalTransactionId){

        //Account to reduce
        Optional<TAccountMapping> agentAccountMapping = accountMappingRepository.findAgentAccountMapped(request.getAgentId());
        Validate.isPresent(agentAccountMapping,ErrorMessageConstants.AGENT_ACCOUNT_NOT_FOUND);
        Validate.notNull(agentAccountMapping.get().getAccountId(),ErrorMessageConstants.AGENT_ACCOUNT_NOT_FOUND);

        final Optional<TAccount> agentAccount = accountRepository.findByIdForBalanceUpdate(agentAccountMapping.get().getAccountId().getId());
        final TAccount aAccount = agentAccount.get();

        AccountUtilities.checkThatAccountCanTransact(aAccount,AccountTypeEnum.BUSINESS);
        AccountUtilities.checkThatTransactionWontResultInNegativeBalance(aAccount,request.getAmount());
        //Get agent account and check that it has enough


        //Hold commission earned by agent
        Optional<TAccountMapping> agentCommissionAccount = accountMappingRepository.findAgentCommissionAccountMapped(request.getAgentId());
        //Hold commission earned by payhub
        Optional<TAccountMapping> systemCommissionAccount = accountMappingRepository.findSystemCommissionAccountMapped();



    }

    @Transactional
    private void reverseFailedTransaction(){

    }

    private TProduct resolveProductToUse(TProduct currentProduct){

        TAccount currentAccount = currentProduct.getProductAccount();
        if(ObjectUtils.anyNull(currentAccount) || !currentAccount.getAssigned() || currentAccount.getAccountStatus().equals(AccountStatusEnum.NOT_ACTIVE)
        ||currentProduct.getNonActive())
        {
            //Product cannot be used -- invalid states
            //Resolve new product to use that provides same service
            Optional<TProduct> product = productRepository.findByCategoryDetailsForTransaction(currentProduct.getProductCategory().name(),
                    currentProduct.getRootProvider().name());

            Validate.isPresent(product,ErrorMessageConstants.PRODUCT_REQUEST_FAILED_NOT_ACTIVE);

            return product.get();

        }

        return currentProduct;
    }


    private TProductCharge resolveProductCharge(TProduct product, BigDecimal transactionAmount){
        throw new NotImplementedException("Method has not been implemented");
    }

    private Optional<Pair<BigDecimal,BigDecimal>> resolveProductCommission(TProduct product, BigDecimal transactionAmount){

        Optional<TProductCommission> commissionOptional = productCommissionRepository.findProductCommissionGeneric(product.getId());
        if(!commissionOptional.isPresent())
            return Optional.empty();


        TProductCommission commission = commissionOptional.get();

        switch (commission.getPricingType()){
            case TARIFF:
                TProductCommission c = resolveTariff(transactionAmount,commission.getTariffGroupIdentifier());
                return Optional.of(MutablePair.of(c.getSystemTariff(),c.getTariff()));
            case PERCENTAGE:
                final float systemCommission = commission.getSystemPercent();
                final float agentCommission = commission.getPercent();

                return Optional.of(MutablePair.of(getAmountFromPercentCommission(systemCommission,transactionAmount),
                        getAmountFromPercentCommission(agentCommission,transactionAmount)));

            case FLAT_CHARGE:
                return Optional.of(MutablePair.of(commission.getSystemAmount(),commission.getAmount()));

            default:
                throw new IllegalStateException("Unsupported state exception");
        }


    }

    private BigDecimal getAmountFromPercentCommission(float commission,BigDecimal amount){
       return  amount
               .divide(BigDecimal.valueOf(100))
               .multiply(BigDecimal.valueOf(commission));
    }


    private TProductCommission resolveTariff(BigDecimal amount,String tariffGrouping){

        Optional<TProductCommission> tariff = productCommissionRepository.findCorrectTariff(amount,tariffGrouping);
        Validate.isPresent(tariff,"Requested is outside of tariff range for product.");

        return tariff.get();

    }

}
