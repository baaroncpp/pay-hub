package com.jajjamind.payvault.core.service.controller.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author akena
 * 27/11/2020
 * 17:35
 **/
@RestController
public class HelloWorldController {

    @RestController
    public class HelloController {
        @GetMapping("/hello")
        public String hello() {
            return "Hello!";
        }
    }

}
