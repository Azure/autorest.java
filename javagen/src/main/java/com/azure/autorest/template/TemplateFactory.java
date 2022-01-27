// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

public interface TemplateFactory {

    ServiceClientInterfaceTemplate getServiceClientInterfaceTemplate();

    ServiceClientTemplate getServiceClientTemplate();

    ServiceClientBuilderTemplate getServiceClientBuilderTemplate();

    ServiceVersionTemplate getServiceVersionTemplate();

    ManagerTemplate getManagerTemplate();

    MethodGroupInterfaceTemplate getMethodGroupInterfaceTemplate();

    MethodGroupTemplate getMethodGroupTemplate();

    ProxyTemplate getProxyTemplate();

    ClientMethodTemplate getClientMethodTemplate();

    ModelTemplate getModelTemplate();

    ExceptionTemplate getExceptionTemplate();

    EnumTemplate getEnumTemplate();

    PageTemplate getPageTemplate();

    ResponseTemplate getResponseTemplate();

    XmlSequenceWrapperTemplate getXmlSequenceWrapperTemplate();

    PackageInfoTemplate getPackageInfoTemplate();

    ServiceAsyncClientTemplate getServiceAsyncClientTemplate();

    ServiceSyncClientTemplate getServiceSynClientTemplate();

    WrapperClientMethodTemplate getWrapperClientMethodTemplate();

    PomTemplate getPomTemplate();

    ModuleInfoTemplate getModuleInfoTemplate();

    ProtocolSampleTemplate getProtocolSampleTemplate();
}
