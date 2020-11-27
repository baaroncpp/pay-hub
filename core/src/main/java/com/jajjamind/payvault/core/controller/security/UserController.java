package com.jajjamind.payvault.core.controller.security;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author akena
 * 18/11/2020
 * 21:23
 **/

@RestController
public class UserController {

    @RequestMapping("/user/me")
    public Principal user(Principal principal) {
        System.out.println(principal);
        return principal;
    }

}