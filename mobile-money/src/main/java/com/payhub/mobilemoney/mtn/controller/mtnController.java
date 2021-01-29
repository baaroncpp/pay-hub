package com.payhub.mobilemoney.mtn.controller;

import com.payhub.mobilemoney.mtn.mtnModels.InitiateTransferCompletedRequest;
import com.payhub.mobilemoney.mtn.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class mtnController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(method = RequestMethod.POST, path="service")
    public  HttpStatus initiatetransfercompleted(@RequestBody InitiateTransferCompletedRequest initiateTransferCompletedRequest){
        if(transactionService.completeCashOutTransaction(initiateTransferCompletedRequest)){
            return  HttpStatus.OK;
        }else{
           return HttpStatus.NOT_FOUND;
        }
    }
}
