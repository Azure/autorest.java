//====================================================================================================
//The Free Edition of C# to Java Converter limits conversion output to 100 lines per snippet.

//To subscribe to the Premium Edition, visit our website:
//https://www.tangiblesoftwaresolutions.com/order/order-csharp-to-java.html
//====================================================================================================

package com.azure.autorest.template;


import com.azure.autorest.model.xmlmodel.XmlBlock;
import com.azure.autorest.model.xmlmodel.XmlFile;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.Pom;

import java.util.HashMap;
import java.util.Map;

/**
 * Writes a ServiceClient to a JavaFile.
 */
public class PomTemplate implements IXmlTemplate<Pom, XmlFile> {
    private static PomTemplate _instance = new PomTemplate();
    private static JavaSettings settings = JavaSettings.getInstance();

    protected PomTemplate() {
    }

    public static PomTemplate getInstance() {
        return _instance;
    }

    public final void write(Pom pom, XmlFile xmlFile) {
        // TODO: license header
        Map<String, String> projectAnnotations = new HashMap<>();
        projectAnnotations.put("xmlns", "http://maven.apache.org/POM/4.0.0");
        projectAnnotations.put("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        projectAnnotations.put("xsi:schemaLocation", "http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd");

        xmlFile.block("project", projectAnnotations, projectBlock -> {
            projectBlock.tag("modelVersion", "4.0.0");
            if (pom.getParentIdentifier() != null) {
                projectBlock.block("parent", parentBlock -> {
                    String[] parts = pom.getParentIdentifier().split(":");
                    String parentGroupId = parts[0];
                    String parentArtifactId = parts[1];
                    String parentVersion = parts[2];
                    parentBlock.tag("groupId", parentGroupId);
                    parentBlock.tag("artifactId", parentArtifactId);
                    parentBlock.tagWithInlineComment("version", parentVersion,
                            "{x-version-update;com.azure:azure-client-sdk-parent;current}");
                    parentBlock.tag("relativePath", pom.getParentRelativePath());
                });
            }

            projectBlock.line();

            projectBlock.tag("groupId", pom.getGroupId());
            projectBlock.tag("artifactId", pom.getArtifactId());
            projectBlock.tagWithInlineComment("version", pom.getVersion(),
                    String.format("{x-version-update;%1$s:%2$s;current}", pom.getGroupId(), pom.getArtifactId()));
            projectBlock.tag("packaging", "jar");

            projectBlock.line();

            projectBlock.tag("name", String.format("Microsoft Azure SDK for %s Management", pom.getServiceName()));
            projectBlock.tag("description", pom.getServiceDescription());
            projectBlock.tag("url", "https://github.com/Azure/azure-sdk-for-java");

            projectBlock.line();

            projectBlock.block("licenses", licensesBlock -> {
                licensesBlock.block("license", licenseBlock -> {
                    licenseBlock.tag("name", "The MIT License (MIT)");
                    licenseBlock.tag("url", "http://opensource.org/licenses/MIT");
                    licenseBlock.tag("distribution", "repo");
                });
            });

            projectBlock.line();

            projectBlock.block("scm", scmBlock -> {
                scmBlock.tag("url", "https://github.com/Azure/azure-sdk-for-java");
                scmBlock.tag("connection", "scm:git:git@github.com:Azure/azure-sdk-for-java.git");
                scmBlock.tag("developerConnection", "scm:git:git@github.com:Azure/azure-sdk-for-java.git");
                scmBlock.tag("tag", "HEAD");
            });

            projectBlock.block("developers", developersBlock -> {
                developersBlock.block("developer", developerBlock -> {
                    developerBlock.tag("id", "microsoft");
                    developerBlock.tag("name", "Microsoft");
                });
            });

            projectBlock.block("properties", propertiesBlock -> {
                propertiesBlock.tag("project.build.sourceEncoding", "UTF-8");
                propertiesBlock.tag("legal", "<![CDATA[[INFO] Any downloads listed may be third party software.  Microsoft grants you no rights for third party software.]]>");
            });

            if (pom.getDependencyIdentifiers() != null && pom.getDependencyIdentifiers().size() > 0) {
                projectBlock.block("dependencies", dependenciesBlock -> {
                    for (String dependency : pom.getDependencyIdentifiers()) {
                        String[] parts = dependency.split(":");
                        String groupId = parts[0];
                        String artifactId = parts[1];
                        String version;
                        if (parts.length == 3) {
                            version = parts[2];
                        } else {
                            version = null;
                        }
                        dependenciesBlock.block("dependency", dependencyBlock -> {
                            dependenciesBlock.tag("groupId", groupId);
                            dependenciesBlock.tag("artifactId", artifactId);
                            if (version != null) {
                                dependencyBlock.tagWithInlineComment("version", version,
                                        String.format("{x-version-update;%1$s:%2$s;dependency}", groupId, artifactId));
                            }
                        });
                    }
                });
            }

            writeBuildBlock(projectBlock, pom);
        });
    }

    /**
     * Extension for writing a "build" block, with array of "plugin" within.
     *
     * @param projectBlock the project block.
     * @param pom the pom model.
     */
    protected void writeBuildBlock(XmlBlock projectBlock, Pom pom) {
    }
}
