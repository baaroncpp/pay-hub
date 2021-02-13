package com.jajjamind.payvault.core.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author akena
 * 13/02/2021
 * 00:33
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SystemException {
    private String message;
}
