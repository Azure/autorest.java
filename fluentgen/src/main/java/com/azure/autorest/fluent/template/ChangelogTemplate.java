/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.fluent.model.projectmodel.Changelog;

public class ChangelogTemplate extends com.azure.autorest.template.ChangelogTemplate {

    public String write(Changelog changelog) {
        return changelog.getContent();
    }
}
