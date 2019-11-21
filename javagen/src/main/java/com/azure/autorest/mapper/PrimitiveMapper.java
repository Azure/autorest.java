package com.azure.autorest.mapper;

import com.azure.autorest.model.clientmodel.ArrayType;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.extension.base.model.codemodel.PrimitiveSchema;

import java.util.HashMap;
import java.util.Map;

public class PrimitiveMapper implements IMapper<PrimitiveSchema, IType> {
    private PrimitiveMapper() {
    }

    private static PrimitiveMapper instance = new PrimitiveMapper();

    public static PrimitiveMapper getInstance() {
        return instance;
    }

    Map<PrimitiveSchema, IType> parsed = new HashMap<>();

    @Override
    public IType map(PrimitiveSchema primaryType) {
        if (primaryType == null)
        {
            return null;
        }
        if (parsed.containsKey(primaryType))
        {
            return parsed.get(primaryType);
        }
        IType iType;
        switch (primaryType.getType())
        {
//            case null:
//                iType = PrimitiveType.Void;
//                break;
//            case KnownPrimaryType.Base64Url:
//                iType = ClassType.Base64Url;
//                break;
            case BOOLEAN:
                iType = PrimitiveType.Boolean;
                break;
            case BYTE_ARRAY:
                iType = ArrayType.ByteArray;
                break;
            case CHAR:
                iType = PrimitiveType.Char;
                break;
            case DATE:
                iType = ClassType.LocalDate;
                break;
            case DATE_TIME:
                iType = ClassType.DateTime;
                break;
//            case KnownPrimaryType.DateTimeRfc1123:
//                iType = ClassType.DateTimeRfc1123;
//                break;
            case NUMBER:
                iType = PrimitiveType.Double;
                break;
//            case KnownPrimaryType.Decimal:
//                iType = ClassType.BigDecimal;
//                break;
            case INTEGER:
                iType = PrimitiveType.Int;
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
                iType = ClassType.Duration;
                break;
            case UNIXTIME:
                iType = PrimitiveType.UnixTimeLong;
                break;
            case UUID:
                iType = ClassType.UUID;
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
