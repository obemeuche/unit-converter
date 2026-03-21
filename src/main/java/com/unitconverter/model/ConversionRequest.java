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
@Schema(description = "Request body for a unit conversion")
public class ConversionRequest {

    @Schema(description = "Unit category", example = "LENGTH", requiredMode = Schema.RequiredMode.REQUIRED)
    private UnitCategory category;

    @Schema(description = "Unit to convert from", example = "meter", requiredMode = Schema.RequiredMode.REQUIRED)
    private String sourceUnit;

    @Schema(description = "Unit to convert to", example = "foot", requiredMode = Schema.RequiredMode.REQUIRED)
    private String targetUnit;

    @Schema(description = "Value to convert", example = "100", requiredMode = Schema.RequiredMode.REQUIRED)
    private double value;
}
