/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.extension.base.model.codemodel.PrimitiveSchema;
import com.azure.autorest.extension.base.model.codemodel.Schema;
import com.azure.autorest.mapper.PrimitiveMapper;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.IType;

public class FluentPrimitiveMapper extends PrimitiveMapper {

    private static FluentPrimitiveMapper INSTANCE = new FluentPrimitiveMapper();

    public static FluentPrimitiveMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public IType map(PrimitiveSchema primaryType) {
        if (primaryType == null) {
            return null;
        }
        if (parsed.containsKey(primaryType)) {
            return parsed.get(primaryType);
        }
        if (primaryType.getType() == Schema.AllSchemaTypes.CREDENTIAL) {
            IType type = ClassType.String;
            parsed.put(primaryType, type);
            return type;
        } else {
            return super.map(primaryType);
        }
    }
}
