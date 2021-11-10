//====================================================================================================
//The Free Edition of C# to Java Converter limits conversion output to 100 lines per snippet.

//To subscribe to the Premium Edition, visit our website:
//https://www.tangiblesoftwaresolutions.com/order/order-csharp-to-java.html
//====================================================================================================

package com.azure.autorest.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.xmlmodel.XmlBlock;
import com.azure.autorest.model.xmlmodel.XmlFile;
import com.azure.autorest.model.clientmodel.Pom;

import java.util.HashMap;
import java.util.Map;

/**
 * Writes a ServiceClient to a JavaFile.
 */
public class PomTemplate implements IXmlTemplate<Pom, XmlFile> {
    private static PomTemplate _instance = new PomTemplate();

    protected PomTemplate() {
    }

    public static PomTemplate getInstance() {
        return _instance;
    }

    public final void write(Pom pom, XmlFile xmlFile) {
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

            projectBlock.tag("name", String.format("Microsoft Azure SDK for %s", pom.getServiceName()));
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
                // skip jacoco coverage check
                propertiesBlock.tag("jacoco.skip", "true");
                // use new code snippet tooling
                propertiesBlock.tag("codesnippet.skip", "false");
                propertiesBlock.tag("javadocDoclet", "");
                propertiesBlock.tag("javadocDocletOptions", "");
            });

            if (pom.getDependencyIdentifiers() != null && pom.getDependencyIdentifiers().size() > 0) {
                projectBlock.block("dependencies", dependenciesBlock -> {
                    for (String dependency : pom.getDependencyIdentifiers()) {
                        String[] parts = dependency.split(":");
                        String groupId = parts[0];
                        String artifactId = parts[1];
                        String version;
                        if (parts.length >= 3) {
                            version = parts[2];
                        } else {
                            version = null;
                        }
                        String scope;
                        if (parts.length >= 4) {
                            scope = parts[3];
                        } else {
                            scope = null;
                        }
                        dependenciesBlock.block("dependency", dependencyBlock -> {
                            boolean externalDependency = !groupId.startsWith("com.azure");  // a bit of hack here
                            dependenciesBlock.tag("groupId", groupId);
                            dependenciesBlock.tag("artifactId", artifactId);
                            if (version != null) {
                                dependencyBlock.tagWithInlineComment("version", version,
                                        String.format("{x-version-update;%1$s:%2$s;%3$s}",
                                                groupId,
                                                artifactId,
                                                externalDependency ? "external_dependency" : "dependency"));
                            }
                            if (scope != null) {
                                dependenciesBlock.tag("scope", scope);
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
        if (!JavaSettings.getInstance().isSdkIntegration()) {
            projectBlock.block("build", buildBlock -> {
                buildBlock.block("plugins", pluginsBlock -> {
                    writeStandAlonePlugins(projectBlock);
                });
            });
        }
    }

    protected void writeStandAlonePlugins(XmlBlock pluginsBlock) {
        // maven-compiler-plugin
        pluginsBlock.block("plugin", pluginBlock -> {
            pluginBlock.tag("groupId", "org.apache.maven.plugins");
            pluginBlock.tag("artifactId", "maven-compiler-plugin");
            pluginBlock.tag("version", "3.8.1");
            pluginBlock.block("configuration", configurationBlock -> {
                configurationBlock.tag("release", "11");
            });
        });
        // build-helper-maven-plugin: allow samples to be compiled
        pluginsBlock.block("plugin", pluginBlock -> {
            pluginBlock.tag("groupId", "org.codehaus.mojo");
            pluginBlock.tag("artifactId", "build-helper-maven-plugin");
            pluginBlock.tag("version", "3.0.0");
            pluginBlock.block("executions", executionsBlock -> {
                executionsBlock.block("execution", executionBlock -> {
                    executionBlock.tag("id", "add-test-source");
                    executionBlock.tag("phase", "generate-test-sources");
                    executionBlock.block("goals", goalsBlock -> {
                        goalsBlock.tag("goal", "add-test-source");
                    });
                    executionBlock.block("configuration", configurationBlock -> {
                        configurationBlock.block("sources", sourcesBlock -> {
                            sourcesBlock.tag("source", "${basedir}/src/samples");
                        });
                    });
                });
            });
        });
    }
}
