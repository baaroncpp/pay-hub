package com.payhub.mobilemoney.airtel.service.imp;

import com.payhub.mobilemoney.airtel.airtelModels.*;
import com.payhub.mobilemoney.airtel.constants.EnquiryStatus;
import com.payhub.mobilemoney.airtel.constants.TransactionStatus;
import com.payhub.mobilemoney.airtel.constants.TransactionType;
import com.payhub.mobilemoney.airtel.jpa.entities.Transaction;
import com.payhub.mobilemoney.airtel.models.*;
import com.payhub.mobilemoney.airtel.models.DepositTransactionModel;
import com.payhub.mobilemoney.airtel.network.*;
import com.payhub.mobilemoney.airtel.network.RetrofitAirtelMoneyService;
import com.payhub.mobilemoney.airtel.repository.TransactionRepository;
import com.payhub.mobilemoney.airtel.service.TransactionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.com.jajjamind.commons.utils.UUIDUtil;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImp implements TransactionService {

    @Value("{airtelmoney.payhub.username}")
    private String username;

    @Value("{airtelmoney.payhub.password}")
    private String password;

    @Value("{airtelmoney.payhub.account.msisdn}")
    private String accountMsisdn;

    @Value("{airtelmoney.payhub.account.pin}")
    private String accountPin;

    private TransactionRepository transactionRepository;

    public TransactionServiceImp(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction makeDeposit(DepositTransactionModel cashInModel) {

        DepositRequest depositRequest =new DepositRequest();
        depositRequest.setMsisdn(accountMsisdn);
        depositRequest.setMsisdn2(cashInModel.getPayerMsisdn());
        depositRequest.setAmount(cashInModel.getAmount());
        depositRequest.setPin(accountPin);
        //depositRequest

        AirtelMoneyApiService airtelMoneyApiService = RetrofitAirtelMoneyService.getRetrofitAirtelApiMoneyService();
        Call<DepositResponse> call = airtelMoneyApiService.makeCashIn(depositRequest);

        Response<DepositResponse> response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Transaction transaction = new Transaction();
        transaction.setId(UUIDUtil.getUUID());
        transaction.setAmount(cashInModel.getAmount());
        transaction.setCreatedOn(new Date());
        transaction.setPayeeMsisdn(cashInModel.getPayerMsisdn());
        transaction.setPayerMsisdn(accountMsisdn);
        transaction.setTransactionId(response.body().getTrId());
        transaction.setTransactionType(TransactionType.DEPOSIT);

        if(response.isSuccessful() && response.body().getTxnStatus() == 200){
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
            System.out.println(response.body());//delete after test
        }else if(response.isSuccessful() && response.body().getTxnStatus() != 200){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            System.out.println(response.body());//delete after test
        }else{
            throw new RuntimeException(response.errorBody().toString());
        }

        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactions(Pageable pageable, Date from, Date to) {

        return null;
    }

    @Override
    public Transaction getTransaction(String id) {
        Optional<Transaction> cashInTransaction = transactionRepository.findById(id);
        if(cashInTransaction.isEmpty()){
            throw new RuntimeException("AirtelMoney transaction with id: "+id+" not found");
        }
        return cashInTransaction.get();
    }

    @Override
    public Transaction getTransactionStatus(String id) {
        //checking transaction updates transactions
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if(transaction.isEmpty()){
            throw new RuntimeException("AirtelMoney transaction with id: "+id+" not found");
        }

        TransactionEnquiry transactionEnquiry = new TransactionEnquiry();//set
        transactionEnquiry.setTrId(transaction.get().getTransactionId());
        transactionEnquiry.setType("");
        transactionEnquiry.setExtTriId("");
        transactionEnquiry.setInterfaceId("");

        AirtelMoneyApiService airtelMoneyApiService = RetrofitAirtelMoneyService.getRetrofitAirtelApiMoneyService();
        Call<TransactionEnquiryResponse> call = airtelMoneyApiService.makeTransactionEquiry(transactionEnquiry);

        Response<TransactionEnquiryResponse> response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(response.isSuccessful()){
            if(response.body().getTxnStatus().equals(EnquiryStatus.TS.name())){
                transaction.get().setTransactionStatus(TransactionStatus.SUCCESS);
            }else if(response.body().getTxnStatus().equals(EnquiryStatus.TA.name())){
                transaction.get().setTransactionStatus(TransactionStatus.AMBIGUOUS);
            }else if(response.body().getTxnStatus().equals(EnquiryStatus.TIP.name())){
                transaction.get().setTransactionStatus(TransactionStatus.INPROGRESS);
            }else if(response.body().getTxnStatus().equals(EnquiryStatus.TF.name())){
                transaction.get().setTransactionStatus(TransactionStatus.FAILED);
            }
        }else{
            throw new RuntimeException(response.errorBody().toString());
        }

        return transactionRepository.save(transaction.get());
    }

    @Override
    public FetchUserResponse fetchUserInfo(String msisdn) {

        FetchUserRequest fetchUserRequest = new FetchUserRequest();
        fetchUserRequest.setPaymentMode("$PAYMENTMODE");
        fetchUserRequest.setType("CKYCREQ");
        fetchUserRequest.setSndInstruction(12);
        fetchUserRequest.setMsisdn(msisdn);
        fetchUserRequest.setPayId(12);
        fetchUserRequest.setSndProvider(101);
        fetchUserRequest.setLanguage("en");
        fetchUserRequest.setRcvInstrument(12);
        fetchUserRequest.setLanguage2(1);
        fetchUserRequest.setPayId2(12);
        fetchUserRequest.setLanguage1(1);
        fetchUserRequest.setPayId1(12);
        fetchUserRequest.setProvider(101);
        fetchUserRequest.setProvider2(101);
        fetchUserRequest.setExtReq("Y");
        fetchUserRequest.setExtTrid("");
        fetchUserRequest.setInterfaceId("PAYHUBTXN");
        fetchUserRequest.setUsername(username);
        fetchUserRequest.setPassword(password);

        AirtelMoneyApiService airtelMoneyApiService = RetrofitAirtelMoneyService.getRetrofitAirtelApiMoneyService();
        Call<FetchUserResponse> call = airtelMoneyApiService.fetchUserInfo(fetchUserRequest);

        Response<FetchUserResponse> response = null;

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(response.isSuccessful() && response.body().getTxnStatus() == 200){
            return response.body();
        }else if(response.isSuccessful() && !(response.body().getTxnStatus() == 200)){
            throw  new RuntimeException(response.body().getTxnStatus()+": "+response.body().getMessage());
        }else{
            throw new RuntimeException(response.code()+" : "+response.message());
        }
    }

    @Override
    public Transaction makeWithDraw(WithDrawTransactionModel withDrawTransactionModel) {

        WithdrawRequest withdrawRequest = new WithdrawRequest();
        withdrawRequest.setTrId(UUIDUtil.getUUID());
        withdrawRequest.setMsisdn(accountMsisdn);
        withdrawRequest.setPin(accountPin);
        withdrawRequest.setMsisdn2(withDrawTransactionModel.getMsisdn());
        withdrawRequest.setPayId(12);
        withdrawRequest.setProvider2(101);
        withdrawRequest.setProvider(101);
        withdrawRequest.setAmount(withDrawTransactionModel.getAmount());
        withdrawRequest.setOtp(withDrawTransactionModel.getOpt());
        withdrawRequest.setType("CASHOUTPAS");
        withdrawRequest.setPayId2(12);
        withdrawRequest.setIsAmountCheckRequired("Y");

        AirtelMoneyApiService airtelMoneyApiService = RetrofitAirtelMoneyService.getRetrofitAirtelApiMoneyService();
        Call<WithdrawResponse> call = airtelMoneyApiService.makeWithDraw(withdrawRequest);

        Response<WithdrawResponse> response = null;

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Transaction transaction =new Transaction();

        if(response.isSuccessful()){

            //update with success code
            if(response.body().getTxnStatus().equals("")){
                transaction.setId(UUIDUtil.getUUID());
                transaction.setAmount(withdrawRequest.getAmount());
                transaction.setCreatedOn(new Date());
                transaction.setPayeeMsisdn(accountMsisdn);
                transaction.setPayerMsisdn(withdrawRequest.getMsisdn());
                transaction.setTransactionId(response.body().getTxnId());
                transaction.setTransactionType(TransactionType.DEPOSIT);
            }else{
                throw new RuntimeException(response.body().getTxnStatus()+": "+response.body().getMessage());
            }

        }else{
            throw new RuntimeException("Connection to Airtel money failed");
        }

        return transactionRepository.save(transaction);
    }
}
