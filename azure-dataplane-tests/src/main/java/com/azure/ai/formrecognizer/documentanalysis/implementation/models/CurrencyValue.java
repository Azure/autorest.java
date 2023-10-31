// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.formrecognizer.documentanalysis.implementation.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Currency field value.
 */
@Fluent
public final class CurrencyValue {
    /*
     * Currency amount.
     */
    @JsonProperty(value = "amount", required = true)
    private double amount;

    /*
     * Currency symbol label, if any.
     */
    @JsonProperty(value = "currencySymbol")
    private String currencySymbol;

    /**
     * Creates an instance of CurrencyValue class.
     */
    public CurrencyValue() {
    }

    /**
     * Get the amount property: Currency amount.
     * 
     * @return the amount value.
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Set the amount property: Currency amount.
     * 
     * @param amount the amount value to set.
     * @return the CurrencyValue object itself.
     */
    public CurrencyValue setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    /**
     * Get the currencySymbol property: Currency symbol label, if any.
     * 
     * @return the currencySymbol value.
     */
    public String getCurrencySymbol() {
        return this.currencySymbol;
    }

    /**
     * Set the currencySymbol property: Currency symbol label, if any.
     * 
     * @param currencySymbol the currencySymbol value to set.
     * @return the CurrencyValue object itself.
     */
    public CurrencyValue setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
        return this;
    }
}
