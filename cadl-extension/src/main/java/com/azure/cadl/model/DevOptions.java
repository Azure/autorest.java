// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.cadl.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DevOptions {
    @JsonProperty(value="generate-models")
    private Boolean generateModels;

    @JsonProperty(value="generate-convenience-apis")
    private Boolean generateConvenienceApis;

    public Boolean getGenerateModels() {
        return generateModels;
    }

    public Boolean getGenerateConvenienceApis() {
        return generateConvenienceApis;
    }
}
