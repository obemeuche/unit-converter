package org.example.converter;

import org.example.model.ConversionResult;
import org.example.model.UnitCategory;

import java.util.List;

public interface UnitConverter {
    UnitCategory getCategory();
    List<String> getSupportedUnits();
    ConversionResult convert(double value, String sourceUnit, String targetUnit);
    boolean supports(String unit);
}
