/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;

public class GeneratedUtilsClassWorkbenchTests {

    static final class Utils {
        static String getValueFromIdByName(String id, String name) {
            if (id == null) {
                return null;
            }
            Iterable<String> iterable = Arrays.asList(id.split("/"));
            Iterator<String> itr = iterable.iterator();
            while (itr.hasNext()) {
                String part = itr.next();
                if (part != null && !part.trim().isEmpty()) {
                    if (part.equalsIgnoreCase(name)) {
                        if (itr.hasNext()) {
                            return itr.next();
                        } else {
                            return null;
                        }
                    }
                }
            }
            return null;
        }
    }

    @Test
    public void testGetValueFromIdByName() {
        String id = "/subscriptions/00000000-0000-0000-0000-000000000000/resourceGroups/rg-weidxu/providers/Microsoft.ServiceBus/namespaces/sb1weidxu/queues/queue1";

        Assertions.assertEquals("00000000-0000-0000-0000-000000000000", Utils.getValueFromIdByName(id, "subscriptions"));
        Assertions.assertEquals("rg-weidxu", Utils.getValueFromIdByName(id, "ResourceGroups"));
        Assertions.assertEquals("Microsoft.ServiceBus", Utils.getValueFromIdByName(id, "providers"));
        Assertions.assertEquals("sb1weidxu", Utils.getValueFromIdByName(id, "namespaces"));
        Assertions.assertEquals("queue1", Utils.getValueFromIdByName(id, "queues"));

        Assertions.assertNull(Utils.getValueFromIdByName(id, "notExist"));
    }

    @Test void testGetValuesFromId() {
        String idTemplate = "/{scope}/providers/Microsoft.Authorization/roleAssignments/{roleAssignmentName}";
        String id = "/subscriptions/00000000-0000-0000-0000-000000000000/resourceGroups/rg-weidxu/providers/Microsoft.Authorization/roleAssignments/00000000-0000-0000-0000-000000000001";
    }
}
