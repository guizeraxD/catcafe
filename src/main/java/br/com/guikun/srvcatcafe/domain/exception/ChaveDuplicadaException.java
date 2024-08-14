package br.com.guikun.srvcatcafe.domain.exception;

import org.springframework.http.HttpStatus;

public class ChaveDuplicadaException extends BusinessException{
    private static final long serialVersionUID = 1L;

    public ChaveDuplicadaException(Integer errorCode, String errorMsg, Throwable cause){
        super(errorCode,errorMsg,cause);
    }

    public ChaveDuplicadaException(Integer errorCode, String errormsg){
        super(errorCode,errormsg);
    }

    public ChaveDuplicadaException(String errorMsg){
        super(HttpStatus.BAD_REQUEST.value(), errorMsg);
    }
}
