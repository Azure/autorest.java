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

class JsonMergePatchClientTest {

    JsonMergePatchClient client = new JsonMergePatchClientBuilder().buildClient();

    @Test
    void createAndUpdateResource() {
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
        // create resource
        client.createResource(resource);
        // update resource
        ResourcePatch resourcePatch = new ResourcePatch();
        resourcePatch.setDescription(null);
        resourcePatch.setArray(null);
        innerModel.setDescription(null);
        map.put("key2", null);
        resourcePatch.setMap(map);
        resourcePatch.setIntValue(null);
        resourcePatch.setFloatValue(null);
        resourcePatch.setInnerModel(null);
        resourcePatch.setIntArray(null);
        client.updateResource(resourcePatch);
    }

    @Test
    void createAndUpdateOptionalResource() {
        ResourcePatch resourcePatch = new ResourcePatch();
        resourcePatch.setDescription(null);
        resourcePatch.setArray(null);
        InnerModel innerModel = new InnerModel();
        innerModel.setName("InnerMadge");
        innerModel.setDescription("innerDesc");
        innerModel.setDescription(null);
        Map<String, InnerModel> map = new HashMap<>();
        map.put("key", innerModel);
        map.put("key2", null);
        resourcePatch.setMap(map);
        resourcePatch.setIntValue(null);
        resourcePatch.setFloatValue(null);
        resourcePatch.setInnerModel(null);
        resourcePatch.setIntArray(null);
        client.updateOptionalResource(resourcePatch);
    }


}