/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.extension.base.model.codemodel.OperationGroup;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.fluent.util.Utils;
import com.azure.autorest.fluent.model.FluentType;
import com.azure.autorest.fluent.model.WellKnownMethodName;
import com.azure.autorest.mapper.MethodGroupMapper;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class FluentMethodGroupMapper extends MethodGroupMapper {

    private final static Logger logger = LoggerFactory.getLogger(FluentMethodGroupMapper.class);

    private static FluentMethodGroupMapper _instance = new FluentMethodGroupMapper();

    public static FluentMethodGroupMapper getInstance() {
        return _instance;
    }

    @Override
    protected List<IType> supportedInterfaces(OperationGroup operationGroup, List<ClientMethod> clientMethods) {
        Optional<IType> classTypeForGet = supportGetMethod(clientMethods);
        Optional<IType> classTypeForList = supportListMethod(clientMethods);
        Optional<IType> classTypeForDelete = supportDeleteMethod(clientMethods);

        List<IType> interfaces = new ArrayList<>();
        classTypeForGet.ifPresent(iType -> interfaces.add(FluentType.InnerSupportsGet(iType)));
        classTypeForList.ifPresent(iType -> interfaces.add(FluentType.InnerSupportsList(iType)));
        classTypeForDelete.ifPresent(iType -> interfaces.add(FluentType.InnerSupportsDelete(iType)));

        if (!interfaces.isEmpty()) {
            logger.info("Method group {} support interfaces {}",
                    Utils.getJavaName(operationGroup),
                    interfaces.stream().map(IType::toString).collect(Collectors.toList()));
        }

        return interfaces;
    }

    private Optional<IType> supportGetMethod(List<ClientMethod> clientMethods) {
        return clientMethods.stream()
                .filter(m -> WellKnownMethodName.GET_BY_RESOURCE_GROUP.getMethodName().equals(m.getName())
                        && checkNonClientRequiredParameters(m, 2))
                .map(m -> m.getReturnValue().getType())
                .findFirst();
    }

    private Optional<IType> supportDeleteMethod(List<ClientMethod> clientMethods) {
        return clientMethods.stream()
                .filter(m -> WellKnownMethodName.DELETE.getMethodName().equals(m.getName())
                        && checkNonClientRequiredParameters(m, 2))
                .map(m -> m.getReturnValue().getType())
                .findFirst();
    }

    private Optional<IType> supportListMethod(List<ClientMethod> clientMethods) {
        Optional<IType> listType = clientMethods.stream()
                .filter(m -> WellKnownMethodName.LIST.getMethodName().equals(m.getName())
                        && checkNonClientRequiredParameters(m, 0))
                .map(m -> m.getReturnValue().getType())
                .findFirst();

        Optional<IType> listByResourceGroupType =clientMethods.stream()
                .filter(m -> WellKnownMethodName.LIST_BY_RESOURCE_GROUP.getMethodName().equals(m.getName())
                        && checkNonClientRequiredParameters(m, 1))
                .map(m -> m.getReturnValue().getType())
                .findFirst();

        Optional<IType> commonListType = (listType.isPresent() && listByResourceGroupType.isPresent() && Objects.equals(listType.get().toString(), listByResourceGroupType.get().toString()))
                ? listType
                : Optional.empty();

        return commonListType.filter(t -> t instanceof GenericType && ((GenericType) t).getName().equals("PagedIterable"))
                .map(t -> ((GenericType) t).getTypeArguments()[0]);
    }

    private boolean checkNonClientRequiredParameters(ClientMethod clientMethod, int requiredCount) {
        final boolean countRequiredParametersOnly = JavaSettings.getInstance().getRequiredParameterClientMethods();
        return requiredCount == clientMethod.getParameters().stream()
                .filter(p -> (countRequiredParametersOnly && p.getIsRequired()) && !p.getIsConstant() && !p.getFromClient())
                .count();
    }
}
