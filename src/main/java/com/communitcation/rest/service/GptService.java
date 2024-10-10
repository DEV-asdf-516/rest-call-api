package com.communitcation.rest.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.theokanning.openai.service.OpenAiService;

import java.time.Duration;

@Configuration
public class GptService {

    @Value("${api.gpt.key}")
    private String gptKey;
    @Bean
    public OpenAiService openAiService(){
          OpenAiService service = new OpenAiService(gptKey, Duration.ofSeconds(120));
          return service;
    }
}
