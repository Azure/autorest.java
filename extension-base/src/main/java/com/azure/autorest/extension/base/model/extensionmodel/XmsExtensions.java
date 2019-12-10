package com.azure.autorest.extension.base.model.extensionmodel;

public class XmsExtensions {
    private XmsEnum xmsEnum;

    private String xmsClientName;

    private XmsPageable xmsPageable;

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
}
