package io.github.janekkodowanie.ezML.algorithm;


import java.util.List;
import java.util.Optional;

public interface AlgorithmRepository {

    List<Algorithm> findAll();

    Optional<Algorithm> findByName(String name);

    Algorithm save(Algorithm entity);
}
