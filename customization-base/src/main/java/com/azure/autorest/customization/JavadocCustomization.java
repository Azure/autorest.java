// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization;

import com.github.javaparser.ast.nodeTypes.NodeWithJavadoc;
import com.github.javaparser.javadoc.Javadoc;
import com.github.javaparser.javadoc.JavadocBlockTag;
import com.github.javaparser.javadoc.description.JavadocDescription;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.github.javaparser.javadoc.JavadocBlockTag.Type.EXCEPTION;
import static com.github.javaparser.javadoc.JavadocBlockTag.Type.PARAM;
import static com.github.javaparser.javadoc.JavadocBlockTag.Type.RETURN;
import static com.github.javaparser.javadoc.JavadocBlockTag.Type.THROWS;

/**
 * The Javadoc customization for an AutoRest generated classes and methods.
 */
public final class JavadocCustomization {
    private final NodeWithJavadoc<?> node;
    private Javadoc javadoc;

    JavadocCustomization(NodeWithJavadoc<?> node) {
        this.node = node;
        this.javadoc = node.getJavadoc().orElse(new Javadoc(new JavadocDescription()));
    }

    public JavadocCustomization replace(JavadocCustomization other) {
        node.setJavadocComment(other.javadoc);
        return this;
    }

    /**
     * Gets the Javadoc description.
     *
     * @return The Javadoc description.
     */
    public String getDescription() {
        return javadoc.getDescription().toText();
    }

    /**
     * Sets the description in the Javadoc.
     *
     * @param description the description for the current class/method.
     * @return the Javadoc customization object for chaining
     */
    public JavadocCustomization setDescription(String description) {
        Javadoc newJavadoc = new Javadoc(JavadocDescription.parseText(description));
        newJavadoc.getBlockTags().addAll(javadoc.getBlockTags());

        this.javadoc = newJavadoc;
        node.setJavadocComment(javadoc);

        return this;
    }

    /**
     * Gets a read-only view of the Javadoc params.
     *
     * @return Read-only view of the Javadoc params.
     */
    public Map<String, String> getParams() {
        return javadoc.getBlockTags().stream().filter(tag -> tag.getType() == PARAM)
            .collect(Collectors.toMap(tag -> tag.getName().get(), tag -> tag.getContent().toText()));
    }

    /**
     * Sets the param Javadoc for a parameter on the method.
     *
     * @param parameterName the parameter name on the method
     * @param description the description for this parameter
     * @return the Javadoc customization object for chaining
     */
    public JavadocCustomization setParam(String parameterName, String description) {
        int index = indexOf(javadoc.getBlockTags(), PARAM, parameterName);
        if (index == -1) {
            javadoc.getBlockTags().add(JavadocBlockTag.createParamBlockTag(parameterName, description));
        } else {
            javadoc.getBlockTags().set(index, JavadocBlockTag.createParamBlockTag(parameterName, description));
        }

        return this;
    }

    /**
     * Removes a parameter Javadoc on the method.
     *
     * @param parameterName the name of the parameter on the method
     * @return the Javadoc customization object for chaining
     */
    public JavadocCustomization removeParam(String parameterName) {
        int index = indexOf(javadoc.getBlockTags(), PARAM, parameterName);
        if (index != -1) {
            javadoc.getBlockTags().remove(index);
        }

        return this;
    }

    /**
     * Gets the Javadoc return.
     *
     * @return The Javadoc return.
     */
    public String getReturn() {
        int index = indexOf(javadoc.getBlockTags(), JavadocBlockTag.Type.RETURN);

        return (index == -1) ? null : javadoc.getBlockTags().get(index).getContent().toText();
    }

    /**
     * Sets the return Javadoc on the method.
     *
     * @param description the description for the return value
     * @return the Javadoc customization object for chaining
     */
    public JavadocCustomization setReturn(String description) {
        int index = indexOf(javadoc.getBlockTags(), JavadocBlockTag.Type.RETURN);

        if (index == -1) {
            javadoc.getBlockTags().add(new JavadocBlockTag(RETURN, description));
        } else {
            javadoc.getBlockTags().set(index, new JavadocBlockTag(RETURN, description));
        }

        return this;
    }

    /**
     * Removes the return Javadoc for a method.
     *
     * @return the Javadoc customization object for chaining
     */
    public JavadocCustomization removeReturn() {
        int index = indexOf(javadoc.getBlockTags(), JavadocBlockTag.Type.RETURN);
        if (index != -1) {
            javadoc.getBlockTags().remove(index);
        }

        return this;
    }

    /**
     * Gets a read-only view of the Javadoc throws.
     *
     * @return Read-only view of the Javadoc throws.
     */
    public Map<String, String> getThrows() {
        return javadoc.getBlockTags().stream().filter(tag -> tag.getType() == THROWS)
            .collect(Collectors.toMap(tag -> tag.getName().get(), tag -> tag.getContent().toText()));
    }

    /**
     * Sets a throws Javadoc for a method.
     *
     * @param exceptionType the type of the exception the method will throw
     * @param description the description for the exception
     * @return the Javadoc customization object for chaining
     */
    public JavadocCustomization setsThrows(String exceptionType, String description) {
        int index = indexOf(javadoc.getBlockTags(), PARAM, exceptionType);
        if (index == -1) {
            javadoc.getBlockTags().add(new JavadocBlockTag(THROWS, exceptionType + " " + description));
        } else {
            javadoc.getBlockTags().set(index, new JavadocBlockTag(THROWS, exceptionType + " " + description));
        }
        return this;
    }

    /**
     * Removes a throw Javadoc for a method.
     *
     * @param exceptionType the type of the exception the method will throw
     * @return the Javadoc customization object for chaining
     */
    public JavadocCustomization removeThrows(String exceptionType) {
        int index = indexOf(javadoc.getBlockTags(), THROWS, exceptionType);

        if (index != -1) {
            javadoc.getBlockTags().remove(index);
        }

        return this;
    }

    /**
     * Gets a read-only view of the Javadoc sees.
     *
     * @return Read-only view of the Javadoc sees.
     */
    public List<String> getSees() {
        return javadoc.getBlockTags().stream().filter(tag -> tag.getType() == JavadocBlockTag.Type.SEE)
            .map(tag -> tag.getContent().toText()).collect(Collectors.toList());
    }

    /**
     * Adds a see Javadoc.
     *
     * @param seeDoc the link to the extra documentation
     * @return the Javadoc customization object for chaining
     * @see <a href="https://docs.oracle.com/javase/7/docs/technotes/tools/windows/javadoc.html#see">Oracle docs on see tag</a>
     */
    public JavadocCustomization addSee(String seeDoc) {
        javadoc.getBlockTags().add(new JavadocBlockTag(JavadocBlockTag.Type.SEE, seeDoc));

        return this;
    }

    /**
     * Gets the Javadoc since.
     *
     * @return The Javadoc since.
     */
    public String getSince() {
        int index = indexOf(javadoc.getBlockTags(), JavadocBlockTag.Type.DEPRECATED);

        return (index == -1) ? null : javadoc.getBlockTags().get(index).getContent().toText();
    }

    /**
     * Sets the since Javadoc on the method.
     *
     * @param sinceDoc the version for the since tag
     * @return the Javadoc customization object for chaining
     */
    public JavadocCustomization setSince(String sinceDoc) {
        int index = indexOf(javadoc.getBlockTags(), JavadocBlockTag.Type.SINCE);

        if (index == -1) {
            javadoc.getBlockTags().add(new JavadocBlockTag(JavadocBlockTag.Type.SINCE, sinceDoc));
        } else {
            javadoc.getBlockTags().set(index, new JavadocBlockTag(JavadocBlockTag.Type.SINCE, sinceDoc));
        }

        return this;
    }

    /**
     * Removes the Javadoc since.
     *
     * @return The updated JavadocCustomization object.
     */
    public JavadocCustomization removeSince() {
        int index = indexOf(javadoc.getBlockTags(), JavadocBlockTag.Type.SINCE);

        if (index != -1) {
            javadoc.getBlockTags().remove(index);
        }

        return this;
    }

    /**
     * Gets the Javadoc deprecated.
     *
     * @return The Javadoc deprecated.
     */
    public String getDeprecated() {
        int index = indexOf(javadoc.getBlockTags(), JavadocBlockTag.Type.DEPRECATED);

        return (index == -1) ? null : javadoc.getBlockTags().get(index).getContent().toText();
    }

    /**
     * Sets the deprecated Javadoc on the method.
     *
     * @param deprecatedDoc the deprecation reason
     * @return the Javadoc customization object for chaining
     */
    public JavadocCustomization setDeprecated(String deprecatedDoc) {
        int index = indexOf(javadoc.getBlockTags(), JavadocBlockTag.Type.DEPRECATED);

        if (index == -1) {
            javadoc.getBlockTags().add(new JavadocBlockTag(JavadocBlockTag.Type.DEPRECATED, deprecatedDoc));
        } else {
            javadoc.getBlockTags().set(index, new JavadocBlockTag(JavadocBlockTag.Type.DEPRECATED, deprecatedDoc));
        }

        return this;
    }

    /**
     * Removes the Javadoc deprecated.
     *
     * @return The updated JavadocCustomization object.
     */
    public JavadocCustomization removeDeprecated() {
        int index = indexOf(javadoc.getBlockTags(), JavadocBlockTag.Type.DEPRECATED);

        if (index != -1) {
            javadoc.getBlockTags().remove(index);
        }

        return this;
    }

    /**
     * Gets the index in the tags where the specific tag is located. This API is for Javadoc tags that don't have a
     * name.
     *
     * @param tags the list of Javadoc tags
     * @param tagType the type of the Javadoc tag
     * @return the index of the tag in the list
     */
    private static int indexOf(List<JavadocBlockTag> tags, JavadocBlockTag.Type tagType) {
        return indexOf(tags, tagType, null);
    }

    /**
     * Gets the index in the tags where the specific tag is located.
     *
     * @param tags the list of Javadoc tags
     * @param tagType the type of the Javadoc tag
     * @param tagName the name of the Javadoc tag
     * @return the index of the tag in the list
     */
    private static int indexOf(List<JavadocBlockTag> tags, JavadocBlockTag.Type tagType, String tagName) {
        for (int i = 0; i < tags.size(); i++) {
            JavadocBlockTag tag = tags.get(i);
            if (tag.getType() != tagType) {
                continue;
            }

            if (!hasTagName(tagType)) {
                return i;
            }

            if (tag.getName().map(name -> Objects.equals(name, tagName)).orElse(false)) {
                return i;
            }
        }

        return -1;
    }

    private static boolean hasTagName(JavadocBlockTag.Type type) {
        return type == PARAM || type == EXCEPTION || type == THROWS;
    }
}
