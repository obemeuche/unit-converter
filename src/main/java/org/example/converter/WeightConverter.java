package org.example.converter;

import org.example.model.ConversionResult;
import org.example.model.UnitCategory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class WeightConverter implements UnitConverter {

    private static final List<String> SUPPORTED_UNITS = List.of(
            "milligram", "gram", "kilogram", "ounce", "pound"
    );

    // Conversion factors to kilograms (base unit)
    private static final Map<String, Double> TO_KILOGRAMS = Map.of(
            "milligram", 0.000001,
            "gram", 0.001,
            "kilogram", 1.0,
            "ounce", 0.0283495,
            "pound", 0.453592
    );

    @Override
    public UnitCategory getCategory() {
        return UnitCategory.WEIGHT;
    }

    @Override
    public List<String> getSupportedUnits() {
        return SUPPORTED_UNITS;
    }

    @Override
    public ConversionResult convert(double value, String sourceUnit, String targetUnit) {
        if (!supports(sourceUnit) || !supports(targetUnit)) {
            throw new IllegalArgumentException("Unsupported unit: " + sourceUnit + " or " + targetUnit);
        }

        double valueInKilograms = value * TO_KILOGRAMS.get(sourceUnit);
        double convertedValue = valueInKilograms / TO_KILOGRAMS.get(targetUnit);

        double factor = TO_KILOGRAMS.get(sourceUnit) / TO_KILOGRAMS.get(targetUnit);
        String formula = String.format("1 %s = %.6g %s", sourceUnit, factor, targetUnit);

        return new ConversionResult(value, convertedValue, sourceUnit, targetUnit, formula);
    }

    @Override
    public boolean supports(String unit) {
        return SUPPORTED_UNITS.contains(unit);
    }
}
