/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.template.DefaultTemplateFactory;
import com.azure.autorest.template.ProxyTemplate;

public class FluentTemplateFactory extends DefaultTemplateFactory {

    @Override
    public ProxyTemplate getProxyTemplate() {
        return FluentProxyTemplate.getInstance();
    }
}
