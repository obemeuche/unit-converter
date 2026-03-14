package org.example.controller;

import org.example.model.ConversionRequest;
import org.example.model.ConversionResult;
import org.example.model.UnitCategory;
import org.example.service.ConversionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final ConversionService conversionService;

    public ApiController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @GetMapping("/categories")
    public List<UnitCategory> getCategories() {
        return conversionService.getCategories();
    }

    @GetMapping("/units/{category}")
    public List<String> getUnits(@PathVariable String category) {
        UnitCategory unitCategory = UnitCategory.valueOf(category.toUpperCase());
        return conversionService.getUnitsForCategory(unitCategory);
    }

    @PostMapping("/convert")
    public ConversionResult convert(@RequestBody ConversionRequest request) {
        return conversionService.convert(request);
    }
}