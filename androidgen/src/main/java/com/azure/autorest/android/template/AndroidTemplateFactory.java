// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest.android.template;

import com.azure.autorest.template.*;

public class AndroidTemplateFactory extends DefaultTemplateFactory {
    @Override
    public EnumTemplate getEnumTemplate() {
        return AndroidEnumTemplate.getInstance();
    }

    @Override
    public ExceptionTemplate getExceptionTemplate() {
        return AndroidExceptionTemplate.getInstance();
    }

    @Override
    public ServiceClientTemplate getServiceClientTemplate() {
        return AndroidServiceClientTemplate.getInstance();
    }

    @Override
    public MethodGroupTemplate getMethodGroupTemplate() {
        return AndroidMethodGroupTemplate.getInstance();
    }

    @Override
    public ProxyTemplate getProxyTemplate() {
        return AndroidProxyTemplate.getInstance();
    }

    @Override
    public ClientMethodTemplate getClientMethodTemplate() {
        return AndroidClientMethodTemplate.getInstance();
    }

    @Override
    public WrapperClientMethodTemplate getWrapperClientMethodTemplate() {
        return AndroidWrapperClientMethodTemplate.getInstance();
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
    public ModelTemplate getModelTemplate() {
        return AndroidModelTemplate.getInstance();
    }
}