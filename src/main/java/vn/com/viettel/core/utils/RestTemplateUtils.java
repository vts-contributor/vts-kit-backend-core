package vn.com.viettel.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class RestTemplateUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateUtils.class);

    /**
     * Ham xu ly request dung RestTemplate cua Spring Boot
     * @param url
     * @param requestBody
     * @return
     */
    public static ResponseEntity<Object> doRequestRestTemplate(String url, HttpMethod httpMethod, Object requestBody, String token) {
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.TEXT_HTML));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        ResponseEntity<Object> exchange;
        try {
            exchange = getExchange(url, httpMethod, restTemplate, requestBody, headers);
            return exchange;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            LOGGER.error("Has ERROR", e);
        }
        return null;
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
    public static <T> ResponseEntity<Object> getExchange(String url, HttpMethod httpMethod, RestTemplate restTemplate, Object body, HttpHeaders headers) {
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
    public static ResponseEntity<Object> doRequestRestTemplateByFormUrlEncoded(String url, String token, Object requestBody){
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.TEXT_HTML));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Bearer " + token);
        ResponseEntity<Object> exchange;
        try {
            exchange = getExchange(url, HttpMethod.POST, restTemplate, requestBody, headers);
            return exchange;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            LOGGER.error("Has ERROR", e);
        }
        return null;
    }
}
