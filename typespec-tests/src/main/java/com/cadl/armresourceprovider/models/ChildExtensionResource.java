// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.armresourceprovider.models;

import com.azure.core.management.SystemData;
import com.azure.core.util.Context;
import com.cadl.armresourceprovider.fluent.models.ChildExtensionResourceInner;

/**
 * An immutable client-side representation of ChildExtensionResource.
 */
public interface ChildExtensionResource {
    /**
     * Gets the id property: Fully qualified resource Id for the resource.
     * 
     * @return the id value.
     */
    String id();

    /**
     * Gets the name property: The name of the resource.
     * 
     * @return the name value.
     */
    String name();

    /**
     * Gets the type property: The type of the resource.
     * 
     * @return the type value.
     */
    String type();

    /**
     * Gets the properties property: The resource-specific properties for this resource.
     * 
     * @return the properties value.
     */
    ChildExtensionResourceProperties properties();

    /**
     * Gets the systemData property: Azure Resource Manager metadata containing createdBy and modifiedBy information.
     * 
     * @return the systemData value.
     */
    SystemData systemData();

    /**
     * Gets the inner com.cadl.armresourceprovider.fluent.models.ChildExtensionResourceInner object.
     * 
     * @return the inner object.
     */
    ChildExtensionResourceInner innerModel();

    /**
     * The entirety of the ChildExtensionResource definition.
     */
    interface Definition
        extends DefinitionStages.Blank, DefinitionStages.WithParentResource, DefinitionStages.WithCreate {
    }

    /**
     * The ChildExtensionResource definition stages.
     */
    interface DefinitionStages {
        /**
         * The first stage of the ChildExtensionResource definition.
         */
        interface Blank extends WithParentResource {
        }

        /**
         * The stage of the ChildExtensionResource definition allowing to specify parent resource.
         */
        interface WithParentResource {
            /**
             * Specifies resourceUri, topLevelArmResourceName.
             * 
             * @param resourceUri The fully qualified Azure Resource manager identifier of the resource.
             * @param topLevelArmResourceName arm resource name for path.
             * @return the next definition stage.
             */
            WithCreate withExistingTopLevelArmResource(String resourceUri, String topLevelArmResourceName);
        }

        /**
         * The stage of the ChildExtensionResource definition which contains all the minimum required properties for the
         * resource to be created, but also allows for any other optional properties to be specified.
         */
        interface WithCreate extends DefinitionStages.WithProperties {
            /**
             * Executes the create request.
             * 
             * @return the created resource.
             */
            ChildExtensionResource create();

            /**
             * Executes the create request.
             * 
             * @param context The context to associate with this operation.
             * @return the created resource.
             */
            ChildExtensionResource create(Context context);
        }

        /**
         * The stage of the ChildExtensionResource definition allowing to specify properties.
         */
        interface WithProperties {
            /**
             * Specifies the properties property: The resource-specific properties for this resource..
             * 
             * @param properties The resource-specific properties for this resource.
             * @return the next definition stage.
             */
            WithCreate withProperties(ChildExtensionResourceProperties properties);
        }
    }

    /**
     * Begins update for the ChildExtensionResource resource.
     * 
     * @return the stage of resource update.
     */
    ChildExtensionResource.Update update();

    /**
     * The template for ChildExtensionResource update.
     */
    interface Update extends UpdateStages.WithProperties {
        /**
         * Executes the update request.
         * 
         * @return the updated resource.
         */
        ChildExtensionResource apply();

        /**
         * Executes the update request.
         * 
         * @param context The context to associate with this operation.
         * @return the updated resource.
         */
        ChildExtensionResource apply(Context context);
    }

    /**
     * The ChildExtensionResource update stages.
     */
    interface UpdateStages {
        /**
         * The stage of the ChildExtensionResource update allowing to specify properties.
         */
        interface WithProperties {
            /**
             * Specifies the properties property: The resource-specific properties for this resource..
             * 
             * @param properties The resource-specific properties for this resource.
             * @return the next definition stage.
             */
            Update withProperties(ChildExtensionResourceProperties properties);
        }
    }

    /**
     * Refreshes the resource to sync with Azure.
     * 
     * @return the refreshed resource.
     */
    ChildExtensionResource refresh();

    /**
     * Refreshes the resource to sync with Azure.
     * 
     * @param context The context to associate with this operation.
     * @return the refreshed resource.
     */
    ChildExtensionResource refresh(Context context);
}
