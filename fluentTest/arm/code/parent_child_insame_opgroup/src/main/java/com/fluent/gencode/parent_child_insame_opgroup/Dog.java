/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.fluent.gencode.parent_child_insame_opgroup;

import com.microsoft.azure.arm.model.HasInner;
import com.microsoft.azure.arm.resources.models.Resource;
import com.microsoft.azure.arm.resources.models.GroupableResourceCore;
import com.microsoft.azure.arm.resources.models.HasResourceGroup;
import com.microsoft.azure.arm.model.Refreshable;
import com.microsoft.azure.arm.model.Updatable;
import com.microsoft.azure.arm.model.Appliable;
import com.microsoft.azure.arm.model.Creatable;
import com.microsoft.azure.arm.resources.models.HasManager;
import com.fluent.gencode.parent_child_insame_opgroup.implementation.Parent_Child_Insame_OpGroupManager;
import java.util.List;
import org.joda.time.DateTime;
import com.fluent.gencode.parent_child_insame_opgroup.implementation.DogInner;

/**
 * Type representing Dog.
 */
public interface Dog extends HasInner<DogInner>, Resource, GroupableResourceCore<Parent_Child_Insame_OpGroupManager, DogInner>, HasResourceGroup, Refreshable<Dog>, Updatable<Dog.Update>, HasManager<Parent_Child_Insame_OpGroupManager> {
    /**
     * @return the animalSizeGB value.
     */
    Integer animalSizeGB();

    /**
     * @return the creationData value.
     */
    CreationData creationData();

    /**
     * @return the managedBy value.
     */
    String managedBy();

    /**
     * @return the osType value.
     */
    ColorTypes osType();

    /**
     * @return the provisioningState value.
     */
    String provisioningState();

    /**
     * @return the sku value.
     */
    DogSku sku();

    /**
     * @return the timeCreated value.
     */
    DateTime timeCreated();

    /**
     * @return the zones value.
     */
    List<String> zones();

    /**
     * The entirety of the Dog definition.
     */
    interface Definition extends DefinitionStages.Blank, DefinitionStages.WithGroup, DefinitionStages.WithCreationData, DefinitionStages.WithCreate {
    }

    /**
     * Grouping of Dog definition stages.
     */
    interface DefinitionStages {
        /**
         * The first stage of a Dog definition.
         */
        interface Blank extends GroupableResourceCore.DefinitionWithRegion<WithGroup> {
        }

        /**
         * The stage of the Dog definition allowing to specify the resource group.
         */
        interface WithGroup extends GroupableResourceCore.DefinitionStages.WithGroup<WithCreationData> {
        }

        /**
         * The stage of the dog definition allowing to specify CreationData.
         */
        interface WithCreationData {
           /**
            * Specifies creationData.
            * @param creationData Dog source information. CreationData information cannot be changed after the animal has been created
            * @return the next definition stage
*/
            WithCreate withCreationData(CreationData creationData);
        }

        /**
         * The stage of the dog definition allowing to specify AnimalSizeGB.
         */
        interface WithAnimalSizeGB {
            /**
             * Specifies animalSizeGB.
             * @param animalSizeGB If creationData.createOption is Empty, this field is mandatory and it indicates the size of the VHD to create. If this field is present for updates or creation with other options, it indicates a resize. Resizes are only allowed if the dog is not attached to a running owner, and can only increase the dog's size
             * @return the next definition stage
             */
            WithCreate withAnimalSizeGB(Integer animalSizeGB);
        }

        /**
         * The stage of the dog definition allowing to specify OsType.
         */
        interface WithOsType {
            /**
             * Specifies osType.
             * @param osType The Operating System type. Possible values include: 'Black', 'White'
             * @return the next definition stage
             */
            WithCreate withOsType(ColorTypes osType);
        }

        /**
         * The stage of the dog definition allowing to specify Sku.
         */
        interface WithSku {
            /**
             * Specifies sku.
             * @param sku the sku parameter value
             * @return the next definition stage
             */
            WithCreate withSku(DogSku sku);
        }

        /**
         * The stage of the dog definition allowing to specify Zones.
         */
        interface WithZones {
            /**
             * Specifies zones.
             * @param zones The Logical zone list for Dog
             * @return the next definition stage
             */
            WithCreate withZones(List<String> zones);
        }

        /**
         * The stage of the definition which contains all the minimum required inputs for
         * the resource to be created (via {@link WithCreate#create()}), but also allows
         * for any other optional settings to be specified.
         */
        interface WithCreate extends Creatable<Dog>, Resource.DefinitionWithTags<WithCreate>, DefinitionStages.WithAnimalSizeGB, DefinitionStages.WithOsType, DefinitionStages.WithSku, DefinitionStages.WithZones {
        }
    }
    /**
     * The template for a Dog update operation, containing all the settings that can be modified.
     */
    interface Update extends Appliable<Dog>, Resource.UpdateWithTags<Update>, UpdateStages.WithAnimalSizeGB, UpdateStages.WithOsType, UpdateStages.WithSku {
    }

    /**
     * Grouping of Dog update stages.
     */
    interface UpdateStages {
        /**
         * The stage of the dog update allowing to specify AnimalSizeGB.
         */
        interface WithAnimalSizeGB {
            /**
             * Specifies animalSizeGB.
             * @param animalSizeGB If creationData.createOption is Empty, this field is mandatory and it indicates the size of the VHD to create. If this field is present for updates or creation with other options, it indicates a resize. Resizes are only allowed if the dog is not attached to a running owner, and can only increase the dog's size
             * @return the next update stage
             */
            Update withAnimalSizeGB(Integer animalSizeGB);
        }

        /**
         * The stage of the dog update allowing to specify OsType.
         */
        interface WithOsType {
            /**
             * Specifies osType.
             * @param osType the animal color type. Possible values include: 'Black', 'White'
             * @return the next update stage
             */
            Update withOsType(ColorTypes osType);
        }

        /**
         * The stage of the dog update allowing to specify Sku.
         */
        interface WithSku {
            /**
             * Specifies sku.
             * @param sku the sku parameter value
             * @return the next update stage
             */
            Update withSku(DogSku sku);
        }

    }
}
