package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.ByteArraySchema;
import com.azure.autorest.extension.base.model.codemodel.DateTimeSchema;
import com.azure.autorest.extension.base.model.codemodel.NumberSchema;
import com.azure.autorest.extension.base.model.codemodel.PrimitiveSchema;
import com.azure.autorest.extension.base.model.codemodel.TimeSchema;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ArrayType;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import java.util.HashMap;
import java.util.Map;

public class PrimitiveMapper implements IMapper<PrimitiveSchema, IType> {
    private static PrimitiveMapper instance = new PrimitiveMapper();
    protected Map<PrimitiveSchema, IType> parsed = new HashMap<>();

    protected PrimitiveMapper() {
    }

    public static PrimitiveMapper getInstance() {
        return instance;
    }

    @Override
    public IType map(PrimitiveSchema primaryType) {
        if (primaryType == null) {
            return null;
        }
        if (parsed.containsKey(primaryType)) {
            return parsed.get(primaryType);
        }
        boolean isLowLevelClient = JavaSettings.getInstance().isLowLevelClient();
        IType iType;
        switch (primaryType.getType()) {
//            case null:
//                iType = PrimitiveType.Void;
//                break;
            case BOOLEAN:
                iType = PrimitiveType.Boolean;
                break;
            case BYTE_ARRAY:
                ByteArraySchema byteArraySchema = (ByteArraySchema) primaryType;
                if (byteArraySchema.getFormat() == ByteArraySchema.Format.BASE_64_URL) {
                    iType = ClassType.Base64Url;
                } else {
                    iType = ArrayType.ByteArray;
                }
                break;
            case CHAR:
                iType = PrimitiveType.Char;
                break;
            case DATE:
                iType = isLowLevelClient ? ClassType.String : ClassType.LocalDate;
                break;
            case DATE_TIME:
                if (isLowLevelClient) {
                    iType = ClassType.String;
                } else {
                    DateTimeSchema dateTimeSchema = (DateTimeSchema) primaryType;
                    if (dateTimeSchema.getFormat() == DateTimeSchema.Format.DATE_TIME_RFC_1123) {
                        iType = ClassType.DateTimeRfc1123;
                    } else {
                        iType = ClassType.DateTime;
                    }
                }
                break;
            case TIME:
                TimeSchema timeSchema = (TimeSchema) primaryType;
                iType = ClassType.String;
                break;
//            case KnownPrimaryType.DateTimeRfc1123:
//                iType = ClassType.DateTimeRfc1123;
//                break;
            case NUMBER:
                NumberSchema numberSchema = (NumberSchema) primaryType;
                if (numberSchema.getPrecision() == 64) {
                    iType = PrimitiveType.Double;
                } else if (numberSchema.getPrecision() == 32) {
                    iType = PrimitiveType.Float;
                } else {
                    iType = ClassType.BigDecimal;
                }
                break;
            case INTEGER:
                NumberSchema intSchema = (NumberSchema) primaryType;
                if (intSchema.getPrecision() == 64) {
                    iType = PrimitiveType.Long;
                } else {
                    iType = PrimitiveType.Int;
                }
                break;
//            case KnownPrimaryType.Long:
//                iType = PrimitiveType.Long;
//                break;
//            case KnownPrimaryType.Stream:
//                iType = GenericType.FluxByteBuffer;
//                break;
            case STRING:
                iType = ClassType.String;
                break;
            case URI:
                iType = ClassType.URL;
                break;
            case DURATION:
                iType = isLowLevelClient ? ClassType.String : ClassType.Duration;
                break;
            case UNIXTIME:
                iType = isLowLevelClient ? PrimitiveType.Long : PrimitiveType.UnixTimeLong;
                break;
            case UUID:
                iType = isLowLevelClient ? ClassType.String : ClassType.UUID;
                break;
            case OBJECT:
                iType = ClassType.Object;
                break;
            case CREDENTIAL:
                iType = ClassType.TokenCredential;
                break;
            default:
                throw new UnsupportedOperationException(String.format("Unrecognized AutoRest Primitive Type: %s", primaryType.getType()));
        }
        parsed.put(primaryType, iType);
        return iType;
    }
}
