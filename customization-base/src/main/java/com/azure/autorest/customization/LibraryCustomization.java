// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization;

import java.util.HashMap;
import java.util.Map;

/**
 * The top level customization for an AutoRest generated client library.
 */
public final class LibraryCustomization {
    private final Map<String, String> contents;
    private final Map<String, PackageCustomization> packages;

    private static final String MAIN_JAVA = "src/main/java/";

    LibraryCustomization(Map<String, String> contents) {
        this.contents = new HashMap<>(contents);
        this.packages = new HashMap<>();

        // Code customizations only care about source files. Ignore sample and test files, if they exist.
        for (Map.Entry<String, String> entry : contents.entrySet()) {
            String key = entry.getKey();
            if (key.startsWith(MAIN_JAVA) && key.endsWith(".java")) {
                String packageName = key.substring(MAIN_JAVA.length(), key.lastIndexOf('/')).replace("/", ".");
                PackageCustomization packageCustomization = packages.computeIfAbsent(packageName,
                    ignored -> new PackageCustomization(packageName));
                packageCustomization.addFile(key.substring(key.lastIndexOf('/') + 1, key.length() - 5),
                    entry.getValue());
            }
        }
    }

    /**
     * Gets the package level customization for a Java package in the client library.
     *
     * @param packageName the fully qualified name of the package
     * @return the package level customization.
     */
    public PackageCustomization getPackage(String packageName) {
        String resolvedPackageName = packageName.replace(".", "/");
        PackageCustomization packageCustomization = packages.get(resolvedPackageName);
        if (packageCustomization == null) {
            throw new IllegalArgumentException("Package not found: " + packageName);
        }

        return packageCustomization;
    }

    /**
     * Gets the class level customization for a Java class in the client library.
     *
     * @param packageName the fully qualified name of the package
     * @param className the simple name of the class
     * @return the class level customization
     */
    public ClassCustomization getClass(String packageName, String className) {
        return getPackage(packageName).getClass(className);
    }

    Map<String, String> getContents() {
        // Only the parsed files matter, as that indicates the files that have been modified.
        for (PackageCustomization packageCustomization : packages.values()) {
            packageCustomization.getParsedFiles().forEach((key, value) ->
                contents.put(MAIN_JAVA + packageCustomization.getPackageName().replace(".", "/") + "/" + key + ".java",
                    value.toString()));
        }

        return contents;
    }
}
