package com.payhub.mobilemoney.airtel.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class RetrofitAirtelMoneyService {

    private static String base_url = "";

    private static Retrofit getRetroInstance(){

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder().baseUrl(base_url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static AirtelMoneyApiService getRetrofitAirtelApiMoneyService(){
        return getRetroInstance().create(AirtelMoneyApiService.class);
    }

}
