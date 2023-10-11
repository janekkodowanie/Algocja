package io.github.janekkodowanie.algocja.section;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SQLSectionRepository extends SectionRepository, JpaRepository<Section, Integer> {

    @Override
    List<Section> findAll();

}
