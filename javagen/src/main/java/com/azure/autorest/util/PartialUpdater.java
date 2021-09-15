package com.azure.autorest.util;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.javamodel.JavaFile;
import spoon.Launcher;
import spoon.compiler.Environment;
import spoon.reflect.CtModel;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.declaration.CtType;
import spoon.support.compiler.VirtualFile;

import java.io.File;

public class PartialUpdater {
    /**
     *
     * @param javaFile
     */
    public static JavaFile merge(JavaFile javaFile) {
//        if (!javaFile.getContents().toString().contains("@Manual")) {
//            return javaFile;
//        }
        System.err.println(javaFile.getFilePath());
        String outputFolder = JavaSettings.getInstance().getOutputFolder();
        String filepath = outputFolder + File.separator + javaFile.getFilePath();
        String content = javaFile.getContents().toString();

        StringBuilder sb = new StringBuilder();

        CtModel oldModel = getModel(filepath, null);
        for (CtType<?> s : oldModel.getAllTypes()) {
            System.err.println("class: " + s.getQualifiedName());
            s.getTypeMembers().stream().filter(m -> m.getAnnotations().stream().anyMatch(annotation -> annotation.getAnnotationType().toString().equals("com.azure.core.annotation.Manual"))).forEach(m -> {
                System.err.println(m);
                sb.append(m).append('\n');
            });
        }

        if (sb.length() == 0) {
            return javaFile;
        }

//        CtModel newModel = getModel(null, content);
//        for (CtType<?> s : oldModel.getAllTypes()) {
//
//        }

        int pos = content.lastIndexOf("}");
        if (pos <= 0) {
            return javaFile;
        }
        String newContent = content.substring(0, pos) + sb.toString() + content.substring(pos);
        return new JavaFile(javaFile.getFilePath(), newContent);
    }

    private static CtModel getModel(String filepath, String content) {
        Launcher launcher = new Launcher();
        launcher.getEnvironment().setComplianceLevel(9);
        if (content != null) {
            launcher.addInputResource(new VirtualFile(content));
        } else if (filepath != null) {
            launcher.addInputResource(filepath);
        } else {
            return null;
        }
        launcher.buildModel();
        return launcher.getModel();
    }
}
