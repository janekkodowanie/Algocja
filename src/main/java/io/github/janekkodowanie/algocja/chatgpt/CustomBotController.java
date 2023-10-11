package io.github.janekkodowanie.algocja.chatgpt;


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

    @PostMapping
    public Flux<String> chat(@RequestBody String inputData) {
        logger.info("Chat prompt: " + inputData);

        /* https://docs.spring.io/spring-framework/reference/web/webflux-webclient.html */
        WebClient client = WebClient.create(apiUrl);

        /* https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpHeaders.html */
        HttpHeaders httpHeaders = new HttpHeaders();

        /* Providing authorization & request + response content type.
        *  OPENAI_API_KEY is retrieved from environmental variables. */
        httpHeaders.setBearerAuth(System.getenv("OPENAI_API_KEY"));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.TEXT_EVENT_STREAM));

        logger.info(inputData);

        /* Creating ChatGPTRequest object to be posted. */
        ChatGPTRequest request = new ChatGPTRequest(chatVersion, inputData);

        /* Creating ObjectMapper to map JSON to Java objects. */
        ObjectMapper objectMapper = new ObjectMapper();

        /* Making async HTTP POST request and processing response. */
        return client.post()
                .headers(headers -> headers.addAll(httpHeaders))
                .bodyValue(request)
                .retrieve()
                .bodyToFlux(String.class)
                .map(chunk -> {
                    try {
                        /* Parsing String into a JsonNode object. */
                        JsonNode jsonNode = objectMapper.readTree(chunk);

                        /* Returning response content. */
                        return jsonNode.get("choices").get(0).get("delta").get("content").asText();

                    } catch (Exception e) {

                        /* Handles last two responses from the OpenAI API.
                        * First one -> contains empty choices list.
                        * Second one -> entire response contains one token - [DONE].
                        * Returner value = empty string, so that no text
                        * is added in html page. */
                        logger.info(chunk);
                        return "";
                    }
                });
    }


    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    ModelAndView getBotPage() {
        return new ModelAndView("bot");
    }


}