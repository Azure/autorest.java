package com.azure.autorest.extension.base.model.extensionmodel;

import java.util.List;

public class XmsExtensions {
    private XmsEnum xmsEnum;

    private String xmsClientName;

    private XmsPageable xmsPageable;

    private boolean xmsSkipUrlEncoding;

    private boolean xmsClientFlatten;

    private boolean xmsLongRunningOperation;

    private XmsLongRunningOperationOptions xmsLongRunningOperationOptions;

    private boolean xmsFlattened;

    private boolean xmsAzureResource;

    private List<String> xmsMutability;

    private String xmsHeaderCollectionPrefix;

    private XmsInternalAutorestAnonymousSchema xmsInternalAutorestAnonymousSchema;

    private XmsExamples xmsExamples;

    public XmsEnum getXmsEnum() {
        return xmsEnum;
    }

    public void setXmsEnum(XmsEnum xmsEnum) {
        this.xmsEnum = xmsEnum;
    }

    public String getXmsClientName() {
        return xmsClientName;
    }

    public void setXmsClientName(String xmsClientName) {
        this.xmsClientName = xmsClientName;
    }

    public XmsPageable getXmsPageable() {
        return xmsPageable;
    }

    public void setXmsPageable(XmsPageable xmsPageable) {
        this.xmsPageable = xmsPageable;
    }

    public boolean isXmsSkipUrlEncoding() {
        return xmsSkipUrlEncoding;
    }

    public void setXmsSkipUrlEncoding(boolean xmsSkipUrlEncoding) {
        this.xmsSkipUrlEncoding = xmsSkipUrlEncoding;
    }

    public boolean isXmsClientFlatten() {
        return xmsClientFlatten;
    }

    public void setXmsClientFlatten(boolean xmsClientFlatten) {
        this.xmsClientFlatten = xmsClientFlatten;
    }

    public boolean isXmsLongRunningOperation() {
        return xmsLongRunningOperation;
    }

    public void setXmsLongRunningOperation(boolean xmsLongRunningOperation) {
        this.xmsLongRunningOperation = xmsLongRunningOperation;
    }

    public boolean isXmsFlattened() {
        return xmsFlattened;
    }

    public void setXmsFlattened(boolean xmsFlattened) {
        this.xmsFlattened = xmsFlattened;
    }

    public boolean isXmsAzureResource() {
        return xmsAzureResource;
    }

    public void setXmsAzureResource(boolean xmsAzureResource) {
        this.xmsAzureResource = xmsAzureResource;
    }

    public List<String> getXmsMutability() {
        return xmsMutability;
    }

    public void setXmsMutability(List<String> xmsMutability) {
        this.xmsMutability = xmsMutability;
    }

    public String getXmsHeaderCollectionPrefix() {
        return xmsHeaderCollectionPrefix;
    }

    public void setXmsHeaderCollectionPrefix(String xmsHeaderCollectionPrefix) {
        this.xmsHeaderCollectionPrefix = xmsHeaderCollectionPrefix;
    }

    public XmsInternalAutorestAnonymousSchema getXmsInternalAutorestAnonymousSchema() {
        return xmsInternalAutorestAnonymousSchema;
    }

    public void setXmsInternalAutorestAnonymousSchema(XmsInternalAutorestAnonymousSchema xmsInternalAutorestAnonymousSchema) {
        this.xmsInternalAutorestAnonymousSchema = xmsInternalAutorestAnonymousSchema;
    }

    public XmsLongRunningOperationOptions getXmsLongRunningOperationOptions() {
        return xmsLongRunningOperationOptions;
    }

    public void setXmsLongRunningOperationOptions(XmsLongRunningOperationOptions xmsLongRunningOperationOptions) {
        this.xmsLongRunningOperationOptions = xmsLongRunningOperationOptions;
    }

    public XmsExamples getXmsExamples() {
        return xmsExamples;
    }

    public void setXmsExamples(XmsExamples xmsExamples) {
        this.xmsExamples = xmsExamples;
    }
}
