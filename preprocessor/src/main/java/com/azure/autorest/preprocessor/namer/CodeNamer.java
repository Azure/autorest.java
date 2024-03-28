// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.preprocessor.namer;

import org.atteo.evo.inflector.English;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CodeNamer {
    private static final Map<Character, String> BASIC_LATIN_CHARACTERS = new HashMap<>() {{
        put((char) 32, "Space");
        put((char) 33, "ExclamationMark");
        put((char) 34, "QuotationMark");
        put((char) 35, "NumberSign");
        put((char) 36, "DollarSign");
        put((char) 37, "PercentSign");
        put((char) 38, "Ampersand");
        put((char) 39, "Apostrophe");
        put((char) 40, "LeftParenthesis");
        put((char) 41, "RightParenthesis");
        put((char) 42, "Asterisk");
        put((char) 43, "PlusSign");
        put((char) 44, "Comma");
        put((char) 45, "HyphenMinus");
        put((char) 46, "FullStop");
        put((char) 47, "Slash");
        put((char) 48, "Zero");
        put((char) 49, "One");
        put((char) 50, "Two");
        put((char) 51, "Three");
        put((char) 52, "Four");
        put((char) 53, "Five");
        put((char) 54, "Six");
        put((char) 55, "Seven");
        put((char) 56, "Eight");
        put((char) 57, "Nine");
        put((char) 58, "Colon");
        put((char) 59, "Semicolon");
        put((char) 60, "LessThanSign");
        put((char) 61, "EqualSign");
        put((char) 62, "GreaterThanSign");
        put((char) 63, "QuestionMark");
        put((char) 64, "AtSign");
        put((char) 91, "LeftSquareBracket");
        put((char) 92, "Backslash");
        put((char) 93, "RightSquareBracket");
        put((char) 94, "CircumflexAccent");
        put((char) 96, "GraveAccent");
        put((char) 123, "LeftCurlyBracket");
        put((char) 124, "VerticalBar");
        put((char) 125, "RightCurlyBracket");
        put((char) 126, "Tilde");
    }};
    private static final Set<String> RESERVED_WORDS = new HashSet<>(Arrays.asList(
            "abstract", "assert", "boolean", "Boolean", "break",
            "byte", "Byte", "case", "catch", "char",
            "Character", "class", "Class", "const", "continue",
            "default", "do", "double", "Double", "else",
            "enum", "extends", "false", "final", "finally",
            "float", "Float", "for", "goto", "if",
            "implements", "import", "int", "Integer", "long",
            "Long", "interface", "instanceof", "native", "new",
            "null", "package", "private", "protected", "public",
            "return", "short", "Short", "static", "strictfp",
            "super", "switch", "synchronized", "this", "throw",
            "throws", "transient", "true", "try", "void",
            "Void", "volatile", "while", "Date", "Datetime",
            "OffsetDateTime", "Duration", "Period", "Stream",
            "String", "Object", "header", "_"
    ));

    private static final Set<String> RESERVED_WORDS_CLASSES = new HashSet<>(RESERVED_WORDS);
    static {
        RESERVED_WORDS_CLASSES.addAll(Arrays.asList(
                // following are commonly used classes/annotations in service client, from azure-core
                "Host", "ServiceInterface", "ServiceMethod", "ServiceClient", "ReturnType",
                "Get", "Put", "Post", "Patch", "Delete", "Headers",
                "ExpectedResponses", "UnexpectedResponseExceptionType", "UnexpectedResponseExceptionTypes",
                "HostParam", "PathParam", "QueryParam", "HeaderParam", "FormParam", "BodyParam",
                "Fluent", "Immutable", "JsonFlatten", "Override"
        ));
    }

    private static final Pattern CASE_SPLIT = Pattern.compile("[_\\- ]");

    private CodeNamer() {
    }

    public static Map<Character, String> getBasicLatinCharacters() {
        return BASIC_LATIN_CHARACTERS;
    }

    public static String toCamelCase(String name) {
        if (name == null || name.trim().isEmpty()) {
            return name;
        }

        // Remove leading underscores.
        if (name.charAt(0) == '_') {
            return toCamelCase(name.substring(1));
        }

        String[] splits = CASE_SPLIT.split(name);
        if (splits.length == 0) {
            return "";
        }

        splits[0] = formatCase(splits[0], true);
        for (int i = 1; i != splits.length; i++) {
            splits[i] = formatCase(splits[i], false);
        }
        return String.join("", splits);
    }

    public static String toPascalCase(String name) {
        if (name == null || name.trim().isEmpty()) {
            return name;
        }

        // Preserve leading underscores and treat them like
        // uppercase characters by calling 'CamelCase()' on the rest.
        if (name.charAt(0) == '_') {
            return '_' + toCamelCase(name.substring(1));
        }

        return CASE_SPLIT.splitAsStream(name)
                .filter(s -> s != null && !s.isEmpty())
                .map(s -> formatCase(s, false))
                .collect(Collectors.joining());
    }

    public static String escapeXmlComment(String comment) {
        if (comment == null || comment.isEmpty()) {
            return comment;
        }

        // Use a linear replacement for the all the characters.
        // This has a few benefits:
        // 1. It performs a single loop over the comment string.
        // 2. It avoids instantiating multiple strings if multiple of the replacement cases are found.
        // 3. If no replacements are needed, it returns the original string.
        StringBuilder sb = null;
        int prevStart = 0;
        int commentLength = comment.length();

        for (int i = 0; i < commentLength; i++) {
            String replacement = null;
            char c = comment.charAt(i);
            if (c == '&') {
                replacement = "&amp;";
            } else if (c == '<') {
                replacement = "&lt;";
            } else if (c == '>') {
                replacement = "&gt;";
            }

            if (replacement != null) {
                if (sb == null) {
                    // Add enough overhead to account for 1/8 of the string to be replaced.
                    sb = new StringBuilder(commentLength + 3 * (commentLength / 8));
                }

                if (prevStart != i) {
                    sb.append(comment, prevStart, i);
                }
                sb.append(replacement);
                prevStart = i + 1;
            }
        }

        if (sb == null) {
            return comment;
        }

        sb.append(comment, prevStart, commentLength);
        return sb.toString();
    }

    private static String formatCase(String name, boolean toLower) {
        if (name != null && !name.isEmpty()) {
            if ((name.length() < 2) || ((name.length() == 2) && Character.isUpperCase(name.charAt(0))
                && Character.isUpperCase(name.charAt(1)))) {
                name = toLower ? name.toLowerCase() : name.toUpperCase();
            } else {
                name = (toLower ? Character.toLowerCase(name.charAt(0))
                    : Character.toUpperCase(name.charAt(0))) + name.substring(1);
            }
        }
        return name;
    }

    public static String removeInvalidCharacters(String name) {
        return getValidName(name, c -> c == '_' || c == '-');
    }

    /**
     * Gets a valid name for the given name.
     *
     * @param name The name to get a valid name for.
     * @return The valid name.
     */
    public static String getValidName(String name) {
        return getValidName(name, c -> false);
    }

    /**
     * Gets a valid name for the given name.
     *
     * @param name The name to get a valid name for.
     * @param allowedCharacterMatcher A predicate that determines if a character is allowed.
     * @return The valid name.
     */
    public static String getValidName(String name, Predicate<Character> allowedCharacterMatcher) {
        String correctName = removeInvalidCharacters(name, allowedCharacterMatcher);

        // here we have only letters and digits or an empty String
        if (correctName == null || correctName.isEmpty() ||
                BASIC_LATIN_CHARACTERS.containsKey(correctName.charAt(0))) {
            StringBuilder sb = new StringBuilder();
            for (char symbol : name.toCharArray()) {
                if (BASIC_LATIN_CHARACTERS.containsKey(symbol)) {
                    sb.append(BASIC_LATIN_CHARACTERS.get(symbol));
                } else {
                    sb.append(symbol);
                }
            }
            correctName = removeInvalidCharacters(sb.toString(), allowedCharacterMatcher);
        }

        // if it is still empty String, throw
        if (correctName == null || correctName.isEmpty()) {
            throw new IllegalArgumentException(
                String.format("Property name %s cannot be used as an Identifier, as it contains only invalid characters.", name));
        }

        return correctName;
    }

    public static String getClientName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return name;
        }
        return getEscapedReservedNameAndClasses(toPascalCase(removeInvalidCharacters(name)), "Client");
    }

    public static String getTypeName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return name;
        }
        return getEscapedReservedNameAndClasses(toPascalCase(removeInvalidCharacters(name)), "Model");
    }

    public static String getParameterName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return name;
        }
        return getEscapedReservedName(toCamelCase(removeInvalidCharacters(name)), "Parameter");
    }

    public static String getPropertyName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return name;
        }
        return getEscapedReservedName(toCamelCase(removeInvalidCharacters(name)), "Property");
    }

    public static String getMethodGroupName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return name;
        }
        name = toPascalCase(name);
        return getEscapedReservedName(name, "Operation");
    }

    public static String getPlural(String name) {
        if (name != null && !name.isEmpty() && !name.endsWith("s") && !name.endsWith("S")) {
            name = English.plural(name);
        }
        return name;
    }

    public static String getMethodName(String name) {
        name = toCamelCase(name);
        return getEscapedReservedName(name, "Method");
    }

    public static String getEscapedReservedName(String name, String appendValue) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(appendValue);

        if (RESERVED_WORDS.contains(name)) {
            name += appendValue;
        }

        return name;
    }

    protected static String getEscapedReservedNameAndClasses(String name, String appendValue) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(appendValue);

        if (RESERVED_WORDS_CLASSES.contains(name)) {
            name += appendValue;
        }

        return name;
    }

    private static String removeInvalidCharacters(String name, Predicate<Character> allowedCharacterMatcher) {
        if (name == null || name.isEmpty()) {
            return name;
        }

        StringBuilder sb = null;
        int prevStart = 0;
        int nameLength = name.length();

        for (int i = 0; i < nameLength; i++) {
            char c = name.charAt(i);
            if (!Character.isLetterOrDigit(c) && !allowedCharacterMatcher.test(c)) {
                if (sb == null) {
                    sb = new StringBuilder(nameLength);
                }

                if (prevStart != i) {
                    sb.append(name, prevStart, i);
                }

                sb.append('_');
                prevStart = i + 1;
            }
        }

        if (sb == null) {
            return name;
        }

        sb.append(name, prevStart, nameLength);
        return sb.toString();
    }
}
