package com.azure.autorest.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    private CodeNamer() { }
}