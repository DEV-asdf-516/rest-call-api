package com.communitcation.rest.service;


import com.theokanning.openai.completion.chat.*;
import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.image.ImageResult;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.util.List;

@Service
public class GenerateImageService {

    @Autowired
    private GptService gptService;
    private OpenAiService openAiService;

    public GenerateImageService(GptService gptService){
        this.gptService = gptService;
        this.openAiService = gptService.openAiService();
    }

    private String translateKoToEng(String prompt){
        String decodePrompt = URLDecoder.decode(prompt);
        String message = """
                Translate the following Korean text to English and Return only the translated result in English.: %s """.formatted(decodePrompt);
        ChatMessage chatMessage = new ChatMessage(ChatMessageRole.USER.value(),message);
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .messages(List.of(chatMessage))
                .model("gpt-4o-mini")
                .build();

        ChatCompletionResult response = openAiService.createChatCompletion(chatCompletionRequest);
        ChatCompletionChoice choice = response.getChoices().get(0);
        String translatePrompt = choice.getMessage().getContent();
        String log  = """
                [%s]\ntranslate: %s""".formatted(decodePrompt,translatePrompt);
        System.out.println(log);
        return translatePrompt;
    }

    public String generateImageUrl(String prompt){
        String message = translateKoToEng(prompt);
        CreateImageRequest createImageRequest = CreateImageRequest.builder()
                .prompt(message)
                .build();
        ImageResult images = openAiService.createImage(createImageRequest);
        String imageUrl = images.getData().get(0).getUrl();
        return imageUrl;
    }

}


