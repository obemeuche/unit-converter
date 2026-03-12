package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConversionResult {
    private double originalValue;
    private double convertedValue;
    private String sourceUnit;
    private String targetUnit;
    private String formula;
}
