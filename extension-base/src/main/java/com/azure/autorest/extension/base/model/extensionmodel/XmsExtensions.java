package com.azure.autorest.extension.base.model.extensionmodel;

public class XmsExtensions {
    private XmsEnum xmsEnum;

    private String xmsClientName;

    private XmsPageable xmsPageable;

    private boolean xmsSkipUrlEncoding;

    private boolean xmsClientFlatten;

    private boolean xmsLongRunningOperation;

    private boolean xmsFlattened;

    private boolean xmsAzureResource;

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
}
