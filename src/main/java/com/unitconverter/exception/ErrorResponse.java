package com.unitconverter.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Error response body")
public class ErrorResponse {

    @Schema(description = "Human-readable error message", example = "No enum constant com.unitconverter.model.UnitCategory.INVALID")
    private String message;
}
