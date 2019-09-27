//====================================================================================================
//The Free Edition of C# to Java Converter limits conversion output to 100 lines per snippet.

//To subscribe to the Premium Edition, visit our website:
//https://www.tangiblesoftwaresolutions.com/order/order-csharp-to-java.html
//====================================================================================================

package com.azure.autorest.template;


import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.Pom;
import com.azure.autorest.model.xmlmodel.XmlFile;

import java.util.HashMap;
import java.util.Map;

/**
 Writes a ServiceClient to a JavaFile.
*/
public class PomTemplate implements IXmlTemplate<Pom, XmlFile>
{
    private static PomTemplate _instance = new PomTemplate();
    private static JavaSettings settings = JavaSettings.getInstance();
    public static PomTemplate getInstance()
    {
        return _instance;
    }

    private PomTemplate()
    {
    }

    public final void Write(Pom pom, XmlFile xmlFile)
    {
        // TODO: license header
        Map<String, String> projectAnnotations = new HashMap<>();
        projectAnnotations.put("xmlns", "http://maven.apache.org/POM/4.0.0");
        projectAnnotations.put("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        projectAnnotations.put("xsi:schemaLocation", "http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd");

        xmlFile.Block("project", projectAnnotations, projectBlock -> {
            projectBlock.Tag("modelVersion", "4.0.0");
            projectBlock.Block("parent", parentBlock -> {
                String[] parts = pom.getParentIdentifier().split(":");
                String parentGroupId = parts[0];
                String parentArtifactId = parts[1];
                String parentVersion = parts[2];
                parentBlock.Tag("groupId", parentGroupId);
                parentBlock.Tag("artifactId", parentArtifactId);
                parentBlock.Tag("parentVersion", parentVersion);
                parentBlock.Tag("relativePath", pom.getParentRelativePath());
            });

            projectBlock.Line();

            projectBlock.Tag("groupId", pom.getGroupId());
            projectBlock.Tag("artifactId", pom.getArtifactId());
            projectBlock.Tag("packing", "jar");

            projectBlock.Line();

            projectBlock.Tag("name", "Microsoft Azure SDK for " + pom.getServiceName());
            projectBlock.Tag("description", pom.getServiceDescription());
            projectBlock.Tag("url", "https://github.com/Azure/azure-sdk-for-java");

            projectBlock.Line();

            projectBlock.Block("licenses", licensesBlock -> {
                licensesBlock.Block("license", licenseBlock -> {
                    licenseBlock.Tag("name", "The MIT License (MIT)");
                    licenseBlock.Tag("url", "http://opensource.org/licenses/MIT");
                    licenseBlock.Tag("distribution", "repo");
                });
            });

            projectBlock.Line();

            projectBlock.Block("scm", scmBlock -> {
                scmBlock.Tag("url", "scm:git:https://github.com/Azure/azure-sdk-for-java");
                scmBlock.Tag("connection", "scm:git:git@github.com:Azure/azure-sdk-for-java.git");
                scmBlock.Tag("tag", "HEAD");
            });

            projectBlock.Block("developers", developersBlock -> {
                developersBlock.Block("developer", developerBlock -> {
                    developerBlock.Tag("id", "microsoft");
                    developerBlock.Tag("name", "Microsoft");
                });
            });

            if (pom.getDependencyIdentifiers() != null && pom.getDependencyIdentifiers().size() > 0) {
                projectBlock.Block("dependencies", dependenciesBlock -> {
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
                        dependenciesBlock.Block("dependency", dependencyBlock -> {
                            dependenciesBlock.Tag("groupId", groupId);
                            dependenciesBlock.Tag("artifactId", artifactId);
                            if (version != null) {
                                dependencyBlock.Tag("version", version);
                            }
                        });
                    }
                });
            }
        });
    }
}