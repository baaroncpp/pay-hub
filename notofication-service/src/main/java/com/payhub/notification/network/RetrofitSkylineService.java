package com.payhub.notification.network;

import com.jajjamind.commons.utils.EncryptUtil;
import com.jajjamind.commons.utils.configs.Provider;
import com.jajjamind.commons.utils.configs.ProviderConfiguration;
import com.jajjamind.commons.utils.configs.ProviderMap;
import okhttp3.OkHttpClient;
import org.springframework.boot.system.SystemProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.SystemPropertyUtils;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RetrofitSkylineService {

    @Resource(name = "httpClient")
    OkHttpClient client;

    private  Retrofit getRetroInstance(){

        final ProviderConfiguration configuration = ProviderMap.getProviderConfiguration(Provider.SKYSMS);
        OkHttpClient mClient = client.newBuilder()
                .connectTimeout(configuration.getConnectTimeout(), TimeUnit.MILLISECONDS)
                .writeTimeout(configuration.getWriteTimeout(), TimeUnit.MILLISECONDS)
                .readTimeout(configuration.getReadTimeout(), TimeUnit.MILLISECONDS)
                .build();

        return new Retrofit.Builder().baseUrl(configuration.getEndpoint())
                .client(mClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public SkylineAPIService getSkylineAPIService(){
        return getRetroInstance().create(SkylineAPIService.class);
    }

    public String getSkylineToken(){
        return EncryptUtil.decryptAES(ProviderMap.getConfigurationMap().get(Provider.SKYSMS).getApiKey());
    }

}
