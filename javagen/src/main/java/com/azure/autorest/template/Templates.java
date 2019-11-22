// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest.template;


/**
 * A collection of templates for writing JV models to Java files and contexts.
 */
public class Templates {
    public static ServiceClientInterfaceTemplate getServiceClientInterfaceTemplate() {
        return ServiceClientInterfaceTemplate.getInstance();
    }

    public static ServiceClientTemplate getServiceClientTemplate() {
        return ServiceClientTemplate.getInstance();
    }

    public static ServiceClientBuilderTemplate getServiceClientBuilderTemplate() {
        return ServiceClientBuilderTemplate.getInstance();
    }

    public static ManagerTemplate getManagerTemplate() {
        return ManagerTemplate.getInstance();
    }

    public static MethodGroupInterfaceTemplate getMethodGroupInterfaceTemplate() {
        return MethodGroupInterfaceTemplate.getInstance();
    }

    public static MethodGroupTemplate getMethodGroupTemplate() {
        return MethodGroupTemplate.getInstance();
    }

    public static ProxyTemplate getProxyTemplate() {
        return ProxyTemplate.getInstance();
    }

    public static ClientMethodTemplate getClientMethodTemplate() {
        return ClientMethodTemplate.getInstance();
    }

    public static ModelTemplate getModelTemplate() {
        return ModelTemplate.getInstance();
    }

    public static ExceptionTemplate getExceptionTemplate() {
        return ExceptionTemplate.getInstance();
    }

    public static EnumTemplate getEnumTemplate() {
        return EnumTemplate.getInstance();
    }

    public static PageTemplate getPageTemplate() {
        return PageTemplate.getInstance();
    }

    public static ResponseTemplate getResponseTemplate() {
        return ResponseTemplate.getInstance();
    }

    public static XmlSequenceWrapperTemplate getXmlSequenceWrapperTemplate() {
        return XmlSequenceWrapperTemplate.getInstance();
    }

    public static PackageInfoTemplate getPackageInfoTemplate() {
        return PackageInfoTemplate.getInstance();
    }
}