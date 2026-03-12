package org.example.converter;

import org.example.model.ConversionResult;
import org.example.model.UnitCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LengthConverterTest {

    private LengthConverter converter;

    @BeforeEach
    void setUp() {
        converter = new LengthConverter();
    }

    @Test
    void getCategory_returnsLength() {
        assertEquals(UnitCategory.LENGTH, converter.getCategory());
    }

    @Test
    void getSupportedUnits_returnsAllUnits() {
        List<String> units = converter.getSupportedUnits();
        assertTrue(units.contains("meter"));
        assertTrue(units.contains("kilometer"));
        assertTrue(units.contains("centimeter"));
        assertTrue(units.contains("mile"));
        assertTrue(units.contains("foot"));
        assertTrue(units.contains("inch"));
        assertTrue(units.contains("yard"));
        assertEquals(7, units.size());
    }

    @Test
    void supports_returnsTrueForValidUnits() {
        assertTrue(converter.supports("meter"));
        assertTrue(converter.supports("foot"));
    }

    @Test
    void supports_returnsFalseForInvalidUnits() {
        assertFalse(converter.supports("invalid"));
        assertFalse(converter.supports("kilogram"));
    }

    @Test
    void convert_meterToFoot() {
        ConversionResult result = converter.convert(1.0, "meter", "foot");
        assertEquals(1.0, result.getOriginalValue());
        assertEquals(3.28084, result.getConvertedValue(), 0.0001);
        assertEquals("meter", result.getSourceUnit());
        assertEquals("foot", result.getTargetUnit());
    }

    @Test
    void convert_kilometerToMile() {
        ConversionResult result = converter.convert(1.0, "kilometer", "mile");
        assertEquals(0.621371, result.getConvertedValue(), 0.0001);
    }

    @Test
    void convert_inchToCentimeter() {
        ConversionResult result = converter.convert(1.0, "inch", "centimeter");
        assertEquals(2.54, result.getConvertedValue(), 0.0001);
    }

    @Test
    void convert_yardToMeter() {
        ConversionResult result = converter.convert(1.0, "yard", "meter");
        assertEquals(0.9144, result.getConvertedValue(), 0.0001);
    }

    @Test
    void convert_sameUnit_returnsOriginalValue() {
        ConversionResult result = converter.convert(100.0, "meter", "meter");
        assertEquals(100.0, result.getConvertedValue(), 0.0001);
    }

    @Test
    void convert_invalidUnit_throwsException() {
        assertThrows(IllegalArgumentException.class, () ->
            converter.convert(1.0, "invalid", "meter"));
    }
}
