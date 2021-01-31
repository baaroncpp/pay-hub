package com.payhub.data.mtn.network;

import com.google.gson.JsonObject;
import com.payhub.data.mtn.mtnModel.ActivateBundleRequest;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MtnBundleApiService {

    @GET("v3/products/{beneficiaryId}/{subscriptionId}")
    Call<JsonObject> getBundlePrice(@Path("beneficiaryId")String beneficiaryId,
                                    @Path("subscriptionId")String subscriptionId,
                                    @Query("transactionId")String transactionId);


    @POST("v2/customers/{customerId}/subscriptions")
    Call<JsonObject> activateBundle(@Path("customerId")String customerId, ActivateBundleRequest activateBundleRequest);

    @GET("v2/customers/subscriptions/subscriptionId/status/{transactionId}")
    Call<JsonObject> getTransactionStatus(@Path("transactionId") String transactionId,
                                          @Query("customerId") String customerId,
                                          @Query("subscriptionProviderId") String subscriptionProviderId);
}
