package br.com.guikun.srvcatcafe.domain.exception;

import org.springframework.http.HttpStatus;

public class NaoEncontradoException extends BusinessException{
    private static final long serialVersionUID = 1L;

    public NaoEncontradoException(Integer errorCode, String errorMsg, Throwable cause){
        super(errorCode,errorMsg,cause);
    }

    public NaoEncontradoException(Integer errorCode, String errormsg){
        super(errorCode,errormsg);
    }

    public NaoEncontradoException(String errorMsg){
        super(HttpStatus.NOT_FOUND.value(), errorMsg);
    }
}
