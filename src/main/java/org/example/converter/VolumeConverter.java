package org.example.converter;

import org.example.model.ConversionResult;
import org.example.model.UnitCategory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class VolumeConverter implements UnitConverter {

    private static final List<String> SUPPORTED_UNITS = List.of(
            "liter", "milliliter", "gallon", "cup", "fluid_ounce"
    );

    // Conversion factors to liters (base unit)
    private static final Map<String, Double> TO_LITERS = Map.of(
            "liter", 1.0,
            "milliliter", 0.001,
            "gallon", 3.78541,
            "cup", 0.236588,
            "fluid_ounce", 0.0295735
    );

    @Override
    public UnitCategory getCategory() {
        return UnitCategory.VOLUME;
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

        double valueInLiters = value * TO_LITERS.get(sourceUnit);
        double convertedValue = valueInLiters / TO_LITERS.get(targetUnit);

        double factor = TO_LITERS.get(sourceUnit) / TO_LITERS.get(targetUnit);
        String formula = String.format("1 %s = %.6g %s", sourceUnit, factor, targetUnit);

        return new ConversionResult(value, convertedValue, sourceUnit, targetUnit, formula);
    }

    @Override
    public boolean supports(String unit) {
        return SUPPORTED_UNITS.contains(unit);
    }
}
