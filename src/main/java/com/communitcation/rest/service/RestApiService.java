package com.communitcation.rest.service;

 import com.communitcation.rest.client.CommunicationInfo;
import com.communitcation.rest.model.UrlDto;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.Method;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

import static com.communitcation.rest.util.JsonUtil.fromJson;
import static com.communitcation.rest.util.JsonUtil.toJson;

@Service
@RequiredArgsConstructor
public class RestApiService {

    private final CommunicationService communicationService;

    @Value("${api.naver.client-id}")
    private String naverClientId;

    @Value("${api.naver.client-key}")
    private String naverClientKey;


    // 단축 url api
    public UrlDto makeShortUrlByNaverApi(String url) throws IOException {
        Map<String,String> naverReqHeader =  Map.of(
                           "X-Naver-Client-Id",naverClientId,
                           "X-Naver-Client-Secret",naverClientKey
        );

        String host = "openapi.naver.com";
        String shortUrlPath = "/v1/util/shorturl";

        Map<String,Object> requestMap = Map.of(
            "url" , url
        );

        CommunicationInfo naverShortUrlApiInfo = CommunicationInfo
                .builder()
                .scheme("https")
                .port("443")
                .host(host)
                .path(shortUrlPath)
                .method(Method.GET)
                .headers(naverReqHeader)
                .requestData(requestMap)
                .responseClazz(Map.class)
                .build();

        Map<String,Object> urlResMap = communicationService.communicate(naverShortUrlApiInfo);
        String resToStr = toJson(urlResMap.get("result"));
        UrlDto shortUrl = fromJson(resToStr, UrlDto.Builder.class).build();
        return  shortUrl;
    }
}
