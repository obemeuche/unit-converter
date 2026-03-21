package com.unitconverter.converter;

import com.unitconverter.model.ConversionResult;
import com.unitconverter.model.UnitCategory;

import java.util.List;

public interface UnitConverter {
    UnitCategory getCategory();
    List<String> getSupportedUnits();
    ConversionResult convert(double value, String sourceUnit, String targetUnit);
    boolean supports(String unit);
}
