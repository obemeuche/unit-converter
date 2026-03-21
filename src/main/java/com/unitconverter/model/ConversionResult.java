package com.unitconverter.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Result of a unit conversion")
public class ConversionResult {

    @Schema(description = "The original value provided", example = "100.0")
    private double originalValue;

    @Schema(description = "The converted value", example = "328.084")
    private double convertedValue;

    @Schema(description = "Source unit", example = "meter")
    private String sourceUnit;

    @Schema(description = "Target unit", example = "foot")
    private String targetUnit;

    @Schema(description = "Human-readable conversion formula", example = "1 meter = 3.28084 foot")
    private String formula;
}
