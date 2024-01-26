// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.autorest.postprocessor.implementation;

import com.azure.autorest.extension.base.plugin.NewPlugin;
import com.google.googlejavaformat.java.ImportOrderer;
import com.google.googlejavaformat.java.JavaFormatterOptions;
import com.google.googlejavaformat.java.RemoveUnusedImports;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jdt.internal.compiler.env.IModule;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.TextEdit;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class that handles code formatting.
 */
public final class CodeFormatterUtil {
    /**
     * Formats the given files by removing unused imports and applying Eclipse code formatting.
     *
     * @param files The files to format.
     * @param plugin The plugin to use to write the formatted files.
     * @throws Exception If code formatting fails.
     */
    public static void formatCode(Map<String, String> files, NewPlugin plugin) throws Exception {
        Map<String, String> eclipseSettings = loadEclipseSettings();
        files.entrySet().parallelStream().forEach(fileEntry -> {
            try {
                String file = removeUnusedImports(fileEntry.getValue());
                file = formatCode(file, fileEntry.getKey(), ToolFactory.createCodeFormatter(eclipseSettings));

                plugin.writeFile(fileEntry.getKey(), file, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Loads the Eclipse formatter settings from the XML file.
     *
     * @return The Eclipse formatter settings.
     * @throws Exception If the formatter settings could not be loaded.
     */
    private static Map<String, String> loadEclipseSettings() throws Exception {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        org.w3c.dom.Document document = documentBuilder.parse(CodeFormatterUtil.class.getClassLoader()
            .getResourceAsStream("readme/eclipse-format-azure-sdk-for-java.xml"));

        NodeList formatterSettingXml = document.getElementsByTagName("setting");
        Map<String, String> formatterSettings = new HashMap<>();
        for (int i = 0; i < formatterSettingXml.getLength(); i++) {
            org.w3c.dom.Node node = formatterSettingXml.item(i);
            formatterSettings.put(node.getAttributes().getNamedItem("id").getNodeValue(),
                node.getAttributes().getNamedItem("value").getNodeValue());
        }

        return formatterSettings;
    }

    /**
     * Removes unused imports from the given file.
     *
     * @param file The file to remove unused imports from.
     * @return The file with unused imports removed.
     */
    private static String removeUnusedImports(String file) throws Exception {
        return RemoveUnusedImports.removeUnusedImports(
            ImportOrderer.reorderImports(file, JavaFormatterOptions.Style.GOOGLE));
    }

    private static String formatCode(String file, String fileName, CodeFormatter codeFormatter) throws Exception {
        IDocument doc = new Document(file);

        int kind = IModule.MODULE_INFO_JAVA.equals(fileName)
            ? CodeFormatter.K_MODULE_INFO : CodeFormatter.K_COMPILATION_UNIT;
        kind |= CodeFormatter.F_INCLUDE_COMMENTS;
        TextEdit edit = codeFormatter.format(kind, file, 0, file.length(), 0, null);
        edit.apply(doc);

        return doc.get();
    }
}
