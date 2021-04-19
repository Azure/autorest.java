package com.azure.autorest.android.mapper;

import com.azure.autorest.extension.base.model.codemodel.ByteArraySchema;
import com.azure.autorest.extension.base.model.codemodel.DateTimeSchema;
import com.azure.autorest.extension.base.model.codemodel.PrimitiveSchema;
import com.azure.autorest.mapper.PrimitiveMapper;
import com.azure.autorest.model.clientmodel.ArrayType;
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
    public IType map(PrimitiveSchema primaryType) {
        IType baseResolved = super.map(primaryType);
        if (primaryType == null
                || primaryType.getType() == null) {
            return baseResolved;
        }
        switch (primaryType.getType()) {
            case BYTE_ARRAY:
                ByteArraySchema byteArraySchema = (ByteArraySchema) primaryType;
                if (byteArraySchema.getFormat() == ByteArraySchema.Format.BASE_64_URL) {
                    return ClassType.AndroidBase64Url;
                } else {
                    return ArrayType.ByteArray;
                }
            case DATE_TIME:
                DateTimeSchema dateTimeSchema = (DateTimeSchema) primaryType;
                if (dateTimeSchema.getFormat() == DateTimeSchema.Format.DATE_TIME_RFC_1123) {
                    return ClassType.AndroidDateTimeRfc1123;
                } else {
                    return ClassType.AndroidDateTime;
                }
            case DURATION:
                return ClassType.AndroidDuration;
            case DATE:
                return ClassType.AndroidLocalDate;
            case UNIXTIME:
                return ClassType.AndroidDateTime;
            default:
               return baseResolved;
        }
    }
}
