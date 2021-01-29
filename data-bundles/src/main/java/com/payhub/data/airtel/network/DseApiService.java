package com.payhub.data.airtel.network;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DseApiService {
	
	@POST("resource/{msisdn}/{productCode}")
	Call<JsonObject> getBundle(@Path ("msisdn") String msisdn, @Path ("productCode") String productCode);
	
	@POST("resource/{msisdn}/{productCode}/{imsi}")
	Call<JsonObject> getBundles(@Path ("msisdn") String msisdn, @Path ("productCode") String productCode, @Path ("imsi") String imsi);
	
}
