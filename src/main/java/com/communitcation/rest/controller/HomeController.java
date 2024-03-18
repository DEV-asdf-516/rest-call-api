package com.communitcation.rest.controller;

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

    private final RestApiService restApiService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/karlo-img")
    public String kakaoGenAiImageApi(
            @RequestBody  String prompt,
            HttpServletRequest request
    ) throws IOException {
          String genImage =  restApiService.makeAiImageByKakao(prompt);
          HttpSession session = request.getSession();
          session.setAttribute("genImage",genImage);
          session.setAttribute("prompt", prompt);
        return "redirect:/";
    }


}
