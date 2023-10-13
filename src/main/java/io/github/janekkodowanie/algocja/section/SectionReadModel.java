package io.github.janekkodowanie.algocja.section;

import io.github.janekkodowanie.algocja.algorithm.Algorithm;

import java.util.Set;

public class SectionReadModel {

    private String name;
    private Set<Algorithm> algorithms;

    public SectionReadModel() {}

    public SectionReadModel(Section section) {
        this.name = section.getName();
        this.algorithms = section.getAlgorithms();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Algorithm> getAlgorithms() {
        return algorithms;
    }

    public void setAlgorithms(Set<Algorithm> algorithms) {
        this.algorithms = algorithms;
    }

    public Section toSection() {
        return new Section(this.getName(), this.algorithms);
    }

}
