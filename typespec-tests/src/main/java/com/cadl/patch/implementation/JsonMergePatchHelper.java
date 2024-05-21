// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.patch.implementation;

import com.cadl.patch.models.Fish;
import com.cadl.patch.models.InnerModel;
import com.cadl.patch.models.Resource;

/**
 * This is the Helper class to enable json merge patch serialization for a model.
 */
public class JsonMergePatchHelper {
    private static ResourceAccessor resourceAccessor;

    public interface ResourceAccessor {
        Resource prepareModelForJsonMergePatch(Resource resource, boolean jsonMergePatchEnabled);

        boolean isJsonMergePatch(Resource resource);
    }

    public static void setResourceAccessor(ResourceAccessor accessor) {
        resourceAccessor = accessor;
    }

    public static ResourceAccessor getResourceAccessor() {
        return resourceAccessor;
    }

    private static InnerModelAccessor innerModelAccessor;

    public interface InnerModelAccessor {
        InnerModel prepareModelForJsonMergePatch(InnerModel innerModel, boolean jsonMergePatchEnabled);

        boolean isJsonMergePatch(InnerModel innerModel);
    }

    public static void setInnerModelAccessor(InnerModelAccessor accessor) {
        innerModelAccessor = accessor;
    }

    public static InnerModelAccessor getInnerModelAccessor() {
        return innerModelAccessor;
    }

    private static FishAccessor fishAccessor;

    public interface FishAccessor {
        Fish prepareModelForJsonMergePatch(Fish fish, boolean jsonMergePatchEnabled);

        boolean isJsonMergePatch(Fish fish);

        void setId(Fish fish, String id);

        void setName(Fish fish, String name);

        void setAge(Fish fish, int age);

        void setColor(Fish fish, String color);
    }

    public static void setFishAccessor(FishAccessor accessor) {
        fishAccessor = accessor;
    }

    public static FishAccessor getFishAccessor() {
        return fishAccessor;
    }
}
