// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization.implementation.ls;

import com.azure.autorest.customization.implementation.Utils;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;

public class EclipseLanguageServerFacade {
    private final Process server;

    public EclipseLanguageServerFacade(int port) {
        this(null, port);
    }

    public EclipseLanguageServerFacade(String pathToLanguageServerPlugin, int port) {
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
        try {
            Path languageServerPath = (pathToLanguageServerPlugin == null)
                ? getLanguageServerDirectory()
                : Paths.get(pathToLanguageServerPlugin).resolve("jdt-language-server");

            String command = "java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=1044 " +
                "-Declipse.application=org.eclipse.jdt.ls.core.id1 -Dosgi.bundles.defaultStartLevel=4 " +
                "-Declipse.product=org.eclipse.jdt.ls.core.product -Dlog.protocol=true -Dlog.level=ALL " +
                "-noverify -Xmx1G -jar";

            if (Runtime.version().feature() >= 17) {
                command += " ./plugins/org.eclipse.equinox.launcher_1.6.500.v20230717-2134.jar ";
            } else {
                command += " ./plugins/org.eclipse.equinox.launcher_1.6.400.v20210924-0641.jar ";
            }

            command += "--add-modules=ALL-SYSTEM --add-opens java.base/java.util=ALL-UNNAMED --add-opens java.base/java.lang=ALL-UNNAMED ";

            if (Utils.isWindows()) {
                command += "-configuration ./config_win";
            } else if (Utils.isMac()) {
                command += "-configuration ./config_mac";
            } else {
                command += "-configuration ./config_linux";
            }

            server = Runtime.getRuntime().exec(command, new String[]{"CLIENT_PORT=" + port},
                languageServerPath.toFile());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Path getLanguageServerDirectory() throws IOException {
        Path tmp = Paths.get(System.getProperty("java.io.tmpdir"));
        Path autorestLanguageServer = tmp.resolve("autorest-java-language-server");

        int javaVersion = Runtime.version().feature();
        URL downloadUrl;
        Path languageServerPath;
        if (javaVersion < 17) {
            // Eclipse JDT language server version 1.12.0 is the last version that supports Java 11, which is
            // autorest.java's baseline.
            downloadUrl = URI.create("https://www.eclipse.org/downloads/download.php?file=/jdtls/milestones/1.12.0/jdt-language-server-1.12.0-202206011637.tar.gz")
                .toURL();
            languageServerPath = autorestLanguageServer.resolve("1.12.0");
        } else {
            // Eclipse JDT language server version 1.29.0 is the latest version that supports Java 17.
            // In the future this else statement may need to be replaced with an else if as newer versions of
            // Eclipse JDT language server may baseline on Java 21 (or later).
            downloadUrl = URI.create("https://www.eclipse.org/downloads/download.php?file=/jdtls/milestones/1.29.0/jdt-language-server-1.29.0-202310261436.tar.gz")
                .toURL();
            languageServerPath = autorestLanguageServer.resolve("1.29.0");
        }

        Path languageServer = languageServerPath.resolve("jdt-language-server");
        if (!Files.exists(languageServerPath) || !Files.exists(languageServer)) {
            Files.createDirectories(languageServerPath);
            Path zipPath = languageServerPath.resolve("jdt-language-server.tar.gz");
            try (InputStream in = downloadUrl.openStream()) {
                Files.copy(in, zipPath);
            }

            return unzipLanguageServer(zipPath);
        }

        return languageServer;
    }

    private static Path unzipLanguageServer(Path zipPath) throws IOException {
        try (TarInputStream tar = new TarInputStream(new GZIPInputStream(Files.newInputStream(zipPath)))) {
            Path languageServerDirectory = zipPath.getParent().resolve("jdt-language-server");
            Files.createDirectory(languageServerDirectory);
            TarEntry entry;
            while ((entry = tar.getNextEntry()) != null) {
                if (entry.isDirectory()) {
                    Files.createDirectories(languageServerDirectory.resolve(entry.getName()));
                } else {
                    Files.copy(tar, languageServerDirectory.resolve(entry.getName()));
                }
            }

            return languageServerDirectory;
        }
    }

    public InputStream getOutput() {
        return server.getInputStream();
    }

    public void shutdown() {
        if (server != null && server.isAlive()) {
            server.destroy();
        }
    }
}
