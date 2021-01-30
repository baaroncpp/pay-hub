package com.payhub.data.mtn.service.imp;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.payhub.data.airtel.constant.BundlePaymentStatus;
import com.payhub.data.mtn.entity.jpa.MtnBundle;
import com.payhub.data.mtn.entity.jpa.MtnBundlePayment;
import com.payhub.data.mtn.models.MtnBundlePriceModel;
import com.payhub.data.mtn.models.MtnTransactionStatusModel;
import com.payhub.data.mtn.mtnModel.*;
import com.payhub.data.mtn.network.MtnBundleApiService;
import com.payhub.data.mtn.network.RetrofitMtnBundlesService;
import com.payhub.data.mtn.repository.MtnBundlePaymentRepository;
import com.payhub.data.mtn.repository.MtnBundleRepository;
import com.payhub.data.mtn.models.BuyBundle;
import com.payhub.data.mtn.service.MtnBundlePaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.com.jajjamind.commons.utils.UUIDUtil;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Service
public class MtnBundlePaymentServiceImp implements MtnBundlePaymentService {

    @Value("{mtn.data.registration.channel}")
    private String regChannel;

    @Value("{mtn.data.payhub.msisdn}")
    private String accountMsisdn;

    @Value("{mtn.data.payhub.subscription.name}")
    private String subscriptionName;

    @Value("{mtn.data.payhub.subscription.provider.id}")
    private String subscriptionProviderId;

    @Value("{mtn.data.payhub.subscription.payment.source}")
    private String subscriptionPaymentSource;

    private MtnBundleRepository mtnBundleRepository;
    private MtnBundlePaymentRepository mtnBundlePaymentRepository;

    public MtnBundlePaymentServiceImp(MtnBundlePaymentRepository mtnBundlePaymentRepository,
                                      MtnBundleRepository mtnBundleRepository){
        this.mtnBundlePaymentRepository = mtnBundlePaymentRepository;
        this.mtnBundleRepository = mtnBundleRepository;
    }

    @Override
    public MtnBundlePriceModel getBundlePrice(String bundleId, String msisdn) {
        Optional<MtnBundle> mtnBundle = mtnBundleRepository.findById(bundleId);
        if(mtnBundle.isEmpty()){
            throw new RuntimeException("Bundle not found");
        }

        MtnBundleApiService mtnBundleApiService = RetrofitMtnBundlesService.getRetrofitDseService(null);
        Call<JsonObject> call = mtnBundleApiService.getBundlePrice(msisdn, mtnBundle.get().getBundleName(), UUIDUtil.getUUID());

        Response<JsonObject> response = null;

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MtnBundlePriceModel mtnBundlePriceModel = new MtnBundlePriceModel();
        Gson gson = new Gson();

        if(response.isSuccessful()){

            ValidBundleData validBundleData = gson.fromJson(response.body(), ValidBundleData.class);

            mtnBundlePriceModel.setBundleId(mtnBundle.get().getId());
            mtnBundlePriceModel.setBundleCategory(validBundleData.getData().getBundleCategory());
            mtnBundlePriceModel.setAmount(validBundleData.getData().getAmount());
            mtnBundlePriceModel.setBundleValidity(validBundleData.getData().getBundleValidity());
            mtnBundlePriceModel.setBundleId(mtnBundle.get().getBundleName());
            mtnBundlePriceModel.setCurrency(validBundleData.getData().getCurrency());
            mtnBundlePriceModel.setName(validBundleData.getData().getBundleType());
        }else{
            ResponseFailure responseFailure = gson.fromJson(response.body(), ResponseFailure.class);
            throw new RuntimeException(responseFailure.getError()+": "+responseFailure.getMessage());
        }
        return mtnBundlePriceModel;
    }

    @Override
    public MtnBundlePayment activateBundle(BuyBundle buyBundle) {

        Optional<MtnBundle> mtnBundle = mtnBundleRepository.findById(buyBundle.getBundleid());
        if(mtnBundle.isEmpty()){
            throw new RuntimeException("Bundle not found");
        }

        ActivateBundleRequest activateBundleRequest = new ActivateBundleRequest();
        activateBundleRequest.setBeneficiaryId(buyBundle.getMsisdn());
        activateBundleRequest.setSubscriptionId(mtnBundle.get().getBundleName());
        activateBundleRequest.setSendSMSNotification(Boolean.FALSE);
        activateBundleRequest.setSubscriptionName(subscriptionName);
        activateBundleRequest.setSubscriptionProviderId(subscriptionProviderId);
        activateBundleRequest.setRegistrationChannel(regChannel);
        activateBundleRequest.setSubscriptionPaymentSource(subscriptionPaymentSource);

        String transactionId = UUIDUtil.getUUID();

        MtnBundleApiService mtnBundleApiService = RetrofitMtnBundlesService.getRetrofitDseService(transactionId);
        Call<JsonObject> call = mtnBundleApiService.activateBundle(accountMsisdn, activateBundleRequest);

        Response<JsonObject> response = null;

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        MtnBundlePayment mtnBundlePayment = new MtnBundlePayment();

        if(response.isSuccessful()){
            ActivateBundleResponseSuccess activateBundleResponseSuccess = gson.fromJson(response.body(), ActivateBundleResponseSuccess.class);

            mtnBundlePayment.setId(UUIDUtil.getUUID());
            mtnBundlePayment.setCustomerNumber(buyBundle.getMsisdn());
            mtnBundlePayment.setCreatedOn(new Date());
            mtnBundlePayment.setAmount(activateBundleResponseSuccess.getAmountCharged());
            mtnBundlePayment.setBundleId(mtnBundle.get());
            mtnBundlePayment.setTransactionId(activateBundleResponseSuccess.getTransactionId());
            mtnBundlePayment.setDescription(activateBundleResponseSuccess.getSubscriptionDescription());
            mtnBundlePayment.setStatus(BundlePaymentStatus.COMPLETED);

        }else{
            ResponseFailure responseFailure = gson.fromJson(response.body(), ResponseFailure.class);
            throw new RuntimeException(responseFailure.getError()+": "+responseFailure.getMessage());
        }

        return mtnBundlePaymentRepository.save(mtnBundlePayment);
    }

    @Override
    public MtnTransactionStatusModel getBundlePaymentStatus(String id) {
        Optional<MtnBundlePayment> mtnBundlePayment = mtnBundlePaymentRepository.findById(id);
        if(mtnBundlePayment.isEmpty()){
            throw new RuntimeException("Payment not found");
        }

        String transactionId = UUIDUtil.getUUID();
        MtnBundleApiService mtnBundleApiService = RetrofitMtnBundlesService.getRetrofitDseService(transactionId);
        Call<JsonObject> call = mtnBundleApiService.getTransactionStatus(mtnBundlePayment.get().getTransactionId(),accountMsisdn, "CIS");

        Response<JsonObject>  response = null;

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MtnTransactionStatusModel mtnTransactionStatusModel = new MtnTransactionStatusModel();

        Gson gson = new Gson();

        if(response.isSuccessful()){
            TransactionStatusResponse transactionStatusResponse = gson.fromJson(response.body(), TransactionStatusResponse.class);

            mtnTransactionStatusModel.setMtnTransactionId(transactionStatusResponse.getServices().getCis().getData().get(1).getTransactionId());
            mtnTransactionStatusModel.setBundleName(transactionStatusResponse.getServices().getCis().getData().get(1).getSubscriptionId());
            mtnTransactionStatusModel.setSubscriptionStatus(transactionStatusResponse.getServices().getCis().getData().get(1).getSubscriptionStatus());
            mtnTransactionStatusModel.setCustomerMsisdn(transactionStatusResponse.getServices().getCis().getData().get(1).getBeneficiaryId());
            mtnTransactionStatusModel.setPaymentId(id);
            mtnTransactionStatusModel.setAutoRenew(transactionStatusResponse.getServices().getCis().getData().get(1).isAutoRenew());
        }else if(response.code() == 412){
            TransactionStatusResponse transactionStatusResponse = gson.fromJson(response.body(), TransactionStatusResponse.class);

            mtnTransactionStatusModel.setMtnTransactionId(transactionStatusResponse.getServices().getCis().getData().get(1).getTransactionId());
            mtnTransactionStatusModel.setBundleName(transactionStatusResponse.getServices().getCis().getData().get(1).getSubscriptionId());
            mtnTransactionStatusModel.setSubscriptionStatus(transactionStatusResponse.getServices().getCis().getData().get(1).getSubscriptionStatus());
            mtnTransactionStatusModel.setCustomerMsisdn(transactionStatusResponse.getServices().getCis().getData().get(1).getBeneficiaryId());
            mtnTransactionStatusModel.setPaymentId(id);
            mtnTransactionStatusModel.setAutoRenew(transactionStatusResponse.getServices().getCis().getData().get(1).isAutoRenew());
        }else{
            throw new RuntimeException("");
        }

        return mtnTransactionStatusModel;
    }
}
