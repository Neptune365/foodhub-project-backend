package com.project.FoodHub.exception.dto;

import com.project.FoodHub.exception.*;
import com.project.FoodHub.exception.dto.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CategoriaNoValidaException.class)
    public ResponseEntity<ErrorMessage> handleCategoriaNoValidaException(CategoriaNoValidaException exception) {
        ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(CuentaNoConfirmadaException.class)
    public ResponseEntity<ErrorMessage> handleCuentaNoConfirmadaException(CuentaNoConfirmadaException exception) {
        ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
    }
    @ExceptionHandler(UsuarioNoValidoException.class)
    public ResponseEntity<ErrorMessage> handleUsuarioNoValidoException(UsuarioNoValidoException exception) {
        ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
    }

    @ExceptionHandler(ListaRecetasNulaException.class)
    public ResponseEntity<ErrorMessage> handleListaRecetasNulaException(ListaRecetasNulaException exception) {
        ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    @ExceptionHandler(UsuarioNoAutenticadoException.class)
    public ResponseEntity<ErrorMessage> handleUsuarioNoAutenticadoException(UsuarioNoAutenticadoException exception) {
        ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
    }
    @ExceptionHandler(ColegiadoNoEncontradoException.class)
    public ResponseEntity<ErrorMessage> handleColegiadoNoEncontradoException(ColegiadoNoEncontradoException exception) {
        ErrorMessage messageException = new ErrorMessage(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageException);
    }
    @ExceptionHandler(RecetaNoEncontradaException.class)
    public ResponseEntity<ErrorMessage> handleRecetaNoEncontradaException(RecetaNoEncontradaException exception) {
        ErrorMessage messageException = new ErrorMessage(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageException);
    }
    @ExceptionHandler(CreadorNoEncontradoException.class)
    public ResponseEntity<ErrorMessage> handleCreadorNotFoundException(CreadorNoEncontradoException exception) {
        ErrorMessage messageException = new ErrorMessage(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageException);
    }

    @ExceptionHandler(TokenNoEncontradoException.class)
    public ResponseEntity<ErrorMessage> handleTokenNoEncontradoException(TokenNoEncontradoException exception) {
        ErrorMessage messageException = new ErrorMessage(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageException);
    }

    @ExceptionHandler(CorreoConfirmadoException.class)
    public ResponseEntity<ErrorMessage> handleCorreoConfirmadoException(CorreoConfirmadoException exception) {
        ErrorMessage messageException = new ErrorMessage(LocalDateTime.now(), HttpStatus.CONFLICT.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(messageException);
    }

    @ExceptionHandler(TokenExpiradoException.class)
    public ResponseEntity<ErrorMessage> handleTokenExpiradoException(TokenExpiradoException exception) {
        ErrorMessage messageException = new ErrorMessage(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(messageException);
    }

    @ExceptionHandler(CorreoExistenteException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorMessage> handleCorreoExistenteException(CorreoExistenteException exception) {
        ErrorMessage messageException = new ErrorMessage(LocalDateTime.now(), HttpStatus.CONFLICT.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(messageException);
    }

    @ExceptionHandler(ColegiadoNoValidoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> handleColegiadoNoValidoException(ColegiadoNoValidoException exception) {
        ErrorMessage messageException = new ErrorMessage(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageException);
    }

    @ExceptionHandler(CuentaNoCreadaException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorMessage> handleCuentaNoCreadaException(CuentaNoCreadaException exception) {
        ErrorMessage messageException = new ErrorMessage(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageException);
    }

    @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class})
    public ResponseEntity<ErrorMessage> handleAuthenticationException(Exception exception) {
        ErrorMessage messageException = new ErrorMessage(LocalDateTime.now(), HttpStatus.FORBIDDEN.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(messageException);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
