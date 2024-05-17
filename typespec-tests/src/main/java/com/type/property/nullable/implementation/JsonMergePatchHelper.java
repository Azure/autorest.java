// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.nullable.implementation;

import com.type.property.nullable.models.BytesProperty;
import com.type.property.nullable.models.CollectionsByteProperty;
import com.type.property.nullable.models.CollectionsModelProperty;
import com.type.property.nullable.models.DatetimeProperty;
import com.type.property.nullable.models.DurationProperty;
import com.type.property.nullable.models.InnerModel;
import com.type.property.nullable.models.StringProperty;

/**
 * This is the Helper class to enable json merge patch serialization for a model.
 */
public class JsonMergePatchHelper {
    private static StringPropertyAccessor stringPropertyAccessor;

    public interface StringPropertyAccessor {
        StringProperty prepareModelForJsonMergePatch(StringProperty stringProperty, boolean jsonMergePatchEnabled);
    }

    public static void setStringPropertyAccessor(StringPropertyAccessor accessor) {
        stringPropertyAccessor = accessor;
    }

    public static StringPropertyAccessor getStringPropertyAccessor() {
        return stringPropertyAccessor;
    }

    private static BytesPropertyAccessor bytesPropertyAccessor;

    public interface BytesPropertyAccessor {
        BytesProperty prepareModelForJsonMergePatch(BytesProperty bytesProperty, boolean jsonMergePatchEnabled);
    }

    public static void setBytesPropertyAccessor(BytesPropertyAccessor accessor) {
        bytesPropertyAccessor = accessor;
    }

    public static BytesPropertyAccessor getBytesPropertyAccessor() {
        return bytesPropertyAccessor;
    }

    private static DatetimePropertyAccessor datetimePropertyAccessor;

    public interface DatetimePropertyAccessor {
        DatetimeProperty prepareModelForJsonMergePatch(DatetimeProperty datetimeProperty,
            boolean jsonMergePatchEnabled);
    }

    public static void setDatetimePropertyAccessor(DatetimePropertyAccessor accessor) {
        datetimePropertyAccessor = accessor;
    }

    public static DatetimePropertyAccessor getDatetimePropertyAccessor() {
        return datetimePropertyAccessor;
    }

    private static DurationPropertyAccessor durationPropertyAccessor;

    public interface DurationPropertyAccessor {
        DurationProperty prepareModelForJsonMergePatch(DurationProperty durationProperty,
            boolean jsonMergePatchEnabled);
    }

    public static void setDurationPropertyAccessor(DurationPropertyAccessor accessor) {
        durationPropertyAccessor = accessor;
    }

    public static DurationPropertyAccessor getDurationPropertyAccessor() {
        return durationPropertyAccessor;
    }

    private static CollectionsBytePropertyAccessor collectionsBytePropertyAccessor;

    public interface CollectionsBytePropertyAccessor {
        CollectionsByteProperty prepareModelForJsonMergePatch(CollectionsByteProperty collectionsByteProperty,
            boolean jsonMergePatchEnabled);
    }

    public static void setCollectionsBytePropertyAccessor(CollectionsBytePropertyAccessor accessor) {
        collectionsBytePropertyAccessor = accessor;
    }

    public static CollectionsBytePropertyAccessor getCollectionsBytePropertyAccessor() {
        return collectionsBytePropertyAccessor;
    }

    private static CollectionsModelPropertyAccessor collectionsModelPropertyAccessor;

    public interface CollectionsModelPropertyAccessor {
        CollectionsModelProperty prepareModelForJsonMergePatch(CollectionsModelProperty collectionsModelProperty,
            boolean jsonMergePatchEnabled);
    }

    public static void setCollectionsModelPropertyAccessor(CollectionsModelPropertyAccessor accessor) {
        collectionsModelPropertyAccessor = accessor;
    }

    public static CollectionsModelPropertyAccessor getCollectionsModelPropertyAccessor() {
        return collectionsModelPropertyAccessor;
    }

    private static InnerModelAccessor innerModelAccessor;

    public interface InnerModelAccessor {
        InnerModel prepareModelForJsonMergePatch(InnerModel innerModel, boolean jsonMergePatchEnabled);
    }

    public static void setInnerModelAccessor(InnerModelAccessor accessor) {
        innerModelAccessor = accessor;
    }

    public static InnerModelAccessor getInnerModelAccessor() {
        return innerModelAccessor;
    }
}
