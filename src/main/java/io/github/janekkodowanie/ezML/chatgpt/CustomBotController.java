package io.github.janekkodowanie.ezML.chatgpt;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Flux;

import java.util.List;


@RestController
@RequestMapping("/bot")
@NoArgsConstructor
public class CustomBotController {

    private static final Logger logger = LoggerFactory.getLogger(CustomBotController.class);

    @Value("${openai.model}")
    private String chatVersion;

    @Value("${openai.api.url}")
    private String apiUrl;


    /* TODO
    @GetMapping(path = "/{name}")
    Flux<String> getAlgorithm() {
        return Flux.just("Hello, world!");
    }
     */


    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    ModelAndView getBotPage() {
        return new ModelAndView("bot");
    }


}