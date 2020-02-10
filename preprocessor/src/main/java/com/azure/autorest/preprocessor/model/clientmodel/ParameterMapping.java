package com.azure.autorest.preprocessor.model.clientmodel;

public class ParameterMapping {
    private ClientMethodParameter inputParameter;

    private String inputParameterProperty;

    private String outputParameterProperty;

    public ClientMethodParameter getInputParameter() {
        return inputParameter;
    }

    public ParameterMapping setInputParameter(ClientMethodParameter inputParameter) {
        this.inputParameter = inputParameter;
        return this;
    }

    public String getInputParameterProperty() {
        return inputParameterProperty;
    }

    public ParameterMapping setInputParameterProperty(String inputParameterProperty) {
        this.inputParameterProperty = inputParameterProperty;
        return this;
    }

    public String getOutputParameterProperty() {
        return outputParameterProperty;
    }

    public ParameterMapping setOutputParameterProperty(String outputParameterProperty) {
        this.outputParameterProperty = outputParameterProperty;
        return this;
    }
}
