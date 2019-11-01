package com.azure.autorest.extension.base.plugin;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.Kernel32;

public class Debugger {
    public static void await() {
        System.err.println("VM info " + System.getProperty("java.vm.info") + " Waiting for debugger to attach to process " + getProcessId());

        boolean continueExecution = false;

        while (!continueExecution) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static int getProcessId() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("windows")) {
            return Kernel32.INSTANCE.GetCurrentProcessId();
        } else {
            return CLibrary.INSTANCE.getpid();
        }
    }

    private static interface CLibrary extends Library {
        CLibrary INSTANCE = Native.load("c", CLibrary.class);
        int getpid ();
    }
}
