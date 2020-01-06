/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent;

import com.azure.autorest.extension.base.plugin.NewPlugin;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FluentJavaSettings {

    private final NewPlugin host;

    private Set<String> javaNamesForAddInner;

    private Set<ClientFlattenProperty> javaNamesForClientFlatten;

    public static class ClientFlattenProperty {
        private final String javaName;
        private final String propertySerializedName;

        public ClientFlattenProperty(String javaName, String propertySerializedName) {
            this.javaName = javaName;
            this.propertySerializedName = propertySerializedName;
        }

        public String getJavaName() {
            return javaName;
        }

        public String getPropertySerializedName() {
            return propertySerializedName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ClientFlattenProperty that = (ClientFlattenProperty) o;
            return javaName.equals(that.javaName) &&
                    propertySerializedName.equals(that.propertySerializedName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(javaName, propertySerializedName);
        }
    }

    public FluentJavaSettings(NewPlugin host) {
        Objects.requireNonNull(host);
        this.host = host;

        loadSettings();
    }

    public Set<String> getJavaNamesForAddInner() {
        return javaNamesForAddInner;
    }

    public Set<ClientFlattenProperty> getJavaNamesForClientFlatten() {
        return javaNamesForClientFlatten;
    }

    private void loadSettings() {
        String addInnerSetting = host.getStringValue("add-inner");
        if (addInnerSetting != null && !addInnerSetting.isEmpty()) {
            javaNamesForAddInner = Arrays.stream(addInnerSetting.split(Pattern.quote(",")))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toSet());
        } else {
            javaNamesForAddInner = Collections.emptySet();
        }

        String addClientFlattenSetting = host.getStringValue("add-client-flatten");
        if (addClientFlattenSetting != null && !addClientFlattenSetting.isEmpty()) {
            javaNamesForClientFlatten = Arrays.stream(addClientFlattenSetting.split(Pattern.quote(",")))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(s -> {
                        String[] segs = s.split(Pattern.quote("."));
                        if (segs.length == 1) {
                            return new ClientFlattenProperty(segs[0], "properties");
                        } else if (segs.length == 2) {
                            return new ClientFlattenProperty(segs[0], segs[1]);
                        } else {
                            throw new IllegalArgumentException("Failed to parse client flatten: " + s);
                        }
                    })
                    .collect(Collectors.toSet());
        }
    }
}
