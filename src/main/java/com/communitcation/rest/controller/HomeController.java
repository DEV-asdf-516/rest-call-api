package com.communitcation.rest.controller;

import com.communitcation.rest.service.GenerateImageService;
import com.communitcation.rest.service.RestApiService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final GenerateImageService generateImageService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/ai-img")
    public String openAiImageApi(
            @RequestBody  String prompt,
            HttpServletRequest request
    ) {
          String genImage =  generateImageService.generateImageUrl(prompt);
          HttpSession session = request.getSession();
          session.setAttribute("genImage",genImage);
          session.setAttribute("prompt", prompt);
        return "redirect:/";
    }
}
