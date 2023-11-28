// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.util;

import com.azure.core.util.CoreUtils;
import org.atteo.evo.inflector.English;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CodeNamer {
    private static final String[] BASIC_LATIN_CHARACTERS;

    static {
        BASIC_LATIN_CHARACTERS = new String[128];
        BASIC_LATIN_CHARACTERS[32] = "Space";
        BASIC_LATIN_CHARACTERS[33] = "ExclamationMark";
        BASIC_LATIN_CHARACTERS[34] = "QuotationMark";
        BASIC_LATIN_CHARACTERS[35] = "NumberSign";
        BASIC_LATIN_CHARACTERS[36] = "DollarSign";
        BASIC_LATIN_CHARACTERS[37] = "PercentSign";
        BASIC_LATIN_CHARACTERS[38] = "Ampersand";
        BASIC_LATIN_CHARACTERS[39] = "Apostrophe";
        BASIC_LATIN_CHARACTERS[40] = "LeftParenthesis";
        BASIC_LATIN_CHARACTERS[41] = "RightParenthesis";
        BASIC_LATIN_CHARACTERS[42] = "Asterisk";
        BASIC_LATIN_CHARACTERS[43] = "PlusSign";
        BASIC_LATIN_CHARACTERS[44] = "Comma";
        BASIC_LATIN_CHARACTERS[45] = "HyphenMinus";
        BASIC_LATIN_CHARACTERS[46] = "FullStop";
        BASIC_LATIN_CHARACTERS[47] = "Slash";
        BASIC_LATIN_CHARACTERS[48] = "Zero";
        BASIC_LATIN_CHARACTERS[49] = "One";
        BASIC_LATIN_CHARACTERS[50] = "Two";
        BASIC_LATIN_CHARACTERS[51] = "Three";
        BASIC_LATIN_CHARACTERS[52] = "Four";
        BASIC_LATIN_CHARACTERS[53] = "Five";
        BASIC_LATIN_CHARACTERS[54] = "Six";
        BASIC_LATIN_CHARACTERS[55] = "Seven";
        BASIC_LATIN_CHARACTERS[56] = "Eight";
        BASIC_LATIN_CHARACTERS[57] = "Nine";
        BASIC_LATIN_CHARACTERS[58] = "Colon";
        BASIC_LATIN_CHARACTERS[59] = "Semicolon";
        BASIC_LATIN_CHARACTERS[60] = "LessThanSign";
        BASIC_LATIN_CHARACTERS[61] = "EqualSign";
        BASIC_LATIN_CHARACTERS[62] = "GreaterThanSign";
        BASIC_LATIN_CHARACTERS[63] = "QuestionMark";
        BASIC_LATIN_CHARACTERS[64] = "AtSign";
        BASIC_LATIN_CHARACTERS[91] = "LeftSquareBracket";
        BASIC_LATIN_CHARACTERS[92] = "Backslash";
        BASIC_LATIN_CHARACTERS[93] = "RightSquareBracket";
        BASIC_LATIN_CHARACTERS[94] = "CircumflexAccent";
        BASIC_LATIN_CHARACTERS[96] = "GraveAccent";
        BASIC_LATIN_CHARACTERS[123] = "LeftCurlyBracket";
        BASIC_LATIN_CHARACTERS[124] = "VerticalBar";
        BASIC_LATIN_CHARACTERS[125] = "RightCurlyBracket";
        BASIC_LATIN_CHARACTERS[126] = "Tilde";
    }

    private static final Set<String> RESERVED_WORDS = new HashSet<>(
        Arrays.asList("abstract", "assert", "boolean", "Boolean", "break", "byte", "Byte", "case", "catch", "char",
            "Character", "class", "Class", "const", "continue", "default", "do", "double", "Double", "else", "enum",
            "extends", "false", "final", "finally", "float", "Float", "for", "goto", "if", "implements", "import",
            "int", "Integer", "long", "Long", "interface", "instanceof", "native", "new", "null", "package", "private",
            "protected", "public", "return", "short", "Short", "static", "strictfp", "super", "switch", "synchronized",
            "this", "throw", "throws", "transient", "true", "try", "void", "Void", "volatile", "while", "Date",
            "Datetime", "OffsetDateTime", "Duration", "Period", "Stream", "String", "Object", "header", "_"));

    private static NamerFactory factory = new DefaultNamerFactory();

    private static final Pattern CAMEL_CASE_SPLIT = Pattern.compile("[_\\- ]");
    private static final Pattern ESCAPE_COMMENT = Pattern.compile(Pattern.quote("*/"));
    private static final Pattern MERGE_UNDERSCORES = Pattern.compile("_{2,}");
    private static final Pattern CHARACTERS_TO_REPLACE_WITH_UNDERSCORE = Pattern.compile("[\\\\/.+ -]+");
    private static final Pattern NEW_LINE = Pattern.compile("\r?\n");

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
        return CoreUtils.stringJoin("", parts);
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

        return CAMEL_CASE_SPLIT.splitAsStream(name).filter(s -> s != null && !s.isEmpty())
            .map(s -> formatCase(s, false)).collect(Collectors.joining());
    }

    public static String escapeXmlComment(String comment) {
        if (comment == null) {
            return null;
        }

        return comment.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

    public static String escapeComment(String comment) {
        if (comment == null) {
            return null;
        }

        return ESCAPE_COMMENT.matcher(comment).replaceAll("*&#47;");
    }

    private static String formatCase(String name, boolean toLower) {
        if (name != null && !name.isEmpty()) {
            if ((name.length() < 2) || ((name.length() == 2) && Character.isUpperCase(name.charAt(0))
                && Character.isUpperCase(name.charAt(1)))) {
                name = toLower ? name.toLowerCase() : name.toUpperCase();
            } else {
                name = (toLower ? Character.toLowerCase(name.charAt(0)) : Character.toUpperCase(name.charAt(0)))
                    + name.substring(1);
            }
        }
        return name;
    }

    public static String removeInvalidCharacters(String name) {
        return getValidName(name, '_', '-');
    }

    public static String getValidName(String name, char... allowedCharacters) {
        String correctName = removeInvalidCharacters(name, allowedCharacters);

        // here we have only letters and digits or an empty String
        if (correctName == null || correctName.isEmpty() || isBasicLatinCharacter(correctName.charAt(0))) {
            StringBuilder sb = null;
            int previousIndex = 0;

            int length = name.length();
            for (int i = 0; i < length; i++) {
                char symbol = name.charAt(i);
                if (isBasicLatinCharacter(symbol)) {
                    if (sb == null) {
                        sb = new StringBuilder();
                    }
                    if (previousIndex < i) {
                        sb.append(name, previousIndex, i);
                    }
                    sb.append(BASIC_LATIN_CHARACTERS[symbol]);
                    previousIndex = i + 1;
                }
            }

            if (sb != null) {
                if (previousIndex < length) {
                    sb.append(name, previousIndex, length);
                }
                correctName = sb.toString();
            } else {
                correctName = name;
            }

            correctName = removeInvalidCharacters(correctName, allowedCharacters);
        }

        // if it is still empty String, throw
        if (correctName == null || correctName.isEmpty()) {
            throw new IllegalArgumentException(String.format(
                "Property name %s cannot be used as an Identifier, as it contains only invalid characters.", name));
        }

        return correctName;
    }

    public static String getPropertyName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return name;
        }
        return getEscapedReservedName(toCamelCase(removeInvalidCharacters(name)), "Property");
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
                if (result.startsWith("_") && isBasicLatinCharacter(name.charAt(0))) {
                    result = BASIC_LATIN_CHARACTERS[name.charAt(0)] + result.substring(1);
                    if (result.endsWith("_") && isBasicLatinCharacter(name.charAt(name.length() - 1))) {
                        result = result.substring(0, result.length() - 1) + BASIC_LATIN_CHARACTERS[name.charAt(name.length() - 1)];
                    }
                }
            }
        }

        return result.toUpperCase();
    }

    public static List<String> wordWrap(String text, int width) {
        Objects.requireNonNull(text);
        List<String> ret = new ArrayList<>();
        String[] lines = NEW_LINE.split(text, -1);
        for (String line : lines) {
            String processedLine = line.trim();

            // yield empty lines as they are (probably) intentional
            if (processedLine.isEmpty()) {
                ret.add(processedLine);
            }

            // feast on the line until it's gone
            while (!processedLine.isEmpty()) {
                // determine potential wrapping points
                List<Integer> whitespacePositions = new ArrayList<>();
                for (int i = 0; i != processedLine.length(); i++) {
                    if (Character.isWhitespace(processedLine.charAt(i))) {
                        whitespacePositions.add(i);
                    }
                }
                whitespacePositions.add(processedLine.length());
                int preWidthWrapAt = -1;
                int postWidthWrapAt = -1;
                for (int i = 0; i != whitespacePositions.size() - 1; i++) {
                    if (whitespacePositions.get(i + 1) > width) {
                        preWidthWrapAt = whitespacePositions.get(i);
                        postWidthWrapAt = whitespacePositions.get(i + 1);
                        break;
                    }
                }
                int wrapAt = processedLine.length();
                if (preWidthWrapAt > 0) {
                    wrapAt = preWidthWrapAt;
                } else if (postWidthWrapAt > 0) {
                    wrapAt = postWidthWrapAt;
                }
                // wrap
                ret.add(processedLine.substring(0, wrapAt));
                processedLine = processedLine.substring(wrapAt).trim();
            }
        }
        return ret;
    }

    protected static String getEscapedReservedName(String name, String appendValue) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(appendValue);

        if (RESERVED_WORDS.contains(name)) {
            name += appendValue;
        }

        return name;
    }

    private static final Set<String> RESERVED_CLIENT_METHOD_PARAMETER_NAME = new HashSet<>(
        Arrays.asList("service",      // the ServiceInterface local variable
            "client"        // the ManagementClient local variable
        ));

    public static String getEscapedReservedClientMethodParameterName(String name) {
        if (RESERVED_CLIENT_METHOD_PARAMETER_NAME.contains(name)) {
            name += "Param";
        }
        return name;
    }

    private static String removeInvalidCharacters(String name, char... allowedCharacters) {
        if (name == null || name.isEmpty()) {
            return name;
        }

        Set<Character> allowed = new HashSet<>();
        for (Character c : allowedCharacters) {
            allowed.add(c);
        }

        StringBuilder builder = null;
        int prevStart = 0;

        int length = name.length();
        for (int i = 0; i < length; i++) {
            char c = name.charAt(i);
            if (Character.isLetterOrDigit(c) || allowed.contains(c)) {
                continue;
            }

            if (builder == null) {
                builder = new StringBuilder(length);
            }
            if (prevStart != i) {
                builder.append(name, prevStart, i);
            }
            prevStart = i + 1;
        }

        if (builder == null) {
            return name;
        }
        
        if (prevStart < length) {
            builder.append(name, prevStart, length);
        }
        return builder.toString();
    }

    private static boolean isBasicLatinCharacter(char c) {
        return c < 128 && BASIC_LATIN_CHARACTERS[c] != null;
    }
}
