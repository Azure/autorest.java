// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.ByteArraySchema;
import com.azure.autorest.extension.base.model.codemodel.DateTimeSchema;
import com.azure.autorest.extension.base.model.codemodel.DurationSchema;
import com.azure.autorest.extension.base.model.codemodel.NumberSchema;
import com.azure.autorest.extension.base.model.codemodel.PrimitiveSchema;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ArrayType;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.PrimitiveType;

import java.util.HashMap;
import java.util.Map;

public class PrimitiveMapper implements IMapper<PrimitiveSchema, IType> {
    private static final PrimitiveMapper INSTANCE = new PrimitiveMapper();
    protected Map<PrimitiveSchema, IType> parsed = new HashMap<>();

    protected PrimitiveMapper() {
    }

    public static PrimitiveMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public IType map(PrimitiveSchema primaryType) {
        if (primaryType == null) {
            return null;
        }

        return parsed.computeIfAbsent(primaryType, this::createPrimitiveType);
    }

    /**
     * Extension.
     *
     * @param primaryType the primitive schema.
     * @return the client model type.
     */
    protected IType createPrimitiveType(PrimitiveSchema primaryType) {
        boolean isLowLevelClient = JavaSettings.getInstance().isDataPlaneClient();
        boolean urlAsString = JavaSettings.getInstance().urlAsString();

        switch (primaryType.getType()) {
//            case null:
//                iType = PrimitiveType.Void;
//                break;
            case BOOLEAN: return PrimitiveType.Boolean;
            case BYTE_ARRAY:
                ByteArraySchema byteArraySchema = (ByteArraySchema) primaryType;
                return (byteArraySchema.getFormat() == ByteArraySchema.Format.BASE_64_URL)
                    ? ClassType.Base64Url
                    : ArrayType.ByteArray;
            case CHAR: return PrimitiveType.Char;
            case DATE: return isLowLevelClient ? ClassType.String : ClassType.LocalDate;
            case DATE_TIME:
                DateTimeSchema dateTimeSchema = (DateTimeSchema) primaryType;
                return (dateTimeSchema.getFormat() == DateTimeSchema.Format.DATE_TIME_RFC_1123)
                    ? ClassType.DateTimeRfc1123
                    : ClassType.DateTime;
            case TIME:
//                TimeSchema timeSchema = (TimeSchema) primaryType;
                return ClassType.String;
//            case KnownPrimaryType.DateTimeRfc1123:
//                iType = ClassType.DateTimeRfc1123;
//                break;
            case NUMBER:
                NumberSchema numberSchema = (NumberSchema) primaryType;
                if (numberSchema.getPrecision() == 64) {
                    return PrimitiveType.Double;
                } else if (numberSchema.getPrecision() == 32) {
                    return PrimitiveType.Float;
                } else {
                    return ClassType.BigDecimal;
                }
            case INTEGER:
                NumberSchema intSchema = (NumberSchema) primaryType;
                return (intSchema.getPrecision() == 64)
                    ? PrimitiveType.Long
                    : PrimitiveType.Int;
//            case KnownPrimaryType.Long:
//                iType = PrimitiveType.Long;
//                break;
//            case KnownPrimaryType.Stream:
//                iType = GenericType.FluxByteBuffer;
//                break;
            case STRING: return ClassType.String;
            case ARM_ID: return ClassType.String;
            case URI: return isLowLevelClient || urlAsString ? ClassType.String : ClassType.URL;
            case DURATION:
                DurationSchema durationSchema = (DurationSchema) primaryType;
                IType durationType = ClassType.Duration;
                if (durationSchema.getFormat() != null) {
                    switch (durationSchema.getFormat()) {
                        case SECONDS_INTEGER:
                            return PrimitiveType.DurationLong;
                        case SECONDS_NUMBER:
                            return PrimitiveType.DurationDouble;
                    }
                }
                return durationType;
            case UNIXTIME: return isLowLevelClient ? PrimitiveType.Long : PrimitiveType.UnixTimeLong;
            case UUID: return isLowLevelClient ? ClassType.String : ClassType.UUID;
            case OBJECT: return ClassType.Object;
            case CREDENTIAL: return ClassType.TokenCredential;
            default:
                throw new UnsupportedOperationException(String.format("Unrecognized AutoRest Primitive Type: %s",
                    primaryType.getType()));
        }
    }
}
