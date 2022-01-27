// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.mapper;

public interface IMapper<FromT, ToT> {
    ToT map(FromT fromT);
}
