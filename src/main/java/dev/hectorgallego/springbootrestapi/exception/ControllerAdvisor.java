package dev.hectorgallego.springbootrestapi.exception;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> noSuchElementException(NoSuchElementException ex) {

        Map<String, Object> body = new HashMap<>();

        body.put("timestamp", LocalDate.now());
        body.put("message", ex.getLocalizedMessage());
        body.put("status", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> badCredentialsException(BadCredentialsException ex) {

        Map<String, Object> body = new HashMap<>();

        body.put("timestamp", LocalDate.now());
        body.put("message", ex.getLocalizedMessage());
        body.put("status", HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }




    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {


                List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() +" " + fieldError.getDefaultMessage() )
                .collect(Collectors.toList());


                Map<String, Object> body = new HashMap<>();

                body.put("timestamp", LocalDate.now());
                body.put("message", "falta completar campos obligatorios");
                body.put("status", HttpStatus.BAD_REQUEST);
                body.put("erros",errorList);

        
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

   
 



}
