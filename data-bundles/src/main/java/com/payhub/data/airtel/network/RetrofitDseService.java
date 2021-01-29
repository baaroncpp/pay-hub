package com.payhub.data.airtel.network;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RetrofitDseService {
	
	private static String ACCEPT = "Accept";
	private static String CONTENT_TYPE = "Content-Type";
	private static String USER = "user";
	private static String PASS = "pass";
	private static String DSE_URL;
	private static String DSE_USER;
	private static String DSE_PASSWORD;
	
	@Value("${dse.base.url}")
    public void setDSE_URL(String value) {
		DSE_URL = value;
    }
	
	@Value("${dse.user}")
	public void setDSE_USER(String value) {
		DSE_USER = value;
    }
	
	@Value("${dse.password}")
	public void setDSE_PASSWORD(String value) {
		DSE_PASSWORD = value;
    }
	
	private static Retrofit getRetroInstance(){

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {

                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request newRequest  = chain.request().newBuilder()   
                        		.addHeader(ACCEPT, "application/json")
                        		.addHeader(CONTENT_TYPE, "application/json")
                        		.addHeader(USER, DSE_USER)
                        		.addHeader(PASS, DSE_PASSWORD)
                                .build();
                        return chain.proceed(newRequest);
                    }
                }).build();

        return new Retrofit.Builder().baseUrl(DSE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
	
	public static DseApiService getRetrofitDseService(){
        return getRetroInstance().create(DseApiService.class);
    }

}
