package org.example.service;

import org.example.converter.LengthConverter;
import org.example.converter.TemperatureConverter;
import org.example.converter.UnitConverter;
import org.example.converter.VolumeConverter;
import org.example.converter.WeightConverter;
import org.example.model.ConversionRequest;
import org.example.model.ConversionResult;
import org.example.model.UnitCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConversionServiceTest {

    private ConversionService service;

    @BeforeEach
    void setUp() {
        List<UnitConverter> converters = List.of(
            new LengthConverter(),
            new WeightConverter(),
            new TemperatureConverter(),
            new VolumeConverter()
        );
        service = new ConversionService(converters);
    }

    @Test
    void getCategories_returnsAllCategories() {
        List<UnitCategory> categories = service.getCategories();
        assertEquals(4, categories.size());
        assertTrue(categories.contains(UnitCategory.LENGTH));
        assertTrue(categories.contains(UnitCategory.WEIGHT));
        assertTrue(categories.contains(UnitCategory.TEMPERATURE));
        assertTrue(categories.contains(UnitCategory.VOLUME));
    }

    @Test
    void getUnitsForCategory_length() {
        List<String> units = service.getUnitsForCategory(UnitCategory.LENGTH);
        assertTrue(units.contains("meter"));
        assertTrue(units.contains("foot"));
    }

    @Test
    void getUnitsForCategory_weight() {
        List<String> units = service.getUnitsForCategory(UnitCategory.WEIGHT);
        assertTrue(units.contains("kilogram"));
        assertTrue(units.contains("pound"));
    }

    @Test
    void getUnitsForCategory_temperature() {
        List<String> units = service.getUnitsForCategory(UnitCategory.TEMPERATURE);
        assertTrue(units.contains("celsius"));
        assertTrue(units.contains("fahrenheit"));
    }

    @Test
    void getUnitsForCategory_volume() {
        List<String> units = service.getUnitsForCategory(UnitCategory.VOLUME);
        assertTrue(units.contains("liter"));
        assertTrue(units.contains("gallon"));
    }

    @Test
    void convert_lengthConversion() {
        ConversionRequest request = new ConversionRequest(
            UnitCategory.LENGTH, "meter", "foot", 1.0
        );
        ConversionResult result = service.convert(request);
        assertEquals(3.28084, result.getConvertedValue(), 0.0001);
    }

    @Test
    void convert_weightConversion() {
        ConversionRequest request = new ConversionRequest(
            UnitCategory.WEIGHT, "kilogram", "pound", 1.0
        );
        ConversionResult result = service.convert(request);
        assertEquals(2.20462, result.getConvertedValue(), 0.0001);
    }

    @Test
    void convert_temperatureConversion() {
        ConversionRequest request = new ConversionRequest(
            UnitCategory.TEMPERATURE, "celsius", "fahrenheit", 100.0
        );
        ConversionResult result = service.convert(request);
        assertEquals(212.0, result.getConvertedValue(), 0.0001);
    }

    @Test
    void convert_volumeConversion() {
        ConversionRequest request = new ConversionRequest(
            UnitCategory.VOLUME, "gallon", "liter", 1.0
        );
        ConversionResult result = service.convert(request);
        assertEquals(3.78541, result.getConvertedValue(), 0.0001);
    }
}
