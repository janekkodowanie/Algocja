package io.github.janekkodowanie.algocja.section;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/sections")
class SectionController {

    private static final Logger logger = LoggerFactory.getLogger(SectionController.class);
    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping
    ResponseEntity<List<SectionReadModel>> findAll() {
        logger.info("[findAll]");
        return ResponseEntity.ok(sectionService.findAll());
    }

}
