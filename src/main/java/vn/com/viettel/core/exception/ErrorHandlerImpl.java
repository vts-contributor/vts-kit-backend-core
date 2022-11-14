package vn.com.viettel.core.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@NoArgsConstructor
@AllArgsConstructor
public class ErrorHandlerImpl implements ResponseErrorHandler {

    private ErrorHandler errorHandler;

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        if(errorHandler != null) {
            errorHandler.onError(response);
        }
        return false;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if(errorHandler != null) {
            errorHandler.onError(response);
        }
    }

}
