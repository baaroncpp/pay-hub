package com.payhub.data.mtn.mtnModel;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseFailure {
    private String status;
    private String error;
    private String message;
    private Date timestamp;
    private String path;
}