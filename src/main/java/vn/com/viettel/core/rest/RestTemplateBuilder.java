package vn.com.viettel.core.rest;

import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import vn.com.viettel.core.exception.ErrorHandler;
import vn.com.viettel.core.exception.ErrorHandlerImpl;
import vn.com.viettel.core.utils.RestTemplateUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;

public class RestTemplateBuilder{

    private static final int DEFAULT_CONNECTION_TIMEOUT_VALUE = 60000;

    private static final int DEFAULT_READ_TIMEOUT_VALUE = 60000;
    private RestTemplate restTemplate;

    private ErrorHandler errorHandler;

    private ErrorHandlerImpl errorHandlerImpl;

    private SimpleClientHttpRequestFactory clientHttpRequestFactory;

    public RestTemplateBuilder() {
        clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT_VALUE);
        clientHttpRequestFactory.setReadTimeout(DEFAULT_READ_TIMEOUT_VALUE);
    }

    public RestTemplateBuilder setCustomClientHttpRequestFactory(SimpleClientHttpRequestFactory simpleClientHttpRequestFactory) {
        this.clientHttpRequestFactory = simpleClientHttpRequestFactory;
        return this;
    }

    public RestTemplateBuilder setConnectTimeOut(Integer connectTimeout){
        int connectTimeoutValue = connectTimeout == null ? DEFAULT_CONNECTION_TIMEOUT_VALUE : connectTimeout;
        clientHttpRequestFactory.setConnectTimeout(connectTimeoutValue);
        return this;
    }
    public RestTemplateBuilder setReadTimeOut(Integer readTimeout){
        int readTimeoutValue = readTimeout == null ? DEFAULT_READ_TIMEOUT_VALUE : readTimeout;
        clientHttpRequestFactory.setReadTimeout(readTimeoutValue);
        return this;
    }

    public RestTemplateBuilder setProxy(String host, Integer port){
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port));
        clientHttpRequestFactory.setProxy(proxy);
        return this;
    }

    public RestTemplateBuilder setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
        return this;
    }

    public RestTemplateUtils build() {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.TEXT_HTML, MediaType.APPLICATION_XML));
        restTemplate = new RestTemplate(clientHttpRequestFactory);
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        if(errorHandler != null){
            restTemplate.setErrorHandler(new ErrorHandlerImpl(new ErrorHandler() {
                @Override
                public void onError(ClientHttpResponse response) throws IOException {
                    errorHandler.onError(response);
                }
            }));
        }
        return new RestTemplateUtils(restTemplate);
    }

}
