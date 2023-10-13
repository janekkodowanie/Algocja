package io.github.janekkodowanie.algocja.section;

import io.github.janekkodowanie.algocja.algorithm.Algorithm;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "sections")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String name;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "section"
    )

    private Set<Algorithm> algorithms;

    public Section() {}

    public Section(String name) {
        this.name = name;
    }

    public Section(String name, Set<Algorithm> algorithms) {
        this.name = name;
        this.algorithms = algorithms;
    }

    void setId(int id) {
        this.Id = id;
    }

    int getId() {
        return Id;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    Set<Algorithm> getAlgorithms() {
        return algorithms;
    }

    void setAlgorithms(Set<Algorithm> algorithms) {
        this.algorithms = algorithms;
    }
}
