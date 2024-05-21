// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.armresourceprovider.models;

import com.azure.core.management.Region;
import com.azure.core.management.SystemData;
import com.azure.core.util.Context;
import com.cadl.armresourceprovider.fluent.models.CustomTemplateResourceInner;
import java.util.Map;

/**
 * An immutable client-side representation of CustomTemplateResource.
 */
public interface CustomTemplateResource {
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
     * Gets the location property: The geo-location where the resource lives.
     * 
     * @return the location value.
     */
    String location();

    /**
     * Gets the tags property: Resource tags.
     * 
     * @return the tags value.
     */
    Map<String, String> tags();

    /**
     * Gets the identity property: Managed identity.
     * 
     * @return the identity value.
     */
    ManagedServiceIdentity identity();

    /**
     * Gets the systemData property: Azure Resource Manager metadata containing createdBy and modifiedBy information.
     * 
     * @return the systemData value.
     */
    SystemData systemData();

    /**
     * Gets the provisioningState property: The status of the last operation.
     * 
     * @return the provisioningState value.
     */
    ProvisioningState provisioningState();

    /**
     * Gets the dog property: The dog property.
     * 
     * @return the dog value.
     */
    Dog dog();

    /**
     * Gets the region of the resource.
     * 
     * @return the region of the resource.
     */
    Region region();

    /**
     * Gets the name of the resource region.
     * 
     * @return the name of the resource region.
     */
    String regionName();

    /**
     * Gets the name of the resource group.
     * 
     * @return the name of the resource group.
     */
    String resourceGroupName();

    /**
     * Gets the inner com.cadl.armresourceprovider.fluent.models.CustomTemplateResourceInner object.
     * 
     * @return the inner object.
     */
    CustomTemplateResourceInner innerModel();

    /**
     * The entirety of the CustomTemplateResource definition.
     */
    interface Definition extends DefinitionStages.Blank, DefinitionStages.WithLocation,
        DefinitionStages.WithResourceGroup, DefinitionStages.WithCreate {
    }

    /**
     * The CustomTemplateResource definition stages.
     */
    interface DefinitionStages {
        /**
         * The first stage of the CustomTemplateResource definition.
         */
        interface Blank extends WithLocation {
        }

        /**
         * The stage of the CustomTemplateResource definition allowing to specify location.
         */
        interface WithLocation {
            /**
             * Specifies the region for the resource.
             * 
             * @param location The geo-location where the resource lives.
             * @return the next definition stage.
             */
            WithResourceGroup withRegion(Region location);

            /**
             * Specifies the region for the resource.
             * 
             * @param location The geo-location where the resource lives.
             * @return the next definition stage.
             */
            WithResourceGroup withRegion(String location);
        }

        /**
         * The stage of the CustomTemplateResource definition allowing to specify parent resource.
         */
        interface WithResourceGroup {
            /**
             * Specifies resourceGroupName.
             * 
             * @param resourceGroupName A sequence of textual characters.
             * 
             * The resourceGroupName parameter.
             * @return the next definition stage.
             */
            WithCreate withExistingResourceGroup(String resourceGroupName);
        }

        /**
         * The stage of the CustomTemplateResource definition which contains all the minimum required properties for the
         * resource to be created, but also allows for any other optional properties to be specified.
         */
        interface WithCreate extends DefinitionStages.WithTags, DefinitionStages.WithIdentity, DefinitionStages.WithDog,
            DefinitionStages.WithIfMatch, DefinitionStages.WithIfNoneMatch {
            /**
             * Executes the create request.
             * 
             * @return the created resource.
             */
            CustomTemplateResource create();

            /**
             * Executes the create request.
             * 
             * @param context The context to associate with this operation.
             * @return the created resource.
             */
            CustomTemplateResource create(Context context);
        }

        /**
         * The stage of the CustomTemplateResource definition allowing to specify tags.
         */
        interface WithTags {
            /**
             * Specifies the tags property: Resource tags..
             * 
             * @param tags Resource tags.
             * @return the next definition stage.
             */
            WithCreate withTags(Map<String, String> tags);
        }

        /**
         * The stage of the CustomTemplateResource definition allowing to specify identity.
         */
        interface WithIdentity {
            /**
             * Specifies the identity property: Managed identity..
             * 
             * @param identity Managed identity.
             * @return the next definition stage.
             */
            WithCreate withIdentity(ManagedServiceIdentity identity);
        }

        /**
         * The stage of the CustomTemplateResource definition allowing to specify dog.
         */
        interface WithDog {
            /**
             * Specifies the dog property: The dog property..
             * 
             * @param dog The dog property.
             * @return the next definition stage.
             */
            WithCreate withDog(Dog dog);
        }

        /**
         * The stage of the CustomTemplateResource definition allowing to specify ifMatch.
         */
        interface WithIfMatch {
            /**
             * Specifies the ifMatch property: A sequence of textual characters.
             * 
             * The ifMatch parameter.
             * 
             * @param ifMatch A sequence of textual characters.
             * 
             * The ifMatch parameter.
             * @return the next definition stage.
             */
            WithCreate withIfMatch(String ifMatch);
        }

        /**
         * The stage of the CustomTemplateResource definition allowing to specify ifNoneMatch.
         */
        interface WithIfNoneMatch {
            /**
             * Specifies the ifNoneMatch property: A sequence of textual characters.
             * 
             * The ifNoneMatch parameter.
             * 
             * @param ifNoneMatch A sequence of textual characters.
             * 
             * The ifNoneMatch parameter.
             * @return the next definition stage.
             */
            WithCreate withIfNoneMatch(String ifNoneMatch);
        }
    }

    /**
     * Begins update for the CustomTemplateResource resource.
     * 
     * @return the stage of resource update.
     */
    CustomTemplateResource.Update update();

    /**
     * The template for CustomTemplateResource update.
     */
    interface Update extends UpdateStages.WithIdentity {
        /**
         * Executes the update request.
         * 
         * @return the updated resource.
         */
        CustomTemplateResource apply();

        /**
         * Executes the update request.
         * 
         * @param context The context to associate with this operation.
         * @return the updated resource.
         */
        CustomTemplateResource apply(Context context);
    }

    /**
     * The CustomTemplateResource update stages.
     */
    interface UpdateStages {
        /**
         * The stage of the CustomTemplateResource update allowing to specify identity.
         */
        interface WithIdentity {
            /**
             * Specifies the identity property: Managed identity..
             * 
             * @param identity Managed identity.
             * @return the next definition stage.
             */
            Update withIdentity(ManagedServiceIdentity identity);
        }
    }
}
