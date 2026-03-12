package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConversionRequest {
    private UnitCategory category;
    private String sourceUnit;
    private String targetUnit;
    private double value;
}
