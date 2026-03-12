package org.example.model;

public class ConversionResult {
    private double originalValue;
    private double convertedValue;
    private String sourceUnit;
    private String targetUnit;
    private String formula;

    public ConversionResult() {
    }

    public ConversionResult(double originalValue, double convertedValue, String sourceUnit, String targetUnit, String formula) {
        this.originalValue = originalValue;
        this.convertedValue = convertedValue;
        this.sourceUnit = sourceUnit;
        this.targetUnit = targetUnit;
        this.formula = formula;
    }

    public double getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(double originalValue) {
        this.originalValue = originalValue;
    }

    public double getConvertedValue() {
        return convertedValue;
    }

    public void setConvertedValue(double convertedValue) {
        this.convertedValue = convertedValue;
    }

    public String getSourceUnit() {
        return sourceUnit;
    }

    public void setSourceUnit(String sourceUnit) {
        this.sourceUnit = sourceUnit;
    }

    public String getTargetUnit() {
        return targetUnit;
    }

    public void setTargetUnit(String targetUnit) {
        this.targetUnit = targetUnit;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }
}
