package org.example.controller;

import org.example.model.ConversionRequest;
import org.example.model.ConversionResult;
import org.example.model.UnitCategory;
import org.example.service.ConversionService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<String>> getUnits(@PathVariable String category) {
        try {
            UnitCategory unitCategory = UnitCategory.valueOf(category.toUpperCase());
            return ResponseEntity.ok(conversionService.getUnitsForCategory(unitCategory));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/convert")
    public ResponseEntity<ConversionResult> convert(@RequestBody ConversionRequest request) {
        try {
            ConversionResult result = conversionService.convert(request);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
