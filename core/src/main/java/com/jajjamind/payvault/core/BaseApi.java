package com.jajjamind.payvault.core;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author akena
 * 11/01/2021
 * 15:37
 **/
@RequestMapping("/default")
public interface BaseApi<T> {

    String APPLICATION_JSON = "application/json";

    @PostMapping(consumes = APPLICATION_JSON,
            produces = APPLICATION_JSON)
    T add(T t);

    @GetMapping(path="/{id}",
            produces = APPLICATION_JSON)
    T get(@PathVariable("id") Long id);

    @PutMapping(consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    T update(T t);

    @DeleteMapping(produces = APPLICATION_JSON)
    T delete(long id);

    @GetMapping(path="/all", produces = APPLICATION_JSON)
    List<T> getAll();

}