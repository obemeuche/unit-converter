package com.unitconverter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.unitconverter.exception.ErrorResponse;
import com.unitconverter.model.ConversionRequest;
import com.unitconverter.model.ConversionResult;
import com.unitconverter.model.UnitCategory;
import com.unitconverter.service.ConversionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Unit Converter", description = "Convert values between units of measurement")
public class ApiController {

    private final ConversionService conversionService;

    public ApiController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Operation(summary = "List all categories", description = "Returns all supported unit categories.")
    @ApiResponse(responseCode = "200", description = "Successful")
    @GetMapping("/categories")
    public List<UnitCategory> getCategories() {
        return conversionService.getCategories();
    }

    @Operation(summary = "List units for a category", description = "Returns all supported units for the given category.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successful"),
        @ApiResponse(responseCode = "400", description = "Unknown category",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/units/{category}")
    public List<String> getUnits(
            @Parameter(description = "Category name (e.g. LENGTH, WEIGHT, TEMPERATURE, VOLUME)", required = true)
            @PathVariable String category) {
        UnitCategory unitCategory = UnitCategory.valueOf(category.toUpperCase());
        return conversionService.getUnitsForCategory(unitCategory);
    }

    @Operation(summary = "Convert a value", description = "Converts a value from one unit to another within the same category.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Conversion successful"),
        @ApiResponse(responseCode = "400", description = "Invalid category or unit",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/convert")
    public ConversionResult convert(@RequestBody ConversionRequest request) {
        return conversionService.convert(request);
    }
}
