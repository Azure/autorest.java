// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.formrecognizer.documentanalysis.implementation.models;

import com.azure.core.annotation.Fluent;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * A selection mark object representing check boxes, radio buttons, and other elements indicating a selection.
 */
@Fluent
public final class DocumentSelectionMark {
    /*
     * State of the selection mark.
     */
    @JsonProperty(value = "state", required = true)
    private SelectionMarkState state;

    /*
     * Bounding polygon of the selection mark.
     */
    @JsonProperty(value = "polygon")
    private List<Float> polygon;

    /*
     * Location of the selection mark in the reading order concatenated content.
     */
    @JsonProperty(value = "span", required = true)
    private DocumentSpan span;

    /*
     * Confidence of correctly extracting the selection mark.
     */
    @JsonProperty(value = "confidence", required = true)
    private float confidence;

    /**
     * Creates an instance of DocumentSelectionMark class.
     */
    public DocumentSelectionMark() {
    }

    /**
     * Get the state property: State of the selection mark.
     * 
     * @return the state value.
     */
    public SelectionMarkState getState() {
        return this.state;
    }

    /**
     * Set the state property: State of the selection mark.
     * 
     * @param state the state value to set.
     * @return the DocumentSelectionMark object itself.
     */
    public DocumentSelectionMark setState(SelectionMarkState state) {
        this.state = state;
        return this;
    }

    /**
     * Get the polygon property: Bounding polygon of the selection mark.
     * 
     * @return the polygon value.
     */
    public List<Float> getPolygon() {
        return this.polygon;
    }

    /**
     * Set the polygon property: Bounding polygon of the selection mark.
     * 
     * @param polygon the polygon value to set.
     * @return the DocumentSelectionMark object itself.
     */
    public DocumentSelectionMark setPolygon(List<Float> polygon) {
        this.polygon = polygon;
        return this;
    }

    /**
     * Get the span property: Location of the selection mark in the reading order concatenated content.
     * 
     * @return the span value.
     */
    public DocumentSpan getSpan() {
        return this.span;
    }

    /**
     * Set the span property: Location of the selection mark in the reading order concatenated content.
     * 
     * @param span the span value to set.
     * @return the DocumentSelectionMark object itself.
     */
    public DocumentSelectionMark setSpan(DocumentSpan span) {
        this.span = span;
        return this;
    }

    /**
     * Get the confidence property: Confidence of correctly extracting the selection mark.
     * 
     * @return the confidence value.
     */
    public float getConfidence() {
        return this.confidence;
    }

    /**
     * Set the confidence property: Confidence of correctly extracting the selection mark.
     * 
     * @param confidence the confidence value to set.
     * @return the DocumentSelectionMark object itself.
     */
    public DocumentSelectionMark setConfidence(float confidence) {
        this.confidence = confidence;
        return this;
    }
}
