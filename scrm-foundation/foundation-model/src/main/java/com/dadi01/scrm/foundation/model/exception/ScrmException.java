package com.dadi01.scrm.foundation.model.exception;


import com.dadi01.scrm.foundation.model.error.ErrorObject;

/**
 * @author fangming
 * @description
 * @date 2020/1/7 17:42
 */
public class ScrmException extends Exception {

    private ErrorObject errorObject;

    public ScrmException(ErrorObject errorObject) {
        super(errorObject.getMessage());
        this.errorObject = errorObject;
    }

    public ErrorObject getErrorObject() {
        return errorObject;
    }
}
