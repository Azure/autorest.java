// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.util;

import org.atteo.evo.inflector.English;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CodeNamer {

    private static NamerFactory factory = new DefaultNamerFactory();

    private static final Pattern CAMEL_CASE_SPLIT = Pattern.compile("[_\\- ]");
    private static final Pattern ESCAPE_COMMENT = Pattern.compile(Pattern.quote("*/"));
    private static final Pattern MERGE_UNDERSCORES = Pattern.compile("_{2,}");
    private static final Pattern CHARACTERS_TO_REPLACE_WITH_UNDERSCORE = Pattern.compile("[\\\\/.+ -]+");

    public static void setFactory(NamerFactory templateFactory) {
        factory = templateFactory;
    }

    public static ModelNamer getModelNamer() {
        return factory.getModelNamer();
    }

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
        String[] splits = CAMEL_CASE_SPLIT.split(name);
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

        return CAMEL_CASE_SPLIT.splitAsStream(name)
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

    public static String escapeComment(String comment) {
        if (comment == null) {
            return null;
        }

        return ESCAPE_COMMENT.matcher(comment).replaceAll("*&#47;");
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
        return com.azure.autorest.preprocessor.namer.CodeNamer.getValidName(name, '_', '-');
    }

    public static String getPropertyName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return name;
        }
        return com.azure.autorest.preprocessor.namer.CodeNamer.getEscapedReservedName(toCamelCase(removeInvalidCharacters(name)), "Property");
    }

    public static String getPlural(String name) {
        if (name != null && !name.isEmpty() && !name.endsWith("s") && !name.endsWith("S")) {
            name = English.plural(name);
        }
        return name;
    }

    public static String getEnumMemberName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return name;
        }

        // trim leading and trailing '_'
        if ((name.startsWith("_") || name.endsWith("_")) && !name.chars().allMatch(c -> c == '_')) {
            StringBuilder sb = new StringBuilder(name);
            while (sb.length() > 0 && sb.charAt(0) == '_') {
                sb.deleteCharAt(0);
            }
            while (sb.length() > 0 && sb.charAt(sb.length() - 1) == '_') {
                sb.setLength(sb.length() - 1);
            }
            name = sb.toString();
        }

        String result = removeInvalidCharacters(CHARACTERS_TO_REPLACE_WITH_UNDERSCORE.matcher(name).replaceAll("_"));
        result = MERGE_UNDERSCORES.matcher(result).replaceAll("_");  // merge multiple underlines
        Function<Character, Boolean> isUpper = c -> c >= 'A' && c <= 'Z';
        Function<Character, Boolean> isLower = c -> c >= 'a' && c <= 'z';
        for (int i = 1; i < result.length() - 1; i++) {
            if (isUpper.apply(result.charAt(i))) {
                if (result.charAt(i - 1) != '_' && isLower.apply(result.charAt(i - 1))) {
                    result = result.substring(0, i) + "_" + result.substring(i);
                }
            }
        }

        if (result.startsWith("_") || result.endsWith("_")) {
            if (!result.chars().allMatch(c -> c == (int) '_')) {
                // some char is not '_', trim it

                StringBuilder sb = new StringBuilder(result);
                while (sb.length() > 0 && sb.charAt(0) == '_') {
                    sb.deleteCharAt(0);
                }
                while (sb.length() > 0 && sb.charAt(sb.length() - 1) == '_') {
                    sb.setLength(sb.length() - 1);
                }
                result = sb.toString();
            } else {
                // all char is '_', then transform some '_' to

                Map<Character, String> basicLaticCharacters = com.azure.autorest.preprocessor.namer.CodeNamer.getBasicLatinCharacters();
                if (result.startsWith("_") && basicLaticCharacters.containsKey(name.charAt(0))) {
                    result = basicLaticCharacters.get(name.charAt(0)) + result.substring(1);
                    if (result.endsWith("_") && basicLaticCharacters.containsKey(name.charAt(name.length() - 1))) {
                        result = result.substring(0, result.length() - 1) + basicLaticCharacters.get(name.charAt(name.length() - 1));
                    }
                }
            }
        }

        return result.toUpperCase();
    }

    private static final Set<String> RESERVED_CLIENT_METHOD_PARAMETER_NAME = new HashSet<>(Arrays.asList(
            "service",      // the ServiceInterface local variable
            "client"        // the ManagementClient local variable
    ));

    public static String getEscapedReservedClientMethodParameterName(String name) {
        if (RESERVED_CLIENT_METHOD_PARAMETER_NAME.contains(name)) {
            name += "Param";
        }
        return name;
    }
}
