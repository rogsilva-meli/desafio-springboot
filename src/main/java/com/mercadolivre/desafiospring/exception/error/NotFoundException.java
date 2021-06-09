package com.mercadolivre.desafiospring.exception.error;

public class NotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1l;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
