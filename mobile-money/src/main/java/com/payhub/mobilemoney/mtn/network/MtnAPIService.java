package com.payhub.mobilemoney.mtn.network;

import com.payhub.mobilemoney.mtn.mtnModels.*;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MtnAPIService {

    @POST("service")
    Call<InitiateTransferResponse> makeInitialRequest(@Body InitiateTransferRequest initiateTransferRequest);

    @POST("service")
    Call<CashinResponse> cashIn(@Body CashinRequest cashinRequest);

    @POST("service")
    Call<GetBalanceResponse> getBalance(@Body GetBalanceRequest getBalanceRequest);

    @POST("service")
    Call<GetAccountholderInfoResponse> getAccountHolderInfo(@Body GetAccountholderInfoRequest getAccountholderInfoRequest);
}
