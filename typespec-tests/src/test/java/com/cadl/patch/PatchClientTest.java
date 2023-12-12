// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.cadl.patch;

import com.azure.core.util.BinaryData;
import com.cadl.patch.implementation.JsonMergePatchHelper;
import com.cadl.patch.models.Fish;
import com.cadl.patch.models.InnerModel;
import com.cadl.patch.models.Resource;
import com.cadl.patch.models.Shark;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class PatchClientTest {

    @Test
    public void testSerializationForNumbers() {
        // patch not enabled
        Resource resource = new Resource(new HashMap<>());
        resource.setIntValue(null);
        resource.setLongValue(null);
        String json = BinaryData.fromObject(resource).toString();
        Assertions.assertEquals("{\"map\":{}}", json);

        // patch enabled
        JsonMergePatchHelper.getResourceAccessor().prepareModelForJsonMergePatch(resource, true);
        resource.setIntValue(null);
        resource.setLongValue(null);
        json = BinaryData.fromObject(resource).toString();
        Assertions.assertEquals("{\"map\":{},\"longValue\":null,\"intValue\":null}", json);
    }

    @Test
    public void testSerializationForString() {
        // patch not enabled
        Resource resource = new Resource(new HashMap<>());
        resource.setDescription("description");
        String json = BinaryData.fromObject(resource).toString();
        Assertions.assertEquals("{\"map\":{},\"description\":\"description\"}", json);

        // patch enabled
        JsonMergePatchHelper.getResourceAccessor().prepareModelForJsonMergePatch(resource, true);
        resource.setDescription(null);
        json = BinaryData.fromObject(resource).toString();
        Assertions.assertEquals("{\"map\":{},\"description\":null}", json);
    }

    @Test
    public void testSerializationForNestedModelProperty() {
        // patch not enabled, null value is not serialized
        Resource resource = new Resource(new HashMap<>());
        resource.setInnerModelProperty(null);
        String json = BinaryData.fromObject(resource).toString();
        Assertions.assertEquals("{\"map\":{}}", json);

        // patch enabled, null value is serialized
        JsonMergePatchHelper.getResourceAccessor().prepareModelForJsonMergePatch(resource, true);
        resource.setInnerModelProperty(null);
        json = BinaryData.fromObject(resource).toString();
        Assertions.assertEquals("{\"map\":{},\"wireNameForInnerModelProperty\":null}", json);
    }

    @Test
    public void testSerializationForMapProperty() {
        Resource resource = new Resource(new HashMap<>());
        resource.getMap().put("key", new InnerModel("value1"));
        String json = BinaryData.fromObject(resource).toString();
        Assertions.assertEquals("{\"map\":{\"key\":{\"name\":\"value1\"}}}", json);

        JsonMergePatchHelper.getResourceAccessor().prepareModelForJsonMergePatch(resource, true);
        resource.getMap().put("key", null);
        json = BinaryData.fromObject(resource).toString();
        Assertions.assertEquals("{\"map\":{\"key\":null}}", json);
    }

    @Test
    public void testSerializationForEnum() {
        Resource resource = new Resource(new HashMap<>());
        resource.setEnumValue(null);
        String json = BinaryData.fromObject(resource).toString();
        Assertions.assertEquals("{\"map\":{}}", json);

        JsonMergePatchHelper.getResourceAccessor().prepareModelForJsonMergePatch(resource, true);
        resource.setEnumValue(null);
        json = BinaryData.fromObject(resource).toString();
        Assertions.assertEquals("{\"map\":{},\"enumValue\":null}", json);
    }

    @Test
    public void testSerializationForHierarchicalModel() {
        Fish fish = new Shark(1, "goblin");
        fish.setColor("pink");
        String json = BinaryData.fromObject(fish).toString();
        Assertions.assertEquals("{\"age\":1,\"color\":\"pink\",\"sharktype\":\"goblin\"}", json);

        JsonMergePatchHelper.getFishAccessor().prepareModelForJsonMergePatch(fish, true);
        fish.setColor(null);
        json = BinaryData.fromObject(fish).toString();
        Assertions.assertEquals("{\"age\":1,\"color\":null,\"sharktype\":\"goblin\"}", json);
    }
}
