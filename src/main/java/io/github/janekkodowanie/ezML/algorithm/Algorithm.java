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

    public Algorithm(int id, String name, String purpose) {
        this.Id = id;
        this.name = name;
        this.purpose = purpose;
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
