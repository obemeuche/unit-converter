package org.example.converter;

import org.example.model.ConversionResult;
import org.example.model.UnitCategory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class LengthConverter implements UnitConverter {

    private static final List<String> SUPPORTED_UNITS = List.of(
            "meter", "kilometer", "centimeter", "mile", "foot", "inch", "yard"
    );

    // Conversion factors to meters (base unit)
    private static final Map<String, Double> TO_METERS = Map.of(
            "meter", 1.0,
            "kilometer", 1000.0,
            "centimeter", 0.01,
            "mile", 1609.344,
            "foot", 0.3048,
            "inch", 0.0254,
            "yard", 0.9144
    );

    @Override
    public UnitCategory getCategory() {
        return UnitCategory.LENGTH;
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

        double valueInMeters = value * TO_METERS.get(sourceUnit);
        double convertedValue = valueInMeters / TO_METERS.get(targetUnit);

        double factor = TO_METERS.get(sourceUnit) / TO_METERS.get(targetUnit);
        String formula = String.format("1 %s = %.6g %s", sourceUnit, factor, targetUnit);

        return new ConversionResult(value, convertedValue, sourceUnit, targetUnit, formula);
    }

    @Override
    public boolean supports(String unit) {
        return SUPPORTED_UNITS.contains(unit);
    }
}
