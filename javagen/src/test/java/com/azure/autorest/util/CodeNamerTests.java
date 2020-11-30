/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.util;

import org.junit.Assert;
import org.junit.Test;

public class CodeNamerTests {

    @Test
    public void testEnumMemberName() {
        Assert.assertEquals("NINE_NINE_ONE", CodeNamer.getEnumMemberName("991"));

        Assert.assertEquals("TCP", CodeNamer.getEnumMemberName("tcp"));

        Assert.assertEquals("WSDL_LINK", CodeNamer.getEnumMemberName("wsdl-link"));

        Assert.assertEquals("CONTENT_TYPE", CodeNamer.getEnumMemberName("content_type"));

        Assert.assertEquals("MICROSOFT_APP_CONFIGURATION_CONFIGURATION_STORES", CodeNamer.getEnumMemberName("Microsoft.AppConfiguration/configurationStores"));

        Assert.assertEquals("ASTERISK", CodeNamer.getEnumMemberName("*"));

        Assert.assertEquals("ALL", CodeNamer.getEnumMemberName("$all"));

        Assert.assertEquals("ALL", CodeNamer.getEnumMemberName("all*"));

        Assert.assertEquals("SYSTEM_ASSIGNED_USER_ASSIGNED", CodeNamer.getEnumMemberName("SystemAssigned, UserAssigned"));

        Assert.assertEquals("ONE_ZEROMINUTELY", CodeNamer.getEnumMemberName("_10minutely"));

        Assert.assertEquals("_", CodeNamer.getEnumMemberName("_"));
    }
}
