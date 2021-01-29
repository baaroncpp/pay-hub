package com.payhub.interswitch.network;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.payhub.interswitch.constants.PayvaultConstants;
import org.springframework.stereotype.Component;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Component
public class RetrofitInterSwitchService {
	
	private static String TIMESTAMP = "Timestamp";
	private static String NONCE = "Nonce";
	private static String SIGNATURE_METHOD = "SignatureMethod";
	private static String SIGNATURE = "Signature";
	private static String AUTHORIZATION = "Authorization";
	private static String TERMINAL_ID = "TerminalId";
	
	private static Retrofit getRetroInstance(Map<String, String> header){

		OkHttpClient client = getUnsafeOkHttpClient(header);
		
        return new Retrofit.Builder().baseUrl(PayvaultConstants.INTERSWITCH_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
	
	public static InterSwitchAPIService getInterSwitchAPIService(Map<String, String> header){
        return getRetroInstance(header).create(InterSwitchAPIService.class);
    }

	
	public static OkHttpClient getUnsafeOkHttpClient(Map<String, String> header) {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0]);
            
            builder.connectTimeout(60, TimeUnit.SECONDS)
                   .writeTimeout(60, TimeUnit.SECONDS)
                   .readTimeout(80, TimeUnit.SECONDS);
            
            builder.addInterceptor(new Interceptor() {

                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request newRequest  = chain.request().newBuilder()
                    		.addHeader(AUTHORIZATION, header.get(AUTHORIZATION))
                    		.addHeader(TIMESTAMP, header.get(TIMESTAMP))
                    		.addHeader(NONCE, header.get(NONCE))
                    		.addHeader(SIGNATURE_METHOD, header.get(SIGNATURE_METHOD))
                    		.addHeader(SIGNATURE, header.get(SIGNATURE))
                    		.addHeader(TERMINAL_ID, header.get(TERMINAL_ID))
                    		.addHeader("Content-Type", "application/json")
                            .build();
                    return chain.proceed(newRequest);
                }
            }); 
            
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
}
