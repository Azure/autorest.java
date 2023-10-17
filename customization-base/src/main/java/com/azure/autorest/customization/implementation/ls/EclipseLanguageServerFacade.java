// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization.implementation.ls;

import com.azure.autorest.customization.implementation.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class EclipseLanguageServerFacade {
    private final Process server;

    public EclipseLanguageServerFacade(int port) {
        this(null, port);
    }

    public EclipseLanguageServerFacade(String pathToLanguageServerPlugin, int port) {
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
        try {
            if (pathToLanguageServerPlugin == null) {
                pathToLanguageServerPlugin = unzipLanguageServer();
            }

            String command = "java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=1044 " +
                "-Declipse.application=org.eclipse.jdt.ls.core.id1 -Dosgi.bundles.defaultStartLevel=4 " +
                "-Declipse.product=org.eclipse.jdt.ls.core.product -Dlog.protocol=true -Dlog.level=ALL " +
                "-noverify -Xmx1G -jar ./plugins/org.eclipse.equinox.launcher_1.6.400.v20210924-0641.jar ";
            double version = Double.parseDouble(System.getProperty("java.specification.version"));
            if (version >= 9) {
                command += "--add-modules=ALL-SYSTEM --add-opens java.base/java.util=ALL-UNNAMED --add-opens java.base/java.lang=ALL-UNNAMED ";
            }
            if (Utils.isWindows()) {
                command += "-configuration ./config_win";
            } else if (Utils.isMac()) {
                command += "-configuration ./config_mac";
            } else {
                command += "-configuration ./config_linux";
            }
            server = Runtime.getRuntime().exec(command, new String[]{"CLIENT_PORT=" + port},
                Paths.get(pathToLanguageServerPlugin, "jdt-language-server").toFile());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String unzipLanguageServer() throws IOException {
        Path languageServerDirectory = Files.createTempDirectory("tmpjdt");
        InputStream resourceAsStream = EclipseLanguageServerFacade.class.getClassLoader().getResourceAsStream("jdt-language-server.zip");
        ZipInputStream zipInputStream = new ZipInputStream(resourceAsStream);
        ZipEntry zipEntry = zipInputStream.getNextEntry();
        byte[] buffer = new byte[2048];
        while (zipEntry != null) {
            if (zipEntry.isDirectory()) {
                File currentDir = new File(languageServerDirectory.toString(), zipEntry.getName());
                currentDir.mkdirs();
            } else {
                File file = new File(languageServerDirectory.toString(), zipEntry.getName());
                try (FileOutputStream outputStream = new FileOutputStream(file)) {
                    int length;
                    while ((length = zipInputStream.read(buffer)) > 0) {
                        outputStream.write(buffer, 0, length);
                    }
                }
            }
            zipEntry = zipInputStream.getNextEntry();
        }
        return languageServerDirectory.toString();
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
