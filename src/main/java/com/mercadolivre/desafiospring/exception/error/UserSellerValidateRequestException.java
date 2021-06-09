package com.mercadolivre.desafiospring.exception.error;

public class UserSellerValidateRequestException extends RuntimeException{
    private static final long serialVersionUID = 1l;

    public UserSellerValidateRequestException(String message) {
        super(message);
    }

    public UserSellerValidateRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
