package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.ByteArraySchema;
import com.azure.autorest.extension.base.model.codemodel.DateTimeSchema;
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

    private IType createPrimitiveType(PrimitiveSchema primaryType) {
        boolean isLowLevelClient = JavaSettings.getInstance().isLowLevelClient();
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
                if (isLowLevelClient) {
                    return ClassType.String;
                } else {
                    DateTimeSchema dateTimeSchema = (DateTimeSchema) primaryType;
                    return (dateTimeSchema.getFormat() == DateTimeSchema.Format.DATE_TIME_RFC_1123)
                        ? ClassType.DateTimeRfc1123
                        : ClassType.DateTime;
                }
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
            case URI: return isLowLevelClient ? ClassType.String : ClassType.URL;
            case DURATION: return isLowLevelClient ? ClassType.String : ClassType.Duration;
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
