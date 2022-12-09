package br.com.adrianomenezes.gameawards.service.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String message){
        super(message);
    }
}
