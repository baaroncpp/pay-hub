package com.payhub.data.mtn.network;

import com.payhub.data.airtel.network.DseApiService;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RetrofitMtnBundlesService {

    private static String base_url;

    private static String apiKey;

    @Value("{mtn.bundles.api.key}")
    public static void setApiKey(String apiKey) {
        RetrofitMtnBundlesService.apiKey = apiKey;
    }

    @Value("{mtn.bundles.baseurl}")
    public static void setBase_url(String base_url) {
        RetrofitMtnBundlesService.base_url = base_url;
    }

    private static Retrofit getRetroInstance(String transactionId){

        OkHttpClient client = null;

        if(!transactionId.isEmpty()) {
            client = new OkHttpClient.Builder()
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(new Interceptor() {

                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request newRequest = chain.request().newBuilder()
                                    /*.addHeader(ACCEPT, "application/json")
                                    .addHeader(CONTENT_TYPE, "application/json")
                                    .addHeader("transactionId", transactionId)
                                    .addHeader("x-api-key", DSE_PASSWORD)*/
                                    .build();
                            return chain.proceed(newRequest);
                        }
                    }).build();
        }else{
            client = new OkHttpClient.Builder()
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build();
        }
        return new Retrofit.Builder().baseUrl(base_url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static MtnBundleApiService getRetrofitDseService(String t){
        return getRetroInstance(t).create(MtnBundleApiService.class);
    }

}
