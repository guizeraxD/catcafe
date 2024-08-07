package br.com.guikun.srvcatcafe.domain.exception;

public class BusinessException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private final Integer errorCode;
    private final String errorMsg;

    public BusinessException(Integer errorCode, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BusinessException(Integer errorCode, String errorMsg){
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
