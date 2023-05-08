// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.model.clientmodel.ConvenienceExample;
import com.azure.autorest.model.javamodel.JavaFile;

public class ConvenienceSampleTemplate implements IJavaTemplate<ConvenienceExample, JavaFile> {
    private static final ConvenienceSampleTemplate INSTANCE = new ConvenienceSampleTemplate();

    public static ConvenienceSampleTemplate getInstance() {
        return INSTANCE;
    }

    @Override
    public void write(ConvenienceExample convenienceExample, JavaFile javaFile) {
        String filename = convenienceExample.getFilename();
        // TODO(xiaofei) add imports
        javaFile.publicClass(null, filename, classBlock -> {
            classBlock.publicStaticMethod("void main(String[] args)", methodBlock -> {
                // TODO(xiaofei) add method body
                methodBlock.line("// TODO(xiaofei) add method body");
            });
        });
    }

    /**
     * Returns whether the given convenience example should be included in the generated sample code.
     * @param convenienceExample the convenience example to check
     * @return true if the convenience example should be included, false otherwise
     */
    public boolean isExampleIncluded(ConvenienceExample convenienceExample) {
        ConvenienceSyncMethodTemplate syncMethodTemplate = Templates.getConvenienceSyncMethodTemplate();
        return syncMethodTemplate.isMethodIncluded(convenienceExample.getClientMethod())
                && syncMethodTemplate.isMethodIncluded(convenienceExample.getConvenienceMethod());
    }
}
