package com.azure.autorest.template;

import com.azure.autorest.template.protocol.ProtocolAsyncClientTemplate;
import com.azure.autorest.template.protocol.ProtocolAsyncMethodTemplate;
import com.azure.autorest.template.protocol.ProtocolSyncClientTemplate;
import com.azure.autorest.template.protocol.ProtocolSyncMethodTemplate;

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

    ProtocolSyncClientTemplate getLowLevelSyncClientTemplate();

    ProtocolAsyncClientTemplate getLowLevelAsyncClientTemplate();

    PomTemplate getPomTemplate();

    ModuleInfoTemplate getModuleInfoTemplate();
}

