package io.github.janekkodowanie.ezML.algorithm;


import io.github.janekkodowanie.ezML.section.Section;
import jakarta.persistence.*;

@Entity
@Table(name = "algorithms")
public class Algorithm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String name;
    private String purpose;

    @ManyToOne
    @JoinColumn(name = "SECTION_ID")
    private Section section;

    public Algorithm(String name, String purpose, Section section) {
        this.name = name;
        this.purpose = purpose;
        this.section = section;
    }
    public Algorithm(String name, String purpose) {
        this(name, purpose, null);
    }

    public Algorithm() {}

    public int getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getPurpose() {
        return purpose;
    }
}
