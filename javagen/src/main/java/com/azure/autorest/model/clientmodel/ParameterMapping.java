package com.azure.autorest.model.clientmodel;

public class ParameterMapping {
    private ClientMethodParameter inputParameter;

    private ClientModelProperty inputParameterProperty;

    private String outputParameterProperty;

    public ClientMethodParameter getInputParameter() {
        return inputParameter;
    }

    public ParameterMapping setInputParameter(ClientMethodParameter inputParameter) {
        this.inputParameter = inputParameter;
        return this;
    }

    public ClientModelProperty getInputParameterProperty() {
        return inputParameterProperty;
    }

    public ParameterMapping setInputParameterProperty(ClientModelProperty inputParameterProperty) {
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
