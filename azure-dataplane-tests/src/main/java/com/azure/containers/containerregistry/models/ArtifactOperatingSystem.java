// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.containers.containerregistry.models;

import com.azure.core.util.ExpandableStringEnum;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.Collection;

/**
 * Defines values for ArtifactOperatingSystem.
 */
public final class ArtifactOperatingSystem extends ExpandableStringEnum<ArtifactOperatingSystem>
    implements JsonSerializable<ArtifactOperatingSystem> {
    /**
     * Static value aix for ArtifactOperatingSystem.
     */
    public static final ArtifactOperatingSystem AIX = fromString("aix");

    /**
     * Static value android for ArtifactOperatingSystem.
     */
    public static final ArtifactOperatingSystem ANDROID = fromString("android");

    /**
     * Static value darwin for ArtifactOperatingSystem.
     */
    public static final ArtifactOperatingSystem DARWIN = fromString("darwin");

    /**
     * Static value dragonfly for ArtifactOperatingSystem.
     */
    public static final ArtifactOperatingSystem DRAGONFLY = fromString("dragonfly");

    /**
     * Static value freebsd for ArtifactOperatingSystem.
     */
    public static final ArtifactOperatingSystem FREE_BSD = fromString("freebsd");

    /**
     * Static value illumos for ArtifactOperatingSystem.
     */
    public static final ArtifactOperatingSystem ILLUMOS = fromString("illumos");

    /**
     * Static value ios for ArtifactOperatingSystem.
     */
    public static final ArtifactOperatingSystem IOS = fromString("ios");

    /**
     * Static value js for ArtifactOperatingSystem.
     */
    public static final ArtifactOperatingSystem JS = fromString("js");

    /**
     * Static value linux for ArtifactOperatingSystem.
     */
    public static final ArtifactOperatingSystem LINUX = fromString("linux");

    /**
     * Static value netbsd for ArtifactOperatingSystem.
     */
    public static final ArtifactOperatingSystem NET_BSD = fromString("netbsd");

    /**
     * Static value openbsd for ArtifactOperatingSystem.
     */
    public static final ArtifactOperatingSystem OPEN_BSD = fromString("openbsd");

    /**
     * Static value plan9 for ArtifactOperatingSystem.
     */
    public static final ArtifactOperatingSystem PLAN9 = fromString("plan9");

    /**
     * Static value solaris for ArtifactOperatingSystem.
     */
    public static final ArtifactOperatingSystem SOLARIS = fromString("solaris");

    /**
     * Static value windows for ArtifactOperatingSystem.
     */
    public static final ArtifactOperatingSystem WINDOWS = fromString("windows");

    /**
     * Creates a new instance of ArtifactOperatingSystem value.
     * 
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Deprecated
    public ArtifactOperatingSystem() {
    }

    /**
     * Creates or finds a ArtifactOperatingSystem from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding ArtifactOperatingSystem.
     */
    public static ArtifactOperatingSystem fromString(String name) {
        return fromString(name, ArtifactOperatingSystem.class);
    }

    /**
     * Gets known ArtifactOperatingSystem values.
     * 
     * @return known ArtifactOperatingSystem values.
     */
    public static Collection<ArtifactOperatingSystem> values() {
        return values(ArtifactOperatingSystem.class);
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        return jsonWriter.writeString(toString());
    }

    /**
     * Reads a ArtifactOperatingSystem from the JSON stream.
     * &lt;p&gt;.
     * The passed JsonReader must be positioned at a JsonToken.STRING value.
     * 
     * @param jsonReader The JsonReader being read.
     * @return The ArtifactOperatingSystem that the JSON stream represented, may return null.
     * @throws java.io.IOException If a ArtifactOperatingSystem fails to be read from the JsonReader.
     */
    public static ArtifactOperatingSystem fromJson(JsonReader jsonReader) throws IOException {
        return fromString(jsonReader.getString(), ArtifactOperatingSystem.class);
    }
}
