package io.github.janekkodowanie.ezML.algorithm;


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

import java.util.List;
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

    @GetMapping("/all")
    public ResponseEntity<List<Algorithm>> readAllAlgorithms() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping(path = "/desc")
    public ModelAndView showAlgorithm(@RequestParam String name, Model model) {

        logger.info("Searching for algorithm with name " + name + ".");
        Optional<Algorithm> algorithm = repository.findByName(name);

        if (algorithm.isPresent()) {
            logger.info("Algorithm with name " + name + " found.");
            model.addAttribute("algorithm", algorithm.get());

        } else {
            logger.info("Algorithm with name " + name + " not found.");
        }
        return new ModelAndView("algorithms");
    }


    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    ModelAndView getAlgorithmPage(Model model) {
        logger.info("Getting algorithms page.");
        model.addAttribute("algorithms", repository.findAll());
        return new ModelAndView("algorithms");
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
