// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest.android.template;

import com.azure.autorest.template.ClientMethodTemplate;
import com.azure.autorest.template.DefaultTemplateFactory;
import com.azure.autorest.template.EnumTemplate;
import com.azure.autorest.template.ServiceAsyncClientTemplate;
import com.azure.autorest.template.ServiceSyncClientTemplate;
import com.azure.autorest.template.WrapperClientMethodTemplate;

public class AndroidTemplateFactory extends DefaultTemplateFactory {

    @Override
    public EnumTemplate getEnumTemplate() {
        return AndroidEnumTemplate.getInstance();
    }

    @Override
    public ServiceAsyncClientTemplate getServiceAsyncClientTemplate() {
        return AndroidServiceAsyncClientTemplate.getInstance();
    }

    @Override
    public ServiceSyncClientTemplate getServiceSynClientTemplate() {
        return AndroidServiceSyncClientTemplate.getInstance();
    }

    @Override
    public ClientMethodTemplate getClientMethodTemplate() {
        return AndroidClientMethodTemplate.getInstance();
    }

    @Override
    public WrapperClientMethodTemplate getWrapperClientMethodTemplate() {
        return AndroidWrapperClientMethodTemplate.getInstance();
    }
}
