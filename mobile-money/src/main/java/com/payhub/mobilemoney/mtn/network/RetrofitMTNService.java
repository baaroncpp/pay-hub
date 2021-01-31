package com.payhub.mobilemoney.mtn.network;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import retrofit2.Retrofit;
import retrofit2.converter.jaxb.JaxbConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RetrofitMTNService {

    private static String base_url;

    private static String username;

    private static String password;

    @Value("{mtn.mobile.money.username}")
    public static void setUsername(String username) {
        RetrofitMTNService.username = username;
    }

    @Value("{mtn.mobile.money.password}")
    public static void setPassword(String password) {
        RetrofitMTNService.password = password;
    }

    @Value("{mtn.mobile.money.baseurl}")
    public static void setBase_url(String base_url) {
        RetrofitMTNService.base_url = base_url;
    }

    private static Retrofit getRetroInstance(){

        String credentials = Credentials.basic(username, password);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request newRequest  = chain.request().newBuilder()
                                .addHeader("Authorization","Basic "+credentials)
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .build();

        return new Retrofit.Builder().baseUrl(base_url)
                .client(client)
                .addConverterFactory(JaxbConverterFactory.create())
                .build();
    }

    public static MtnAPIService getMtnAPIService(){
        return getRetroInstance().create(MtnAPIService.class);
    }
}
