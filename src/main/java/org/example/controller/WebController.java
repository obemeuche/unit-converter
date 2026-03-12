package org.example.controller;

import org.example.model.UnitCategory;
import org.example.service.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WebController {

    private final ConversionService conversionService;

    public WebController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<UnitCategory> categories = conversionService.getCategories();
        Map<String, List<String>> unitsByCategory = new HashMap<>();

        for (UnitCategory category : categories) {
            unitsByCategory.put(category.name(), conversionService.getUnitsForCategory(category));
        }

        model.addAttribute("categories", categories);
        model.addAttribute("unitsByCategory", unitsByCategory);
        return "index";
    }
}
