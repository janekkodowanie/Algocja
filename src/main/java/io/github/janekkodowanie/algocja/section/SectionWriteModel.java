package io.github.janekkodowanie.algocja.section;

public class SectionWriteModel {

    private String name;

    public SectionWriteModel() {
    }

    public SectionWriteModel(Section section) {
        this.name = section.getName();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Section toSection() {
        return new Section(this.getName());
    }

}
