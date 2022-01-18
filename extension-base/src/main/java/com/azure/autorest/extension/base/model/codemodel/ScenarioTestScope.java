package com.azure.autorest.extension.base.model.codemodel;

/**
 */
public enum ScenarioTestScope {

    /**
     * All of the following API scenario and steps should be under some resourceGroup. It means:
     * The consumer (API scenario runner or anything consumes API scenario) SHOULD maintain the resource group itself.
     * Usually it requires user to input the subscriptionId/location, then it creates the resource group before test running, and deletes the resource group after running
     * The consumer SHOULD set the following variables:
     *  - subscriptionId
     *  - resourceGroupName
     *  - location
     */
    ResourceGroup

}
