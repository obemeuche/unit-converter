package org.example.converter;

import org.example.model.ConversionResult;
import org.example.model.UnitCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureConverterTest {

    private TemperatureConverter converter;

    @BeforeEach
    void setUp() {
        converter = new TemperatureConverter();
    }

    @Test
    void getCategory_returnsTemperature() {
        assertEquals(UnitCategory.TEMPERATURE, converter.getCategory());
    }

    @Test
    void getSupportedUnits_returnsAllUnits() {
        List<String> units = converter.getSupportedUnits();
        assertTrue(units.contains("celsius"));
        assertTrue(units.contains("fahrenheit"));
        assertTrue(units.contains("kelvin"));
        assertEquals(3, units.size());
    }

    @Test
    void convert_celsiusToFahrenheit_boilingPoint() {
        ConversionResult result = converter.convert(100.0, "celsius", "fahrenheit");
        assertEquals(212.0, result.getConvertedValue(), 0.0001);
    }

    @Test
    void convert_celsiusToFahrenheit_freezingPoint() {
        ConversionResult result = converter.convert(0.0, "celsius", "fahrenheit");
        assertEquals(32.0, result.getConvertedValue(), 0.0001);
    }

    @Test
    void convert_fahrenheitToCelsius() {
        ConversionResult result = converter.convert(32.0, "fahrenheit", "celsius");
        assertEquals(0.0, result.getConvertedValue(), 0.0001);
    }

    @Test
    void convert_celsiusToKelvin() {
        ConversionResult result = converter.convert(0.0, "celsius", "kelvin");
        assertEquals(273.15, result.getConvertedValue(), 0.0001);
    }

    @Test
    void convert_kelvinToCelsius_absoluteZero() {
        ConversionResult result = converter.convert(0.0, "kelvin", "celsius");
        assertEquals(-273.15, result.getConvertedValue(), 0.0001);
    }

    @Test
    void convert_kelvinToFahrenheit() {
        ConversionResult result = converter.convert(273.15, "kelvin", "fahrenheit");
        assertEquals(32.0, result.getConvertedValue(), 0.0001);
    }

    @Test
    void convert_fahrenheitToKelvin() {
        ConversionResult result = converter.convert(32.0, "fahrenheit", "kelvin");
        assertEquals(273.15, result.getConvertedValue(), 0.0001);
    }

    @Test
    void convert_returnsCorrectFormula() {
        ConversionResult result = converter.convert(100.0, "celsius", "fahrenheit");
        assertEquals("°F = (°C × 9/5) + 32", result.getFormula());
    }

    @Test
    void convert_invalidUnit_throwsException() {
        assertThrows(IllegalArgumentException.class, () ->
            converter.convert(1.0, "invalid", "celsius"));
    }
}
