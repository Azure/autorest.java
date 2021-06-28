/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.checker;

import com.azure.autorest.fluent.FluentGen;
import com.azure.autorest.fluent.FluentGenAccessor;
import com.azure.autorest.fluent.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class JavaFormatterTests {

    private final String JAVA_CONTENT = "package com.azure.autorest.fluent.checker;\n" +
            "\n" +
            "import com.azure.autorest.extension.base.plugin.PluginLogger;\n" +
            "import com.azure.autorest.fluent.FluentGen;\n" +
            "import com.azure.core.util.CoreUtils;\n" +
            "import org.slf4j.Logger;\n" +
            "\n" +
            "import java.lang.reflect.Method;\n" +
            "import java.util.ArrayList;\n" +
            "import java.util.Collections;\n" +
            "import java.util.List;\n" +
            "import java.util.regex.Pattern;\n" +
            "\n" +
            "public class JavaFormatter {\n" +
            "    private final String content;\n" +
            "    private final String path;\n" +
            "\n" +
            "    public JavaFormatter(String content, String path) {\n" +
            "        this.content = content;\n" +
            "        this.path = path;\n" +
            "        String longString = \"/subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.Compute/virtualMachineScaleSets/{virtualMachineScaleSetName}/virtualMachines/{virtualmachineIndex}/networkInterfaces/{networkInterfaceName}/ipconfigurations/{ipConfigurationName}/publicipaddresses/{publicIpAddressName}\"\n" +
            "    }\n" +
            "}\n";

    private static FluentGenAccessor fluentgenAccessor;

    @BeforeAll
    public static void ensurePlugin() {
        FluentGen fluentgen = new TestUtils.MockFluentGen();
        fluentgenAccessor = new FluentGenAccessor(fluentgen);
    }

    @Test
    public void testFormatter() {
        JavaFormatter formatter = new JavaFormatter(JAVA_CONTENT, "mock");
        formatter.format(true);
    }

    @Test
    public void testLengthLimit() {
        final int lengthLimit = 120;
        String content = JavaFormatter.fixOverlongStringLiteral(JAVA_CONTENT, lengthLimit);
        String[] lines = content.split("\r?\n", -1);
        Assertions.assertTrue(Arrays.stream(lines).allMatch(s -> s.length() <= lengthLimit));
    }
}
