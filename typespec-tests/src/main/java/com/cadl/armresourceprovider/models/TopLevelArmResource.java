// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.armresourceprovider.models;

import com.azure.core.management.Region;
import com.azure.core.util.Context;
import com.cadl.armresourceprovider.fluent.models.TopLevelArmResourceInner;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

/**
 * An immutable client-side representation of TopLevelArmResource.
 */
public interface TopLevelArmResource {
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
     * Gets the configurationEndpoints property: Configuration Endpoints.
     * 
     * @return the configurationEndpoints value.
     */
    List<String> configurationEndpoints();

    /**
     * Gets the username property: The userName property.
     * 
     * @return the username value.
     */
    String username();

    /**
     * Gets the userNames property: The userNames property.
     * 
     * @return the userNames value.
     */
    String userNames();

    /**
     * Gets the accuserName property: The accuserName property.
     * 
     * @return the accuserName value.
     */
    String accuserName();

    /**
     * Gets the startTimestamp property: The startTimeStamp property.
     * 
     * @return the startTimestamp value.
     */
    OffsetDateTime startTimestamp();

    /**
     * Gets the provisioningState property: The status of the last operation.
     * 
     * @return the provisioningState value.
     */
    ProvisioningState provisioningState();

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
     * Gets the inner com.cadl.armresourceprovider.fluent.models.TopLevelArmResourceInner object.
     * 
     * @return the inner object.
     */
    TopLevelArmResourceInner innerModel();

    /**
     * The entirety of the TopLevelArmResource definition.
     */
    interface Definition extends DefinitionStages.Blank, DefinitionStages.WithLocation,
        DefinitionStages.WithResourceGroup, DefinitionStages.WithCreate {
    }

    /**
     * The TopLevelArmResource definition stages.
     */
    interface DefinitionStages {
        /**
         * The first stage of the TopLevelArmResource definition.
         */
        interface Blank extends WithLocation {
        }

        /**
         * The stage of the TopLevelArmResource definition allowing to specify location.
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
         * The stage of the TopLevelArmResource definition allowing to specify parent resource.
         */
        interface WithResourceGroup {
            /**
             * Specifies resourceGroupName.
             * 
             * @param resourceGroupName The name of the resource group. The name is case insensitive.
             * @return the next definition stage.
             */
            WithCreate withExistingResourceGroup(String resourceGroupName);
        }

        /**
         * The stage of the TopLevelArmResource definition which contains all the minimum required properties for the
         * resource to be created, but also allows for any other optional properties to be specified.
         */
        interface WithCreate extends DefinitionStages.WithTags, DefinitionStages.WithUsername,
            DefinitionStages.WithUserNames, DefinitionStages.WithAccuserName, DefinitionStages.WithStartTimestamp {
            /**
             * Executes the create request.
             * 
             * @return the created resource.
             */
            TopLevelArmResource create();

            /**
             * Executes the create request.
             * 
             * @param context The context to associate with this operation.
             * @return the created resource.
             */
            TopLevelArmResource create(Context context);
        }

        /**
         * The stage of the TopLevelArmResource definition allowing to specify tags.
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
         * The stage of the TopLevelArmResource definition allowing to specify username.
         */
        interface WithUsername {
            /**
             * Specifies the username property: The userName property..
             * 
             * @param username The userName property.
             * @return the next definition stage.
             */
            WithCreate withUsername(String username);
        }

        /**
         * The stage of the TopLevelArmResource definition allowing to specify userNames.
         */
        interface WithUserNames {
            /**
             * Specifies the userNames property: The userNames property..
             * 
             * @param userNames The userNames property.
             * @return the next definition stage.
             */
            WithCreate withUserNames(String userNames);
        }

        /**
         * The stage of the TopLevelArmResource definition allowing to specify accuserName.
         */
        interface WithAccuserName {
            /**
             * Specifies the accuserName property: The accuserName property..
             * 
             * @param accuserName The accuserName property.
             * @return the next definition stage.
             */
            WithCreate withAccuserName(String accuserName);
        }

        /**
         * The stage of the TopLevelArmResource definition allowing to specify startTimestamp.
         */
        interface WithStartTimestamp {
            /**
             * Specifies the startTimestamp property: The startTimeStamp property..
             * 
             * @param startTimestamp The startTimeStamp property.
             * @return the next definition stage.
             */
            WithCreate withStartTimestamp(OffsetDateTime startTimestamp);
        }
    }

    /**
     * Begins update for the TopLevelArmResource resource.
     * 
     * @return the stage of resource update.
     */
    TopLevelArmResource.Update update();

    /**
     * The template for TopLevelArmResource update.
     */
    interface Update extends UpdateStages.WithTags, UpdateStages.WithProperties {
        /**
         * Executes the update request.
         * 
         * @return the updated resource.
         */
        TopLevelArmResource apply();

        /**
         * Executes the update request.
         * 
         * @param context The context to associate with this operation.
         * @return the updated resource.
         */
        TopLevelArmResource apply(Context context);
    }

    /**
     * The TopLevelArmResource update stages.
     */
    interface UpdateStages {
        /**
         * The stage of the TopLevelArmResource update allowing to specify tags.
         */
        interface WithTags {
            /**
             * Specifies the tags property: Resource tags..
             * 
             * @param tags Resource tags.
             * @return the next definition stage.
             */
            Update withTags(Map<String, String> tags);
        }

        /**
         * The stage of the TopLevelArmResource update allowing to specify properties.
         */
        interface WithProperties {
            /**
             * Specifies the properties property: The properties property..
             * 
             * @param properties The properties property.
             * @return the next definition stage.
             */
            Update withProperties(TopLevelArmResourceUpdateProperties properties);
        }
    }

    /**
     * Refreshes the resource to sync with Azure.
     * 
     * @return the refreshed resource.
     */
    TopLevelArmResource refresh();

    /**
     * Refreshes the resource to sync with Azure.
     * 
     * @param context The context to associate with this operation.
     * @return the refreshed resource.
     */
    TopLevelArmResource refresh(Context context);
}
