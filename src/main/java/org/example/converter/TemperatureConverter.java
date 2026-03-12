package org.example.converter;

import org.example.model.ConversionResult;
import org.example.model.UnitCategory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TemperatureConverter implements UnitConverter {

    private static final List<String> SUPPORTED_UNITS = List.of(
            "celsius", "fahrenheit", "kelvin"
    );

    @Override
    public UnitCategory getCategory() {
        return UnitCategory.TEMPERATURE;
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

        double convertedValue = convertTemperature(value, sourceUnit, targetUnit);
        String formula = getFormula(sourceUnit, targetUnit);

        return new ConversionResult(value, convertedValue, sourceUnit, targetUnit, formula);
    }

    private double convertTemperature(double value, String from, String to) {
        // First convert to Celsius as intermediate
        double celsius;
        switch (from) {
            case "celsius":
                celsius = value;
                break;
            case "fahrenheit":
                celsius = (value - 32) * 5 / 9;
                break;
            case "kelvin":
                celsius = value - 273.15;
                break;
            default:
                throw new IllegalArgumentException("Unknown source unit: " + from);
        }

        // Then convert from Celsius to target
        switch (to) {
            case "celsius":
                return celsius;
            case "fahrenheit":
                return celsius * 9 / 5 + 32;
            case "kelvin":
                return celsius + 273.15;
            default:
                throw new IllegalArgumentException("Unknown target unit: " + to);
        }
    }

    private String getFormula(String from, String to) {
        if (from.equals(to)) {
            return "No conversion needed";
        }

        return switch (from + "_to_" + to) {
            case "celsius_to_fahrenheit" -> "°F = (°C × 9/5) + 32";
            case "celsius_to_kelvin" -> "K = °C + 273.15";
            case "fahrenheit_to_celsius" -> "°C = (°F - 32) × 5/9";
            case "fahrenheit_to_kelvin" -> "K = (°F - 32) × 5/9 + 273.15";
            case "kelvin_to_celsius" -> "°C = K - 273.15";
            case "kelvin_to_fahrenheit" -> "°F = (K - 273.15) × 9/5 + 32";
            default -> from + " to " + to;
        };
    }

    @Override
    public boolean supports(String unit) {
        return SUPPORTED_UNITS.contains(unit);
    }
}
