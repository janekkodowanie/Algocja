package io.github.janekkodowanie.ezML.chatgpt;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Flux;

import java.awt.image.DataBuffer;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping("/bot")
@NoArgsConstructor
class CustomBotController {

    private static final Logger logger = LoggerFactory.getLogger(CustomBotController.class);

    @Value("${openai.model}")
    private String chatVersion;

    @Value("${openai.api.url}")
    private String apiURL;

    @PostMapping
    public Flux<String> addQuery(@RequestBody String inputData) {
        logger.info(inputData);

        WebClient client = WebClient.create("https://api.openai.com/v1");

        ChatGPTRequest request = new ChatGPTRequest(chatVersion, inputData);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + System.getenv("OPENAI_API_KEY"));

        return client.post().uri("/chat/completions")
                .headers(headers -> headers.addAll(httpHeaders))
                .bodyValue(request)
                .retrieve()
                .bodyToFlux(Chunk.class)
                .map(chunk -> {
                    if (chunk.getChoices().get(0).getDelta().getContent() == null) {
                        return ".";
                    }
                    return chunk.getChoices().get(0).getDelta().getContent();
                });
    }


    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    ModelAndView hello() {
        return new ModelAndView("bot");
    }


}