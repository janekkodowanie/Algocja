package io.github.janekkodowanie.algocja.section;

import java.util.List;
import java.util.Optional;

public interface SectionRepository {

    List<Section> findAll();

    Section save(Section entity);

    Optional<Section> findByName(String name);

}
