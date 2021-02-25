package com.jajjamind.payvault.core;

import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @author akena
 * 11/01/2021
 * 15:37
 **/
@RolesAllowed("ROLE.ADMIN")
@RequestMapping("/default")
public interface BaseApi<T> {

    String APPLICATION_JSON = "application/json";

    @PostMapping(consumes = APPLICATION_JSON,
            produces = APPLICATION_JSON)
    T add(@RequestBody  T t);

    @GetMapping(path="/{id}",
            produces = APPLICATION_JSON)
    T get(@PathVariable("id") Long id);

    @PutMapping(consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    T update(T t);

    @DeleteMapping(path="/{id}",produces = APPLICATION_JSON)
    T delete(@PathVariable("id") Long id);

    @GetMapping(path="/all", produces = APPLICATION_JSON)
    List<T> getAll();

}
