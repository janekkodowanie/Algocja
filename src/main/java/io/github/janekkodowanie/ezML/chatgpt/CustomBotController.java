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
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
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
    public Flux<String> addQuery(@RequestBody String inputData) {
        logger.info(inputData);

        List<String> responses = Arrays.asList(
                "Response 1 to: " + inputData,
                "Response 2 to: " + inputData,
                "Response 3 to: " + inputData
        );

        return Flux.fromIterable(responses)
                .delayElements(Duration.ofSeconds(1));
    }

    public static class InputData {
        private final String input;

        public InputData(String input) {
            this.input = input;
        }

        public String getInput() {
            return input;
        }
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