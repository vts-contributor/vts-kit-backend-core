package vn.com.viettel.core.exception;

import lombok.Data;

@Data
public class ValidateException extends RuntimeException {

    private int errorCode = 404;

    public ValidateException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.setErrorCode(errorCode);
    }

    public ValidateException(String errorMessage) {
        super(errorMessage);
    }

}
