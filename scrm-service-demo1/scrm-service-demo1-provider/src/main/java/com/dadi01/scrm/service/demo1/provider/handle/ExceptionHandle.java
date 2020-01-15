package com.dadi01.scrm.service.demo1.provider.handle;

import com.dadi01.scrm.foundation.model.dto.ResultDTO;
import com.dadi01.scrm.foundation.model.error.ErrorEnum;
import com.dadi01.scrm.foundation.model.error.ErrorObject;
import com.dadi01.scrm.foundation.model.exception.ScrmException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author fangming
 * @description 异常处理
 * @date 2020/1/7 17:33
 */
@Component
@Aspect
public class ExceptionHandle {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @Pointcut("execution(* com.dadi01.scrm.service.demo1.api.*.*(..))")
    public void exceptionIntercept(){

    }

    @Around("exceptionIntercept()")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) throws Throwable{
        Object result = null;
        Object[] args = pjp.getArgs();
        try {
            return pjp.proceed();
        } catch (ScrmException e){
            result = handlerException(pjp, e);
        } catch (Exception e) {
            result = handlerException(pjp, e);
        } catch (Throwable e) {
            result = handlerException(pjp, e);
        }
        return result;
    }

    private ResultDTO handlerException(ProceedingJoinPoint pjp, Throwable e) {
        pjp.getArgs();
        ErrorObject errorObject = null;
        // 已知异常
        if (e instanceof ScrmException) {
            errorObject = ((ScrmException) e).getErrorObject();
        } else {
            //未知异常
            logger.error(pjp.getSignature().toString(), e);
            errorObject = ErrorEnum.SYSTEM_BUSY_ERROR.build(e.getMessage());
        }
        return ResultDTO.failure(errorObject);
    }
}
