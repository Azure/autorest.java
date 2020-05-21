package com.azure.autorest.preprocessor.namer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CodeNamer {
    private static final Map<Character, String> BASIC_LATIC_CHARACTERS = new HashMap<Character, String>() {{
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
    private static final List<String> RESERVED_WORDS = Arrays.asList(
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
    );

    private CodeNamer() {
    }

    public static String toCamelCase(String name) {
        if (name == null || name.trim().isEmpty()) {
            return name;
        }

        if (name.charAt(0) == '_')
        // Remove leading underscores.
        {
            return toCamelCase(name.substring(1));
        }

        List<String> parts = new ArrayList<>();
        String[] splits = name.split("[_\\- ]");
        if (splits.length == 0) {
            return "";
        }
        parts.add(formatCase(splits[0], true));
        for (int i = 1; i != splits.length; i++) {
            parts.add(formatCase(splits[i], false));
        }
        return String.join("", parts);
    }

    public static String toPascalCase(String name) {
        if (name == null || name.trim().isEmpty()) {
            return name;
        }

        if (name.charAt(0) == '_')
        // Preserve leading underscores and treat them like
        // uppercase characters by calling 'CamelCase()' on the rest.
        {
            return '_' + toCamelCase(name.substring(1));
        }

        return Arrays.stream(name.split("[_\\- ]"))
                .filter(s -> s != null && !s.isEmpty())
                .map(s -> formatCase(s, false))
                .collect(Collectors.joining());
    }

    public static String escapeXmlComment(String comment) {
        if (comment == null) {
            return null;
        }

        return comment
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }

    private static String formatCase(String name, boolean toLower) {
        if (name != null && !name.isEmpty()) {
            if ((name.length() < 2) || ((name.length() == 2) && Character.isUpperCase(name.charAt(0)) && Character.isUpperCase(name.charAt(1)))) {
                name = toLower ? name.toLowerCase() : name.toUpperCase();
            } else {
                name = (toLower ? Character.toLowerCase(name.charAt(0))
                        : Character.toUpperCase(name.charAt(0))) + name.substring(1);
            }
        }
        return name;
    }

    public static String removeInvalidCharacters(String name) {
        return getValidName(name, '_', '-');
    }

    protected static String removeInvalidCharactersNamespace(String name) {
        return getValidName(name, '_', '-', '.');
    }

    public static String getValidName(String name, char... allowedCharacters) {
        String correctName = removeInvalidCharacters(name, allowedCharacters);

        // here we have only letters and digits or an empty String
        if (correctName == null || correctName.isEmpty() ||
                BASIC_LATIC_CHARACTERS.containsKey(correctName.charAt(0))) {
            StringBuilder sb = new StringBuilder();
            for (char symbol : name.toCharArray()) {
                if (BASIC_LATIC_CHARACTERS.containsKey(symbol)) {
                    sb.append(BASIC_LATIC_CHARACTERS.get(symbol));
                } else {
                    sb.append(symbol);
                }
            }
            correctName = removeInvalidCharacters(sb.toString(), allowedCharacters);
        }

        // if it is still empty String, throw
        if (correctName == null || correctName.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("Property name %s cannot be used as an Identifier, as it contains only invalid characters.", name));
        }

        return correctName;
    }

    public static String getTypeName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return name;
        }
        return toPascalCase(removeInvalidCharacters(getEscapedReservedName(name, "Model")));
    }

    public static String getParameterName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return name;
        }
        return toCamelCase(removeInvalidCharacters(getEscapedReservedName(name, "Parameter")));
    }

    public static String getPropertyName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return name;
        }
        return toCamelCase(removeInvalidCharacters(getEscapedReservedName(name, "Property")));
    }

    public static String getMethodGroupName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return name;
        }
        name = toPascalCase(name);
        return getEscapedReservedName(name, "Operation");
    }

    public static String getPlural(String name) {
        if (!name.endsWith("s") && !name.endsWith("S")) {
            name += "s";
        }
        return name;
    }

    public static String getMethodName(String name) {
        name = toCamelCase(name);
        return getEscapedReservedName(name, "Method");
    }

    protected static String getEscapedReservedName(String name, String appendValue) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(appendValue);

        if (RESERVED_WORDS.contains(name)) {
            name += appendValue;
        }

        return name;
    }

    private static String removeInvalidCharacters(String name, char... allowerCharacters) {
        if (name == null || name.isEmpty()) {
            return name;
        }

        StringBuilder builder = new StringBuilder();
        List<Character> allowed = new ArrayList<>();
        for (Character c : allowerCharacters) {
            allowed.add(c);
        }
        for (Character c : name.toCharArray()) {
            if (Character.isLetterOrDigit(c) || allowed.contains(c)) {
                builder.append(c);
            } else {
                builder.append("_");
            }
        }
        return builder.toString();
    }
}
