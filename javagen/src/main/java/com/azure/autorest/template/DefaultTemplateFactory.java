package com.azure.autorest.template;

public class DefaultTemplateFactory implements TemplateFactory {
    @Override
    public ServiceClientInterfaceTemplate getServiceClientInterfaceTemplate() {
        return ServiceClientInterfaceTemplate.getInstance();
    }

    @Override
    public ServiceClientTemplate getServiceClientTemplate() {
        return ServiceClientTemplate.getInstance();
    }

    @Override
    public ServiceClientBuilderTemplate getServiceClientBuilderTemplate() {
        return ServiceClientBuilderTemplate.getInstance();
    }

    @Override
    public ServiceVersionTemplate getServiceVersionTemplate() {
        return ServiceVersionTemplate.getInstance();
    }

    @Override
    public ManagerTemplate getManagerTemplate() {
        return ManagerTemplate.getInstance();
    }

    @Override
    public MethodGroupInterfaceTemplate getMethodGroupInterfaceTemplate() {
        return MethodGroupInterfaceTemplate.getInstance();
    }

    @Override
    public MethodGroupTemplate getMethodGroupTemplate() {
        return MethodGroupTemplate.getInstance();
    }

    @Override
    public ProxyTemplate getProxyTemplate() {
        return ProxyTemplate.getInstance();
    }

    @Override
    public ClientMethodTemplate getClientMethodTemplate() {
        return ClientMethodTemplate.getInstance();
    }

    @Override
    public ModelTemplate getModelTemplate() {
        return ModelTemplate.getInstance();
    }

    @Override
    public ExceptionTemplate getExceptionTemplate() {
        return ExceptionTemplate.getInstance();
    }

    @Override
    public EnumTemplate getEnumTemplate() {
        return EnumTemplate.getInstance();
    }

    @Override
    public PageTemplate getPageTemplate() {
        return PageTemplate.getInstance();
    }

    @Override
    public ResponseTemplate getResponseTemplate() {
        return ResponseTemplate.getInstance();
    }

    @Override
    public XmlSequenceWrapperTemplate getXmlSequenceWrapperTemplate() {
        return XmlSequenceWrapperTemplate.getInstance();
    }

    @Override
    public PackageInfoTemplate getPackageInfoTemplate() {
        return PackageInfoTemplate.getInstance();
    }

    @Override
    public ServiceAsyncClientTemplate getServiceAsyncClientTemplate() {
        return ServiceAsyncClientTemplate.getInstance();
    }

    @Override
    public ServiceSyncClientTemplate getServiceSynClientTemplate() {
        return ServiceSyncClientTemplate.getInstance();
    }

    @Override
    public WrapperClientMethodTemplate getWrapperClientMethodTemplate() {
        return WrapperClientMethodTemplate.getInstance();
    }

    @Override
    public PomTemplate getPomTemplate() {
        return PomTemplate.getInstance();
    }

    @Override
    public ModuleInfoTemplate getModuleInfoTemplate() {
        return ModuleInfoTemplate.getInstance();
    }

    @Override
    public ProtocolSampleTemplate getProtocolSampleTemplate() {
        return ProtocolSampleTemplate.getInstance();
    }
}
