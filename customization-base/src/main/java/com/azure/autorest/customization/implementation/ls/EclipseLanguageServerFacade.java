package com.azure.autorest.customization.implementation.ls;

import com.sun.jna.Platform;

import java.io.InputStream;
import java.nio.file.Paths;

public class EclipseLanguageServerFacade {
    private final Process server;

    public EclipseLanguageServerFacade(String workspaceDir, int port) {
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
        try {
            String command = "java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=1044 " +
                    "-Declipse.application=org.eclipse.jdt.ls.core.id1 -Dosgi.bundles.defaultStartLevel=4 " +
                    "-Declipse.product=org.eclipse.jdt.ls.core.product -Dlog.protocol=true -Dlog.level=ALL " +
                    "-noverify -Xmx1G -jar ./plugins/org.eclipse.equinox.launcher_1.5.700.v20200207-2156.jar ";
            double version = Double.parseDouble(System.getProperty("java.specification.version"));
            if (version >= 9) {
                command += "--add-modules=ALL-SYSTEM --add-opens java.base/java.util=ALL-UNNAMED --add-opens java.base/java.lang=ALL-UNNAMED ";
            }
            if (Platform.isWindows()) {
                command += "-configuration ./config_win";
            } else if (Platform.isMac()) {
                command += "-configuration ./config_mac";
            } else {
                command += "-configuration ./config_linux";
            }
            server = Runtime.getRuntime().exec(command, new String[] { "CLIENT_PORT=" + port },
                    Paths.get(System.getProperty("user.dir"), "jdt-language-server").toFile());
        } catch (Exception e) {
            throw new RuntimeException(e);
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
