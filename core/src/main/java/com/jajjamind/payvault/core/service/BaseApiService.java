package com.jajjamind.payvault.core.service;

import java.util.List;

/**
 * @author akena
 * 18/01/2021
 * 03:25
 **/
public interface BaseApiService<T> {

    T add(T t);
    T get(Long id);
    T update(T t);
    T delete(Long id);
    List<T> getAll();
}
