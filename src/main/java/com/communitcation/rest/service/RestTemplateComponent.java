package com.communitcation.rest.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;


@Getter
@Setter
public class RestTemplateComponent {

    private  RestTemplate restTemplate;

    // get
    public <T> ResponseEntity<T> getMethod(HttpHeaders headers, String url, Class<T> clazz){
        HttpEntity<T>  getRequestEntity = new HttpEntity<>(headers);
        ResponseEntity<T> getResponse = (ResponseEntity<T>) restTemplate.exchange(url, HttpMethod.GET,getRequestEntity,clazz);
        return getResponse;
    }


    // post
    public <B> ResponseEntity<B> postMethod(HttpHeaders headers, String url, B body, Class<?> clazz){
        HttpEntity<B>  postRequestEntity = new HttpEntity<>(body,headers);
        ResponseEntity<B> postResponse = (ResponseEntity<B>) restTemplate.exchange(url, HttpMethod.POST, postRequestEntity, clazz);
        return postResponse;
    }

    // put
    public <B> ResponseEntity<B> putMethod(HttpHeaders headers, String url, B body, Class<?> clazz){
        HttpEntity<B>  putRequestEntity = new HttpEntity<>(body,headers);
        ResponseEntity<B> putResponse = (ResponseEntity<B>) restTemplate.exchange(url,HttpMethod.PUT,putRequestEntity,clazz);
        return putResponse;
    }


    // delete
    public <T> ResponseEntity<T> deleteMethod(HttpHeaders headers, String url, Class<T> clazz){
        HttpEntity<T> deleteRequestEntity = new HttpEntity<>(headers);
        ResponseEntity<T> deleteResponse = (ResponseEntity<T>) restTemplate.exchange(url,HttpMethod.DELETE,deleteRequestEntity,clazz);
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
