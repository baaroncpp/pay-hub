package com.jajjamind.payvault.core.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author akena
 * 13/11/2020
 * 11:21
 **/
@RestController
public class Users  {

    @GetMapping("test")
    public String test(){
        return "This is a test";
    }

}
