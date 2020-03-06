/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 *
 */

package com.azure.autorest.fluent.transformer;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Schema;
import com.azure.autorest.fluent.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class MultipleInheritanceFlatten {

    private static final Logger logger = LoggerFactory.getLogger(MultipleInheritanceFlatten.class);

    public CodeModel process(CodeModel codeModel) {
        codeModel.getSchemas().getObjects().stream()
                .filter(s -> s.getParents() != null && s.getParents().getImmediate().size() > 1)
                .forEach(compositeType -> {
                    List<ObjectSchema> parentsNeedFlatten = new ArrayList<>();
                    ObjectSchema firstParent = null;
                    for (Schema parent : compositeType.getParents().getImmediate()) {
                        if (parent instanceof ObjectSchema) {
                            if (firstParent == null) {
                                firstParent = (ObjectSchema) parent;
                            } else {
                                parentsNeedFlatten.add((ObjectSchema) parent);
                            }
                        }
                    }

                    // Java does not support multiple inheritance, flatten others
                    if (!parentsNeedFlatten.isEmpty()) {
                        compositeType.getParents().getImmediate().removeIf(parentsNeedFlatten::contains);

                        for (ObjectSchema parent : parentsNeedFlatten) {
                            logger.info("Properties from base class {} is moved to class {}", Utils.getJavaName(parent), Utils.getJavaName(compositeType));

                            compositeType.getProperties().addAll(parent.getProperties());
                        }
                    }
                });
        return codeModel;
    }
}
