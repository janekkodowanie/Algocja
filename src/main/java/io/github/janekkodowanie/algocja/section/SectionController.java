package io.github.janekkodowanie.algocja.section;

import io.github.janekkodowanie.algocja.algorithm.AlgorithmService;
import io.github.janekkodowanie.algocja.algorithm.AlgorithmWriteModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/sections")
class SectionController {

    private static final Logger logger = LoggerFactory.getLogger(SectionController.class);
    private final SectionService sectionService;
    private final AlgorithmService algorithmService;

    public SectionController(SectionService sectionService, AlgorithmService algorithmService, SectionRepository sectionRepository) {
        this.sectionService = sectionService;
        this.algorithmService = algorithmService;
    }

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


    @GetMapping("/section")
    ModelAndView showSection(@RequestParam(name = "name", required = false) String name, ModelAndView modelAndView) {
        // Handle /show logic
        modelAndView.setViewName("sections");

        if (name != null) {
            Optional<SectionReadModel> sectionReadModel = sectionService.findByName(name);
            sectionReadModel.ifPresent(readModel -> modelAndView.addObject("sectionReadModel", readModel));
            modelAndView.addObject("sectionName", new StringBuilder());
        }

        return modelAndView;
    }

    @PostMapping("/section/add")
    public ModelAndView processAlgorithmForm(@ModelAttribute AlgorithmWriteModel algorithmWriteModel,
                                             @RequestParam("sectionName") String sectionName,
                                             BindingResult bindingResult) {

        logger.info("[processAlgorithmForm]]: {}", algorithmWriteModel.getName() + " -> " + sectionName);

        if ((!bindingResult.hasErrors())) {
            algorithmService.saveToSection(algorithmWriteModel, sectionName);
        }

        return new ModelAndView("redirect:/sections");

    }

    @ModelAttribute("algorithmWriteModel")
    public AlgorithmWriteModel getAlgorithmWriteModel() {
        return new AlgorithmWriteModel();
    }

    @ModelAttribute("sectionName")
    public String getSectionName() {
        // Retrieve the section name here, e.g., from your service or repository
        return "YourSectionName";
    }


}
