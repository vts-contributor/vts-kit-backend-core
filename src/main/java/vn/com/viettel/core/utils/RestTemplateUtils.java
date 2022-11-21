package vn.com.viettel.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestTemplateUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateUtils.class);

    private RestTemplate restTemplate;

    public RestTemplateUtils(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Ham xu ly request dung RestTemplate cua Spring Boot
     * @param url
     * @param requestBody
     * @return
     */
    public ResponseEntity<Object> doRequestRestTemplate(String url, HttpMethod httpMethod, Object requestBody, String token, MediaType contentType) {
        HttpHeaders headers = new HttpHeaders();
        MediaType contentTypeValue = contentType == null ? MediaType.APPLICATION_JSON : contentType;
        headers.setContentType(contentTypeValue);
        headers.add("Authorization", "Bearer " + token);
        ResponseEntity<Object> exchange;
        exchange = getExchange(url, httpMethod, restTemplate, requestBody, headers);
        return exchange;
    }

    /**
     *
     * @param url
     * @param httpMethod
     * @param restTemplate
     * @param body
     * @param headers
     * @param <T>
     * @return
     */
    public <T> ResponseEntity<Object> getExchange(String url, HttpMethod httpMethod, RestTemplate restTemplate, Object body, HttpHeaders headers) {
        return restTemplate.exchange(url,
                httpMethod, new org.springframework.http.HttpEntity<>(body, headers),
                Object.class);
    }

    /**
     *
     * @param url
     * @param token
     * @param requestBody
     * @return
     */
    public ResponseEntity<Object> doRequestRestTemplateByFormUrlEncoded(String url, String token, Object requestBody){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Bearer " + token);
        ResponseEntity<Object> exchange;
        exchange = getExchange(url, HttpMethod.POST, restTemplate, requestBody, headers);
        return exchange;
    }
}
