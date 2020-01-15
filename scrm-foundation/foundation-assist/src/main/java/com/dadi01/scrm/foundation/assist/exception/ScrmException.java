package com.dadi01.scrm.foundation.assist.exception;
import com.dadi01.scrm.foundation.model.error.ErrorObject;

import java.io.Serializable;

/**
 * @author fangming
 * @description
 * @date 2020/1/7 17:42
 */
public class ScrmException extends RuntimeException implements Serializable {

    private ErrorObject errorObject;
    public ScrmException(){super();}
    public ScrmException(ErrorObject errorObject) {
        super(errorObject.getMessage());
        this.errorObject = errorObject;
    }

    public ErrorObject getErrorObject() {
        return errorObject;
    }

}
