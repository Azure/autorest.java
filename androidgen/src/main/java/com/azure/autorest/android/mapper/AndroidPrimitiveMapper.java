package com.azure.autorest.android.mapper;

import com.azure.autorest.mapper.PrimitiveMapper;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.IType;

public class AndroidPrimitiveMapper extends PrimitiveMapper {
    private static AndroidPrimitiveMapper instance = new AndroidPrimitiveMapper();

    protected AndroidPrimitiveMapper() {
    }

    public static PrimitiveMapper getInstance() {
        return instance;
    }

    @Override
    protected IType getBase64UrlClassType() {
        return ClassType.AndroidBase64Url;
    }

    @Override
    protected IType getLocalDateClassType() {
        return ClassType.AndroidLocalDate;
    }

    @Override
    protected IType getDateTimeClassType() {
        return ClassType.AndroidDateTime;
    }

    @Override
    protected IType getDurationClassType() {
        return ClassType.AndroidDuration;
    }

    @Override
    protected IType getDateTimeRfc1123ClassType() {
        return ClassType.AndroidDateTimeRfc1123;
    }

    @Override
    protected IType getUnixTimeDateTime() {
        return ClassType.AndroidUnixTimeDateTime;
    }
}
