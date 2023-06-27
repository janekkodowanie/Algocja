package io.github.janekkodowanie.ezML.section;

import jakarta.persistence.*;

@Entity
@Table(name = "sections")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String name;

    public void setId(int id) {
        this.Id = id;
    }

    public int getId() {
        return Id;
    }
}
