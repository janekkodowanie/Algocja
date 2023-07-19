package io.github.janekkodowanie.ezML.section;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class SectionService {

    private final SectionRepository sectionRepository;

    SectionService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    List<SectionReadModel> findAll() {
        return sectionRepository.findAll().stream()
                .map(SectionReadModel::new)
                .collect(Collectors.toList());
    }

}
