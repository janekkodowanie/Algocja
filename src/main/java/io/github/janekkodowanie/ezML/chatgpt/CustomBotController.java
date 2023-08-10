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


@RestController
@RequestMapping("/bot")
@NoArgsConstructor
class CustomBotController {

    private static final Logger logger = LoggerFactory.getLogger(CustomBotController.class);

    @Value("${openai.model}")
    private String chatVersion;

    @Value("${openai.api.url}")
    private String apiUrl;

    @PostMapping
    public Flux<String> addQuery(@RequestBody String inputData) {

        /* todo comment */

        logger.info("Chat prompts: " + inputData);
        WebClient client = WebClient.create(apiUrl);

        ChatGPTRequest request = new ChatGPTRequest(chatVersion, inputData);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + System.getenv("OPENAI_API_KEY"));

        ObjectMapper objectMapper = new ObjectMapper();

        return client.post()
                .headers(headers -> headers.addAll(httpHeaders))
                .bodyValue(request)
                .retrieve()
                .bodyToFlux(String.class)
                .map(chunk -> {
                    try {
                        JsonNode jsonNode = objectMapper.readTree(chunk);
                        return jsonNode.get("choices").get(0).get("delta").get("content").asText();
                    } catch (Exception e) {
                        return "";
                    }
                });
    }


    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    ModelAndView hello() {
        return new ModelAndView("bot");
    }


}