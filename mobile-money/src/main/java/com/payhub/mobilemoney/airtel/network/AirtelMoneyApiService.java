package com.payhub.mobilemoney.airtel.network;

import com.payhub.mobilemoney.airtel.airtelModels.*;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AirtelMoneyApiService {

    @POST("service")
    Call<DepositResponse> makeCashIn(@Body DepositRequest depositRequest);

    @POST("service")
    Call<TransactionEnquiryResponse> makeTransactionEquiry(@Body TransactionEnquiry transactionEnquiry);

    @POST("service")
    Call<FetchUserResponse> fetchUserInfo(@Body FetchUserRequest fetchUserRequest);

    @POST("service")
    Call<WithdrawResponse> makeWithDraw(@Body WithdrawRequest withdrawRequest);

}
