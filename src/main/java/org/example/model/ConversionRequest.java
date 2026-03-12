package org.example.model;

public class ConversionRequest {
    private UnitCategory category;
    private String sourceUnit;
    private String targetUnit;
    private double value;

    public ConversionRequest() {
    }

    public ConversionRequest(UnitCategory category, String sourceUnit, String targetUnit, double value) {
        this.category = category;
        this.sourceUnit = sourceUnit;
        this.targetUnit = targetUnit;
        this.value = value;
    }

    public UnitCategory getCategory() {
        return category;
    }

    public void setCategory(UnitCategory category) {
        this.category = category;
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
