package org.example.converter;

import org.example.model.ConversionResult;
import org.example.model.UnitCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VolumeConverterTest {

    private VolumeConverter converter;

    @BeforeEach
    void setUp() {
        converter = new VolumeConverter();
    }

    @Test
    void getCategory_returnsVolume() {
        assertEquals(UnitCategory.VOLUME, converter.getCategory());
    }

    @Test
    void getSupportedUnits_returnsAllUnits() {
        List<String> units = converter.getSupportedUnits();
        assertTrue(units.contains("liter"));
        assertTrue(units.contains("milliliter"));
        assertTrue(units.contains("gallon"));
        assertTrue(units.contains("cup"));
        assertTrue(units.contains("fluid_ounce"));
        assertEquals(5, units.size());
    }

    @Test
    void convert_literToMilliliter() {
        ConversionResult result = converter.convert(1.0, "liter", "milliliter");
        assertEquals(1000.0, result.getConvertedValue(), 0.0001);
    }

    @Test
    void convert_gallonToLiter() {
        ConversionResult result = converter.convert(1.0, "gallon", "liter");
        assertEquals(3.78541, result.getConvertedValue(), 0.0001);
    }

    @Test
    void convert_literToGallon() {
        ConversionResult result = converter.convert(3.78541, "liter", "gallon");
        assertEquals(1.0, result.getConvertedValue(), 0.0001);
    }

    @Test
    void convert_cupToMilliliter() {
        ConversionResult result = converter.convert(1.0, "cup", "milliliter");
        assertEquals(236.588, result.getConvertedValue(), 0.001);
    }

    @Test
    void convert_fluidOunceToMilliliter() {
        ConversionResult result = converter.convert(1.0, "fluid_ounce", "milliliter");
        assertEquals(29.5735, result.getConvertedValue(), 0.0001);
    }

    @Test
    void convert_invalidUnit_throwsException() {
        assertThrows(IllegalArgumentException.class, () ->
            converter.convert(1.0, "invalid", "liter"));
    }
}
