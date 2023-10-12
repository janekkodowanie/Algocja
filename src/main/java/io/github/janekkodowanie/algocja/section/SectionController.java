package io.github.janekkodowanie.algocja.section;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sections")
class SectionController {

    private static final Logger logger = LoggerFactory.getLogger(SectionController.class);
    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    /*
    @GetMapping
    ResponseEntity<List<SectionReadModel>> findAll() {
        logger.info("[findAll]");
        return ResponseEntity.ok(sectionService.findAll());
    }
    */

    @GetMapping
    ModelAndView showSections() {
        ModelAndView modelAndView = new ModelAndView("sections");
        modelAndView.addObject("sections", sectionService.findAll());
        return modelAndView;
    }

    @GetMapping("/add")
    ModelAndView addSection() {
        logger.info("[addSection]");
        ModelAndView modelAndView = new ModelAndView("addSection");
        modelAndView.addObject("section", new SectionWriteModel());
        return modelAndView;
    }

    @PostMapping("/add")
    ModelAndView addSection(SectionWriteModel section) {
        logger.info("[addSection] section: {}", section.getName());
        sectionService.add(section);
        return new ModelAndView("redirect:/sections");
    }


    /* TODO:
        GetMapping specific section as parameter
        hyperlinks to specific sections being printed instead of just names of sections
    * */


}
