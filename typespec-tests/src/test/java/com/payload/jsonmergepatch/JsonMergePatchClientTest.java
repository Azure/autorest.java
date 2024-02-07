// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.payload.jsonmergepatch;


import com.payload.jsonmergepatch.models.InnerModel;
import com.payload.jsonmergepatch.models.Resource;
import com.payload.jsonmergepatch.models.ResourcePatch;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonMergePatchClientTest {

    private final JsonMergePatchClient client = new JsonMergePatchClientBuilder().buildClient();

    @Test
    void createAndUpdateResource() {
        // create resource
        Resource resource = createResource();
        client.createResource(resource);
        // update resource
        ResourcePatch resourcePatch = createResourcePatch();
        updateResourcePatch(resourcePatch);
        client.updateResource(resourcePatch);
    }

    @Test
    void updateOptionalResource() {
        ResourcePatch resourcePatch = createResourcePatch();
        updateResourcePatch(resourcePatch);
        client.updateOptionalResource(resourcePatch);
    }

    private static Resource createResource() {
        InnerModel innerModel = new InnerModel();
        innerModel.setName("InnerMadge");
        innerModel.setDescription("innerDesc");
        Map<String, InnerModel> map = new HashMap<>();
        map.put("key", innerModel);
        List<InnerModel> array = Arrays.asList(innerModel);
        Resource resource = new Resource("Madge");
        resource.setArray(array);
        resource.setMap(map);
        resource.setDescription("desc");
        resource.setIntValue(1);
        resource.setFloatValue(1.1);
        resource.setInnerModel(innerModel);
        resource.setIntArray(Arrays.asList(1, 2, 3));
        return resource;
    }

    private static ResourcePatch createResourcePatch() {
        ResourcePatch resourcePatch = new ResourcePatch();
        resourcePatch.setDescription("desc");
        InnerModel innerModel = new InnerModel();
        innerModel.setName("InnerMadge");
        innerModel.setDescription("innerDesc");
        Map<String, InnerModel> map = new HashMap<>();
        map.put("key", innerModel);
        List<InnerModel> array = Arrays.asList(innerModel);
        resourcePatch.setArray(array);
        resourcePatch.setMap(map);
        resourcePatch.setIntValue(1);
        resourcePatch.setFloatValue(1.1);
        resourcePatch.setInnerModel(innerModel);
        resourcePatch.setIntArray(Arrays.asList(1, 2, 3));
        return resourcePatch;
    }

    private static void updateResourcePatch(ResourcePatch resourcePatch) {
        resourcePatch.setDescription(null);
        resourcePatch.getMap().get("key").setDescription(null);
        resourcePatch.getMap().put("key2", null);
        resourcePatch.setArray(null);
        resourcePatch.setInnerModel(null);
        resourcePatch.setIntValue(null);
        resourcePatch.setFloatValue(null);
        resourcePatch.setInnerModel(null);
        resourcePatch.setIntArray(null);
    }
}