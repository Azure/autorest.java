package com.azure.autorest.customization.ls.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum FileChangeType {
    CREATED(1),
    CHANGED(2),
    DELETED(3);

    private int value;

    FileChangeType(int value) {
        this.value = value;
    }

    @JsonCreator
    public static FileChangeType fromInt(int value) {
        FileChangeType[] items = FileChangeType.values();
        for (FileChangeType item : items) {
            if (item.value == value) {
                return item;
            }
        }
        return null;
    }

    @JsonValue
    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
