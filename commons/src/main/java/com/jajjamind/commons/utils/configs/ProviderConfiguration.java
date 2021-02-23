package com.jajjamind.commons.utils.configs;

/**
 * @author akena
 * 21/02/2021
 * 18:58
 **/
public class ProviderConfiguration {

    private String endpoint;
    private String username;
    private String password;
    private String apiKey;
    private Integer connectionPort;
    private Integer readTimeout;
    private Integer connectTimeout;
    private Integer writeTimeout;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Integer getConnectionPort() {
        return connectionPort;
    }

    public void setConnectionPort(Integer connectionPort) {
        this.connectionPort = connectionPort;
    }

    public Integer getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(Integer readTimeout) {
        this.readTimeout = readTimeout;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getWriteTimeout() {
        return writeTimeout;
    }

    public void setWriteTimeout(Integer writeTimeout) {
        this.writeTimeout = writeTimeout;
    }
}
