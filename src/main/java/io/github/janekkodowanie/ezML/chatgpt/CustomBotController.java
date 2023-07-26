package io.github.janekkodowanie.ezML.chatgpt;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;


@RestController
@RequestMapping("/bot")
class CustomBotController {

    private static final Logger logger = LoggerFactory.getLogger(CustomBotController.class);

    @Value("${openai.model}")
    private String chatVersion;

    @Value("${openai.api.url}")
    private String apiURL;

    private final RestTemplate template;
    private final Session session;

    public CustomBotController(RestTemplate template) {
        this.template = template;
        this.session = new Session();
    }


    @PostMapping
    ResponseEntity<String> addQuery(@RequestBody String inputData) {
        ChatGPTResponse response = template.postForObject(apiURL, new ChatGPTRequest(chatVersion, inputData), ChatGPTResponse.class);
        session.addQuery(inputData, response.getChoices().get(0).getMessage().getContent());

        return ResponseEntity.ok(response.getChoices().get(0).getMessage().getContent());
    }

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    ModelAndView hello() {
        return new ModelAndView("bot");
    }


    @GetMapping("/chat")
    public String chat(@RequestParam("prompt") String prompt) {

        ChatGPTRequest request = new ChatGPTRequest(chatVersion, prompt);
        ChatGPTResponse response = template.postForObject(apiURL, request, ChatGPTResponse.class);

        return Objects.requireNonNull(response).getChoices().get(0).getMessage().getContent();
    }

}
