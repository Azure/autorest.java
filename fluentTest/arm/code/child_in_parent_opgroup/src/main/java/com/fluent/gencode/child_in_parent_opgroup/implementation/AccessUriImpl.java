/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.fluent.gencode.child_in_parent_opgroup.implementation;

import com.fluent.gencode.child_in_parent_opgroup.AccessUri;
import com.microsoft.azure.arm.model.implementation.WrapperImpl;

class AccessUriImpl extends WrapperImpl<AccessUriInner> implements AccessUri {
    private final Child_In_Parent_OpGroupManager manager;
    AccessUriImpl(AccessUriInner inner, Child_In_Parent_OpGroupManager manager) {
        super(inner);
        this.manager = manager;
    }

    @Override
    public Child_In_Parent_OpGroupManager manager() {
        return this.manager;
    }

    @Override
    public String accessSAS() {
        return this.inner().accessSAS();
    }

}
