package io.github.janekkodowanie.ezML.algorithm;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/algorithms")
class AlgorithmController {

    private final AlgorithmRepository repository;


    @Autowired
    AlgorithmController(AlgorithmRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Algorithm>> readAllAlgorithms() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping(path = "/name")
    public ResponseEntity<Algorithm> showAlgorithm(@RequestParam String name) {
        return ResponseEntity.ok(repository.findByName(name).orElseThrow(() -> new IllegalArgumentException(name)));
    }


}
