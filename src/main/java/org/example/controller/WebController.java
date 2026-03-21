package org.example.controller;

import org.example.model.UnitCategory;
import org.example.service.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WebController {

    private final ConversionService conversionService;

    public WebController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/length")
    public String length(Model model) {
        List<String> units = conversionService.getUnitsForCategory(UnitCategory.LENGTH);
        model.addAttribute("units", units);
        return "length";
    }

    @GetMapping("/weight")
    public String weight(Model model) {
        List<String> units = conversionService.getUnitsForCategory(UnitCategory.WEIGHT);
        model.addAttribute("units", units);
        return "weight";
    }

    @GetMapping("/temperature")
    public String temperature(Model model) {
        List<String> units = conversionService.getUnitsForCategory(UnitCategory.TEMPERATURE);
        model.addAttribute("units", units);
        return "temperature";
    }
}
