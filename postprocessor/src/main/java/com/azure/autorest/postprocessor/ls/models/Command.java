package com.azure.autorest.postprocessor.ls.models;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "command",
        defaultImpl = Command.class)
@JsonTypeName("Command")
@JsonSubTypes({
        @JsonSubTypes.Type(name = "java.apply.workspaceEdit", value = WorkspaceEditCommand.class),
})
public class Command {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
