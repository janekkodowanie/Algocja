package io.github.janekkodowanie.ezML.algorithm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SQLAlgorithmRepository extends AlgorithmRepository, JpaRepository<Algorithm, Integer> {

    @Override
    List<Algorithm> findAll();


}
