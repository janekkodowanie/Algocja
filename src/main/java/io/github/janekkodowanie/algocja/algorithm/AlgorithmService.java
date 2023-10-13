package io.github.janekkodowanie.algocja.algorithm;

import io.github.janekkodowanie.algocja.section.Section;
import io.github.janekkodowanie.algocja.section.SectionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlgorithmService {


    private final AlgorithmRepository algorithmRepository;
    private final SectionRepository sectionRepository;

    AlgorithmService(AlgorithmRepository algorithmRepository, SectionRepository sectionRepository) {
        this.algorithmRepository = algorithmRepository;
        this.sectionRepository = sectionRepository;
    }

    public void saveToSection(AlgorithmWriteModel algorithmWriteModel, String sectionName) {
        Optional<Section> section = sectionRepository.findByName(sectionName);
        section.ifPresent(value -> algorithmRepository.save(algorithmWriteModel.toAlgorithmWithSection(value)));
    }


}
