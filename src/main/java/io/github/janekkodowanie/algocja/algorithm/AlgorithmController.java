package io.github.janekkodowanie.algocja.algorithm;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Controller
@RequestMapping("/algorithms")
class AlgorithmController {

    private final AlgorithmRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(AlgorithmController.class);


    @Autowired
    AlgorithmController(AlgorithmRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/desc")
    public Flux<String> showAlgorithm(@RequestParam String name) {

        logger.info("Searching for algorithm with name " + name + ".");
        Optional<Algorithm> algorithm = repository.findByName(name);

        if (algorithm.isPresent() && algorithm.get().getDescription() != null) {
            logger.info("Algorithm with name " + name + " found.");
            return Flux.just(algorithm.get().getDescription());

        } else if (algorithm.isPresent() && algorithm.get().getDescription() == null) {
            logger.info("Algorithm with name " + name + " found, but description is null.");
        }

        logger.info("Algorithm with name " + name + " not found.");
        return Flux.empty();
    }

    @GetMapping(path = "/add")
    String addAlgorithm(Model model) {
        model.addAttribute("algorithm", new Algorithm());
        return "addAlgorithm";
    }


    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    ModelAndView getAlgorithmPage() {
        ModelAndView modelAndView = new ModelAndView("algorithms");
        logger.info("Getting algorithms page.");
        modelAndView.addObject("algorithms", repository.findAll());
        return modelAndView;
    }

    /* TODO
    *   'Edit distance'
    *   'Levenshtein' co to
    *   'Jaro-Winkler distance' co to
    *   'Jaccard distance' co to
    *   'Cosine distance' co to
    *   'Hamming distance' co to
    *   Zaleznie od tego dodawac lub nie
    * */

    /*
    @PostMapping("/add/{name}")
    public ResponseEntity<Algorithm> addAlgorithm(@PathVariable String name) {
        if (repository.findByName(name).isEmpty()) {
            Algorithm algorithm = new Algorithm(name, "Undefined.");
            return ResponseEntity.ok(repository.save(algorithm));
        }

        logger.info("Algorithm with name " + name + " already exists.");
        return ResponseEntity.badRequest().build();
    }
    */
}
