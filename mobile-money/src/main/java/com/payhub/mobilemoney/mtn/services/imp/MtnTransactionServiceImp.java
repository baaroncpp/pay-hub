package com.payhub.mobilemoney.mtn.services.imp;

import com.payhub.mobilemoney.mtn.constants.ResponseStatusCodes;
import com.payhub.mobilemoney.mtn.jpa.entities.Transaction;
import com.payhub.mobilemoney.mtn.jpa.entities.TransactionApproval;
import com.payhub.mobilemoney.mtn.models.DepositModel;
import com.payhub.mobilemoney.mtn.models.WithdrawModel;
import com.payhub.mobilemoney.mtn.mtnModels.*;
import com.payhub.mobilemoney.mtn.constants.Status;
import com.payhub.mobilemoney.mtn.mtnModels.Transactiontype;
import com.payhub.mobilemoney.mtn.network.MtnAPIService;
import com.payhub.mobilemoney.mtn.network.RetrofitMTNService;
import com.payhub.mobilemoney.mtn.repository.TransactionApprovalRepository;
import com.payhub.mobilemoney.mtn.repository.TransactionRepository;
import com.payhub.mobilemoney.mtn.services.MtnTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import com.jajjamind.commons.utils.UUIDUtil;
import java.io.IOException;
import java.util.*;

@Service
public class MtnTransactionServiceImp implements MtnTransactionService {

    @Value("{mtn.payhub.account.msisdn}")
    private String accountMsisdn;

    @Value("{mtn.payhub.account.name}")
    private String accountName;

    private TransactionRepository transactionRepository;
    private TransactionApprovalRepository transactionApprovalRepository;

    @Autowired
    public MtnTransactionServiceImp(TransactionRepository transactionRepository,
                                    TransactionApprovalRepository transactionApprovalRepository){
        this.transactionRepository = transactionRepository;
        this.transactionApprovalRepository = transactionApprovalRepository;
    }

    @Override
    public Transaction initiateTransfer(DepositModel depositModel) {

        InitiateTransferRequest initiateTransferRequest = new InitiateTransferRequest();
        initiateTransferRequest.setMessage("PAYHUB TXN");
        initiateTransferRequest.setAmount(new Amount(depositModel.getAmount(), depositModel.getCurrency()));
        initiateTransferRequest.setExternaltransactionid(UUIDUtil.getUUID());
        initiateTransferRequest.setAccountholderidentity("ID:"+depositModel.getMsisdn()+"/MSISDN");

        MtnAPIService mtnAPIService = RetrofitMTNService.getMtnAPIService();
        Call<InitiateTransferResponse> call = mtnAPIService.makeInitialRequest(initiateTransferRequest);

        Response<InitiateTransferResponse> response = null;

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Transaction transaction = new Transaction();

        int responseCode = response.code();
        List<ResponseStatusCodes> errorCodes = new ArrayList<>(Arrays.asList(ResponseStatusCodes.values()));

        if(response.isSuccessful()){
            transaction.setId(initiateTransferRequest.getExternaltransactionid());
            transaction.setStatus(Status.PENDING);
            transaction.setApproved(Boolean.FALSE);
            transaction.setCurrency(depositModel.getCurrency());
            transaction.setMessage("PENDING");
            transaction.setFinancialTransactionId(response.body().getTransactionid());
            transaction.setAccountHolderMsisdn(depositModel.getMsisdn());
            transaction.setAmount(depositModel.getAmount());
            transaction.setCreatedOn(new Date());
            transaction.setApprovalId(response.body().getApprovalid());
            transaction.setTransactiontype(Transactiontype.DEPOSIT);
        }else{
            Optional<ResponseStatusCodes> responseStatusCodes = errorCodes.stream().filter(code -> code.getCode() == responseCode).findFirst();
            throw new RuntimeException(responseStatusCodes.get().getMessage());
        }

        return transactionRepository.save(transaction);
    }

    @Override
    public boolean completeCashOutTransaction(InitiateTransferCompletedRequest initiateTransferCompletedRequest) {
        Optional<Transaction> transaction = transactionRepository.findById(initiateTransferCompletedRequest.getExternaltransactionid());
        if(transaction.isEmpty() && initiateTransferCompletedRequest.getStatus().equals("SUCCESSFUL")){
            return Boolean.FALSE;
        }else{
            transaction.get().setApproved(Boolean.TRUE);
            transaction.get().setStatus(Status.COMPLETED);
            transaction.get().setMessage("COMPLETED");
            transactionRepository.save(transaction.get());

            TransactionApproval transactionApproval = new TransactionApproval();
            transactionApproval.setTransaction(transaction.get());
            transactionApproval.setApprovedOn(new Date());
            transactionApproval.setId(UUIDUtil.getUUID());

            transactionApprovalRepository.save(transactionApproval);

            return Boolean.TRUE;
        }
    }

    @Override
    public Transaction cashInTransaction(WithdrawModel withdrawModel) {

        CashinRequest cashinRequest = new CashinRequest();
        cashinRequest.setAmount(new Amount(withdrawModel.getAmount(), withdrawModel.getCurrency()));
        cashinRequest.setSendingfri("FRI:"+accountMsisdn+"/MSISDN");
        cashinRequest.setReceivingfri("FRI:"+withdrawModel.getMsisdn()+"/MSISDN");
        cashinRequest.setSendernote("PAYHUB");
        cashinRequest.setReceivermessage("PAYHUB");

        MtnAPIService mtnAPIService = RetrofitMTNService.getMtnAPIService();
        Call<CashinResponse> call = mtnAPIService.cashIn(cashinRequest);

        Response<CashinResponse> response = null;

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<ResponseStatusCodes> errorCodes = new ArrayList<>(Arrays.asList(ResponseStatusCodes.values()));
        Transaction transaction = new Transaction();

        if(response.isSuccessful()){
            transaction.setId(UUIDUtil.getUUID());

            transaction.setApproved(Boolean.FALSE);
            transaction.setCurrency(withdrawModel.getCurrency());
            transaction.setFinancialTransactionId(response.body().getFinancialtransactionid());
            transaction.setAccountHolderMsisdn(withdrawModel.getMsisdn());
            transaction.setAmount(withdrawModel.getAmount());
            transaction.setCreatedOn(new Date());
            transaction.setApprovalId("PAYHUB");
            transaction.setTransactiontype(Transactiontype.WITHDRAWAL);

            if(response.code() == 200){
                transaction.setStatus(Status.COMPLETED);
                transaction.setMessage(ResponseStatusCodes.SUCCESS.getMessage());
            }else if(response.code() == 202){
                transaction.setStatus(Status.CREATED);
                transaction.setMessage(ResponseStatusCodes.ACCEPTED.getMessage());
            }

        }else{
            int responseCode = response.code();
            Optional<ResponseStatusCodes> responseStatusCodes = errorCodes.stream().filter(code -> code.getCode() == responseCode).findFirst();
            throw new RuntimeException("MTN MOBILE MONEY: "+responseStatusCodes.get().getMessage());
        }

        return transactionRepository.save(transaction);
    }

    @Override
    public AccountHolderBasicInfo getUserInfo(String msisdn) {
        GetAccountholderInfoRequest getAccountholderInfoRequest = new GetAccountholderInfoRequest();
        getAccountholderInfoRequest.setIdentity(msisdn);

        MtnAPIService mtnAPIService = RetrofitMTNService.getMtnAPIService();
        Call<GetAccountholderInfoResponse> call = mtnAPIService.getAccountHolderInfo(getAccountholderInfoRequest);

        Response<GetAccountholderInfoResponse> response = null;

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<ResponseStatusCodes> errorCodes = new ArrayList<>(Arrays.asList(ResponseStatusCodes.values()));

        if(response.isSuccessful()){
            return response.body().getAccountholderbasicinfo();
        }else{
            int responseCode = response.code();
            Optional<ResponseStatusCodes> responseStatusCodes = errorCodes.stream().filter(code -> code.getCode() == responseCode).findFirst();
            throw new RuntimeException("MTN MOBILE MONEY: "+responseStatusCodes.get().getMessage());
        }

    }

    @Override
    public GetBalanceResponse getAccountBalance() {
        GetBalanceRequest getBalanceRequest = new GetBalanceRequest();
        getBalanceRequest.setFri("FRI:"+accountName+"/USER");

        MtnAPIService mtnAPIService = RetrofitMTNService.getMtnAPIService();
        Call<GetBalanceResponse> call = mtnAPIService.getBalance(getBalanceRequest);

        Response<GetBalanceResponse> response = null;

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<ResponseStatusCodes> errorCodes = new ArrayList<>(Arrays.asList(ResponseStatusCodes.values()));

        if(response.isSuccessful()){
            return response.body();
        }else{
            int responseCode = response.code();
            Optional<ResponseStatusCodes> responseStatusCodes = errorCodes.stream().filter(code -> code.getCode() == responseCode).findFirst();
            throw new RuntimeException("MTN MOBILE MONEY: "+responseStatusCodes.get().getMessage());
        }
    }
}
