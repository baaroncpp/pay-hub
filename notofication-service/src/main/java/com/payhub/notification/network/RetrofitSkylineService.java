package com.payhub.notification.network;

import okhttp3.OkHttpClient;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

@Service
public class RetrofitSkylineService {

    private static String base_url = "http://skylinesms.com";

    private static Retrofit getRetroInstance(){

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder().baseUrl(base_url/*"http://skylinesms.com"*/)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static SkylineAPIService getSkylineAPIService(){
        return getRetroInstance().create(SkylineAPIService.class);
    }

}
