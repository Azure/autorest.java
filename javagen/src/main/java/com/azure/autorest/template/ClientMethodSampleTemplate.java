// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodExample;
import com.azure.autorest.model.clientmodel.ConvenienceMethod;
import com.azure.autorest.model.javamodel.JavaFile;

public class ClientMethodSampleTemplate implements IJavaTemplate<ClientMethodExample, JavaFile> {
    private static final ClientMethodSampleTemplate INSTANCE = new ClientMethodSampleTemplate();

    public static ClientMethodSampleTemplate getInstance() {
        return INSTANCE;
    }

    @Override
    public void write(ClientMethodExample clientMethodExample, JavaFile javaFile) {
        String filename = clientMethodExample.getFilename();
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
     * @param clientMethod the client method to generate samples for
     * @param convenienceMethod the convenience method
     * @return whether the given convenience example should be included in the generated sample code
     */
    public boolean isExampleIncluded(ClientMethod clientMethod, ConvenienceMethod convenienceMethod) {
        ConvenienceSyncMethodTemplate syncMethodTemplate = Templates.getConvenienceSyncMethodTemplate();
        return syncMethodTemplate.isMethodIncluded(clientMethod)
                && syncMethodTemplate.isMethodIncluded(convenienceMethod);
    }
}
