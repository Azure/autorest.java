// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.cadl.patch.implementation;

import com.cadl.patch.models.Fish;
import com.cadl.patch.models.InnerModel;
import com.cadl.patch.models.Resource;
import com.cadl.patch.models.Salmon;
import com.cadl.patch.models.Shark;

/**
 * This is the Helper class to enable json merge patch serialization for a model.
 */
public class JsonMergePatchHelper {

    private static ResourceAccessor resourceAccessor;

    private static InnerModelAccessor innerModelAccessor;

    private static FishAccessor fishAccessor;

    private static SharkAccessor sharkAccessor;

    private static SalmonAccessor salmonAccessor;

    public interface ResourceAccessor {

        Resource prepareModelForJsonMergePatch(Resource resource, boolean jsonMergePatchEnabled);
    }

    public interface InnerModelAccessor {

        InnerModel prepareModelForJsonMergePatch(InnerModel innerModel, boolean jsonMergePatchEnabled);
    }

    public interface FishAccessor {

        Fish prepareModelForJsonMergePatch(Fish fish, boolean jsonMergePatchEnabled);
    }

    public interface SharkAccessor {

        Shark prepareModelForJsonMergePatch(Shark shark, boolean jsonMergePatchEnabled);
    }

    public interface SalmonAccessor {

        Salmon prepareModelForJsonMergePatch(Salmon salmon, boolean jsonMergePatchEnabled);
    }

    public static void setResourceAccessor(ResourceAccessor accessor) {
        resourceAccessor = accessor;
    }

    public static ResourceAccessor getResourceAccessor() {
        return resourceAccessor;
    }

    public static void setInnerModelAccessor(InnerModelAccessor accessor) {
        innerModelAccessor = accessor;
    }

    public static InnerModelAccessor getInnerModelAccessor() {
        return innerModelAccessor;
    }

    public static void setFishAccessor(FishAccessor accessor) {
        fishAccessor = accessor;
    }

    public static FishAccessor getFishAccessor() {
        return fishAccessor;
    }

    public static void setSharkAccessor(SharkAccessor accessor) {
        sharkAccessor = accessor;
    }

    public static SharkAccessor getSharkAccessor() {
        return sharkAccessor;
    }

    public static void setSalmonAccessor(SalmonAccessor accessor) {
        salmonAccessor = accessor;
    }

    public static SalmonAccessor getSalmonAccessor() {
        return salmonAccessor;
    }
}
