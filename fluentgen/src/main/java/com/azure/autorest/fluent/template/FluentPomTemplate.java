/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.fluent.model.projectmodel.Project;
import com.azure.autorest.model.clientmodel.Pom;
import com.azure.autorest.model.xmlmodel.XmlBlock;
import com.azure.autorest.template.PomTemplate;

public class FluentPomTemplate extends PomTemplate {

    private static final FluentPomTemplate INSTANCE = new FluentPomTemplate();

    private static Project project;

    protected FluentPomTemplate() {
    }

    public static FluentPomTemplate getInstance() {
        return INSTANCE;
    }

    public static void setProject(Project project) {
        FluentPomTemplate.project = project;
    }

    @Override
    protected void writeBuildBlock(XmlBlock projectBlock, Pom pom) {
        projectBlock.block("build", buildBlock -> {
            buildBlock.block("plugins", pluginsBlock -> {
                if (FluentStatic.getFluentJavaSettings().isSdkIntegration()) {
                    // jacoco-maven-plugin
                    pluginsBlock.block("plugin", pluginBlock -> {
                        pluginBlock.tag("groupId", "org.jacoco");
                        pluginBlock.tag("artifactId", "jacoco-maven-plugin");
                        pluginBlock.tagWithInlineComment("version", project.getPackageVersions().getJacocoMavenPlugin(),
                                "{x-version-update;org.jacoco:jacoco-maven-plugin;external_dependency}");
                        pluginBlock.block("configuration", configurationBlock -> {
                            configurationBlock.tag("skip", "true");
                        });
                    });

                    // revapi-maven-plugin
                    pluginsBlock.block("plugin", pluginBlock -> {
                        pluginBlock.tag("groupId", "org.revapi");
                        pluginBlock.tag("artifactId", "revapi-maven-plugin");
                        pluginBlock.tagWithInlineComment("version", project.getPackageVersions().getRevapiMavenPlugin(),
                                "{x-version-update;org.revapi:revapi-maven-plugin;external_dependency}");
                        pluginBlock.block("configuration", configurationBlock -> {
                            configurationBlock.block("analysisConfiguration", analysisConfigurationBlock -> {
                                analysisConfigurationBlock.block("revapi.ignore", ignoreBlock -> {
                                    ignoreBlock.block("item", itemBlock -> {
                                        itemBlock.tag("code", "java.method.addedToInterface");
                                    });

                                    ignoreBlock.block("item", itemBlock -> {
                                        itemBlock.tag("regex", "true");
                                        itemBlock.tag("code", ".*");
                                        itemBlock.tag("package", "com\\.azure\\.resourcemanager(\\.[^.]+)+\\.fluent(\\.[^.]+)*");
                                    });
                                });
                            });
                        });
                    });
                } else {
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
            });
        });
    }
}
