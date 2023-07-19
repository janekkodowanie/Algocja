package io.github.janekkodowanie.ezML.algorithm;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface AlgorithmRepository {

    List<Algorithm> findAll();

    Optional<Algorithm> findByName(String name);
}
