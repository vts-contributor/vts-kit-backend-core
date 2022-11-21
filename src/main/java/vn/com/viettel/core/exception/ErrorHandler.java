package vn.com.viettel.core.exception;

import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public interface ErrorHandler {
    public void onError(ClientHttpResponse response) throws IOException;
}
