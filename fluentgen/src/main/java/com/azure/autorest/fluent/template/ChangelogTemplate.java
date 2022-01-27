// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.template;

import com.azure.autorest.fluent.model.projectmodel.Changelog;

public class ChangelogTemplate extends com.azure.autorest.template.ChangelogTemplate {

    public String write(Changelog changelog) {
        return changelog.getContent();
    }
}
