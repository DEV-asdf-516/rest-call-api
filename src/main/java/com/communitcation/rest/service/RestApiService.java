package com.communitcation.rest.service;


import com.communitcation.rest.client.CommunicationInfo;
import com.communitcation.rest.model.ImageDto;
import com.communitcation.rest.model.UrlDto;
import com.google.gson.reflect.TypeToken;
import com.theokanning.openai.completion.chat.*;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.Method;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import static com.communitcation.rest.util.JsonUtil.fromJson;
import static com.communitcation.rest.util.JsonUtil.toJson;

@Service
@RequiredArgsConstructor
public class RestApiService {

    private final CommunicationService communicationService;
    private final GptService gptService;

    @Value("${api.naver.client-id}")
    private String naverClientId;

    @Value("${api.naver.client-key}")
    private String naverClientKey;

    @Value("${api.kakao.key}")
    private String kakaoRestApiKey;


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

    // karlo 이미지 생성 api
    public String makeAiImageByKakao(String prompt) throws IOException {
        Map<String,String> karloReqHeader = Map.of(
                "Authorization", "KakaoAK " + kakaoRestApiKey
        );

        String host = "api.kakaobrain.com";
        String genImgPath = "/v2/inference/karlo/t2i";
        prompt = prompt.replaceAll("prompt=","");
        String koToEng = translateKoToEng(prompt);

        Map<String, Object> requestMap = Map.of(
                "version" , "v2.1",
                "prompt",koToEng,
                "negative_prompt","low quality, low contrast, draft, amateur, cut off, cropped, frame",
                "width" , 768,
                "height", 768,
                "image_format","png",
                "image_quality",100
        );

        CommunicationInfo karloGenApiInfo = CommunicationInfo
                .builder()
                .scheme("https")
                .port("443")
                .host(host)
                .path(genImgPath)
                .method(Method.POST)
                .headers(karloReqHeader)
                .requestData(requestMap)
                .responseClazz(Map.class)
                .build();
        Map<String,Object> imgResMap = communicationService.communicate(karloGenApiInfo);
        System.out.println(imgResMap);
        String imagesToStr = toJson(imgResMap.get("images"));
        TypeToken<List<ImageDto>> imagesTypeToken = new TypeToken<List<ImageDto>>() {};
        List<ImageDto> images = fromJson(imagesToStr, imagesTypeToken);
        ImageDto imageDto = images.get(0);
        String imageUrl =  imageDto.getImage();
        return imageUrl;
    }

    private String translateKoToEng(String prompt){
        OpenAiService openAiService = gptService.openAiService();
        String decodePrompt = URLDecoder.decode(prompt);
        String message = """
                Translate the following Korean text to English: '{%s}'
                """.formatted(decodePrompt);
        ChatMessage chatMessage = new ChatMessage(ChatMessageRole.USER.value(),message);
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .messages(List.of(chatMessage))
                .model("gpt-4o-mini")
                .build();

        ChatCompletionResult response = openAiService.createChatCompletion(chatCompletionRequest);
        ChatCompletionChoice choice = response.getChoices().get(0);
        String translatePrompt = choice.getMessage().getContent();
        String log  = """
                prompt: %s \ntranslate: %s""".formatted(decodePrompt,translatePrompt);
        System.out.println(log);
        return translatePrompt;
    }

}
