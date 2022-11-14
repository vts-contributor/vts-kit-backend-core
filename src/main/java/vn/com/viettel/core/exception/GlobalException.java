package vn.com.viettel.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import vn.com.viettel.core.config.I18n;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidateException.class)
    public ResponseEntity<Object> handleValidateException(ValidateException ex) {
        return ResponseEntity.status(HttpStatus.resolve(ex.getErrorCode())).body(I18n.getMessage(ex.getMessage()));
    }

}
