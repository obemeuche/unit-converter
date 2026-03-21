package com.unitconverter.converter;

import com.unitconverter.model.ConversionResult;
import com.unitconverter.model.UnitCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WeightConverterTest {

    private WeightConverter converter;

    @BeforeEach
    void setUp() {
        converter = new WeightConverter();
    }

    @Test
    void getCategory_returnsWeight() {
        assertEquals(UnitCategory.WEIGHT, converter.getCategory());
    }

    @Test
    void getSupportedUnits_returnsAllUnits() {
        List<String> units = converter.getSupportedUnits();
        assertTrue(units.contains("milligram"));
        assertTrue(units.contains("gram"));
        assertTrue(units.contains("kilogram"));
        assertTrue(units.contains("ounce"));
        assertTrue(units.contains("pound"));
        assertEquals(5, units.size());
    }

    @Test
    void convert_kilogramToPound() {
        ConversionResult result = converter.convert(1.0, "kilogram", "pound");
        assertEquals(2.20462, result.getConvertedValue(), 0.0001);
    }

    @Test
    void convert_poundToKilogram() {
        ConversionResult result = converter.convert(1.0, "pound", "kilogram");
        assertEquals(0.453592, result.getConvertedValue(), 0.0001);
    }

    @Test
    void convert_kilogramToGram() {
        ConversionResult result = converter.convert(1.0, "kilogram", "gram");
        assertEquals(1000.0, result.getConvertedValue(), 0.0001);
    }

    @Test
    void convert_ounceToGram() {
        ConversionResult result = converter.convert(1.0, "ounce", "gram");
        assertEquals(28.3495, result.getConvertedValue(), 0.0001);
    }

    @Test
    void convert_milligramToGram() {
        ConversionResult result = converter.convert(1000.0, "milligram", "gram");
        assertEquals(1.0, result.getConvertedValue(), 0.0001);
    }

    @Test
    void convert_invalidUnit_throwsException() {
        assertThrows(IllegalArgumentException.class, () ->
            converter.convert(1.0, "meter", "kilogram"));
    }
}
