// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest.template;

import com.azure.autorest.template.DefaultTemplateFactory;
import com.azure.autorest.template.AndroidEnumTemplate;

public class AndroidTemplateFactory extends DefaultTemplateFactory {

    @Override
    public EnumTemplate getEnumTemplate() {
        return AndroidEnumTemplate.getInstance();
    }

}