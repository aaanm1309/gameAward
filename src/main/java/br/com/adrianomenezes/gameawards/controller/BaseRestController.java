package br.com.adrianomenezes.gameawards.controller;

import br.com.adrianomenezes.gameawards.service.exception.BusinessException;
import br.com.adrianomenezes.gameawards.service.exception.NoContentException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public abstract class BaseRestController {

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<Void> handlerNoContent(NoContentException exception){
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiErrorDTO> handlerBusinessException(BusinessException exception){
        ApiErrorDTO error = new ApiErrorDTO(exception.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiErrorDTO> handlerUnexpectedException(Throwable exception){
        exception.printStackTrace();
        ApiErrorDTO error = new ApiErrorDTO("Ops, ocorreu um erro inesperado. /n"+exception.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }
}
