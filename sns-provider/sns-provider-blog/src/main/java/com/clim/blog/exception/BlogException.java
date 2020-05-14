package com.clim.blog.exception;

import com.clim.common.enums.ErrorCodeEnum;
import com.clim.common.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BlogException extends BusinessException {
    Logger logger = LoggerFactory.getLogger(getClass());
    public BlogException() {
    }


    public BlogException(String message) {
        super(message);
    }


    public BlogException(int code, String message) {
        super(code, message);
        logger.info("<== BlogException, code:{}, message:{}", this.code, super.getMessage());
    }

    public BlogException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
        logger.info("<== BlogException, code:{}, message:{}", this.code, super.getMessage());
    }

    public BlogException(ErrorCodeEnum codeEnum, Object... args) {
        super(codeEnum, args);
        logger.info("<== BlogException, code:{}, message:{}", this.code, super.getMessage());
    }
    public BlogException(ErrorCodeEnum codeEnum) {
        super(codeEnum);
        logger.info("<== BlogException, code:{}, message:{}", this.code, super.getMessage());
    }
}
