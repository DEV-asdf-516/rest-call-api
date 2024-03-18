package com.communitcation.rest.controller;

import com.communitcation.rest.model.UrlDto;
import com.communitcation.rest.service.RestApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class RestApiCallController {

    private final RestApiService restApiService;

    @GetMapping("/naver/short-url")
    public ResponseEntity<?> naverShortUrlApi(
            @RequestParam("url")  String url
    ) throws IOException {
        UrlDto shortUrl = restApiService.makeShortUrlByNaverApi(url);
        return ResponseEntity
                .ok(shortUrl);
    }

}
