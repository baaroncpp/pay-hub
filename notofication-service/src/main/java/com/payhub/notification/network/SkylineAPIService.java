package com.payhub.notification.network;


import com.payhub.notification.model.BalanceResponse;
import com.payhub.notification.model.ReportResponse;
import com.payhub.notification.model.SmsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SkylineAPIService {

    @GET("api/v2/json/messages")
    Call<SmsResponse> sendSms(@Query("token")String token, @Query("to")String to, @Query("from")String from, @Query("message")String message);

    @GET("api/v2/json/balance")
    Call<BalanceResponse> checkBalance(@Query("token")String token);

    @GET("api/v2/json/delivery")
    Call<ReportResponse> deliveryReports(@Query("token")String token, @Query("reference")String reference);

}
