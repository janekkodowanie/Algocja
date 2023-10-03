package io.github.janekkodowanie.ezML.algorithm;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.janekkodowanie.ezML.chatgpt.ChatGPTRequest;
import io.github.janekkodowanie.ezML.chatgpt.CustomBotController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Flux;

import java.util.List;

@Controller
@RequestMapping("/algorithms")
class AlgorithmController {

    private final AlgorithmRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(AlgorithmController.class);


    @Autowired
    AlgorithmController(AlgorithmRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Algorithm>> readAllAlgorithms() {
        return ResponseEntity.ok(repository.findAll());
    }


    @Value("${openai.model}")
    private String chatVersion;

    @Value("${openai.api.url}")
    private String apiUrl;


    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    ModelAndView getAlgorithmPage(Model model) {
        model.addAttribute("algorithms", repository.findAll());
        return new ModelAndView("algorithms");
    }

    @GetMapping(path = "/{name}")
    public Flux<String> showAlgorithm(@PathVariable String name) {
        try {
            logger.info("Chat prompt: " + name);

            /* https://docs.spring.io/spring-framework/reference/web/webflux-webclient.html */
            WebClient client = WebClient.create(apiUrl);

            /* https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpHeaders.html */
            HttpHeaders httpHeaders = new HttpHeaders();

            /* Providing authorization & request + response content type.
             *  OPENAI_API_KEY is retrieved from environmental variables. */
            httpHeaders.setBearerAuth(System.getenv("OPENAI_API_KEY"));
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.setAccept(List.of(MediaType.TEXT_EVENT_STREAM));

            /* Creating ChatGPTRequest object to be posted. */
            ChatGPTRequest request = new ChatGPTRequest(chatVersion, "Elaborate " + name + " algorithm.");

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
        catch (Exception e) {
            logger.info("Exception: " + e.getMessage());
            return Flux.just("Exception: " + e.getMessage());
        }

//        return ResponseEntity.ok(repository.findByName(name).orElseThrow(() -> new IllegalArgumentException(name)));
    }

    /* TODO
    * 'Edit distance'
    * 'Levenshtein' co to
    * 'Jaro-Winkler distance' co to
    * 'Jaccard distance' co to
    * 'Cosine distance' co to
    * 'Hamming distance' co to
    * Zaleznie od tego dodawac lub nie
    * */

    @PostMapping("/add/{name}")
    public ResponseEntity<Algorithm> addAlgorithm(@PathVariable String name) {
        if (repository.findByName(name).isEmpty()) {
            Algorithm algorithm = new Algorithm(name, "Undefined.");
            return ResponseEntity.ok(repository.save(algorithm));
        }

        logger.info("Algorithm with name " + name + " already exists.");
        return ResponseEntity.badRequest().build();
    }



}
