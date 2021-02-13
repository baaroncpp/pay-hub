package com.jajjamind.payvault.core.exception;

import com.jajjamind.commons.exceptions.BadRequestException;
import com.jajjamind.commons.utils.RealTimeUtil;
import com.jajjamind.payvault.core.api.constants.ErrorMessageConstants;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author akena
 * 13/02/2021
 * 00:33
 **/
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = {UnsupportedOperationException.class,ServiceApiNotSupported.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SystemException unsupportedOperations(UnsupportedOperationException ex, ServiceApiNotSupported sex){
        if(sex != null) {
            ex.printStackTrace();
            return getSystemException(sex.getMessage());
        }

        ex.printStackTrace();
        return getSystemException(ErrorMessageConstants.OPERATION_NOT_SUPPORTED);
    }

    @ExceptionHandler(value = {BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SystemException unsupportedOperations(BadRequestException bre){
             bre.printStackTrace();
            return getSystemException(bre.getMessage());
    }


    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SystemException httpParsingException(HttpMessageNotReadableException ex){
        ex.printStackTrace();
        return getSystemException("Http request body could not be parsed. Ensure that correct request format is used");
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public SystemException genericException(Exception ex){
        final String tracingId = RealTimeUtil.generateTransactionId();
        logException(ex,tracingId);
        if(ex instanceof HibernateException){
            return getSystemException("Record CRUD operation failure. Please contact support ID: "+tracingId);

        }

        if(ex instanceof RuntimeException){
            return getSystemException("System exception occurred. Please contact support ID: "+tracingId);
        }
        return getSystemException(ex.getMessage());
    }

    private void logException(Exception ex,String tracingId){
        ex.addSuppressed(new Throwable("Support ID: "+tracingId));
        ex.printStackTrace();
    }
    private SystemException getSystemException(String message){
        return new SystemException(message);
    }
}
