package io.github.janekkodowanie.algocja.algorithm;


import io.github.janekkodowanie.algocja.section.Section;
import jakarta.persistence.*;

@Entity
@Table(name = "algorithms")
public class Algorithm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String name;
    private String purpose;
    private String description;


    @ManyToOne
    @JoinColumn(name = "SECTION_ID")
    private Section section;

    public Algorithm(String name, String purpose, String description, Section section) {
        this.name = name;
        this.purpose = purpose;
        this.section = section;
        this.description = description;
    }
    public Algorithm(String name, String purpose, String description) {
        this(name, purpose, description, null);
    }

    public Algorithm(String name, String purpose) {
        this(name, purpose, null, null);
    }

    public Algorithm() {}

    public int getId() {
        return this.Id;
    }

    public String getName() {
        return name;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}
