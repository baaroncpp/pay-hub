package com.payhub.data.mtn.mtnModel;

import java.util.List;

public class Cis {
    private String statusCode;
    private List<Data> data;

    public Cis() {
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
