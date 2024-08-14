package br.com.guikun.srvcatcafe.domain.exception;

import org.springframework.http.HttpStatus;

public class TipoUsuarioInvalidoException extends BusinessException{
    private static final long serialVersionUID = 1L;

    public TipoUsuarioInvalidoException(Integer errorCode, String errorMsg, Throwable cause){
        super(errorCode,errorMsg,cause);
    }

    public TipoUsuarioInvalidoException(Integer errorCode, String errormsg){
        super(errorCode,errormsg);
    }

    public TipoUsuarioInvalidoException(String errorMsg){
        super(HttpStatus.NOT_FOUND.value(), errorMsg);
    }
}
