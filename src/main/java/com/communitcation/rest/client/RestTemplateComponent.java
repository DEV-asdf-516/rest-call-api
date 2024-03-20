package com.communitcation.rest.client;


import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import java.util.Collections;


@Component
@RequiredArgsConstructor
public class RestTemplateComponent {

    private final RestTemplateConfig restConfig;
    // get
    public <T> ResponseEntity <T> getMethod(HttpHeaders headers, String url, Class<T> clazz){
        HttpEntity<?>  getRequestEntity = new HttpEntity<>(headers);
        ResponseEntity<T> getResponse =  restConfig
                .restTemplate()
                .exchange(url, HttpMethod.GET,getRequestEntity,clazz);
        return getResponse;
    }

    // post
    public <B> ResponseEntity<B> postMethod(HttpHeaders headers, String url, B body, Class<?> clazz){
        HttpEntity<?>  postRequestEntity = new HttpEntity<>(body,headers);
        ResponseEntity<B> postResponse = (ResponseEntity<B>) restConfig
                .restTemplate()
                .exchange(url, HttpMethod.POST, postRequestEntity, clazz);
        return postResponse;
    }

    // put
    public <B> ResponseEntity<B> putMethod(HttpHeaders headers, String url, B body, Class<?> clazz){
        HttpEntity<?>  putRequestEntity = new HttpEntity<>(body,headers);
        ResponseEntity<B> putResponse = (ResponseEntity<B>) restConfig
                .restTemplate()
                .exchange(url,HttpMethod.PUT,putRequestEntity,clazz);
        return putResponse;
    }

    // delete
    public <T> ResponseEntity<T> deleteMethod(HttpHeaders headers, String url, Class<T> clazz){
        HttpEntity<?> deleteRequestEntity = new HttpEntity<>(headers);
        ResponseEntity<T> deleteResponse =  restConfig
                .restTemplate()
                .exchange(url,HttpMethod.DELETE,deleteRequestEntity,clazz);
        return deleteResponse;
    }

    // json header 기본설정
    public HttpHeaders createBodyHeader(MediaType mediaType){
        final HttpHeaders jsonHeaders = new HttpHeaders();
        jsonHeaders.setContentType(mediaType);
        jsonHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return jsonHeaders;
    }

    // query param header 기본설정
    public HttpHeaders createQueryParamHeader(){
        final HttpHeaders urlEncodedHeaders = new HttpHeaders();
        urlEncodedHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        urlEncodedHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return urlEncodedHeaders;
    }
}
