package io.github.janekkodowanie.algocja.section;

import java.util.List;

public interface SectionRepository {

    List<Section> findAll();

    Section save(Section entity);

}
