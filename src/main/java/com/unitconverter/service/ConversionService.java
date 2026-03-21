package com.unitconverter.service;

import com.unitconverter.converter.UnitConverter;
import com.unitconverter.model.ConversionRequest;
import com.unitconverter.model.ConversionResult;
import com.unitconverter.model.UnitCategory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ConversionService {

    private final Map<UnitCategory, UnitConverter> converters;

    public ConversionService(List<UnitConverter> converterList) {
        this.converters = converterList.stream()
                .collect(Collectors.toMap(UnitConverter::getCategory, Function.identity()));
    }

    public List<UnitCategory> getCategories() {
        return Arrays.asList(UnitCategory.values());
    }

    public List<String> getUnitsForCategory(UnitCategory category) {
        UnitConverter converter = converters.get(category);
        if (converter == null) {
            throw new IllegalArgumentException("Unknown category: " + category);
        }
        return converter.getSupportedUnits();
    }

    public ConversionResult convert(ConversionRequest request) {
        UnitConverter converter = converters.get(request.getCategory());
        if (converter == null) {
            throw new IllegalArgumentException("Unknown category: " + request.getCategory());
        }
        return converter.convert(request.getValue(), request.getSourceUnit(), request.getTargetUnit());
    }
}
