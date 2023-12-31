package io.github.janekkodowanie.algocja.section;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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


    void add(SectionWriteModel sectionWriteModel) {
        sectionRepository.save(sectionWriteModel.toSection());
    }


    Optional<SectionReadModel> findByName(String name) {
        return Optional.of(new SectionReadModel(sectionRepository.findByName(name).get()));
    }


}
