// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstylexmlserialization.models;

/** Defines values for CopyStatusType. */
public enum CopyStatusType {
    /** Enum value pending. */
    PENDING("pending"),

    /** Enum value success. */
    SUCCESS("success"),

    /** Enum value aborted. */
    ABORTED("aborted"),

    /** Enum value failed. */
    FAILED("failed");

    /** The actual serialized value for a CopyStatusType instance. */
    private final String value;

    CopyStatusType(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a CopyStatusType instance.
     *
     * @param value the serialized value to parse.
     * @return the parsed CopyStatusType object, or null if unable to parse.
     */
    public static CopyStatusType fromString(String value) {
        if (value == null) {
            return null;
        }
        CopyStatusType[] items = CopyStatusType.values();
        for (CopyStatusType item : items) {
            if (item.toString().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return this.value;
    }
}
