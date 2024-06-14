// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.flatten.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;

/**
 * Options for uploadTodo API.
 */
@Fluent
public final class UploadTodoOptions {
    /*
     * The item's title
     */
    @Generated
    private final String title;

    /*
     * A longer description of the todo item in markdown format
     */
    @Generated
    private String description;

    /*
     * The status of the todo item
     */
    @Generated
    private final TodoItemStatus status;

    /*
     * The _dummy property.
     */
    @Generated
    private String dummy;

    /*
     * The prop1 property.
     */
    @Generated
    private String prop1;

    /*
     * The prop2 property.
     */
    @Generated
    private String prop2;

    /*
     * The prop3 property.
     */
    @Generated
    private String prop3;

    /**
     * Creates an instance of UploadTodoOptions class.
     * 
     * @param title the title value to set.
     * @param status the status value to set.
     */
    @Generated
    public UploadTodoOptions(String title, TodoItemStatus status) {
        this.title = title;
        this.status = status;
    }

    /**
     * Get the title property: The item's title.
     * 
     * @return the title value.
     */
    @Generated
    public String getTitle() {
        return this.title;
    }

    /**
     * Get the description property: A longer description of the todo item in markdown format.
     * 
     * @return the description value.
     */
    @Generated
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the description property: A longer description of the todo item in markdown format.
     * 
     * @param description the description value to set.
     * @return the UploadTodoOptions object itself.
     */
    @Generated
    public UploadTodoOptions setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get the status property: The status of the todo item.
     * 
     * @return the status value.
     */
    @Generated
    public TodoItemStatus getStatus() {
        return this.status;
    }

    /**
     * Get the dummy property: The _dummy property.
     * 
     * @return the dummy value.
     */
    @Generated
    public String getDummy() {
        return this.dummy;
    }

    /**
     * Set the dummy property: The _dummy property.
     * 
     * @param dummy the dummy value to set.
     * @return the UploadTodoOptions object itself.
     */
    @Generated
    public UploadTodoOptions setDummy(String dummy) {
        this.dummy = dummy;
        return this;
    }

    /**
     * Get the prop1 property: The prop1 property.
     * 
     * @return the prop1 value.
     */
    @Generated
    public String getProp1() {
        return this.prop1;
    }

    /**
     * Set the prop1 property: The prop1 property.
     * 
     * @param prop1 the prop1 value to set.
     * @return the UploadTodoOptions object itself.
     */
    @Generated
    public UploadTodoOptions setProp1(String prop1) {
        this.prop1 = prop1;
        return this;
    }

    /**
     * Get the prop2 property: The prop2 property.
     * 
     * @return the prop2 value.
     */
    @Generated
    public String getProp2() {
        return this.prop2;
    }

    /**
     * Set the prop2 property: The prop2 property.
     * 
     * @param prop2 the prop2 value to set.
     * @return the UploadTodoOptions object itself.
     */
    @Generated
    public UploadTodoOptions setProp2(String prop2) {
        this.prop2 = prop2;
        return this;
    }

    /**
     * Get the prop3 property: The prop3 property.
     * 
     * @return the prop3 value.
     */
    @Generated
    public String getProp3() {
        return this.prop3;
    }

    /**
     * Set the prop3 property: The prop3 property.
     * 
     * @param prop3 the prop3 value to set.
     * @return the UploadTodoOptions object itself.
     */
    @Generated
    public UploadTodoOptions setProp3(String prop3) {
        this.prop3 = prop3;
        return this;
    }
}
