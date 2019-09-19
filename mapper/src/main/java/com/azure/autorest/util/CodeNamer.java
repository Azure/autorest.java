package com.azure.autorest.util;

import java.util.*;
import java.util.stream.Collectors;

public class CodeNamer {
    public static String toCamelCase(String name)
    {
        if (name == null || name.trim().isEmpty())
        {
            return name;
        }

        if (name.charAt(0) == '_')
        // Preserve leading underscores.
        {
            return '_' + toCamelCase(name.substring(1));
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

    public static String toPascalCase(String name)
    {
        if (name == null || name.trim().isEmpty())
        {
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

    public static String EscapeXmlComment(String comment)
    {
        if (comment == null)
        {
            return null;
        }

        return comment
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }

    private static String formatCase(String name, boolean toLower)
    {
        if (name != null && !name.isEmpty())
        {
            if ((name.length() < 2) || ((name.length() == 2) && Character.isUpperCase(name.charAt(0)) && Character.isUpperCase(name.charAt(1))))
            {
                name = toLower ? name.toLowerCase() : name.toUpperCase();
            }
            else
            {
                name =
                        (toLower
                                ? Character.toLowerCase(name.charAt(0))
                                : Character.toUpperCase(name.charAt(0))) + name.substring(1, name.length() - 1);
            }
        }
        return name;
    }

    public static String RemoveInvalidCharacters(String name)
    {
        return GetValidName(name, '_', '-');
    }

    protected static String RemoveInvalidCharactersNamespace(String name)
    {
        return GetValidName(name, '_', '-', '.');
    }

    public static String GetValidName(String name, char... allowedCharacters)
    {
        String correctName = RemoveInvalidCharacters(name, allowedCharacters);

        // here we have only letters and digits or an empty String
        if (correctName == null || correctName.isEmpty() ||
                basicLaticCharacters.containsKey(correctName.charAt(0)))
        {
            StringBuilder sb = new StringBuilder();
            for (char symbol : name.toCharArray())
            {
                if (basicLaticCharacters.containsKey(symbol))
                {
                    sb.append(basicLaticCharacters.get(symbol));
                }
                else
                {
                    sb.append(symbol);
                }
            }
            correctName = RemoveInvalidCharacters(sb.toString(), allowedCharacters);
        }

        // if it is still empty String, throw
        if (correctName == null || correctName.isEmpty())
        {
            throw new IllegalArgumentException(
                    String.format("Property name %s cannot be used as an Identifier, as it contains only invalid characters.", name));
        }

        return correctName;
    }

    public static String GetTypeName(String name)
    {
        if (name == null || name.trim().isEmpty())
        {
            return name;
        }
        return toPascalCase(RemoveInvalidCharacters(GetEscapedReservedName(name, "Model")));
    }

    protected static String GetEscapedReservedName(String name, String appendValue)
    {
        Objects.nonNull(name);
        Objects.nonNull(appendValue);

        if (ReservedWords.contains(name))
        {
            name += appendValue;
        }

        return name;
    }

    private static String RemoveInvalidCharacters(String name, char... allowerCharacters)
    {
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
            }
        }
        return builder.toString();
    }

    private static final Map<Character, String> basicLaticCharacters = new HashMap<Character, String>() {{
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

    private static final List<String> ReservedWords = Arrays.asList(
            "abstract", "assert",   "boolean",  "Boolean",  "break",
            "byte",     "Byte",     "case",     "catch",    "char",
            "Character","class",    "Class",    "const",    "continue",
            "default",  "do",       "double",   "Double",   "else",
            "enum",     "extends",  "false",    "final",    "finally",
            "float",    "Float",    "for",      "goto",     "if",
            "implements","import",  "int",      "Integer",  "long",
            "Long",     "interface","instanceof","native",  "new",
            "null",     "package",  "private",  "protected","public",
            "return",   "short",    "Short",    "static",   "strictfp",
            "super",    "switch",   "synchronized","this",  "throw",
            "throws",   "transient","true",     "try",      "void",
            "Void",     "volatile", "while",    "Date",     "Datetime",
            "OffsetDateTime",       "Duration", "Period",   "Stream",
            "String",   "Object",   "header"
    );

    private CodeNamer() { }
}