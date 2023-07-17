package io.github.janekkodowanie.ezML.chatgpt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@RestController
@RequestMapping("/bot")
class CustomBotController {

    @Value("${openai.model}")
    private String model;


    @Value("${openai.api.url}")
    private String apiURL;


    @Autowired
    private RestTemplate template;


    @GetMapping("/chat")
    public String chat(@RequestParam("prompt") String prompt) {

        ChatGPTRequest request = new ChatGPTRequest(model, prompt);
        ChatGPTResponse response = template.postForObject(apiURL, request, ChatGPTResponse.class);

        return Objects.requireNonNull(response).getChoices().get(0).getMessage().getContent();
    }

}
