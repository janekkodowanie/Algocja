package io.github.janekkodowanie.algocja.algorithm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SQLAlgorithmRepository extends AlgorithmRepository, JpaRepository<Algorithm, Integer> {

    @Override
    List<Algorithm> findAll();

    /* TODO
    *   add description variable to Algorithm entity
    *   type -> text - cause long text
    * */

}
