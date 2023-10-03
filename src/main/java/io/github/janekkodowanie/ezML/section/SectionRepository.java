package io.github.janekkodowanie.ezML.section;

import java.util.List;

public interface SectionRepository {

    List<Section> findAll();

    Section save(Section entity);

}
