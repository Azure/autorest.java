package com.azure.autorest.template;

import com.azure.autorest.template.protocol.*;

public interface TemplateFactory {

    ServiceClientInterfaceTemplate getServiceClientInterfaceTemplate();

    ServiceClientTemplate getServiceClientTemplate();

    ServiceClientBuilderTemplate getServiceClientBuilderTemplate();

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

    ProtocolAsyncMethodTemplate getProtocolAsyncMethodTemplate();

    ProtocolSyncMethodTemplate getProtocolSyncMethodTemplate();

    ProtocolAsyncPagingMethodTemplate getProtocolAsyncPagingMethodTemplate();

    ProtocolAsyncPagingSinglePageMethodTemplate getProtocolAsyncPagingSinglePageMethodTemplate();

    ProtocolSyncPagingMethodTemplate getProtocolSyncPagingMethodTemplate();

    ProtocolSyncClientTemplate getLowLevelSyncClientTemplate();

    ProtocolAsyncClientTemplate getLowLevelAsyncClientTemplate();

    ProtocolRestProxyTemplate getProtocolRestProxyTemplate();

    PomTemplate getPomTemplate();

    ModuleInfoTemplate getModuleInfoTemplate();
}

