package vn.com.viettel.core.exception;

import vn.com.viettel.core.rest.ErrorDTO;
import vn.com.viettel.core.utils.RestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface IExceptionHandler {

    @ExceptionHandler({AccessDeniedException.class})
    default ResponseEntity<ErrorDTO> handleAccessDeniedException(AccessDeniedException ex) {
        return RestUtils.responseStatus(HttpStatus.FORBIDDEN, new ErrorDTO(HttpStatus.FORBIDDEN.value(), "Access Denied"));
    }

    @ExceptionHandler({AuthenticationException.class})
    default ResponseEntity<ErrorDTO> handleAuthenticationException(AuthenticationException ex) {
        return RestUtils.responseStatus(HttpStatus.UNAUTHORIZED, new ErrorDTO(HttpStatus.UNAUTHORIZED.value(), "Unauthorized"));
    }

    @ExceptionHandler({Exception.class})
    default ResponseEntity<ErrorDTO> handleCommonException(Exception ex) {
        return RestUtils.responseServerError();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    default ResponseEntity<ErrorDTO> handleMethodArgumentException(MethodArgumentNotValidException ex) {
        List<String> errorList = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> errorList.add(error.getDefaultMessage()));
        String errorMessage = errorList.stream().collect(Collectors.joining("; "));
        return RestUtils.responseStatus(HttpStatus.BAD_REQUEST, new ErrorDTO(HttpStatus.BAD_REQUEST.value(), errorMessage));
    }

    @ExceptionHandler({ConstraintViolationException.class})
    default ResponseEntity<ErrorDTO> handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> errorList = new ArrayList<>();
        ex.getConstraintViolations().forEach(error -> errorList.add(error.getMessage()));
        String errorMessage = errorList.stream().collect(Collectors.joining("; "));
        return RestUtils.responseStatus(HttpStatus.BAD_REQUEST, new ErrorDTO(HttpStatus.BAD_REQUEST.value(), errorMessage));
    }

    @ExceptionHandler({BindException.class, MissingServletRequestPartException.class,
            MissingServletRequestParameterException.class, MissingRequestHeaderException.class})
    default ResponseEntity<ErrorDTO> handleMissingPartException(Exception ex) {
        return RestUtils.responseStatus(HttpStatus.BAD_REQUEST, new ErrorDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

}
