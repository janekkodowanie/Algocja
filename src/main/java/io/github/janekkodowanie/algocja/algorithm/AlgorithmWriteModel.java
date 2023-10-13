package io.github.janekkodowanie.algocja.algorithm;

import io.github.janekkodowanie.algocja.section.Section;

public class AlgorithmWriteModel {

    private String name;
    private String description;

    public AlgorithmWriteModel() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Algorithm toAlgorithmWithSection(Section section) {
        return new Algorithm(this.getName(), "", this.getDescription(), section);
    }
}
