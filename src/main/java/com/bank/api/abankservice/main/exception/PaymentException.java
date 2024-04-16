package com.bank.api.abankservice.main.exception;

public class PaymentException extends RuntimeException{

    private Integer code;

    public PaymentException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public PaymentException(String message) {
            super(message);
    }

    public Integer getCode() {
            return code;
    }

}
