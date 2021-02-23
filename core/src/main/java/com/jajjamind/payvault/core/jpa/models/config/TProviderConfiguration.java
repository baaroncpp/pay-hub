package com.jajjamind.payvault.core.jpa.models.config;

import com.jajjamind.payvault.core.jpa.models.AuditedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author akena
 * 21/02/2021
 * 19:40
 **/
@Entity
@Table(schema = "core",name = "t_provider_configuration")
public class TProviderConfiguration extends AuditedEntity {
    private String name;
    private String endpoint;
    private String username;
    private String password;
    private String apiKey;
    private Integer connectionPort;
    private Integer readTimeout;
    private Integer connectTimeout;
    private Integer writeTimeout;

    @Column(name = "provider_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "endpoint")
    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "api_key")
    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Column(name = "connection_port")
    public Integer getConnectionPort() {
        return connectionPort;
    }

    public void setConnectionPort(Integer connectionPort) {
        this.connectionPort = connectionPort;
    }

    @Column(name = "read_timeout")
    public Integer getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(Integer readTimeout) {
        this.readTimeout = readTimeout;
    }

    @Column(name = "connection_timeout")
    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    @Column(name = "write_timeout")
    public Integer getWriteTimeout() {
        return writeTimeout;
    }

    public void setWriteTimeout(Integer writeTimeout) {
        this.writeTimeout = writeTimeout;
    }
}
